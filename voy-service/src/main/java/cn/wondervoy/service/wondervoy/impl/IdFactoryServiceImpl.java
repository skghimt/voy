package cn.wondervoy.service.wondervoy.impl;

import cn.wondervoy.utils.IdBuildType;
import cn.wondervoy.dao.wondervoy.IdFactoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 使用sprig配置成单例模式使用，并注入相关的初始化参数
 *
 * @author ckzhang
 *
 */
@Component("idFactory")
public class IdFactoryServiceImpl {

    /**
     * 进行获取ID时默认的尝试次数,使用spring注入管理 默认值为3
     */
//	@Value("${idFactory.count}")
    private String count = "3";

    /**
     * 每个生成器的增长速度,使用spring注入管理
     */
//	@Value("${idFactory.increase}")
    private String increase = "100";

    /**
     * 默认的businessCode对应的初始值，当id_Build表中不存在时使用
     * 针对user服务器需要进行较大化处理，建议user服务器设置为 10000000
     *
     * 使用spring进行注入管理
     */
//	@Value("${idFactory.defaultValue}")
    private String defaultIndex = "10000";

    /**
     * 内部存在的缓存，不对外暴漏，存储id生成相关信息
     */
    private HashMap<Integer, BusinessProduceCache> cache = new HashMap<Integer, BusinessProduceCache>();

    // use spring
    @Autowired
    private IdFactoryDao IdFactoryDao;

    /**
     * 构造方法
     */
    public IdFactoryServiceImpl(){

    }

    /**
     * 获取对应的businessCode生成的id
     *
     * @param businessCodeType
     * @param count
     * @return
     */
    public synchronized long getIdByBusinessCode(IdBuildType businessCodeType, int count){

        int businessCode = businessCodeType.getCode();
        if (count < 0){
            return 0l;
        }

        BusinessProduceCache temp = cache.get(businessCode);

        // 对应的businessCode已经存在缓存中
        if (temp != null){

            // 缓存对应的businessCode存在可用的id
            if (temp.index.get() < temp.endIndex){

                // 直接获取对应的id并返回
                return temp.getId();
            } else {

                //缓存中不存在可用的id，获取新的id起始位置和终止位置并进行对应的更新判断
                long newStart = IdFactoryDao.getIndexByBusinessCode(businessCode);
                long newEnd = newStart + temp.increase;

                Map<String,Object> params = new HashMap<String,Object>();
                params.put("businessCode", businessCode);
                params.put("newEnd", newEnd);
                params.put("newStart", newStart);

                // 更新成功
                if (IdFactoryDao.updateIdBuildByBusinessCodeAndIndex(params) > 0){

                    //进行新值的填充并返回获取到的id
                    temp.startIndex = newStart;
                    temp.endIndex = newEnd;
                    temp.setNewIndex(newStart);
                    return temp.getId();

                } else {

                    // 更新失败，可能多服务引起冲突，再次进行尝试调用
                    return getIdByBusinessCode(businessCodeType, count - 1);
                }
            }
        } else {
            // 不存在缓存中

            if(IdFactoryDao.isExistBusinessCode(businessCode) > 0){
                // 数据库中存在的

                //获取数据库中id起始位置
                long newStart = IdFactoryDao.getIndexByBusinessCode(businessCode);

                // 设置终止位置
                long newEnd = newStart + getIncreaseAsInt();

                Map<String,Object> params = new HashMap<String,Object>();
                params.put("businessCode", businessCode);
                params.put("newEnd", newEnd);
                params.put("newStart", newStart);

                // 更新成功
                if (IdFactoryDao.updateIdBuildByBusinessCodeAndIndex(params) > 0){

                    //针对businessCode生成对应的BusinessProduceCache
                    BusinessProduceCache tempCache = new BusinessProduceCache(businessCode, newStart, newEnd, getIncreaseAsInt(), newStart);

                    //进行新值的填充并返回获取到的id
                    cache.put(businessCode, tempCache);

                    return tempCache.getId();

                } else {

                    // 跟新失败，可能多服务引起冲突，再次进行尝试调用
                    return getIdByBusinessCode(businessCodeType, count - 1);
                }
            } else {

                //数据库中不存在对应的BusinessCode信息

                try {

                    long defaultEndIndex = getDefaultIndexAsLong() + getIncreaseAsInt();

                    Map<String,Object> params = new HashMap<String,Object>();
                    params.put("businessCode", businessCode);
                    params.put("defaultEndIndex", defaultEndIndex);

                    // 写入新的businessCode对应的id生成基础数据
                    IdFactoryDao.createNewBusinessCodeIdProduce(params);

                    //针对businessCode生成对应的BusinessProduceCache
                    BusinessProduceCache tempCache = new BusinessProduceCache(businessCode, getDefaultIndexAsLong(), defaultEndIndex, getIncreaseAsInt(), getDefaultIndexAsLong());

                    //进行新值的填充并返回获取到的id
                    cache.put(businessCode, tempCache);

                    return tempCache.getId();

                } catch (Exception e){

                    // 插入数据，可能多服务引起冲突，再次进行尝试调用
                    return getIdByBusinessCode(businessCodeType, count - 1);
                }

            }

        }


    }

    /**
     * 内部类，单个businessCode生成id的基础组件
     *
     * @author ckzhang
     *
     */
    class BusinessProduceCache{

        /**
         * 业务编码
         */
        public int businessCode;

        /**
         * 对应的id生成起始位置
         */
        public long startIndex;

        /**
         * 对应的id生成终止位置
         */
        public long endIndex;

        /**
         * 增长率
         */
        public int increase;

        /**
         * id的当前位置
         */
        public AtomicLong index = new AtomicLong();

        public BusinessProduceCache(int bussinessCode, long startIndex, long endIndex, int increase, long index){
            this.businessCode = bussinessCode;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.increase = increase;
            this.index = new AtomicLong(index);
        }

        /**
         * 设置新的id位置
         * @param index
         */
        public void setNewIndex(long index){
            this.index = new AtomicLong(index);
        }

        /**
         * 线程安全的获取当前的id，并进行id+1
         * @return
         */
        public long getId(){
            return index.getAndAdd(1l);
        }

    }

    /*****************getters and setters start*****************/
    public int getCountAsInt() {
        return Integer.parseInt(count);
    }

    public IdFactoryDao getIdFactoryDao() {
        return IdFactoryDao;
    }

    public void setIdFactoryDao(IdFactoryDao idFactoryDao) {
        this.IdFactoryDao = idFactoryDao;
    }

    public String getCount() {
        return count;
    }

    public String getIncrease() {
        return increase;
    }

    public String getDefaultIndex() {
        return defaultIndex;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getIncreaseAsInt() {
        return Integer.parseInt(increase);
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    public long getDefaultIndexAsLong() {
        return Long.parseLong(defaultIndex);
    }

    public void setDefaultIndex(String defaultIndex) {
        this.defaultIndex = defaultIndex;
    }

    /*****************getters and setters end*****************/

}

package cn.wondervoy.dao.wondervoy;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 基础服务器dao接口
 *
 * @author ckzhang
 *
 */
public interface IdFactoryDao {

    /**
     * 根据businessCode获取对应的当前index
     * 对应sql
     *    select businessIndex from id_builder where businessCode = #businessCode#
     *
     * @param businessCode
     * @return
     */
    long getIndexByBusinessCode(@Param("businessCode") int businessCode);

    /**
     * 跟新对应businessCode到新的index
     * 对应的sql
     *    update id_builder set businessIndex = #newIndex# where businessCode = #businessCode# and businessIndex = #oldIndex#
     *
     * @return
     */
    int updateIdBuildByBusinessCodeAndIndex(Map<String, Object> params);

    /**
     * 判断的对应的businessCode是否在id_build中存在
     * 对应的sql
     *     count (id) from id_builder where businessCode = #businessCode#
     *
     * @param businessCode
     * @return
     */
    int isExistBusinessCode(@Param("businessCode") int businessCode);

    /**
     * 因为对应的businessCode不存在，所以进行对应的新建工作
     * 对应的sql
     *     insert into id_builder (businessCode,businessIndex) value (#businessCode#,#defaultIndex#)
     *
     */
    void createNewBusinessCodeIdProduce(Map<String, Object> params);

}

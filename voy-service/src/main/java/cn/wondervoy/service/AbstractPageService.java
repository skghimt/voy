package cn.wondervoy.service;

import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.base.view.BizData4Page;
import cn.wondervoy.dao.IBaseDAO;

import java.util.List;
import java.util.Map;

/**
 * Created by ckzhang on 14-12-17.
 */
public abstract class AbstractPageService<D extends IBaseDAO,T extends BaseDomain> extends AbstractBaseService<D,T> implements IPageService<D,T>{

    @Override
    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows) {


        List<T> mainData = getDao().queryPage(conditions, offset, rows);
        int records =  getDao().count(conditions);

        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(curPage);
        bizData4Page.setRecords(records);

        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);

        return bizData4Page;
    }
}


package cn.wondervoy.service;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.base.view.BizData4Page;
import cn.wondervoy.dao.IBaseDAO;

import java.util.Map;

public interface IPageService <D extends IBaseDAO, T extends BaseDomain> {

    /**
     * 数据权限后的分页数据获取
     * @param resUri
     * @param conditions
     * @param offset
     * @param rows
     * @return
     */
     BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows);
}

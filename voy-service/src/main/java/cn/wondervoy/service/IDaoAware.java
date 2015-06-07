package cn.wondervoy.service;

import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;

/**
 * Created by ckzhang on 14-12-17.
 */
public interface IDaoAware<D extends IBaseDAO,T extends BaseDomain> {
    public D getDao();
}

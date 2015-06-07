package cn.wondervoy.facade;

import cn.wondervoy.dao.bean.SystemProperty;

import java.util.List;

/**
 * Created by ckzhang on 15-4-26.
 */
public interface ISystemFacade {

    List<SystemProperty> findSystemProperties(String code);

    SystemProperty findById(long id);
}

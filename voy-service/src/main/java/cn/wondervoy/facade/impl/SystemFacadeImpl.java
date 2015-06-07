package cn.wondervoy.facade.impl;

import cn.wondervoy.dao.bean.SystemProperty;
import cn.wondervoy.facade.ISystemFacade;
import cn.wondervoy.service.wondervoy.ISystemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ckzhang on 15-4-26.
 */
@Service("SystemFacadeImpl")
public class SystemFacadeImpl implements ISystemFacade{

    @Autowired
    private ISystemPropertyService systemPropertyService;

    @Override
    public List<SystemProperty> findSystemProperties(String code) {
        return systemPropertyService.findList("type", code);
    }

    @Override
    public SystemProperty findById(long id) {
        return (SystemProperty)systemPropertyService.findOne("id", id);
    }
}


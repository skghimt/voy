package com.wondervoy.controller.business;

import cn.wondervoy.dao.bean.SystemProperty;
import cn.wondervoy.facade.ISystemFacade;
import com.wondervoy.controller.response.ResponseBean;
import com.wondervoy.controller.response.SystemPropertiesResponse;
import com.wondervoy.controller.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/system")
public class SystemController {

//    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ISystemFacade systemFacade;

    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/properties", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean properties(@RequestParam String code) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        SystemPropertiesResponse systemProperties = new SystemPropertiesResponse();

        List<SystemProperty> propertieses = systemFacade.findSystemProperties(code);

        List<SystemPropertiesResponse.SystemProperty> propertyList = new ArrayList<>();
        for (SystemProperty item : propertieses){
            SystemPropertiesResponse.SystemProperty newItem = new SystemPropertiesResponse.SystemProperty();
            newItem.setId(item.getId());
            newItem.setValue(item.getValue());
            propertyList.add(newItem);
        }
        systemProperties.setProperties(propertyList);
        response.setData(systemProperties);

        return response;
    }


}


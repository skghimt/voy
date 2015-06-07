package com.wondervoy.controller.business;

import cn.wondervoy.dao.bean.SystemProperty;
import cn.wondervoy.dao.bean.UserInvite;
import cn.wondervoy.dao.bean.UserSkill;
import cn.wondervoy.dao.bean.UserVerification;
import cn.wondervoy.facade.IStoryFacade;
import cn.wondervoy.facade.ISystemFacade;
import cn.wondervoy.facade.IUserFacade;
import cn.wondervoy.utils.email.MailRegisterHtmlFormat;
import cn.wondervoy.utils.email.MailSendService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wondervoy.controller.business.utils.ConvertBean;
import com.wondervoy.controller.response.*;
import com.wondervoy.controller.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

//    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserFacade userFacade;

    @Autowired
    private IStoryFacade storyFacade;

    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private MailRegisterHtmlFormat mailRegisterHtmlFormat;

    @Autowired
    private ISystemFacade systemFacade;

    @RequestMapping(value = "/funnyV", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean funnyV(){

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Invitation has been sent!");

        try {

            FunnyVResponse funnyVResponse = new FunnyVResponse();
            funnyVResponse.setFunny(userFacade.funnyV());

            response.setData(funnyVResponse);

        }catch (Exception e){
            response = new ResponseBean(StateEnum.ERROR.getCode(), "AH,Some thing went wrong!");
        }

        return response;

    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean userInfo(@RequestParam String userId) {

        long userIdV = Long.parseLong(userId);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        UserDetail userDetail = new UserDetail();

        cn.wondervoy.dao.bean.UserInfo info = userFacade.getUserInfo(userIdV);

        UserSkill skill = userFacade.getUserSkill(userIdV);
        List<UserVerification> verifications = userFacade.getUserVerification(userIdV);

        UserSkills skills = ConvertBean.convertUserSkill(skill);

        // 添加对应的id翻译成value
        if (skill.getJob() != null && skill.getJob().length() > 0){
            skills.setJob(systemFacade.findById(Integer.parseInt(skill.getJob())).getValue());

            SystemPropertiesResponse.SystemProperty jobProperty = new SystemPropertiesResponse.SystemProperty();
            jobProperty.setId(Integer.parseInt(skill.getJob()));
            jobProperty.setValue(skills.getJob());
            skills.setJobDetail(jobProperty);

        }
        if (skill.getAddress() != null && skill.getAddress().length() > 0){
            skills.setAddress(systemFacade.findById(Integer.parseInt(skill.getAddress())).getValue());

            SystemPropertiesResponse.SystemProperty addressProperty = new SystemPropertiesResponse.SystemProperty();
            addressProperty.setId(Integer.parseInt(skill.getAddress()));
            addressProperty.setValue(skills.getAddress());
            skills.setAddressDetail(addressProperty);
        }


        List<SystemPropertiesResponse.SystemProperty> languageList = new ArrayList<>();

        List<SystemPropertiesResponse.SystemProperty> interestList = new ArrayList<>();
        if (skill.getInterest() != null && skill.getInterest().length() > 2){
            String[] ids = getStringArray(skill.getInterest());
            String interest = "";
            if (ids != null) {
                for (String id : ids) {
                    SystemProperty systemProperty = systemFacade.findById(Long.parseLong(id));
                    if (systemProperty != null) {
                        interest = interest == "" ? systemProperty.getValue() : interest + "," + systemProperty.getValue();
                    }
                    SystemPropertiesResponse.SystemProperty itemProperty = new SystemPropertiesResponse.SystemProperty();
                    itemProperty.setId(systemProperty.getId());
                    itemProperty.setValue(systemProperty.getValue());
                    interestList.add(itemProperty);
                }
            }
            skills.setInterest(interest);
        } else {
            skills.setInterest("");
        }

        if (skill.getLanguage() != null && skill.getLanguage().length() > 2){
            String[] ids = getStringArray(skill.getLanguage());
            String language = "";
            if (ids != null) {
                for (String id : ids) {
                    SystemProperty systemProperty = systemFacade.findById(Long.parseLong(id));
                    if (systemProperty != null) {
                        language = language == "" ? systemProperty.getValue() : language + "," + systemProperty.getValue();
                    }
                    SystemPropertiesResponse.SystemProperty itemProperty = new SystemPropertiesResponse.SystemProperty();
                    itemProperty.setId(systemProperty.getId());
                    itemProperty.setValue(systemProperty.getValue());
                    languageList.add(itemProperty);
                }
            }
            skills.setLanguage(language);
        } else {
            skills.setLanguage("");
        }

        skills.setLanguageList(languageList);
        skills.setInterestList(interestList);

        UserInfo self = ConvertBean.convertUserInfo(info);

        if (self.getConstellation() != 0) {
            SystemProperty constellationProperty = systemFacade.findById(self.getConstellation());
            if (constellationProperty != null) {
                self.setConstellationStr(constellationProperty.getValue());
            }
        }

        userDetail.setInfo(self);
        userDetail.setSkills(skills);
        userDetail.setVerifications(ConvertBean.convertUserVerificationList(verifications));

        userDetail.setStoryCount(storyFacade.userStoryCount(userIdV));

        response.setData(userDetail);


        return response;
    }

    /**
     * 添加电话号码
     * @return
     */
    @RequestMapping(value = "/addPhoneNumber", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addPhoneNumber(@RequestHeader String voySession ,@RequestParam String phone) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");



        return response;
    }

    /**
     * 更新用户信息
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean updateUserInfo(@RequestHeader String voySession, @RequestParam final String newInfo) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Profile updated!");

        try {
            JSONObject jsonObject = JSON.parseObject(URLDecoder.decode(newInfo));

            userFacade.updateUserInfo(jsonObject, userId);

            if(jsonObject.get("job") != null) {
                userFacade.updateUserSkills(jsonObject, userId);
            }

        }catch (Exception e){
            response = new ResponseBean(StateEnum.USER_UPDATE_ERROR.getCode(), "Profile update failed!");
            e.printStackTrace();
        }

        return response;
    }

    /**
     * 更新用户技能
     * @return
     */
    @RequestMapping(value = "/updateUserSkills", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean updateUserSkills(@RequestHeader String voySession, @RequestParam final String newSkills) {

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Information updated!");

        try {
            JSONObject jsonObject = JSON.parseObject(URLDecoder.decode(newSkills));

            userFacade.updateUserSkills(jsonObject, userId);

        }catch (Exception e){
            response = new ResponseBean(StateEnum.USER_UPDATE_ERROR.getCode(), "Information update failed!");
        }

        return response;
    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean invite(@RequestHeader String voySession, final @RequestParam String email){

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Invitation has been sent!");

        final String vKey = userFacade.invite(email, userId);

        new Thread() {
            public void run() {
                mailSendService.sendHtmlMail(email, vKey, mailRegisterHtmlFormat);
            }
        }.start();

        return response;

    }

    @RequestMapping(value = "/queryMyInvite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean queryMyInvite(@RequestHeader String voySession ,@RequestParam String time,@RequestParam String size){

        long userId = userFacade.getUserIdBySession(voySession);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");
        try {
            long timeVal = Long.parseLong(time);
            int sizeVal = Integer.parseInt(size);

            timeVal = timeVal == 0 ? System.currentTimeMillis() : timeVal;

            List<UserInvite> userInvites = userFacade.getUserInvites(userId, timeVal, sizeVal);
            InviteDetails inviteDetails = new InviteDetails();
            List<InviteDescResponse> inviteDescs = new ArrayList<>();

            for (UserInvite item : userInvites){
                InviteDescResponse inviteDescResponse = new InviteDescResponse();
                inviteDescResponse.setCode(item.getCode());
                inviteDescResponse.setEmail(item.getEmail());

                if (item.getInviteId() != null && item.getInviteId() != 0){
                    cn.wondervoy.dao.bean.UserInfo  userInfo = userFacade.getUserInfo(item.getInviteId());
                    UserInfo inviteUser = new UserInfo();
                    inviteUser.setUserId(userInfo.getUserId());
                    inviteUser.setLastName(userInfo.getLastName());
                    inviteUser.setFirstName(userInfo.getFirstName());
                    inviteDescResponse.setInviteUser(inviteUser);
                }
                inviteDescs.add(inviteDescResponse);
            }

            inviteDetails.setInvites(inviteDescs);
            response.setData(inviteDetails);

        }catch (Exception e){
                response = new ResponseBean(StateEnum.USER_UPDATE_ERROR.getCode(), "");
        }

        return response;

    }

    private String[] getStringArray(String str){

        if (str == null || str.length() < 1){
            return null;
        }
        return str.replace("[","").replace("]","").split(",");
    }


}


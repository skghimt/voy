package com.wondervoy.controller.business;

import cn.wondervoy.dao.bean.UserFindPassword;
import cn.wondervoy.dao.bean.UserInvite;
import cn.wondervoy.dao.bean.UserLogin;
import cn.wondervoy.facade.IUserFacade;
import cn.wondervoy.utils.email.MailRegisterHtmlFormat;
import cn.wondervoy.utils.email.MailSendService;
import com.wondervoy.controller.business.utils.ConvertBean;
import com.wondervoy.controller.response.InviteDescResponse;
import com.wondervoy.controller.response.ResponseBean;
import com.wondervoy.controller.response.UserInfo;
import com.wondervoy.controller.response.UserLoginResponseBean;
import com.wondervoy.controller.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/account")
public class AccountController {

//    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserFacade userFacade;

    @Autowired
    private MailRegisterHtmlFormat mailRegisterHtmlFormat;

    @Autowired
    private MailSendService mailSendService;

    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean signin(@RequestParam String email, @RequestParam String password,  @RequestParam String source) {

        UserLogin login = userFacade.signin(email, password, source);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Login successfully!");

        if (login == null){
            response = new ResponseBean(StateEnum.ACCOUNT_NOT_REGISTER.getCode(), "Email or password invalid!");
        } else if (login.getToken() == null){
            response = new ResponseBean(StateEnum.ACCOUNT_ERROR_PASSWORD.getCode(), "Email or password invalid!");
        } else {

            cn.wondervoy.dao.bean.UserInfo info = userFacade.getUserInfo(login.getUserId());

            UserLoginResponseBean responseBean = new UserLoginResponseBean();
            responseBean.setSession(login.getToken());
            responseBean.setUserInfo(ConvertBean.convertUserInfo(info));

            response.setData(responseBean);
        }
        return response;
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean register(@RequestParam final String email,@RequestParam String password,
                                 @RequestParam String firstName, @RequestParam String lastName, @RequestParam String code) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Register successfully!");
        if (userFacade.existAccount(email,null)){
            response = new ResponseBean(StateEnum.ACCOUNT_EXIST.getCode(), "Email has been registered!");
        } else {

            final String vKey = userFacade.signup(email, password, firstName, lastName, code);

//            new Thread() {
//                public void run() {
//                    mailSendService.sendHtmlMail(email, vKey, mailRegisterHtmlFormat);
//                }
//            }.start();

        }
        return response;
    }

    /**
     * 激活
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean active(@RequestParam final String code) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "激活成功正常");

        int result = userFacade.active(code);

        switch (result){
            case -1 :
                response = new ResponseBean(StateEnum.ACCOUNT_ACTIVE_ERROR.getCode(), "激活码错误");
                break;
            case 0 :
                UserLoginResponseBean responseBean = new UserLoginResponseBean();
                responseBean.setSession("asdasdqwfqwrqwfsfwqtfasfa");
                response.setData(responseBean);
                break;
            case 1 :
                response = new ResponseBean(StateEnum.ACCOUNT_ACTIVE_INVALID.getCode(), "激活码已失效");
                break;
            case 2 :
                response = new ResponseBean(StateEnum.ACCOUNT_ACTIVEED.getCode(), "该账号已激活");
                UserLoginResponseBean responseBean1 = new UserLoginResponseBean();
                responseBean1.setSession("asdasdqwfqwrqwfsfwqtfasfa");
                response.setData(responseBean1);
                break;
        }

        return response;
    }

    /**
     * 验证
     * @return
     */
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean verification(@RequestParam final String email) {

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "获取邮箱验证信息正常");

        final String vKey = userFacade.newActiveInfo(email);

        if (vKey == null){
            response = new ResponseBean(StateEnum.ACCOUNT_NOT_REGISTER.getCode(), "账号不存在，请注册后获取邮箱验证信息");
        } else if (vKey.equals("hasActive")){
            response = new ResponseBean(StateEnum.ACCOUNT_ACTIVEED.getCode(), "无法获取验证邮件，因为此账号已激活");
        } else {
            new Thread() {
                public void run() {
                    mailSendService.sendHtmlMail(email, vKey, mailRegisterHtmlFormat);
                }
            }.start();

        }
        return response;
    }

    /**
     * 验证
     * @return
     */
    @RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean changePasswd(@RequestHeader String voySession, @RequestParam String newpasswd, @RequestParam String oldpasswd ) {

        ResponseBean response = null;

        try{

            long userId = userFacade.getUserIdBySession(voySession);

            int result = userFacade.changePassword(userId, oldpasswd, newpasswd);

            if (result == 0){
                response = new ResponseBean(StateEnum.OK.getCode(), "Password updated!");
            } else {
                response = new ResponseBean(StateEnum.ACCOUNT_ERROR_PASSWORD.getCode(), "Please input your password!");
            }

        }catch (Exception e){
            e.printStackTrace();
            response = new ResponseBean(StateEnum.ACCOUNT_ERROR_PASSWORD.getCode(), "Password update failed!");
        }

        return response;
    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean invite(final @RequestParam String email){

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Invitation has been sent!");

        final String vKey = userFacade.invite(email, 0l);

        new Thread() {
            public void run() {
                mailSendService.sendHtmlMail(email, vKey, mailRegisterHtmlFormat);
            }
        }.start();

        return response;

    }

    @RequestMapping(value = "/inviteUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean inviteUser(@RequestHeader String voySession ,final @RequestParam String email){

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

    @RequestMapping(value = "/inviteDesc", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean inviteDesc(final @RequestParam String code){

        UserInvite inviteDesc = userFacade.getInvite(code);

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");

        if (inviteDesc == null){
            response = new ResponseBean(StateEnum.ACCOUNT_INVITE_ERROR.getCode(), "Invitation invalid!");
        }   else{
            if (inviteDesc.getInvalidDate() < System.currentTimeMillis()){
                response = new ResponseBean(StateEnum.ACCOUNT_INVITE_INVALID.getCode(), "Invitation has expired!");
            } else {
                InviteDescResponse inviteDescResponse = new InviteDescResponse();
                inviteDescResponse.setCode(inviteDesc.getCode());
                inviteDescResponse.setEmail(inviteDesc.getEmail());

                if (inviteDesc.getUserId() != 0 ){
                    cn.wondervoy.dao.bean.UserInfo  userInfo = userFacade.getUserInfo(inviteDesc.getUserId());
                    UserInfo inviteUser = new UserInfo();
                    inviteUser.setUserId(userInfo.getUserId());
                    inviteUser.setLastName(userInfo.getLastName());
                    inviteUser.setFirstName(userInfo.getFirstName());
                    inviteDescResponse.setInviteUser(inviteUser);
                }


                response.setData(inviteDescResponse);
            }
        }

        // 增加邀请码已使用状态

        return response;

    }

    @RequestMapping(value = "/findPass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean findPass(final @RequestParam String email){

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Retrieve password mail has been sent!");

        if (userFacade.existAccount(email,null)) {

            UserFindPassword findPassword = userFacade.findPassword(email);

            final String vKey = findPassword.getCode();

            new Thread() {
                public void run() {
                    mailSendService.sendPasswdHtmlMail(email, vKey, mailRegisterHtmlFormat);
                }
            }.start();
        } else {
            response = new ResponseBean(StateEnum.ACCOUNT_NOT_REGISTER.getCode(), "Email does not exist!");
        }

        return response;

    }

    @RequestMapping(value = "/findChangePass", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean findChangePass(final @RequestParam String email, final @RequestParam String code, final @RequestParam String newPasswd){

        ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "Password updated!");

        UserFindPassword findPassword = userFacade.findPasswordByCode(code);

        if (findPassword.getState().equalsIgnoreCase("N")) {

            if (findPassword != null && findPassword.getEmail().equalsIgnoreCase(email)) {

                if (findPassword.getInvalidDate() > System.currentTimeMillis()) {

                    userFacade.changePassword(email, newPasswd);
                    userFacade.updateFindPasswdCode(code);

                } else {
                    response = new ResponseBean(StateEnum.ACCOUNT_INVITE_INVALID.getCode(), "The link has expired!");
                }

            } else {
                response = new ResponseBean(StateEnum.ACCOUNT_INVITE_ERROR.getCode(), "The link is invalid!");
            }
        }else {
            response = new ResponseBean(StateEnum.ACCOUNT_INVITE_INVALID.getCode(), "The link is invalid!");
        }


        return response;

    }

}


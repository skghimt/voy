package cn.wondervoy.facade.impl;

import cn.wondervoy.dao.bean.*;
import cn.wondervoy.facade.IUserFacade;
import cn.wondervoy.service.wondervoy.*;
import cn.wondervoy.service.wondervoy.impl.IdFactoryServiceImpl;
import cn.wondervoy.utils.EffectiveTime;
import cn.wondervoy.utils.IdBuildType;
import cn.wondervoy.utils.MD5Util;
import cn.wondervoy.utils.VerificationTerrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ckzhang on 14-12-21.
 */
@Service("UserFacadeImpl")
public class UserFacadeImpl implements IUserFacade {

    @Autowired
    private IdFactoryServiceImpl idFactoryService;

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private IUserVerificationService verificationService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserSkillService userSkillService;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private IUserInviteService userInviteService;

    @Autowired
    private IUserFindPasswordService findPasswordService;

    @Override
    public UserLogin signin(String email, String password, String source){

        UserAccount account = (UserAccount)userAccountService.findOne("email", email);

        if (account == null){
            return null;
        } else {

            UserLogin login = new UserLogin();

            String md5Key = MD5Util.getMD5String(password);

            if (account.getPasswd().equalsIgnoreCase(md5Key)){

                String session = MD5Util.getMD5String(email + System.currentTimeMillis());


                login.setUserId(account.getUserId());
                login.setInvalidDate(System.currentTimeMillis() + EffectiveTime.SESSION_DURACTION_TIME);
                login.setSource(source);
                login.setToken(session);

                userLoginService.insert(login);

                return login;

            } else {
              return login;
            }
        }

    }

    @Override
    public String signup(String email, String password, String firstName, String lastName, String code) {

        long userId = idFactoryService.getIdByBusinessCode(IdBuildType.USERID,idFactoryService.getCountAsInt());

        UserAccount userAccount = new UserAccount();

        userAccount.setPasswd(MD5Util.getMD5String(password));
        userAccount.setEmail(email);
        userAccount.setUserId(userId);
        userAccount.setCreateDate(System.currentTimeMillis());

        userAccountService.insert(userAccount);

        String verificationKey = MD5Util.getMD5String(System.currentTimeMillis() + VerificationTerrace.EMAIL + email);

        UserVerification userVerification = new UserVerification();
        userVerification.setVerificationTerrace(VerificationTerrace.EMAIL);
        userVerification.setUserId(userId);
        userVerification.setPasswd("");
        userVerification.setAccount(email);
        userVerification.setVerificationTime(System.currentTimeMillis());
        userVerification.setVerificationInfo(verificationKey);
        userVerification.setCreateDate(System.currentTimeMillis());
        verificationService.insert(userVerification);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
        userInfo.setName(firstName + lastName);

        userInfoService.insert(userInfo);

        UserInvite invite =  (UserInvite)userInviteService.findOne("code", code);
        invite.setInviteId(userId);
        invite.setUpdateDate(System.currentTimeMillis());
        invite.setState("U");
        userInviteService.update(invite);

        UserSkill userSkill = new UserSkill();
        userSkill.setUserId(userId);
        userSkill.setCreateDate(System.currentTimeMillis());
        userSkill.setUpdateDate(userSkill.getCreateDate());
        userSkillService.insert(userSkill);

        return verificationKey;
    }

    @Override
    public boolean existAccount(String email, String phone) {

        Map<String,Object> param = new HashMap<>();
        param.put("email",email);
        param.put("phone",phone);
        int count = userAccountService.count(param);


        return count > 0;
    }

    @Override
    public int active(String activeInfo) {

        Map<String, Object> params = new HashMap<>();
        params.put("verificationInfo",activeInfo);
        params.put("verificationTerrace",VerificationTerrace.EMAIL);

        UserVerification verification = (UserVerification)verificationService.queryOne(params);

        if (verification == null){
            return -1;
        }

        if (verification.getState().equals("U")){
            return 2;
        }

        if (System.currentTimeMillis() - verification.getVerificationTime() < EffectiveTime.ACTIVE_DURACTION_TIME){

            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put("id", verification.getId());
            updateParams.put("updateDate", System.currentTimeMillis());
            updateParams.put("state","U");

            verificationService.updateMap(updateParams);

            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public String newActiveInfo(String email) {

        Map<String, Object> params = new HashMap<>();
        params.put("account",email);
        params.put("verificationTerrace",VerificationTerrace.EMAIL);
        UserVerification verification = (UserVerification)verificationService.queryOne(params);

        if (verification == null ){
            return null;
        } else if (verification.getState().equals("U")){
                return "hasActive";
        } else{

            String verificationKey = MD5Util.getMD5String(System.currentTimeMillis() + VerificationTerrace.EMAIL + email);

            Map<String, Object> updateParams = new HashMap<>();
            updateParams.put("id", verification.getId());
            updateParams.put("verificationTime", System.currentTimeMillis());
            updateParams.put("verificationInfo",verificationKey);

            verificationService.updateMap(updateParams);

            return verificationKey;
        }
    }

    @Override
    public String invite(String email, long userId){
        String inviteCode = MD5Util.getMD5String(System.currentTimeMillis() + VerificationTerrace.EMAIL + email);


        UserInvite invite = new UserInvite();
        invite.setUserId(userId);
        invite.setCreateDate(System.currentTimeMillis());
        invite.setUpdateDate(System.currentTimeMillis());
        invite.setCode(inviteCode);
        invite.setEmail(email);
        invite.setInvalidDate(System.currentTimeMillis() + EffectiveTime.INVITE_DURACTION_TIME);


        userInviteService.insert(invite);

        return inviteCode;

    }

    @Override
    public UserInvite getInvite(String code) {

        UserInvite invite =  (UserInvite)userInviteService.findOne("code", code);

        return invite;
    }

    @Override
    public UserInfo getUserInfo(long userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        return (UserInfo)userInfoService.queryOne(params);
    }

    @Override
    public UserSkill getUserSkill(long userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        return (UserSkill) userSkillService.queryOne(params);
    }

    @Override
    public int updateUserInfo(Map<String, Object> updateInfo, long userId) {

        updateInfo.put("userId",userId);
        updateInfo.put("state","U");
        updateInfo.put("updateDate",System.currentTimeMillis());
        updateInfo.put("name",updateInfo.get("firstName").toString() + updateInfo.get("lastName").toString());

        userInfoService.updateMap(updateInfo);
        return 1;
    }

    @Override
    public int updateUserSkills(Map<String, Object> updateInfo, long userId) {

        updateInfo.put("userId",userId);
        updateInfo.put("state","U");
        updateInfo.put("updateDate",System.currentTimeMillis());
        if (updateInfo.get("language") != null ){
            Object languages = updateInfo.get("language");
            updateInfo.put("language",languages.toString());
        }
        if (updateInfo.get("interest") != null ){
            Object languages = updateInfo.get("interest");
            updateInfo.put("interest",languages.toString());
        }

        userSkillService.updateMap(updateInfo);

        return 1;
    }

    @Override
    public List<UserVerification> getUserVerification(long userId) {

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        List<UserVerification> verifications = verificationService.queryList(params, "createDate" , "Desc");

        return verifications;
    }

    @Override
    public int addUserVerification(UserVerification verification) {

        verification.setCreateDate(System.currentTimeMillis());

        verificationService.insert(verification);

        return 1;
    }

    @Override
    public long getUserIdBySession(String session) {

        UserLogin login = (UserLogin)userLoginService.findOne("token",session);

        return login.getUserId();
    }

    @Override
    public int changePassword(long userId, String passwd, String newPasswd){
        UserAccount account = (UserAccount) userAccountService.findOne("userId", userId);

        String md5Key = MD5Util.getMD5String(passwd);

        if (account.getPasswd().equalsIgnoreCase(md5Key)){
            account.setPasswd(MD5Util.getMD5String(newPasswd));
            account.setUpdateDate(System.currentTimeMillis());
            userAccountService.update(account);

            return 0;

        } else {
            return -1;
        }
    }

    @Override
    public int changePassword(String email, String newPasswd){
        UserAccount account = (UserAccount) userAccountService.findOne("email", email);

        String md5Key = MD5Util.getMD5String(newPasswd);

            account.setPasswd(MD5Util.getMD5String(newPasswd));
            account.setUpdateDate(System.currentTimeMillis());
            userAccountService.update(account);

            return 1;
    }

    @Override
    public List<UserInvite> getUserInvites(long userId, long inviteTime, int size) {

        return userInviteService.queryPageByTime(userId,inviteTime,size);

    }

    @Override
    public UserFindPassword findPassword(String mail) {

        UserFindPassword findPassword = new UserFindPassword();
        findPassword.setCreateDate(System.currentTimeMillis());
        findPassword.setInvalidDate(findPassword.getCreateDate() + EffectiveTime.FIND_PASSWORD_TIME);
        findPassword.setEmail(mail);
        findPassword.setCode(MD5Util.getMD5String(System.currentTimeMillis() + VerificationTerrace.EMAIL + mail));

        Map<String,Object> params = new HashMap<>();
        params.put("state","I");
        params.put("email",mail);
        params.put("updateDate",System.currentTimeMillis());
        findPasswordService.updateMap(params);


        findPasswordService.insert(findPassword);

        return findPassword;
    }

    @Override
    public UserFindPassword findPasswordByCode(String code) {

        return (UserFindPassword) findPasswordService.findOne("code",code);
    }

    @Override
    public int updateFindPasswdCode(String code) {

        UserFindPassword findPassword = (UserFindPassword) findPasswordService.findOne("code",code);
        findPassword.setState("U");
        findPassword.setUpdateDate(System.currentTimeMillis());

        findPasswordService.update(findPassword);

        return 1;
    }

    @Override
    public List<FunnyVEx> funnyV() {
        return userInfoService.funnyV();
    }
}



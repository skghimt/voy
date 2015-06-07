package cn.wondervoy.facade;

import cn.wondervoy.dao.bean.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ckzhang on 14-12-21.
 */
public interface IUserFacade {

    UserLogin signin(String email, String password, String source);

    String signup(String email, String password, String firstName, String lastName, String code);

    boolean existAccount(String email, String phone);

    int active(String activeInfo);

    String newActiveInfo(String email);

    String invite(String email, long userId);

    UserInfo getUserInfo(long userId);

    UserSkill getUserSkill(long userId);

    int updateUserInfo(Map<String,Object> updateInfo, long userId);

    int updateUserSkills(Map<String,Object> updateInfo, long userId);

    List<UserVerification> getUserVerification(long userId);

    int addUserVerification(UserVerification verification);

    long getUserIdBySession(String session);

    int changePassword(long userId, String passwd, String newPasswd);

    int changePassword(String email, String newPasswd);

    UserInvite getInvite(String code);

    List<UserInvite> getUserInvites(long userId, long inviteTime, int size);

    UserFindPassword findPassword(String mail);

    UserFindPassword findPasswordByCode(String code);

    int updateFindPasswdCode(String code);

    List<FunnyVEx> funnyV();
}

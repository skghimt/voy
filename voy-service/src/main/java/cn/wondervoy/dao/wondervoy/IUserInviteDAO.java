/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: vip
 * $Id:  UserInviteDAO.java 2015-02-04 21:44:17 $
 */
package cn.wondervoy.dao.wondervoy;


import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.UserInvite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserInviteDAO extends IBaseDAO<UserInvite> {

    List<UserInvite> queryPageByTime(@Param("userId")long userId, @Param("time")long time, @Param("size")int size);
}

/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserInfoDAO.java 2014-12-17 09:45:18 $
 */
package cn.wondervoy.dao.wondervoy;

import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.FunnyVEx;
import cn.wondervoy.dao.bean.UserInfo;

import java.util.List;

public interface IUserInfoDAO extends IBaseDAO<UserInfo> {
	
	UserInfo findByUserId(Long userId);

    List<FunnyVEx> funnyV();

}

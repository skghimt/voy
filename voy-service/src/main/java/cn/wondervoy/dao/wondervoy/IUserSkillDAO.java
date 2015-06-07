/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserSkillDAO.java 2014-12-17 09:45:18 $
 */
package cn.wondervoy.dao.wondervoy;


import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.UserSkill;

public interface IUserSkillDAO extends IBaseDAO<UserSkill> {
	
	UserSkill findByUserId(Long userId);

}

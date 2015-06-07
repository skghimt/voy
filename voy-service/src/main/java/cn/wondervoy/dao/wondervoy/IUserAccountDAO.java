/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  UserAccountDAO.java 2014-12-17 09:45:17 $
 */
package cn.wondervoy.dao.wondervoy;


import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.UserAccount;

public interface IUserAccountDAO extends IBaseDAO<UserAccount> {
	
	UserAccount findByEmail(String email);
	UserAccount findByPhone(String phone);

}

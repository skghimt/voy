/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: vip
 * $Id:  UserInviteService.java 2015-02-04 21:44:17 $
 */

package cn.wondervoy.service.wondervoy;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.dao.bean.UserInvite;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

import java.util.List;

public interface IUserInviteService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

    List<UserInvite> queryPageByTime(long userId, long time, int size);

}

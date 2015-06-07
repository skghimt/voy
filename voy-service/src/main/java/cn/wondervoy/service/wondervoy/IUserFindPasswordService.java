/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: wondervoy
 * $Id:  UserFindPasswordService.java 2015-05-24 11:35:38 $
 */

package cn.wondervoy.service.wondervoy;

import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

public interface IUserFindPasswordService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

}

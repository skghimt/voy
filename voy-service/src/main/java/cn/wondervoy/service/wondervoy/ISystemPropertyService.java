/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: xym
 * $Id:  SystemPropertyService.java 2015-03-14 23:28:16 $
 */

package cn.wondervoy.service.wondervoy;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

public interface ISystemPropertyService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

}

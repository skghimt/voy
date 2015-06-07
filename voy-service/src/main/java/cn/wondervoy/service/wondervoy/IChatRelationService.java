/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: message
 * $Id:  ChatRelationService.java 2014-12-17 09:45:17 $
 */

package cn.wondervoy.service.wondervoy;


import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import cn.wondervoy.service.IBaseService;
import cn.wondervoy.service.IPageService;

public interface IChatRelationService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T> {

}

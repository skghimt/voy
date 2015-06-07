package cn.wondervoy.service;

import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by ckzhang on 14-12-17.
 */
public abstract class AbstractBaseService<D extends IBaseDAO,T extends BaseDomain> implements IBaseService<D,T>, IDaoAware<D,T>{

    @Override
    public final void add(T entity) {
        enhanceNewCreateBaseDomain(entity);
        getDao().insert(entity);
    }

    @Override
    public final void edit(T entity) {
        enhanceCreateBaseDomain(entity);

        getDao().update(entity);
    }

    @Override
    public final void delete(Long id) {
        getDao().deleteById(id);
    }

    @Override
    public final T view(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public final void insert(T entity) {
        enhanceNewCreateBaseDomain(entity);
        getDao().insert(entity);
    }

    @Override
    public final void update(T entity) {
        enhanceCreateBaseDomain(entity);
        getDao().update(entity);
    }

    @Override
    public final void updateNull(T entity) {
        enhanceCreateBaseDomain(entity);

        getDao().updateNull(entity);

    }

    @Override
    public final void deleteById(Long id) {

        getDao().deleteById(id);
    }

    @Override
    public final void deleteByProperty(String property, Object value) {
        getDao().deleteByProperty(property,value);

    }

    @Override
    public final T fetch(Long id) {
        return (T)getDao().fetch(id);
    }

    @Override
    public final T findOne(String property, Object value) {
        return (T)getDao().findOne(property,value);
    }

    @Override
    public final List findList(String property, Object value) {
        return getDao().findList(property,value);
    }

    @Override
    public final List findAll() {
        return getDao().findAll();
    }

    @Override
    public final List like(String property, Object value) {
        return getDao().like(property,value);
    }

    @Override
    public final Long selectMaxId() {
        return getDao().selectMaxId();
    }

    @Override
    public final void updateOrSave(T entity, Long id) {
        if(id!=null&&!StringUtils.isEmpty(id)){
            enhanceCreateBaseDomain(entity);
            getDao().update(entity);
        }else{
            enhanceNewCreateBaseDomain(entity);
            getDao().insert(entity);
        }
    }

    @Override
    public final T selectOne(String mapperId, Object obj) {
        return (T)getDao().selectOne(mapperId,obj);
    }

    @Override
    public final List selectList(String mapperId, Object obj) {
        return getDao().selectList(mapperId,obj);
    }

    @Override
    public final int count(Map condition) {
        return getDao().count(condition);
    }

    @Override
    public final T queryOne(Map condition) {
        return (T)getDao().queryOne(condition);
    }

    @Override
    public final List queryList(Map condition, String orderBy, String sortBy) {
        return getDao().queryList(condition,orderBy,sortBy);
    }

    @Override
    public final List queryPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition,offset,rows);
    }

    @Override
    public final void deleteByCondition(Map condition) {
        getDao().deleteByCondition(condition);
    }

    @Override
    public final void updateMap(Map entityMap) {
        enhanceCreateBaseDomain(entityMap);
        getDao().updateMap(entityMap);
    }

    @Override
    public final void insertMap(Map entityMap) {
        enhanceNewCreateBaseDomain(entityMap);
        getDao().insertMap(entityMap);
    }

    @Override
    public final List listByPage(Map condition, int offset, int rows) {
        return getDao().queryPage(condition, offset, rows);
    }

    private final T enhanceCreateBaseDomain(T entity){
        return entity;
    }

    private final T enhanceNewCreateBaseDomain(T entity){
        return entity;
    }

    private final Map enhanceCreateBaseDomain(Map entityMap){
        return entityMap;
    }

    private final Map enhanceNewCreateBaseDomain(Map entityMap){
        return entityMap;
    }
}

package cn.wondervoy.service;

import cn.wondervoy.base.BaseDomain;
import cn.wondervoy.dao.IBaseDAO;

public interface IBaseService <D extends IBaseDAO, T extends BaseDomain> {
    D getDao();

    void add(T t);

    void edit(T t);

    void delete(java.lang.Long aLong);

    T view(java.lang.Long aLong);

    java.util.List<T> listByPage(java.util.Map<java.lang.String,java.lang.Object> map, int i, int i1);

    void insert(T t);

    void update(T t);

    void insertMap(java.util.Map<java.lang.String,java.lang.Object> map);

    void updateMap(java.util.Map<java.lang.String,java.lang.Object> map);

    void updateNull(T t);

    void deleteById(java.lang.Long aLong);

    void deleteByCondition(java.util.Map<java.lang.String,java.lang.Object> map);

    void deleteByProperty(java.lang.String s, java.lang.Object o);

    T fetch(java.lang.Long aLong);

    T findOne(java.lang.String s, java.lang.Object o);

    java.util.List<T> findList(java.lang.String s, java.lang.Object o);

    java.util.List<T> findAll();

    java.util.List<T> queryPage(java.util.Map<java.lang.String,java.lang.Object> map, int i, int i1);

    java.util.List<T> like(java.lang.String s, java.lang.Object o);

    java.util.List<T> queryList(java.util.Map<java.lang.String,java.lang.Object> map, java.lang.String s, java.lang.String s1);

    T queryOne(java.util.Map<java.lang.String,java.lang.Object> map);

    int count(java.util.Map<java.lang.String,java.lang.Object> map);

    java.lang.Long selectMaxId();

    void updateOrSave(T t, java.lang.Long aLong);

    T selectOne(java.lang.String s, java.lang.Object o);

    java.util.List<T> selectList(java.lang.String s, java.lang.Object o);
}
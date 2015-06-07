package cn.wondervoy.dao;

import cn.wondervoy.base.BaseDomain;

public interface IBaseDAO <T extends BaseDomain> {
    int insert(T t);

    int update(T t);

    int updateMap(@org.apache.ibatis.annotations.Param("map") java.util.Map<java.lang.String,java.lang.Object> map);

    int updateByCondition(@org.apache.ibatis.annotations.Param("update") java.util.Map<java.lang.String,java.lang.Object> map, @org.apache.ibatis.annotations.Param("condition") java.util.Map<java.lang.String,java.lang.Object> map1);

    int insertMap(@org.apache.ibatis.annotations.Param("map") java.util.Map<java.lang.String,java.lang.Object> map);

    int updateNull(T t);

    int deleteById(java.lang.Long aLong);

    int deleteByCondition(java.util.Map<java.lang.String,java.lang.Object> map);

    int deleteByProperty(java.lang.String s, java.lang.Object o);

    T fetch(java.lang.Long aLong);

    T findOne(@org.apache.ibatis.annotations.Param("property") java.lang.String s, @org.apache.ibatis.annotations.Param("value") java.lang.Object o);

    java.util.List<T> findList(@org.apache.ibatis.annotations.Param("property") java.lang.String s, @org.apache.ibatis.annotations.Param("value") java.lang.Object o);

    java.util.List<T> findAll();

    java.util.List<T> queryPage(@org.apache.ibatis.annotations.Param("condition") java.util.Map<java.lang.String,java.lang.Object> map, @org.apache.ibatis.annotations.Param("offset") int i, @org.apache.ibatis.annotations.Param("rows") int i1);

    java.util.List<T> like(java.lang.String s, java.lang.Object o);

    java.util.List<T> queryList(@org.apache.ibatis.annotations.Param("condition") java.util.Map<java.lang.String,java.lang.Object> map, @org.apache.ibatis.annotations.Param("orderBy") java.lang.String s, @org.apache.ibatis.annotations.Param("sortBy") java.lang.String s1);

    T queryOne(java.util.Map<java.lang.String,java.lang.Object> map);

    int count(java.util.Map<java.lang.String,java.lang.Object> map);

    java.lang.Long selectMaxId();

    T updateOrSave(T t, java.lang.Long aLong);

    T selectOne(java.lang.String s, java.lang.Object o);

    java.util.List<T> selectList(java.lang.String s, java.lang.Object o);

    java.lang.Class<T> getEntityClass();
}

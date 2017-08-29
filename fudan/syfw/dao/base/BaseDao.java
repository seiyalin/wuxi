package org.wuxi.fudan.syfw.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;


public interface BaseDao<T> {
	//新增
	public Serializable save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findObjectById(Serializable id);
	//查找列表
	public List<T> findObjects();
	
	public List<T> findObjects(String hql, List<Object> parameters);
	//条件查询实体列表--查询助手queryHelper
	public List<T> findObjects(QueryHelper queryHelper);
	//分页条件查询实体列表--查询助手queryHelper
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
	
	//分页
	public PageResult getPageResult(List list, int pageNo, int pageSize);
}

package org.wuxi.fudan.syfw.service.base;

import java.util.List;

import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;


public interface BaseService<T> {
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(String id);
/*	session缓存中逐出该对象
	public void deletePersist(T entity);*/
	//根据id查找
	public T findObjectById(String id);
	//查找列表
	public List<T> findObjects();
	//条件查询实体列表
//	@Deprecated
	public List<T> findObjects(String hql, List<Object> parameters);
//	条件查询实体列表--查询助手queryHelper
	public List<T> findObjects(QueryHelper queryHelper);
//	分页条件查询实体列表--查询助手queryHelper
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);

}

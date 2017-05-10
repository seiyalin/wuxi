package org.wuxi.fudan.syfw.service.base;

import java.util.List;

import javax.annotation.Resource;

import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.base.BaseDao;


public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;
	
	/*@Resource,basedao不能用resource注入，实现时因为泛型不明确会出错*/
	public void setBaseDao(BaseDao<T> baseDao) {//该方法提供给子Service调用，设置BaseDao对象，在BaseSevice便可
														//实现对baseDao对象基本方法的调用
		System.out.println("进入了BaseService<T>的setBaseDao(IBaseDao<T> baseDao)");
		
		this.baseDao = baseDao;
		
		System.out.println("进入了BaseService<T>的setBaseDao(IBaseDao<T> baseDao)");
	}

	@Override
	public void save(T entity) {
		System.out.println("进入BaseService 的save方法");
		baseDao.save(entity);
		System.out.println("退出BaseService 的save方法");

	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		System.out.println("进入BaseService 的update方法");
		baseDao.update(entity);
		System.out.println("退出BaseService 的update方法");
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(String id) {
		// TODO Auto-generated method stub
		return baseDao.findObjectById(id);
	}



	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		return baseDao.findObjects(hql, parameters);
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		return baseDao.getPageResult(queryHelper, pageNo, pageSize);
	}

}

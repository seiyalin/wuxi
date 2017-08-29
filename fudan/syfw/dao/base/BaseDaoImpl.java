package org.wuxi.fudan.syfw.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;


public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		ParameterizedType pt =  (ParameterizedType)this.getClass().getGenericSuperclass();//BaseDaoImpl<User>
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}
	

	@Override
	public Serializable save(T entity) {
		//getHibernateTemplate().getSessionFactory().getCurrentSession().merge(entity);
		return getHibernateTemplate().save(entity);
	/*	getHibernateTemplate().flush();
		getHibernateTemplate().clear();*/
	}
	

	@Override
	public void update(T entity) {
		//Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		//session.clear();
		getHibernateTemplate().update(entity);
		/*getHibernateTemplate().flush();
		getHibernateTemplate().clear();*/
		//session.flush();
	}

	
	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findObjectById(id));
	}

	/*session缓存中逐出该对象
	public void deletePersist(T entity){
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.evict(entity);
	}*/
	
	@Override
	public T findObjectById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		Query query = getSession().createQuery(hql);
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNo < 1) pageNo = 1;
		
		query.setFirstResult((pageNo-1)*pageSize);//设置数据起始索引号
		query.setMaxResults(pageSize);
		List items = query.list();
		//获取总记录数
		Query queryCount = getSession().createQuery(queryHelper.getQueryCountHql());
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				queryCount.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long)queryCount.uniqueResult();
		
		return new PageResult(totalCount, pageNo, pageSize, items);
	}
	

	public PageResult getPageResult(List list, int pageNo, int pageSize){
		int totalCount = list.size();
		int start = (pageNo-1)*pageSize;
		int end = Math.min(pageNo*pageSize, totalCount);
		//subList取从start开始，到end终止（不包含end）的list
		List items = list.subList(start, end);
		return new PageResult(totalCount, pageNo, pageSize, items);
	}

	
}

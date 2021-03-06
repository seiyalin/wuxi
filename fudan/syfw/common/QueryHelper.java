package org.wuxi.fudan.syfw.common;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
	
	//from子句
	private String fromClause = "";
	//where子句
	private String whereClause = "";
	//order by子句
	private String orderByClause = "";
	
	private List<Object> parameters;
	//排序顺序
	public static String ORDER_BY_DESC = "DESC";//降序
	public static String ORDER_BY_ASC = "ASC";//升序
	
	/**
	 * 构造from 子句
	 * @param clazz 实体类
	 * @param alias 实体类对应的别名
	 */
	public QueryHelper(Class clazz, String alias){
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 构造from 子句
	 * @param fromClause from子句
	 * @param alias 实体类对应的别名
	 */
	public QueryHelper(String fromClause, String alias){
		fromClause = "FROM " + fromClause + " " + alias;
	}
	
	/**
	 * 构造from 子句+addition
	 * @param clazz 实体类
	 * @param alias 实体类对应的别名
	 * @param whereClause 条件查询语句
	 */
	public QueryHelper(Class clazz, String alias, String whereClause){
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
		this.whereClause = whereClause;
	}
	
	/**
	 * 构造where子句
	 * @param condition 查询条件语句；例如：i.title like ?
	 * @param params 查询条件语句中?对应的查询条件值；例如： %标题%
	 */
	public void addCondition(String condition, Object... params){
		if (whereClause.length() > 1) {//非第一个查询条件
			whereClause += " AND " + condition;
		} else {//第一个查询条件
			whereClause += " WHERE " + condition;
		}
		
		//设置查询条件值到查询条件值集合中
		if(parameters == null){
			parameters = new ArrayList<Object>();
		}
		if(params != null){
			for(Object param: params){
				parameters.add(param);
			}
		}
	}
	
	/**
	 * 构造order by子句
	 * @param property 排序属性，如：i.createTime
	 * @param order 排序顺序，如：DESC 或者 ASC
	 */
	public void addOrderByProperty(String property, String order){
		if (orderByClause.length() > 1) {//非第一个排序属性
			orderByClause += "," + property + " " + order;
		} else {//第一个排序属性
			orderByClause = " ORDER BY " + property + " " + order;
		}
	}

	//查询hql语句
	public String getQueryListHql(){
		return fromClause + whereClause + orderByClause;
	}
	
	//查询统计数的hql语句
	public String getQueryCountHql(){
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	//查询hql语句中?对应的查询条件值集合
	public List<Object> getParameters(){
		return parameters;
	}
}

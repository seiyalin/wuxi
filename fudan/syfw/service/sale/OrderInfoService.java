package org.wuxi.fudan.syfw.service.sale;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface OrderInfoService extends BaseService<OrderInfo> {
	
	//find restaurant by Id
	public RestaurantCompany findRestaurantById(String id);
	
	//find all restaurant
	public List<RestaurantCompany> findAllRestaurant();
	
	public void saveOrder(OrderInfo order, String cages);
		//save restaurant
	public void saveRestaurant(RestaurantCompany restaurant);

		//delete restaurant
	public void deleteRestaurant(String id);
	
	//get order info list
	public List<OrderInfo> getList(List<OrderInfo> list, Integer start, Integer limit);
	
	//获取公司下的所有销售订单，分页
	public List<OrderInfo> getOrder(Integer start, Integer limit, ProcessCompany processCompany);
		
	//get restaurant list
	public List<RestaurantCompany> getList(Integer start, Integer limit);
	
	//get total number of breedArea
	public int getCount();
	
}

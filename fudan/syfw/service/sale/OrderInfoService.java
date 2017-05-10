package org.wuxi.fudan.syfw.service.sale;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface OrderInfoService extends BaseService<OrderInfo> {
	
	//find restaurant by Id
	public RestaurantCompany findRestaurantById(String id);
	
	//find all restaurant
	public List<RestaurantCompany> findAllRestaurant();
	
		//save restaurant
	public void saveRestaurant(RestaurantCompany restaurant);

		//delete restaurant
	public void deleteRestaurant(String id);
	
	
	//get order info list
	public List<OrderInfo> getList(Integer start, Integer limit);
	
	//get total number of breedArea
	public int getCount();
	
}

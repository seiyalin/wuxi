package org.wuxi.fudan.syfw.service.sale;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.sale.OrderInfoDao;
import org.wuxi.fudan.syfw.dao.sale.RestaurantCompanyDao;
import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("orderInfoService")
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo> implements OrderInfoService{
	
	private RestaurantCompanyDao restaurantCompanyDao;
	private OrderInfoDao orderInfoDao;
	
	
	PageResult pageResult;
	
	//construtor
	public OrderInfoServiceImpl(){
		
	}
	
	//find restaurant by Id
	public RestaurantCompany findRestaurantById(String id){
		return restaurantCompanyDao.findObjectById(id);
	}
	
	//find all restaurant
	public List<RestaurantCompany> findAllRestaurant(){
		return restaurantCompanyDao.findObjects();
	}
	
	//save restaurant
	public void saveRestaurant(RestaurantCompany restaurant){
		restaurantCompanyDao.save(restaurant);
	}
	
	//delete restaurant
	public void deleteRestaurant(String id){
		restaurantCompanyDao.delete(id);
	}
	
	
	//get order info list
	public List<OrderInfo> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(OrderInfo.class, "orderInfo");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = orderInfoDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
		
	//get total number of breedArea
	public int getCount() {
		// TODO Auto-generated method stub

		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}

	public RestaurantCompanyDao getRestaurantCompanyDao() {
		return restaurantCompanyDao;
	}

	@Resource
	public void setRestaurantCompanyDao(RestaurantCompanyDao restaurantCompanyDao) {
		this.restaurantCompanyDao = restaurantCompanyDao;
	}

	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}

	@Resource
	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		super.setBaseDao(orderInfoDao);
		this.orderInfoDao = orderInfoDao;
	}

	
	
	
	

}

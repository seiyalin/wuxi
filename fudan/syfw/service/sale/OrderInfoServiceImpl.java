package org.wuxi.fudan.syfw.service.sale;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.process.ProcessInfoDao;
import org.wuxi.fudan.syfw.dao.sale.OrderInfoDao;
import org.wuxi.fudan.syfw.dao.sale.RestaurantCompanyDao;
import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("orderInfoService")
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo> implements OrderInfoService{
	
	private RestaurantCompanyDao restaurantCompanyDao;
	private OrderInfoDao orderInfoDao;
	private ProcessInfoDao processInfoDao;
	
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
	
	public void saveOrder(OrderInfo order, String cages){
		Serializable orderId = orderInfoDao.save(order);
		OrderInfo orderInfo = orderInfoDao.findObjectById(orderId);
		if(cages != null){
			String[] sellCages = cages.split(",");
			for(String sellCage: sellCages){
				sellCage.trim();
				ProcessInfo process = processInfoDao.findObjectById(sellCage);
				process.setOrderInfo(orderInfo);
				processInfoDao.update(process);
			}
		}
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
	public List<OrderInfo> getList(List<OrderInfo> list, Integer start, Integer limit){
		pageResult = orderInfoDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//获取公司下的所有销售订单，分页
	public List<OrderInfo> getOrder(Integer start, Integer limit, ProcessCompany processCompany){
		QueryHelper queryHelper = new QueryHelper(OrderInfo.class, "orderInfo");
						
				//隐藏内连接，需要在配置文件多方设置lazy=false
		queryHelper.addCondition("orderInfo.processCompany = ?", processCompany);
		queryHelper.addOrderByProperty("orderInfo.orderTime", "DESC");  //降序排列
		pageResult = orderInfoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//get restaurant list
	public List<RestaurantCompany> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(RestaurantCompany.class, "restaurant");
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

	public ProcessInfoDao getProcessInfoDao() {
		return processInfoDao;
	}

	@Resource
	public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
		this.processInfoDao = processInfoDao;
	}

	
	
	
	

}

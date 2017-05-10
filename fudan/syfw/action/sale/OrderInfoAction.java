package org.wuxi.fudan.syfw.action.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.FeedInfo;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessStaff;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.service.breed.BreedAreaService;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.breed.BreedStaffService;
import org.wuxi.fudan.syfw.service.breed.FeedInfoService;
import org.wuxi.fudan.syfw.service.process.ProcessInfoService;
import org.wuxi.fudan.syfw.service.sale.OrderInfoService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class OrderInfoAction extends ActionSupport{

	/**
	 * 加工企业销售管理，管理客户餐饮企业和销售订单
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderInfoService orderInfoService;
	private UserService		 userService;
	private ProcessInfoService processInfoService;
	

	//用户信息
	private String	userId;
	private String	companyId;
	
	//餐饮企业信息
	private String restaurantId; 	//餐厅工商号，前端要填
	private String restaurantName;	//餐厅名称
	private String legalPerson;	//法人
	private String restaurantAddress;//餐厅地址
	private String contactNumber;	//联系电话
	private String environment;	//餐厅环境
	private RestaurantCompany restaurantCompany; //卖给的餐饮企业，封装餐饮企业信息
 
	//销售订单记录
	private String orderId;		//订单编号（自动生成）	
	private ProcessCompany processCompany; //卖家，加工企业
	private String cages;			//卖出的成品箱（多选）
	private String product;		//产品名称
	private Double number;		//产品数量
	private String handler;		//经手人
	private Date orderTime;		//订单时间
	private String buyerPhone;		//买家电话
	private String buyer;			//买家
	private OrderInfo orderInfo;
	
	
	//delete params
	private String restaurantIds;
	private String orderIds;

	
	//response
	private JSONObject OptionalRestaurantList;			//餐厅列表响应
	private JSONObject OptionalCagesList;			//餐厅列表响应
	
	private JSONObject RestaurantSave;				//保存餐厅响应
	private JSONObject RestaurantDisplay;			//显示餐厅响应
	private JSONObject RestaurantDelete;				//删除餐厅响应
	
	private JSONObject OrderInfoSave;				//保存销售记录响应
	private JSONObject OrderInfoDisplay;			//显示销售记录响应
	private JSONObject OrderInfoDelete;				//删除销售记录响应
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	

     //获取可选的餐厅（单选框的选项）
     public String getOptionalRestaurant(){
    	 JSONArray restaurants = new JSONArray();
    	 JSONObject restaurant = new JSONObject();
    	 List<RestaurantCompany> list = orderInfoService.findAllRestaurant();
    	 for(RestaurantCompany res: list){
    		 restaurant.put("id", res.getRestaurantId());
    		 restaurant.put("name", res.getRestaurantName());
    		 restaurants.add(restaurant);
    	
    	 }
    	 OptionalRestaurantList = JsonUtils.toJSONResult(true, restaurants);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     }
     
     //保存餐厅信息
     public String saveRestaurant(){
    	 try {
			
			 orderInfoService.saveRestaurant(restaurantCompany);			
			 RestaurantSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RestaurantSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
	
     //获取所有餐厅
     @SuppressWarnings("unused")
	public String getListRestaurant(){
    	 try {
			
			 List<RestaurantCompany> list = orderInfoService.findAllRestaurant();
			 int count = list.size();
			 sEcho = count;
			 RestaurantDisplay= JsonUtils.toJSONResult(count, list, sEcho);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RestaurantDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除餐饮企业
     public String deleteRestaurant(){
    	 try {
 			if(restaurantIds != null)
 			{
 				String[] delete_id = restaurantIds.split(",");
 				for(String id: delete_id){
 					orderInfoService.deleteRestaurant(id);
 				}
 			}
 			RestaurantDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			RestaurantDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //获取可选的成品箱列表
     public String getOptionalCage(){
    	 try {
			processCompany = userService.findProcessCompanyById(companyId);
			 List<ProcessInfo> list = processInfoService.getCagesInSell(processCompany);
			 OptionalCagesList = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalCagesList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
    
     
   //保存销售记录信息
     public String saveOrderInfo(){
    	 try {
    		 orderInfo.setProcessCompany(userService.findProcessCompanyById(companyId));
    		 orderInfo.setRestaurantCompany(orderInfoService.findRestaurantById(restaurantId));
			if(orderInfo.getOrderTime()== null){
				orderInfo.setOrderTime(new Date());
			}
			
			Set<ProcessInfo> processInfos = new HashSet<ProcessInfo>();
			//成品箱保存订单外键
			if((cages =orderInfo.getCages() )!= null){
				String[] sellCages = cages.split(",");
				for(String sellCage: sellCages){
					ProcessInfo process = processInfoService.findObjectById(sellCage);
					process.setOrderInfo(orderInfo);
					processInfoService.update(process);
					processInfos.add(process);
				}
				orderInfo.setProcessInfos(processInfos);
			}
			
			orderInfoService.save(orderInfo);
			
			 OrderInfoSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OrderInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
	
     //获取本企业下的所有销售记录，加工企业调用
     @SuppressWarnings("unused")
	public String getListOrderInfo(){
    	 try {
			Set<OrderInfo> sets = new HashSet<OrderInfo>();
			 processCompany = userService.findProcessCompanyById(companyId);
			 sets = processCompany.getOrderInfos();
			 List<OrderInfo> list = new ArrayList<OrderInfo>();
			 list.addAll(sets);
			 int count = list.size();
			 sEcho = count;
			 OrderInfoDisplay= JsonUtils.toJSONResult(count, list, sEcho);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OrderInfoDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除销售记录
     public String deleteOrderInfo(){
    	 try {
 			if(orderIds != null)
 			{
 				String[] delete_id = orderIds.split(",");
 				for(String id: delete_id){
 					orderInfoService.delete(id);
 				}
 			}
 			OrderInfoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			OrderInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
 	
	public int getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	

	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	@Resource
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public ProcessInfoService getProcessInfoService() {
		return processInfoService;
	}

	@Resource
	public void setProcessInfoService(ProcessInfoService processInfoService) {
		this.processInfoService = processInfoService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	

	public ProcessCompany getProcessCompany() {
		return processCompany;
	}

	public void setProcessCompany(ProcessCompany processCompany) {
		this.processCompany = processCompany;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public RestaurantCompany getRestaurantCompany() {
		return restaurantCompany;
	}

	public void setRestaurantCompany(RestaurantCompany restaurantCompany) {
		this.restaurantCompany = restaurantCompany;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCages() {
		return cages;
	}

	public void setCages(String cages) {
		this.cages = cages;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getRestaurantIds() {
		return restaurantIds;
	}

	public void setRestaurantIds(String restaurantIds) {
		this.restaurantIds = restaurantIds;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public JSONObject getOptionalRestaurantList() {
		return OptionalRestaurantList;
	}

	public void setOptionalRestaurantList(JSONObject optionalRestaurantList) {
		OptionalRestaurantList = optionalRestaurantList;
	}

	public JSONObject getOptionalCagesList() {
		return OptionalCagesList;
	}

	public void setOptionalCagesList(JSONObject optionalCagesList) {
		OptionalCagesList = optionalCagesList;
	}

	public JSONObject getRestaurantSave() {
		return RestaurantSave;
	}

	public void setRestaurantSave(JSONObject restaurantSave) {
		RestaurantSave = restaurantSave;
	}

	public JSONObject getRestaurantDisplay() {
		return RestaurantDisplay;
	}

	public void setRestaurantDisplay(JSONObject restaurantDisplay) {
		RestaurantDisplay = restaurantDisplay;
	}

	public JSONObject getRestaurantDelete() {
		return RestaurantDelete;
	}

	public void setRestaurantDelete(JSONObject restaurantDelete) {
		RestaurantDelete = restaurantDelete;
	}

	public JSONObject getOrderInfoSave() {
		return OrderInfoSave;
	}

	public void setOrderInfoSave(JSONObject orderInfoSave) {
		OrderInfoSave = orderInfoSave;
	}

	public JSONObject getOrderInfoDisplay() {
		return OrderInfoDisplay;
	}

	public void setOrderInfoDisplay(JSONObject orderInfoDisplay) {
		OrderInfoDisplay = orderInfoDisplay;
	}

	public JSONObject getOrderInfoDelete() {
		return OrderInfoDelete;
	}

	public void setOrderInfoDelete(JSONObject orderInfoDelete) {
		OrderInfoDelete = orderInfoDelete;
	}

	

	
	
	
	
	
	
}

package org.wuxi.fudan.syfw.action.transport;

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
import org.wuxi.fudan.syfw.model.hibernate.TransportationInfo;
import org.wuxi.fudan.syfw.service.breed.BreedAreaService;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.breed.BreedStaffService;
import org.wuxi.fudan.syfw.service.breed.FeedInfoService;
import org.wuxi.fudan.syfw.service.process.ProcessInfoService;
import org.wuxi.fudan.syfw.service.sale.OrderInfoService;
import org.wuxi.fudan.syfw.service.transport.TransportationInfoService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class TransportationInfoAction extends ActionSupport{

	/**
	 * 加工企业运输管理，管理运输信息
	 */
	private static final long serialVersionUID = 1L;
	
	private TransportationInfoService transInfoService;
	private OrderInfoService orderInfoService;
	private UserService		 userService;
	private ProcessInfoService processInfoService;
	

	//用户信息
	private String	userId;
	private String	companyId;
	
	//运输信息
	private String transId; 		//运输编号，自动生成
	private RestaurantCompany restaurantCompany; //餐饮企业 （单选）
	private ProcessCompany processCompany; //发货的加工企业（不填，提供登录用户的companyId）
	private String cages;			//运输的成品箱 （多选，返回string用逗号隔开）
	private String product;		//运输的产品
	private Double number;		//运输数量（单位kg）
	private String transPerson;	//运输人
	private String transVehicle;	//运输车辆信息
	private Date transTime;		//运输时间
	private String transMethod;	//运输方式
	private String receiver;		//收货人
	private String toAddress;		//运送地址
	private String receiverPhone;  //联系方式
	private TransportationInfo transInfo;
	
	private String restaurantId; 	//餐厅工商号
	
	
	//delete params
	private String transIds;
	

	
	//response
	private JSONObject OptionalRestaurantList;			//餐厅列表响应
	private JSONObject OptionalCagesList;			//成品箱列表响应
	
	private JSONObject TransInfoSave;				//保存运输记录响应
	private JSONObject TransInfoDisplay;			//显示运输记录响应
	private JSONObject TransInfoDelete;				//删除运输记录响应
	
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
     
     
     //获取可选的成品箱列表
     public String getOptionalCage(){
    	 try {
			processCompany = userService.findProcessCompanyById(companyId);
			 List<ProcessInfo> list = processInfoService.getCagesToTrans(processCompany);
			 OptionalCagesList = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalCagesList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
    
     
   //保存运输记录信息
     public String saveTransInfo(){
    	 try {
    		 transInfo.setProcessCompany(userService.findProcessCompanyById(companyId));
    		 transInfo.setRestaurantCompany(orderInfoService.findRestaurantById(restaurantId));
			if(transInfo.getTransTime()== null){
				transInfo.setTransTime(new Date());
			}
			
			Set<ProcessInfo> processInfos = new HashSet<ProcessInfo>();
			//成品箱保存订单外键
			if((cages = transInfo.getCages() )!= null){
				String[] transCages = cages.split(",");
				for(String transCage: transCages){
					ProcessInfo process = processInfoService.findObjectById(transCage);
					process.setTransportationInfo(transInfo);
					processInfoService.update(process);
					processInfos.add(process);
				}
				transInfo.setProcessInfos(processInfos);
			}
			
			transInfoService.save(transInfo);
			
			 TransInfoSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
	
     //获取本企业下的所有运输记录，加工企业调用
     @SuppressWarnings("unused")
	public String getListTransInfo(){
    	 try {
			Set<TransportationInfo> sets = new HashSet<TransportationInfo>();
			 processCompany = userService.findProcessCompanyById(companyId);
			 sets = processCompany.getTransportationInfos();
			 List<TransportationInfo> list = new ArrayList<TransportationInfo>();
			 list.addAll(sets);
			 int count = list.size();
			 sEcho = count;
			 TransInfoDisplay= JsonUtils.toJSONResult(count, list, sEcho);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransInfoDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除运输记录
     public String deleteTransInfo(){
    	 try {
 			if(transIds != null)
 			{
 				String[] delete_id = transIds.split(",");
 				for(String id: delete_id){
 					transInfoService.delete(id);
 				}
 			}
 			TransInfoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			TransInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
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

	

	public TransportationInfoService getTransInfoService() {
		return transInfoService;
	}

	@Resource
	public void setTransInfoService(TransportationInfoService transInfoService) {
		this.transInfoService = transInfoService;
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

	
	public RestaurantCompany getRestaurantCompany() {
		return restaurantCompany;
	}

	public void setRestaurantCompany(RestaurantCompany restaurantCompany) {
		this.restaurantCompany = restaurantCompany;
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


	public String getTransId() {
		return transId;
	}


	public void setTransId(String transId) {
		this.transId = transId;
	}


	public Double getNumber() {
		return number;
	}


	public void setNumber(Double number) {
		this.number = number;
	}


	public String getTransPerson() {
		return transPerson;
	}


	public void setTransPerson(String transPerson) {
		this.transPerson = transPerson;
	}


	public String getTransVehicle() {
		return transVehicle;
	}


	public void setTransVehicle(String transVehicle) {
		this.transVehicle = transVehicle;
	}


	public Date getTransTime() {
		return transTime;
	}


	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}


	public String getTransMethod() {
		return transMethod;
	}


	public void setTransMethod(String transMethod) {
		this.transMethod = transMethod;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getToAddress() {
		return toAddress;
	}


	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}


	public String getReceiverPhone() {
		return receiverPhone;
	}


	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}


	public TransportationInfo getTransInfo() {
		return transInfo;
	}


	public void setTransInfo(TransportationInfo transInfo) {
		this.transInfo = transInfo;
	}


	public String getTransIds() {
		return transIds;
	}


	public void setTransIds(String transIds) {
		this.transIds = transIds;
	}


	public JSONObject getTransInfoSave() {
		return TransInfoSave;
	}


	public void setTransInfoSave(JSONObject transInfoSave) {
		TransInfoSave = transInfoSave;
	}


	public JSONObject getTransInfoDisplay() {
		return TransInfoDisplay;
	}


	public void setTransInfoDisplay(JSONObject transInfoDisplay) {
		TransInfoDisplay = transInfoDisplay;
	}


	public JSONObject getTransInfoDelete() {
		return TransInfoDelete;
	}


	public void setTransInfoDelete(JSONObject transInfoDelete) {
		TransInfoDelete = transInfoDelete;
	}

	
}

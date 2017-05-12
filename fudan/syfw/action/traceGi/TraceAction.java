package org.wuxi.fudan.syfw.action.traceGi;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.model.hibernate.TransportationInfo;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.process.ProcessInfoService;
import org.wuxi.fudan.syfw.service.sale.OrderInfoService;
import org.wuxi.fudan.syfw.service.traceGi.TraceService;
import org.wuxi.fudan.syfw.service.transport.TransportationInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class TraceAction extends ActionSupport{

	/**
	 * 加工企业运输管理，管理运输信息
	 */
	private static final long serialVersionUID = 1L;
	
	private TransportationInfoService transInfoService;
	private OrderInfoService orderInfoService;
	private BreedLogService breedLogService;
	private ProcessInfoService processInfoService;
	
	private TraceService traceService;
	
	
	//追溯信息
	private String epcis;			//追溯码
	private TransportationInfo transportationInfo; //运输信息
	private String transId; 		//选中后返回运输编号
	private OrderInfo orderInfo;	//订单信息
	private String orderId;			//返回订单号
	private RestaurantCompany restaurantCompany; //餐饮企业 
	//private String restaurantId; 	//餐厅工商号
	//private ProcessCompany processCompany; //发货的加工企业
	private BreedNo breedNo; 	//养殖批次信息
	private String breedId;		//返回的养殖批次
	private String breedCompany;		//养殖企业
	private ProcessInfo processInfo;	//加工记录
	private String cageId;				//返回成品箱编号
	private String processCompany;		//加工企业
	private Date outPondTime;			//出塘时间
	private String restaurant;			//餐饮企业
	private String breedSpecies;		//品种
	private Trace  trace;

	//delete param
	private String epcisIds;
	
	//response
	private JSONObject OptionalOrderList;			//销售列表响应
	private JSONObject OptionalCagesList;			//成品箱列表响应
	private JSONObject OptionalBreedList;			//养殖批次列表响应
	private JSONObject OptionalTransInfoList;			//运输列表响应
	
	private JSONObject TraceInfoSave;				//保存追溯记录响应
	private JSONObject TraceInfoDisplay;			//查询追溯记录响应
	private JSONObject TraceInfoDelete;				//删除追溯记录响应
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	

     //获取可选的养殖批次（单选框的选项）
     public String getOptionalBreedNo(){
    	 List<BreedNo> list = breedLogService.findObjects();
    	
    	 OptionalBreedList = JsonUtils.toJSONResult(true, list);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     } 
     
     //获取可选的成品箱列表
     public String getOptionalCage(){
    	 try {
			
			 List<ProcessInfo> list = processInfoService.findObjects();
			 OptionalCagesList = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalCagesList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
     
   //获取可选的运输批次列表
     public String getOptionalTrans(){
    	 try {
			
			 List<TransportationInfo> list = transInfoService.findObjects();
			 OptionalTransInfoList = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalTransInfoList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
     
   //获取可选的运输批次列表
     public String getOptionalOrder(){
    	 try {
			
			 List<OrderInfo> list = orderInfoService.findObjects();
			 OptionalOrderList = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalOrderList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
    
     
   //保存追溯记录信息,前端传4个ID：breedId, cageId, transId, orderId,组成追溯码
     public String saveTraceInfo(){
    	 try {
    		 breedNo = breedLogService.findObjectById(breedId);    		//养殖批次 
    		 breedSpecies = breedNo.getBreedSpecies();  		    //品种
    		 outPondTime = breedNo.getOutPond().getOutTime(); 		 //出塘时间
    		 breedCompany = breedNo.getNetCage().getBreedArea().getBreedCompany().getCompanyName();
    		 
    		 trace.setBreedNo(breedNo);
    		 trace.setBreedSpecies(breedSpecies);
    		 trace.setOutPondTime(outPondTime);
    		 trace.setBreedCompany(breedCompany);
    		 
    		 processInfo = processInfoService.findObjectById(cageId);   //成品箱
    		 processCompany = processInfo.getProcessCompany().getCompanyName();
    		 
    		 trace.setProcessInfo(processInfo);
    		 trace.setProcessCompany(processCompany);
    		 
    		 transportationInfo = transInfoService.findObjectById(transId);  //运输信息
    		 orderInfo = orderInfoService.findObjectById(orderId);		//销售信息
    		 restaurantCompany = orderInfo.getRestaurantCompany();
    		 restaurant = restaurantCompany.getRestaurantName();     //餐厅
    		 
    		 trace.setTransportationInfo(transportationInfo);
    		 trace.setOrderInfo(orderInfo);
    		 trace.setRestaurant(restaurant);  		 
			
			traceService.save(trace);
	
			 TraceInfoSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TraceInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
	
     //产品溯源
     @SuppressWarnings("unused")
	public String searchByEpcis(){
    	 try {
    		 trace = traceService.findObjectById(epcis);
			
			 TraceInfoDisplay= JsonUtils.toJSONResult(true, trace);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TraceInfoDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除溯源信息
     public String deleteTraceInfo(){
    	 try {
 			if(epcisIds != null)
 			{
 				String[] delete_id = epcisIds.split(",");
 				for(String id: delete_id){
 					traceService.delete(id);
 				}
 			}
 			TraceInfoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			TraceInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
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

	

	public TraceService getTraceService() {
		return traceService;
	}

	@Resource
	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
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

	public BreedLogService getBreedLogService() {
		return breedLogService;
	}


	@Resource
	public void setBreedLogService(BreedLogService breedLogService) {
		this.breedLogService = breedLogService;
	}


	public ProcessInfoService getProcessInfoService() {
		return processInfoService;
	}

	@Resource
	public void setProcessInfoService(ProcessInfoService processInfoService) {
		this.processInfoService = processInfoService;
	}

	public String getEpcis() {
		return epcis;
	}

	public void setEpcis(String epcis) {
		this.epcis = epcis;
	}

	public TransportationInfo getTransportationInfo() {
		return transportationInfo;
	}

	public void setTransportationInfo(TransportationInfo transportationInfo) {
		this.transportationInfo = transportationInfo;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public RestaurantCompany getRestaurantCompany() {
		return restaurantCompany;
	}

	public void setRestaurantCompany(RestaurantCompany restaurantCompany) {
		this.restaurantCompany = restaurantCompany;
	}

	public BreedNo getBreedNo() {
		return breedNo;
	}

	public void setBreedNo(BreedNo breedNo) {
		this.breedNo = breedNo;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

	public String getBreedCompany() {
		return breedCompany;
	}

	public void setBreedCompany(String breedCompany) {
		this.breedCompany = breedCompany;
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}

	public String getCageId() {
		return cageId;
	}

	public void setCageId(String cageId) {
		this.cageId = cageId;
	}

	public String getProcessCompany() {
		return processCompany;
	}

	public void setProcessCompany(String processCompany) {
		this.processCompany = processCompany;
	}

	public Date getOutPondTime() {
		return outPondTime;
	}

	public void setOutPondTime(Date outPondTime) {
		this.outPondTime = outPondTime;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getBreedSpecies() {
		return breedSpecies;
	}

	public void setBreedSpecies(String breedSpecies) {
		this.breedSpecies = breedSpecies;
	}

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	public String getEpcisIds() {
		return epcisIds;
	}

	public void setEpcisIds(String epcisIds) {
		this.epcisIds = epcisIds;
	}

	public JSONObject getOptionalOrderList() {
		return OptionalOrderList;
	}

	public void setOptionalOrderList(JSONObject optionalOrderList) {
		OptionalOrderList = optionalOrderList;
	}

	public JSONObject getOptionalCagesList() {
		return OptionalCagesList;
	}

	public void setOptionalCagesList(JSONObject optionalCagesList) {
		OptionalCagesList = optionalCagesList;
	}

	public JSONObject getOptionalBreedList() {
		return OptionalBreedList;
	}

	public void setOptionalBreedList(JSONObject optionalBreedList) {
		OptionalBreedList = optionalBreedList;
	}

	public JSONObject getOptionalTransInfoList() {
		return OptionalTransInfoList;
	}

	public void setOptionalTransInfoList(JSONObject optionalTransInfoList) {
		OptionalTransInfoList = optionalTransInfoList;
	}

	public JSONObject getTraceInfoSave() {
		return TraceInfoSave;
	}

	public void setTraceInfoSave(JSONObject traceInfoSave) {
		TraceInfoSave = traceInfoSave;
	}

	public JSONObject getTraceInfoDisplay() {
		return TraceInfoDisplay;
	}

	public void setTraceInfoDisplay(JSONObject traceInfoDisplay) {
		TraceInfoDisplay = traceInfoDisplay;
	}

	public JSONObject getTraceInfoDelete() {
		return TraceInfoDelete;
	}

	public void setTraceInfoDelete(JSONObject traceInfoDelete) {
		TraceInfoDelete = traceInfoDelete;
	}




	

	
}

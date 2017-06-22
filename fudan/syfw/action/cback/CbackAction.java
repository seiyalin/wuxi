package org.wuxi.fudan.syfw.action.cback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;











import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.ComplaintInfo;
import org.wuxi.fudan.syfw.model.hibernate.ComplaintReact;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.process.ProcessInfoService;
import org.wuxi.fudan.syfw.service.supervision.SupervisionService;
import org.wuxi.fudan.syfw.service.traceGi.TraceService;
import org.wuxi.fudan.syfw.service.transport.TransportationInfoService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class CbackAction extends ActionSupport{

	/**
	 * 产品召回
	 */
	private static final long serialVersionUID = 1L;

	private TraceService	   traceService;
	
	

	private String[] json_exclude = new String[]{"handler","hibernateLazyInitializer"};
	
	
	/*溯源信息*/
	private String epcis;
	private Trace  trace;
	private String cageId;	//追溯到的成品箱编号
	private String breedId; //追溯到的养殖批次
	private String transId; //追溯到的运输编号
	private String restaurant; //追溯到的餐厅
	
	private String traceIds; //要修改的产品状态
	private String foodStatus; //产品修改后的状态
	
	
	private JSONObject TraceInfoDisplay;   //查看溯源信息响应
	private JSONObject TraceInfoUpdate;   //修改溯源信息响应
	
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	
     //根据追溯码查看供应链
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
     
     //根据成品箱查看所有可能需要召回的最终产品(加工环节有问题）
     public String traceByCage(){
    	 List<Trace> list = traceService.traceByCage(iDisplayStart, iDisplayLength, cageId);
    	 int count = traceService.getCount();
    	 TraceInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho, json_exclude);
    	 return SUCCESS;
     }
     
   //根据养殖批次查看所有可能需要召回的最终产品(养殖环节有问题）
     public String traceByBreed(){
    	 List<Trace> list = traceService.traceByBreed(iDisplayStart, iDisplayLength, breedId);
    	 int count = traceService.getCount();
    	 TraceInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho, json_exclude);
    	 return SUCCESS;
     }
     
   //根据运输编号查看所有可能需要召回的最终产品(运输环节有问题）
     public String traceByTrans(){
    	 List<Trace> list = traceService.traceByTrans(iDisplayStart, iDisplayLength, transId);
    	 int count = traceService.getCount();
    	 TraceInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho, json_exclude);
    	 return SUCCESS;
     }
     
     //根据餐厅查看所有可能需要召回的最终产品(制作环节有问题）
     public String traceByRestaurant(){
    	 List<Trace> list = traceService.traceByRest(iDisplayStart, iDisplayLength, restaurant);
    	 int count = traceService.getCount();
    	 TraceInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho, json_exclude);
    	 return SUCCESS;
     }
   
     //修改产品状态
     public String changeFoodStatus(){
    	 if(traceIds != null){
    		 String[] cid = traceIds.split(",");
    		 for(String epc:cid){
    			 trace = traceService.findObjectById(epc);
    			 trace.setFoodStatus(foodStatus);
    			 traceService.update(trace);
    		 }
    	 }
    	 TraceInfoUpdate = JsonUtils.toJSONResult(true);
    	 return SUCCESS;
    		
     }
     
 	public TraceService getTraceService() {
		return traceService;
	}

 	@Resource
	public void setTraceService(TraceService traceService) {
		this.traceService = traceService;
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

	public Integer getSEcho() {
		return sEcho;
	}

	public void setSEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}
	

	public String getEpcis() {
		return epcis;
	}

	public void setEpcis(String epcis) {
		this.epcis = epcis;
	}

	public Trace getTrace() {
		return trace;
	}

	public void setTrace(Trace trace) {
		this.trace = trace;
	}

	public String getCageId() {
		return cageId;
	}

	public void setCageId(String cageId) {
		this.cageId = cageId;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getTraceIds() {
		return traceIds;
	}

	public void setTraceIds(String traceIds) {
		this.traceIds = traceIds;
	}

	public String getFoodStatus() {
		return foodStatus;
	}

	public void setFoodStatus(String foodStatus) {
		this.foodStatus = foodStatus;
	}

	public JSONObject getTraceInfoDisplay() {
		return TraceInfoDisplay;
	}

	public void setTraceInfoDisplay(JSONObject traceInfoDisplay) {
		TraceInfoDisplay = traceInfoDisplay;
	}

	public JSONObject getTraceInfoUpdate() {
		return TraceInfoUpdate;
	}

	public void setTraceInfoUpdate(JSONObject traceInfoUpdate) {
		TraceInfoUpdate = traceInfoUpdate;
	}

	
	
	
}

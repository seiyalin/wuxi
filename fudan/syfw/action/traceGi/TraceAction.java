package org.wuxi.fudan.syfw.action.traceGi;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.wuxi.fudan.syfw.common.FileUtils2;
import org.wuxi.fudan.syfw.common.GeneralUtils;
import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.CrabGi;
import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.model.hibernate.TransportationInfo;
import org.wuxi.fudan.syfw.model.hibernate.TrepangGi;
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
	
	private String[] json_exclude;
	
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
	private Trace  trace = new Trace();
	
	//crab信息
	private String crabId;		//蟹编号（自动生成）
	private String thumbnail;   //缩略图
	private String type;		   //品种
	private String sex;		//性别
	private String appearance;	//感官特色
	private String crabRoe;	//蟹黄
	/*private Float crudeProtein;	//粗蛋白(百分比）
	private Float crudeFat;		//粗脂肪(百分比）
*/	private String otherDescr;	//其他
	private String crust;		//壳
	private String belly;		//肚（腹）
	private String claw;		//爪
	private String fair;		//毛
	private String clamp;		//钳
	private String back;		//背
	private String paw;		//螯
	private CrabGi crabGi;
	
	//trepang info
	private String trepangId;  //海参编号（自动生成）
	//private String thumbnail;	//缩略图  （name=file）
	private String trepangBrand;	//海参品牌（獐子岛，大连，烟台）
	private String trepangType;	//海参种类（淡干，盐渍，活，干，盐干）
	//private String appearance;	//感官特色
	/*private Float protein;		//蛋白质（百分比）
	private Float water;		//水分（百分比）
	private Float salt;			//盐分（百分比）
	private Float sand;			//含砂量（百分比）
*/	//private String otherDescr;	//其他
	private String color;		//色泽
	private String shape;		//体型
	private TrepangGi trepangGi;
	
	//上传图片
	private File file;
	//提交过来的file的名字
    private String fileFileName;
    //提交过来的file的MIME类型
    private String fileContentType;

	//delete param
	private String epcisIds;
	private String crabIds;
	private String trepangIds;
	
	//response
	private JSONObject OptionalOrderList;			//销售列表响应
	private JSONObject OptionalCagesList;			//成品箱列表响应
	private JSONObject OptionalBreedList;			//养殖批次列表响应
	private JSONObject OptionalTransInfoList;			//运输列表响应
	
	private JSONObject TraceInfoSave;				//保存追溯记录响应
	private JSONObject TraceInfoDisplay;			//查询追溯记录响应
	private JSONObject TraceInfoDelete;				//删除追溯记录响应
	private JSONObject CrabInfoSave;				//保存蟹地理标识响应
	private JSONObject CrabInfoDisplay;			//查询蟹地理标识响应
	private JSONObject CrabInfoDelete;				//删除蟹地理标识响应
	private JSONObject TrepangInfoSave;				//保存海参地理标识响应
	private JSONObject TrepangInfoDisplay;			//查询海参地理标识响应
	private JSONObject TrepangInfoDelete;				//删除海参地理标识响应
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	
     //添加蟹地理标识
     public String addCrabGi(){
     try {
    	  if(GeneralUtils.isNotNull(file)){
    		
				thumbnail = FileUtils2.saveFile(file, fileFileName);
				crabGi.setThumbnail(thumbnail);
			}
    	  traceService.saveCrabGi(crabGi);
    	  CrabInfoSave = JsonUtils.toJSONResult(true);
    	  return SUCCESS;
    	 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				CrabInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
	     		return ERROR;		
		}
    	 
     }
     //蟹地理标识数据库
     public String getAllCrabGi(){
    	 List<CrabGi> list = traceService.getCrab(iDisplayStart, iDisplayLength);
    	 int count = traceService.getCount();
    	 json_exclude = new String[]{"handler","hibernateLazyInitializer"};
		 CrabInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho);
		 return SUCCESS;
     }
     
     //根据type查询蟹地理标识
     public String findCrabByType(){
    	 try {
			List<CrabGi> list = traceService.findCrabByType(type);
			json_exclude = new String[]{"handler","hibernateLazyInitializer"};
			 CrabInfoDisplay = JsonUtils.toJSONResult(true, list, json_exclude);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CrabInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;	
		}
    	 
     }
     
     //根据外观查询蟹地理标识
     public String findCrabByAppearance(){
    	 try {
			Map<String, String> map = new HashMap<String,String>();
			 if(crust != null){
				 map.put("crust", crust);
			 }
			 if(belly != null){
				 map.put("belly", belly);
			 }
			 if(claw != null){
				 map.put("claw", claw);
			 }
			 if(fair != null){
				 map.put("fair", fair);
			 }
			 if(clamp != null){
				 map.put("clamp", clamp);
			 }
			 if(back != null){
				 map.put("back", back);
			 }
			 if(paw != null){
				 map.put("paw", paw);
			 }
			 
			 //List<CrabGi> list = traceService.findCrabByAppearance(map);
			 List<CrabGi> list = traceService.findCrabByAppearance(crust, belly, claw, fair, clamp, back, paw);
			// json_exclude = new String[]{"handler","hibernateLazyInitializer"};
			 CrabInfoDisplay = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CrabInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;	
		}
     }
     
     //删除蟹地理标识
     public String deleteCrab(){
    	 try {
  			if(crabIds != null)
  			{
  				String[] delete_id = crabIds.split(",");
  				for(String id: delete_id){
  					traceService.deleteCrabGi(id);
  				}
  			}
  			CrabInfoDelete = JsonUtils.toJSONResult(true);
  			return SUCCESS;
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			CrabInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
      		return ERROR;		
  		}
     }
     
   //添加海参地理标识
     public String addTrepangGi(){
     try {
    	  if(GeneralUtils.isNotNull(file)){
    		
				thumbnail = FileUtils2.saveFile(file, fileFileName);
				trepangGi.setThumbnail(thumbnail);
			}
    	  traceService.saveTrepangGi(trepangGi);
    	  TrepangInfoSave = JsonUtils.toJSONResult(true);
    	  return SUCCESS;
    	 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TrepangInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
	     		return ERROR;		
		}
    	 
     }
     
   //根据type查询海参地理标识
     public String findAllTrepang(){
    	 try {
			List<TrepangGi> list = traceService.getTrepang(iDisplayStart, iDisplayLength);
			int count = traceService.getCount();
			 TrepangInfoDisplay = JsonUtils.toJSONResult(count, list,  sEcho);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TrepangInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;	
		}
    	 
     }
     
   //根据type查询海参地理标识
     public String findTrepangByType(){
    	 try {
			List<TrepangGi> list = traceService.findTrepangByType(trepangType);
			 TrepangInfoDisplay = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TrepangInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;	
		}
    	 
     }
     
   //根据brand查询海参地理标识
     public String findTrepangByBrand(){
    	 try {
			List<TrepangGi> list = traceService.findTrepangByBrand(trepangBrand);
			 TrepangInfoDisplay = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TrepangInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;	
		}
    	 
     }
     
   //根据type查询海参地理标识
     public String findTrepangByAppearance(){
    	 try {
			List<TrepangGi> list = traceService.findTrepangByAppearance(color, shape);
			 TrepangInfoDisplay = JsonUtils.toJSONResult(true, list);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TrepangInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;	
		}
    	 
     }
     
   //删除蟹地理标识
     public String deleteTrepang(){
    	 try {
  			if(trepangIds != null)
  			{
  				String[] delete_id = trepangIds.split(",");
  				for(String id: delete_id){
  					traceService.deleteTrepangGi(id);
  				}
  			}
  			TrepangInfoDelete = JsonUtils.toJSONResult(true);
  			return SUCCESS;
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			TrepangInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
      		return ERROR;		
  		}
     }

     //获取可选的养殖批次（单选框的选项）
     public String getOptionalBreedNo(){
    	 List<BreedNo> list = breedLogService.findObjects();
    	
    	 json_exclude = new String[]{"handler","hibernateLazyInitializer","breedLogs","illnessInfos","netCage","outPond"
    			 ,"processInfos","qualityControls","vaccineInfos","inPondTime","traces"};
    	 OptionalBreedList = JsonUtils.toJSONResult(true, list, json_exclude);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     } 
     
     //获取可选的成品箱列表
     public String getOptionalCage(){
    	 try {
			
			 List<ProcessInfo> list = processInfoService.findObjects();
			 json_exclude = new String[]{"handler","hibernateLazyInitializer","breedNo","orderInfo","processCompany","transportationInfo"
			 ,"traces"};
			 OptionalCagesList = JsonUtils.toJSONResult(true, list, json_exclude);
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
			 json_exclude = new String[]{"handler","hibernateLazyInitializer","processInfos","restaurantCompany","processCompany","traces"};
			 OptionalTransInfoList = JsonUtils.toJSONResult(true, list, json_exclude);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//json_exclude = new String[]{"handler","hibernateLazyInitializer","processInfos","restaurantCompany","processCompany"};
			OptionalTransInfoList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
     
   //获取可选的运输批次列表
     public String getOptionalOrder(){
    	 try {
			
			 List<OrderInfo> list = orderInfoService.findObjects();
			 json_exclude = new String[]{"handler","hibernateLazyInitializer","processInfos","restaurantCompany","processCompany","traces"};
			 OptionalOrderList = JsonUtils.toJSONResult(true, list, json_exclude);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalOrderList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
    
     
   //保存追溯记录信息,前端传2个ID：breedId, cageId,组成追溯码
     public String saveTraceInfo(){
    	 try {		
    		 
    		 processInfo = processInfoService.findObjectById(cageId);   //成品箱
    		 processCompany = processInfo.getProcessCompany().getCompanyName();
    		 
    		 trace.setProcessInfo(processInfo);
    		 trace.setProcessCompany(processCompany);
    		/* 
    		 if(breedId.equals(processInfo.getBreedNo().getBreedNo())){
    			 breedNo = processInfo.getBreedNo();
    		 }
    		 else{
    			 breedNo = breedLogService.findObjectById(breedId);    		//养殖批次 
    		 }  */  
    		 breedNo = processInfo.getBreedNo();
    		 if(breedNo.getBreedNo().equals(breedId)==false){
    			 TraceInfoSave = JsonUtils.toJSONResult(false, "养殖批次不对！");
    			 return SUCCESS;
    		 }
    		 breedSpecies = breedNo.getBreedSpecies();  		    //品种
    		 outPondTime = breedNo.getOutPond().getOutTime(); 		 //出塘时间
    		 breedCompany = breedNo.getNetCage().getBreedArea().getBreedCompany().getCompanyName();
    		 
    		 trace.setBreedNo(breedNo);
    		 trace.setBreedSpecies(breedSpecies);
    		 trace.setOutPondTime(outPondTime);
    		 trace.setBreedCompany(breedCompany);
    		 
    		/* transportationInfo = transInfoService.findObjectById(transId);  //运输信息
    		 orderInfo = orderInfoService.findObjectById(orderId);		//销售信息
*/    		 transportationInfo = processInfo.getTransportationInfo();
			 orderInfo = processInfo.getOrderInfo();
    		 restaurantCompany = orderInfo.getRestaurantCompany();
    		 restaurant = restaurantCompany.getRestaurantName();     //餐厅
    		 
    		 trace.setTransportationInfo(transportationInfo);
    		 trace.setOrderInfo(orderInfo);
    		 trace.setRestaurant(restaurant);  	
    		 trace.setFoodStatus("正常");
			
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

	public Integer getSEcho() {
		return sEcho;
	}

	public void setSEcho(Integer sEcho) {
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

	public String getCrabId() {
		return crabId;
	}

	public void setCrabId(String crabId) {
		this.crabId = crabId;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public String getCrabRoe() {
		return crabRoe;
	}

	public void setCrabRoe(String crabRoe) {
		this.crabRoe = crabRoe;
	}


	public String getOtherDescr() {
		return otherDescr;
	}

	public void setOtherDescr(String otherDescr) {
		this.otherDescr = otherDescr;
	}

	public String getCrust() {
		return crust;
	}

	public void setCrust(String crust) {
		this.crust = crust;
	}

	public String getBelly() {
		return belly;
	}

	public void setBelly(String belly) {
		this.belly = belly;
	}

	public String getClaw() {
		return claw;
	}

	public void setClaw(String claw) {
		this.claw = claw;
	}

	public String getFair() {
		return fair;
	}

	public void setFair(String fair) {
		this.fair = fair;
	}

	public String getClamp() {
		return clamp;
	}

	public void setClamp(String clamp) {
		this.clamp = clamp;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public String getPaw() {
		return paw;
	}

	public void setPaw(String paw) {
		this.paw = paw;
	}

	public CrabGi getCrabGi() {
		return crabGi;
	}

	public void setCrabGi(CrabGi crabGi) {
		this.crabGi = crabGi;
	}

	public String getTrepangId() {
		return trepangId;
	}

	public void setTrepangId(String trepangId) {
		this.trepangId = trepangId;
	}

	public String getTrepangBrand() {
		return trepangBrand;
	}

	public void setTrepangBrand(String trepangBrand) {
		this.trepangBrand = trepangBrand;
	}

	public String getTrepangType() {
		return trepangType;
	}

	public void setTrepangType(String trepangType) {
		this.trepangType = trepangType;
	}

	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public TrepangGi getTrepangGi() {
		return trepangGi;
	}

	public void setTrepangGi(TrepangGi trepangGi) {
		this.trepangGi = trepangGi;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getCrabIds() {
		return crabIds;
	}

	public void setCrabIds(String crabIds) {
		this.crabIds = crabIds;
	}

	public String getTrepangIds() {
		return trepangIds;
	}

	public void setTrepangIds(String trepangIds) {
		this.trepangIds = trepangIds;
	}

	public JSONObject getCrabInfoSave() {
		return CrabInfoSave;
	}

	public void setCrabInfoSave(JSONObject crabInfoSave) {
		CrabInfoSave = crabInfoSave;
	}

	public JSONObject getCrabInfoDisplay() {
		return CrabInfoDisplay;
	}

	public void setCrabInfoDisplay(JSONObject crabInfoDisplay) {
		CrabInfoDisplay = crabInfoDisplay;
	}

	public JSONObject getCrabInfoDelete() {
		return CrabInfoDelete;
	}

	public void setCrabInfoDelete(JSONObject crabInfoDelete) {
		CrabInfoDelete = crabInfoDelete;
	}

	public JSONObject getTrepangInfoSave() {
		return TrepangInfoSave;
	}

	public void setTrepangInfoSave(JSONObject trepangInfoSave) {
		TrepangInfoSave = trepangInfoSave;
	}

	public JSONObject getTrepangInfoDisplay() {
		return TrepangInfoDisplay;
	}

	public void setTrepangInfoDisplay(JSONObject trepangInfoDisplay) {
		TrepangInfoDisplay = trepangInfoDisplay;
	}

	public JSONObject getTrepangInfoDelete() {
		return TrepangInfoDelete;
	}

	public void setTrepangInfoDelete(JSONObject trepangInfoDelete) {
		TrepangInfoDelete = trepangInfoDelete;
	}

	




	

	
}

package org.wuxi.fudan.syfw.action.breed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.mapping.Array;
import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.WaterQuality;
import org.wuxi.fudan.syfw.service.breed.BreedAreaService;
import org.wuxi.fudan.syfw.service.breed.BreedStaffService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class BreedAreaAction extends ActionSupport{

	/**
	 * 养殖企业的养殖区域管理，养殖人员管理
	 */
	private static final long serialVersionUID = 1L;
	
	private BreedAreaService breedAreaService;
	private UserService		 userService;
	private BreedStaffService breedStaffService;

	//用户信息
	private String	userId;
	private String[] json_exclude;
	
	//养殖区域
	private String areaId;					//养殖区域编号 （填写e.g. breed1）
	private String companyId;				//选择的养殖企业id
	private BreedCompany breedCompany;		//养殖企业 （单选框）
	private String areaName;				//养殖区域名称
	private String breedScale;				//养殖规模
	private String waterQuality;			    //水质
	private String breedEnvironment;		    //养殖环境
	private BreedArea breedArea;				//封装养殖企业信息
	private String netcages;				//添加的网箱

	//水质管理
	private String waterId;  //水检id
	private NetCage netCage;	//检测网箱
	private String color;		//水体颜色
	private Float temperature;	//温度
	private Float ph;			//ph
	private Float oxygen;		//含氧量
	private Date testTime;		//检测时间
	private String personInCharge; //负责人
	private WaterQuality water;

	//网箱
	private String netcageId;					//网箱编号
	
	//养殖人员
	private String idcard;				//身份证号
	private String staffName;			//姓名
	private String breedAreas;			//负责的养殖区域
	private String position;			//职位
	private String breedSpecies;		//负责养殖品种
	private String experience;			//相关资质和养殖经历
	private BreedStaff breedStaff;		//封装养殖人员信息

	//delete param
	private String idcards;					//要删除的养殖人员ID
	private String areaIds;					//要删除的养殖区域ID
	private String waterIds;
	//response
	private JSONObject OptionalCompanyList;			//养殖企业列表响应
	private JSONObject BreedAreaSave;				//保存养殖区域响应
	private JSONObject BreedAreaDisplay;			//显示养殖区域列表响应
	private JSONObject BreedAreaDelete;				//删除养殖区域响应
	private JSONObject BreedAreaLoad;				//获取要修改的养殖区域响应
	private JSONObject BreedAreaUpdate;				//修改养殖区域响应
	private JSONObject BreedStaffSave;				//保存养殖人员响应
	private JSONObject BreedStaffDisplay;			//显示养殖人员列表响应
	private JSONObject BreedStaffDelete;				//删除养殖人员响应
	private JSONObject OptionalNetCageList;			//网箱列表响应
	private JSONObject WaterQualitySave;				//保存养殖人员响应
	private JSONObject WaterQualityDisplay;			//显示养殖人员列表响应
	private JSONObject WaterQualityDelete;				//删除养殖人员响应
	
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	

     //获取可选的养殖企业（单选框的选项）
     public String getOptionalCompany(){
    	 JSONArray breedCompanies = new JSONArray();
    	 JSONObject company = new JSONObject();
    	 List<BreedCompany> list = userService.findAllBreedCompany();
    	 for(BreedCompany breedCompany: list){
    		 company.put("id", breedCompany.getCompanyId());
    		 company.put("name", breedCompany.getCompanyName());
    		 breedCompanies.add(company);
    	 }
    	 json_exclude = new String[]{"handler","hibernateLazyInitializer"};
    	 OptionalCompanyList = JsonUtils.toJSONResult(true,breedCompanies, json_exclude);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     }
     
     //保存养殖区域信息
     public String saveBreedArea(){
    	 breedArea.setBreedCompany(userService.findBreedCompanyById(companyId));  //设置所属养殖企业
    	 Set<NetCage> set = new HashSet<NetCage>();
    	 if(netcages != null){
    		 String[] netcage = netcages.split(",");
    		 for(String net:netcage){
    			 NetCage nc = new NetCage();
    			 nc.setNetcageId(net);
    			 nc.setBreedArea(breedArea);
    			 set.add(nc);
    			 //breedArea.getNetCages().add(nc);
    		 }
    	 }
    	 breedArea.setNetCages(set);   //设置网箱
    	 breedAreaService.save(breedArea); //保存养殖区域，同时级联保存网箱信息
    	 BreedAreaSave = JsonUtils.toJSONResult(true);
    	 return SUCCESS;
    	 
     }
	
     //获取本养殖企业下的所有养殖区域，养殖企业调用
     @SuppressWarnings("unused")
	public String getListBreedArea(){
    	 try {
			Set<BreedArea> sets = new HashSet<BreedArea>();
			 breedCompany = userService.findBreedCompanyById(companyId);
			 sets = breedCompany.getBreedAreas();
			 List<BreedArea> list = new ArrayList<BreedArea>();
			 list.addAll(sets);
			/* Collections.sort(list,new comparator<BreedArea>(){
				 public int compare(BreedArea a, BreedArea b){
					 return a.hashCode().compareTo(b.hashCode());
				 }
			 });*/
			 Collections.sort(list);
			 List<BreedArea> areaList = breedAreaService.getList(list, iDisplayStart, iDisplayLength);
			 int count = breedAreaService.getCount();
			// sEcho = count;
			/* JSONArray areas = new JSONArray();
			 JSONObject area = new JSONObject();
			 for(BreedArea bArea: list){
				 area.put("areaId", bArea.getAreaId());
				 area.put("areaName", bArea.getAreaName());
				 area.put("breedScale", bArea.getBreedScale());
				 area.put("breedEnvironment", bArea.getBreedEnvironment());
				 area.put("waterQuality", bArea.getWaterQuality());
				 areas.add(area);			 
				}*/
			 json_exclude = new String[]{"handler","hibernateLazyInitializer","breedCompany"};
			 setBreedAreaDisplay(JsonUtils.toJSONResult(count, areaList, sEcho));
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setBreedAreaDisplay(JsonUtils.toJSONResult(false, "发生未知错误"));
    		return ERROR;		
		}
    	 
     }
     
     //删除养殖区域
     public String deleteBreedArea(){
    	 try {
 			if(areaIds != null)
 			{
 				String[] delete_id = areaIds.split(",");
 				for(String id: delete_id){
 					breedAreaService.delete(id);  //delete complaint
 				}
 			}
 			BreedAreaDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			BreedAreaDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //获取要修改的养殖区域
     public String loadForUpdate(){
    	 try {
 			breedArea = breedAreaService.findObjectById(areaId);
 			json_exclude = new String[]{"handler","hibernateLazyInitializer","breedCompany"};
 			BreedAreaLoad = JsonUtils.toJSONResult(true, breedArea, json_exclude);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			BreedAreaLoad = JsonUtils.toJSONResult(false, "发生未知错误");
 				
     		return ERROR;		
 		}
     }
     
     //更新养殖区域信息
     public String updateBreedArea(){
    	 try {
    		 if(breedAreaService.findObjectById(breedArea.getAreaId())!=null){
    			 breedAreaService.update(breedArea);
    		 }
			
			BreedAreaUpdate = JsonUtils.toJSONResult(true);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BreedAreaUpdate = JsonUtils.toJSONResult(false, "发生未知错误");			
     		return ERROR;	
		}
     }
     
     //添加养殖人员
     public String saveBreedStaff(){
    	 try {
			breedStaff.setBreedCompany(userService.findBreedCompanyById(companyId));
			breedStaffService.save(breedStaff);
			BreedStaffSave = JsonUtils.toJSONResult(true);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BreedStaffSave = JsonUtils.toJSONResult(false, "发生未知错误");			
     		return ERROR;	
		}
    	 
     }
     
     //显示本企业所有养殖人员信息
     public String getListBreedStaff(){
    	 try {
 			Set<BreedStaff> sets = new HashSet<BreedStaff>();
 			breedCompany = userService.findBreedCompanyById(companyId);
 			sets = breedCompany.getBreedStaffs();
 			List<BreedStaff> list = new ArrayList<BreedStaff>();
 			list.addAll(sets);
 			
 			Collections.sort(list);
			List<BreedStaff> staffList = breedStaffService.getList(list, iDisplayStart, iDisplayLength);
			int count = breedStaffService.getCount();
			 
 			//sEcho = count;
			json_exclude = new String[]{"handler","hibernateLazyInitializer","breedCompany","breedLogs"};
 			setBreedStaffDisplay(JsonUtils.toJSONResult(count, staffList, sEcho, json_exclude));
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			setBreedStaffDisplay(JsonUtils.toJSONResult(false, "发生未知错误"));
     		return ERROR;		
 		}
    	 
     }
     
     //删除养殖人员
     public String deleteBreedStaff(){
    	 try {
  			if(idcards != null)
  			{
  				String[] delete_id = idcards.split(",");
  				for(String id: delete_id){
  					breedStaffService.delete(id);  //delete complaint
  				}
  			}
  			BreedStaffDelete = JsonUtils.toJSONResult(true);
  			return SUCCESS;
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			BreedStaffDelete = JsonUtils.toJSONResult(false, "发生未知错误");
      		return ERROR;		
  		}
     }

     //获取可选的网箱列表
     public String getOptionalNetCageByArea(){
    	 breedArea = breedAreaService.findObjectById(areaId);
    	 Set<NetCage> set = breedArea.getNetCages();
    	 json_exclude = new String[]{"handler","hibernateLazyInitializer","breedArea","breedNos","waterQualities"};
    	 OptionalNetCageList = JsonUtils.toJSONResult(true,set, json_exclude);
    	 return SUCCESS;
     
     }
     
     //添加水质信息
     public String saveWaterQuality(){
    	 try {
    		 water.setNetCage(breedAreaService.findNetCageById(netcageId));
    		 if(water.getTestTime()==null){
    			 water.setTestTime(new Date());
    		 }
    		 /*waterId = UUID.randomUUID().toString();
    		 water.setWaterId(waterId);*/
    		 breedAreaService.saveWaterQuality(water);
			
    		 WaterQualitySave = JsonUtils.toJSONResult(true);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WaterQualitySave = JsonUtils.toJSONResult(false, "发生未知错误");			
     		return ERROR;	
		}
    	 
     }
     
     //显示本网箱所有水质信息
     public String getListWaterQualityByNetCage(){
    	 try {
 			Set<WaterQuality> sets = new HashSet<WaterQuality>();
 			netCage = breedAreaService.findNetCageById(netcageId);
 			
 			sets = netCage.getWaterQualities();
 			List<WaterQuality> list = new ArrayList<WaterQuality>();
 			list.addAll(sets);
 			Collections.sort(list);
 			json_exclude = new String[]{"handler","hibernateLazyInitializer","netCage"};
 			WaterQualityDisplay = JsonUtils.toJSONResult(true,list, json_exclude);
 			
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			WaterQualityDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
    	 
     }
     
     //显示本区域所有水质信息
     public String getListWaterQualityByArea(){
    	 try {
 			
 			breedArea = breedAreaService.findObjectById(areaId);
 			Set<NetCage> sets = breedArea.getNetCages();
 			List<WaterQuality> wq = new ArrayList<WaterQuality>();
 			for(NetCage net:sets){
 				Set<WaterQuality> set = net.getWaterQualities();
 				wq.addAll(set);
 			}
 			Collections.sort(wq);
 			json_exclude = new String[]{"handler","hibernateLazyInitializer","netCage"};
 			WaterQualityDisplay = JsonUtils.toJSONResult(true, wq, json_exclude);
 			
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			WaterQualityDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
    	 
     }
     
     //删除水质信息
     public String deleteWaterQuality(){
    	 try {
  			if(waterIds != null)
  			{
  				String[] delete_id = waterIds.split(",");
  				for(String id: delete_id){
  					breedAreaService.deleteWaterQuality(id);
  				}
  			}
  			WaterQualityDelete = JsonUtils.toJSONResult(true);
  			return SUCCESS;
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			WaterQualityDelete = JsonUtils.toJSONResult(false, "发生未知错误");
      		return ERROR;		
  		}
     }
     
     
     
     public BreedAreaService getBreedAreaService() {
 		return breedAreaService;
 	}

     @Resource
 	public void setBreedAreaService(BreedAreaService breedAreaService) {
 		this.breedAreaService = breedAreaService;
 	}


 	public UserService getUserService() {
 		return userService;
 	}

 	@Resource
 	public void setUserService(UserService userService) {
 		this.userService = userService;
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

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBreedScale() {
		return breedScale;
	}

	public void setBreedScale(String breedScale) {
		this.breedScale = breedScale;
	}

	public String getWaterQuality() {
		return waterQuality;
	}

	public void setWaterQuality(String waterQuality) {
		this.waterQuality = waterQuality;
	}

	public String getNetcageId() {
		return netcageId;
	}

	public void setNetcageId(String netcageId) {
		this.netcageId = netcageId;
	}

	public String getBreedEnvironment() {
		return breedEnvironment;
	}

	public void setBreedEnvironment(String breedEnvironment) {
		this.breedEnvironment = breedEnvironment;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public JSONObject getOptionalCompanyList() {
		return OptionalCompanyList;
	}



	public void setOptionalCompanyList(JSONObject optionalCompanyList) {
		OptionalCompanyList = optionalCompanyList;
	}



	public String getCompanyId() {
		return companyId;
	}



	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getNetcages() {
		return netcages;
	}

	public void setNetcages(String netcages) {
		this.netcages = netcages;
	}

	public JSONObject getBreedAreaSave() {
		return BreedAreaSave;
	}

	public void setBreedAreaSave(JSONObject breedAreaSave) {
		BreedAreaSave = breedAreaSave;
	}

	public JSONObject getBreedAreaDisplay() {
		return BreedAreaDisplay;
	}

	public void setBreedAreaDisplay(JSONObject breedAreaDisplay) {
		BreedAreaDisplay = breedAreaDisplay;
	}

	public JSONObject getBreedAreaDelete() {
		return BreedAreaDelete;
	}

	public void setBreedAreaDelete(JSONObject breedAreaDelete) {
		BreedAreaDelete = breedAreaDelete;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public JSONObject getBreedAreaLoad() {
		return BreedAreaLoad;
	}

	public void setBreedAreaLoad(JSONObject breedAreaLoad) {
		BreedAreaLoad = breedAreaLoad;
	}

	public JSONObject getBreedAreaUpdate() {
		return BreedAreaUpdate;
	}

	public void setBreedAreaUpdate(JSONObject breedAreaUpdate) {
		BreedAreaUpdate = breedAreaUpdate;
	}

	public BreedStaffService getBreedStaffService() {
		return breedStaffService;
	}

	@Resource
	public void setBreedStaffService(BreedStaffService breedStaffService) {
		this.breedStaffService = breedStaffService;
	}

	public BreedCompany getBreedCompany() {
		return breedCompany;
	}

	public void setBreedCompany(BreedCompany breedCompany) {
		this.breedCompany = breedCompany;
	}

	public BreedArea getBreedArea() {
		return breedArea;
	}

	public void setBreedArea(BreedArea breedArea) {
		this.breedArea = breedArea;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getBreedAreas() {
		return breedAreas;
	}

	public void setBreedAreas(String breedAreas) {
		this.breedAreas = breedAreas;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getBreedSpecies() {
		return breedSpecies;
	}

	public void setBreedSpecies(String breedSpecies) {
		this.breedSpecies = breedSpecies;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public BreedStaff getBreedStaff() {
		return breedStaff;
	}

	public void setBreedStaff(BreedStaff breedStaff) {
		this.breedStaff = breedStaff;
	}

	public JSONObject getBreedStaffSave() {
		return BreedStaffSave;
	}

	public void setBreedStaffSave(JSONObject breedStaffSave) {
		BreedStaffSave = breedStaffSave;
	}

	public JSONObject getBreedStaffDisplay() {
		return BreedStaffDisplay;
	}

	public void setBreedStaffDisplay(JSONObject breedStaffDisplay) {
		BreedStaffDisplay = breedStaffDisplay;
	}

	public String getIdcards() {
		return idcards;
	}

	public void setIdcards(String idcards) {
		this.idcards = idcards;
	}

	public JSONObject getBreedStaffDelete() {
		return BreedStaffDelete;
	}

	public void setBreedStaffDelete(JSONObject breedStaffDelete) {
		BreedStaffDelete = breedStaffDelete;
	}

	public String getWaterId() {
		return waterId;
	}

	public void setWaterId(String waterId) {
		this.waterId = waterId;
	}

	public NetCage getNetCage() {
		return netCage;
	}

	public void setNetCage(NetCage netCage) {
		this.netCage = netCage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public Float getPh() {
		return ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	public Float getOxygen() {
		return oxygen;
	}

	public void setOxygen(Float oxygen) {
		this.oxygen = oxygen;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public WaterQuality getWater() {
		return water;
	}

	public void setWater(WaterQuality water) {
		this.water = water;
	}

	public String getWaterIds() {
		return waterIds;
	}

	public void setWaterIds(String waterIds) {
		this.waterIds = waterIds;
	}

	public JSONObject getOptionalNetCageList() {
		return OptionalNetCageList;
	}

	public void setOptionalNetCageList(JSONObject optionalNetCageList) {
		OptionalNetCageList = optionalNetCageList;
	}

	public JSONObject getWaterQualitySave() {
		return WaterQualitySave;
	}

	public void setWaterQualitySave(JSONObject waterQualitySave) {
		WaterQualitySave = waterQualitySave;
	}

	public JSONObject getWaterQualityDisplay() {
		return WaterQualityDisplay;
	}

	public void setWaterQualityDisplay(JSONObject waterQualityDisplay) {
		WaterQualityDisplay = waterQualityDisplay;
	}

	public JSONObject getWaterQualityDelete() {
		return WaterQualityDelete;
	}

	public void setWaterQualityDelete(JSONObject waterQualityDelete) {
		WaterQualityDelete = waterQualityDelete;
	}


	
	
	
	
}

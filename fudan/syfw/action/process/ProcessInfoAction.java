package org.wuxi.fudan.syfw.action.process;

import java.util.ArrayList;
import java.util.Collections;
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
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessStaff;
import org.wuxi.fudan.syfw.service.breed.BreedAreaService;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.breed.BreedStaffService;
import org.wuxi.fudan.syfw.service.breed.FeedInfoService;
import org.wuxi.fudan.syfw.service.process.ProcessInfoService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class ProcessInfoAction extends ActionSupport{

	/**
	 * 加工企业人员管理，加工信息管理
	 */
	private static final long serialVersionUID = 1L;
	
	private ProcessInfoService processInfoService;
	private UserService		 userService;
	private BreedLogService  breedLogService;
	
	private String[] json_exclude;

	//用户信息
	private String	userId;
	private String	companyId;
	
	//加工人员信息
	private String idcard;					//身份证号
	private ProcessCompany processCompany; //所属加工企业（单选）
	private String pcompanyId;				//选中后返回的加工企业id
	private String staffName;				//姓名
	private String position;				//职位
	private String health;					//健康情况
	private String experience;				//培训经验
	private ProcessStaff processStaff;

	//加工记录
	private String cageId; 		//成品箱编号 （填写e.g. cage1）
	private BreedNo breedNo;		//养殖批次 （单选）
	private String breedId;			//选中后返回的产品批次
	private Date processTime;		//加工日期
	private String personInCharge; //负责人
	private ProcessInfo processInfo;
	
	//delete params
	private String idcards;
	private String cageIds;

	
	//response
	private JSONObject OptionalCompanyList;			//加工企业列表响应
	private JSONObject OptionalBreedNoList;			//养殖批次列表响应
	private JSONObject ProcessStaffSave;				//保存加工人员响应
	private JSONObject ProcessStaffDisplay;			//显示加工人员响应
	private JSONObject ProcessStaffDelete;				//删除加工人员响应
	
	private JSONObject ProcessInfoSave;				//保存加工记录响应
	private JSONObject ProcessInfoDisplay;			//显示加工记录响应
	private JSONObject ProcessInfoDelete;				//删除加工记录响应
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	

     //获取可选的养殖企业（单选框的选项）
     public String getOptionalCompany(){
    	 JSONArray processCompanies = new JSONArray();
    	 JSONObject company = new JSONObject();
    	 List<ProcessCompany> list = userService.findAllProcessCompany();
    	 for(ProcessCompany processCompany: list){
    		 company.put("id", processCompany.getCompanyId());
    		 company.put("name", processCompany.getCompanyName());
    		 processCompanies.add(company);
    	 }
    	 json_exclude = new String[]{"handler","hibernateLazyInitializer"};
    	 OptionalCompanyList = JsonUtils.toJSONResult(true,processCompanies, json_exclude);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     }
     
     //保存加工人员信息
     public String saveProcessStaff(){
    	 try {
			processStaff.setProcessCompany(userService.findProcessCompanyById(pcompanyId));
			
			 processInfoService.saveProcessStaff(processStaff);
			
			 ProcessStaffSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ProcessStaffSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
	
     //获取本企业下的所有加工人员，加工企业调用
   
	public String getListProcessStaff(){
    	 try {
			//Set<ProcessStaff> sets = new HashSet<ProcessStaff>();
			 processCompany = userService.findProcessCompanyById(companyId);
			 /*sets = processCompany.getProcessStaffs();
			 List<ProcessStaff> list = new ArrayList<ProcessStaff>();
			 list.addAll(sets);
			 Collections.sort(list);
			 List<ProcessStaff> staffList = processInfoService.getList(list, iDisplayStart,iDisplayLength);*/
			 List<ProcessStaff> list = processInfoService.getProcessStaff(iDisplayStart, iDisplayLength, processCompany);
			 int count = processInfoService.getCount();
			
			 json_exclude = new String[]{"handler","hibernateLazyInitializer","processCompany"};
			 ProcessStaffDisplay= JsonUtils.toJSONResult(count, list, sEcho, json_exclude);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ProcessStaffDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除加工人员
     public String deleteProcessStaff(){
    	 try {
 			if(idcards != null)
 			{
 				String[] delete_id = idcards.split(",");
 				for(String id: delete_id){
 					processInfoService.deleteProcessStaff(id);
 				}
 			}
 			ProcessStaffDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			ProcessStaffDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //获取可选的养殖批次
     public String getOptionalBreed(){
    	 try {
			List<BreedNo> list = breedLogService.findObjects();
			json_exclude = new String[]{"handler","hibernateLazyInitializer","breedLogs","Netcage","illnessInfos",
	    			 "netCage", "inPondTime", "inPondNumber", "outPond","processInfos","qualityControls","vaccineInfos","traces" };
			 OptionalBreedNoList = JsonUtils.toJSONResult(true, list, json_exclude);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OptionalBreedNoList = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
     
   //保存加工记录信息
     public String saveProcessInfo(){
    	 try {
			processInfo.setProcessCompany(userService.findProcessCompanyById(pcompanyId));
			processInfo.setBreedNo(breedLogService.findObjectById(breedId));
			if(processInfo.getProcessTime() == null){
				processInfo.setProcessTime(new Date());
			}
			 processInfoService.save(processInfo);
			
			 ProcessInfoSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ProcessInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
	
     //获取本企业下的所有加工记录，加工企业调用
 
	public String getListProcessInfo(){
    	 try {
		//	Set<ProcessInfo> sets = new HashSet<ProcessInfo>();
			 processCompany = userService.findProcessCompanyById(companyId);
			/* sets = processCompany.getProcessInfos();
			 List<ProcessInfo> list = new ArrayList<ProcessInfo>();
			 list.addAll(sets);
			 Collections.sort(list);
			 List<ProcessInfo> infoList = processInfoService.getInfoList(list, iDisplayStart, iDisplayLength);*/
    		 List<ProcessInfo> infoList = processInfoService.getProcessCage(iDisplayStart, iDisplayLength, processCompany);
			 int count = processInfoService.getCount();
			// sEcho = count;
			 json_exclude = new String[]{"handler","hibernateLazyInitializer","orderInfo","processCompany","transportationInfo" };
			 ProcessInfoDisplay= JsonUtils.toJSONResult(count, infoList, sEcho, json_exclude);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ProcessInfoDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除加工记录
     public String deleteProcessInfo(){
    	 try {
 			if(cageIds != null)
 			{
 				String[] delete_id = cageIds.split(",");
 				for(String id: delete_id){
 					processInfoService.delete(id);
 				}
 			}
 			ProcessInfoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			ProcessInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
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

	public BreedLogService getBreedLogService() {
		return breedLogService;
	}

	@Resource
	public void setBreedLogService(BreedLogService breedLogService) {
		this.breedLogService = breedLogService;
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

	
	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	
	public JSONObject getOptionalCompanyList() {
		return OptionalCompanyList;
	}

	public void setOptionalCompanyList(JSONObject optionalCompanyList) {
		OptionalCompanyList = optionalCompanyList;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public ProcessCompany getProcessCompany() {
		return processCompany;
	}

	public void setProcessCompany(ProcessCompany processCompany) {
		this.processCompany = processCompany;
	}

	public String getPcompanyId() {
		return pcompanyId;
	}

	public void setPcompanyId(String pcompanyId) {
		this.pcompanyId = pcompanyId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public ProcessStaff getProcessStaff() {
		return processStaff;
	}

	public void setProcessStaff(ProcessStaff processStaff) {
		this.processStaff = processStaff;
	}

	public String getCageId() {
		return cageId;
	}

	public void setCageId(String cageId) {
		this.cageId = cageId;
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

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}

	public String getIdcards() {
		return idcards;
	}

	public void setIdcards(String idcards) {
		this.idcards = idcards;
	}

	public String getCageIds() {
		return cageIds;
	}

	public void setCageIds(String cageIds) {
		this.cageIds = cageIds;
	}

	public JSONObject getOptionalBreedNoList() {
		return OptionalBreedNoList;
	}

	public void setOptionalBreedNoList(JSONObject optionalBreedNoList) {
		OptionalBreedNoList = optionalBreedNoList;
	}

	public JSONObject getProcessStaffSave() {
		return ProcessStaffSave;
	}

	public void setProcessStaffSave(JSONObject processStaffSave) {
		ProcessStaffSave = processStaffSave;
	}

	public JSONObject getProcessStaffDisplay() {
		return ProcessStaffDisplay;
	}

	public void setProcessStaffDisplay(JSONObject processStaffDisplay) {
		ProcessStaffDisplay = processStaffDisplay;
	}

	public JSONObject getProcessStaffDelete() {
		return ProcessStaffDelete;
	}

	public void setProcessStaffDelete(JSONObject processStaffDelete) {
		ProcessStaffDelete = processStaffDelete;
	}

	public JSONObject getProcessInfoSave() {
		return ProcessInfoSave;
	}

	public void setProcessInfoSave(JSONObject processInfoSave) {
		ProcessInfoSave = processInfoSave;
	}

	public JSONObject getProcessInfoDisplay() {
		return ProcessInfoDisplay;
	}

	public void setProcessInfoDisplay(JSONObject processInfoDisplay) {
		ProcessInfoDisplay = processInfoDisplay;
	}

	public JSONObject getProcessInfoDelete() {
		return ProcessInfoDelete;
	}

	public void setProcessInfoDelete(JSONObject processInfoDelete) {
		ProcessInfoDelete = processInfoDelete;
	}

	
	
	
	
	
	
}

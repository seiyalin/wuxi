package org.wuxi.fudan.syfw.action.supervision;

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
import org.wuxi.fudan.syfw.service.supervision.SupervisionService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class SupervisionAction extends ActionSupport{

	/**
	 * 投诉，监管管理
	 */
	private static final long serialVersionUID = 1L;

	private SupervisionService supervisionService;
	private UserService userService;
	
	private String companyId;
	private String userId;
	
	/* 投诉信息*/
	private String complaintId;				//投诉编号，自动生成
	private String complaintCompany; 	    //投诉公司
	private String complaintContent;	    //投诉内容 
	private Date   complaintTime;		    //投诉时间，自动生成
	private String complaintant;		    //投诉人
	private String complaintPhone;			//投诉人电话
	private String complaintStatus;	    	//投诉状态
	private String complaintRole;		    //投诉人角色
	private ComplaintInfo complaintInfo;	//封装投诉信息

	/*投诉反馈*/
	private String reactId;					//反馈编号
	private String complaintAnswer;			//投诉反馈
	private Date   answerTime;				//回复时间，自动生成
	private String answerPerson;			//回复人
	private ComplaintReact complaintReact;  //封装投诉反馈信息
	
	private JSONObject companyList;   //所有企业
	private JSONObject ComplaintInfoSave;   //保存投诉信息响应
	private JSONObject ComplaintInfoLoad;   //获取投诉信息响应
	private JSONObject ComplaintReactSave;  //保存投诉反馈响应
	
	private String complaintIds;			//要删除的投诉信息id
	private JSONObject ComplaintInfoDelete;  //删除投诉响应
	private String reactIds;			//要删除的投诉回复id
	private JSONObject ComplaintReactDelete;  //删除投诉回复响应
	
	private JSONObject ComplaintInfoDisplay;   //显示投诉响应
	private JSONObject ComplaintReactDisplay;   //显示投诉回复响应
	
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	
     //获取所有企业
     public String getAllCompany(){
    	 JSONArray companies = new JSONArray();
    	 JSONObject company = new JSONObject();
    	 List<BreedCompany> breed = userService.findAllBreedCompany();
    	 for(BreedCompany bc : breed){
    		 company.put("companyId", bc.getCompanyId());
    		 company.put("companyName", bc.getCompanyName());
    		 companies.add(company);
    	}
    	 
    	 List<ProcessCompany> process = userService.findAllProcessCompany();
    	 for(ProcessCompany pc: process){
    		 company.put("companyId", pc.getCompanyId());
    		 company.put("companyName", pc.getCompanyName());
    		 companies.add(company); 
    	 }
    	 
    	 companyList = JsonUtils.toJSONResult(true, companies);
    	 return SUCCESS;
    	 
     }

	//提交投诉信息
	public String addComplaintInfo(){		
		try {
			if(complaintInfo.getComplaintTime()==null)
				complaintInfo.setComplaintTime(new Date()); //获取时间
			if(companyId != null){
				BreedCompany bc = userService.findBreedCompanyById(companyId);
				if(bc != null)
					complaintInfo.setUserId(bc.getUserId());
				else{
					ProcessCompany pc = userService.findProcessCompanyById(companyId);
					complaintInfo.setUserId(pc.getUserId());
				}
			}
			supervisionService.save(complaintInfo);  	//储存投诉信息 
			ComplaintInfoSave = JsonUtils.toJSONResult(true);
			return SUCCESS;
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
				
    		return ERROR;				
			}			
			
	}
	
	//查看本企业收到的投诉
	public String complaintAccept(){
		try {
			List<ComplaintInfo> list = supervisionService.getList(iDisplayStart, iDisplayLength, companyId);
			int count = supervisionService.getCount();
			ComplaintInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
	}
	
	//查看本用户发起的投诉
		public String complaintLaunch(){
			try {
				if(userId==null){
					BreedCompany bc = userService.findBreedCompanyById(companyId);
					if(bc != null)
						userId = bc.getUserId();
					else{
						ProcessCompany pc = userService.findProcessCompanyById(companyId);
						userId = pc.getUserId();
					}
				}
					
				List<ComplaintInfo> list = supervisionService.getLaunchedComplaint(iDisplayStart, iDisplayLength, userId);
				int count = supervisionService.getCount();
				ComplaintInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho);
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ComplaintInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
	    		return ERROR;		
			}
		}
	
	//回复投诉前获取要回复的投诉
	public String loadForComplaint(){
		try {
			complaintInfo = supervisionService.findObjectById(complaintId);
			ComplaintInfoLoad = JsonUtils.toJSONResult(true, complaintInfo);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintInfoLoad = JsonUtils.toJSONResult(false, "发生未知错误");
				
    		return ERROR;		
		}
	}
	
	//获取某个投诉对应的所有回复
	public String displayComplaintReact(){
		try {
			complaintInfo = supervisionService.findObjectById(complaintId);
			Set<ComplaintReact> set = complaintInfo.getComplaintReacts();
			List<ComplaintReact> list = new ArrayList<ComplaintReact>();
			list.addAll(set);
			//int count = list.size();
			//sEcho = count;
			ComplaintReactDisplay = JsonUtils.toJSONResult(true, list);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintReactDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		
		}
	}
	
	
	//提交投诉反馈
	public String addComplaintReact(){
		try {
			if(complaintReact.getAnswerTime()==null)
				complaintReact.setAnswerTime(new Date()); 		 //回复时间
			complaintInfo = supervisionService.findObjectById(complaintId);
			complaintReact.setComplaintInfo(complaintInfo);  //回复的投诉内容
			supervisionService.addComplaintReact(complaintReact);
			ComplaintReactSave = JsonUtils.toJSONResult(true);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintReactSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;				
		}
	}
	
	//delete complaints
	public String deleteComplaintInfo(){
		try {
			if(complaintIds != null)
			{
				String[] delete_id = complaintIds.split(",");
				for(String id: delete_id){
					supervisionService.delete(id);  //delete complaint
				}
			}
			setComplaintInfoDelete(JsonUtils.toJSONResult(true));
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
		
	}
	
	//delete complaint reactions
	public String deleteComplaintReact(){
		try {
			if(reactIds != null){
				String[] delete_id = complaintIds.split(",");
				for(String id: delete_id){
					supervisionService.deleteComplaintReact(id);  //delete complaint reaction
				}
			}
			ComplaintReactDelete = JsonUtils.toJSONResult(true);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintReactDelete = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
	}
	
	//get complaintInfo list
	public String getComplaintInfoList(){
		try {
			List<ComplaintInfo> list = supervisionService.getList(iDisplayStart, iDisplayLength);
			int count = supervisionService.getCount();
			ComplaintInfoDisplay = JsonUtils.toJSONResult(count, list, sEcho);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ComplaintInfoDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
		
	}


	public SupervisionService getSupervisionService() {
		return supervisionService;
	}


	@Resource
	public void setSupervisionService(SupervisionService supervisionService) {
		this.supervisionService = supervisionService;
	}


	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getComplaintId() {
		return complaintId;
	}


	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}


	public String getComplaintCompany() {
		return complaintCompany;
	}


	public void setComplaintCompany(String complaintCompany) {
		this.complaintCompany = complaintCompany;
	}


	public String getComplaintContent() {
		return complaintContent;
	}


	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}


	public Date getComplaintTime() {
		return complaintTime;
	}


	public void setComplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}


	public String getComplaintant() {
		return complaintant;
	}


	public void setComplaintant(String complaintant) {
		this.complaintant = complaintant;
	}


	public String getComplaintPhone() {
		return complaintPhone;
	}


	public void setComplaintPhone(String complaintPhone) {
		this.complaintPhone = complaintPhone;
	}


	public String getComplaintStatus() {
		return complaintStatus;
	}


	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}


	public String getComplaintRole() {
		return complaintRole;
	}


	public void setComplaintRole(String complaintRole) {
		this.complaintRole = complaintRole;
	}


	public ComplaintInfo getComplaintInfo() {
		return complaintInfo;
	}


	public void setComplaintInfo(ComplaintInfo complaintInfo) {
		this.complaintInfo = complaintInfo;
	}


	public String getReactId() {
		return reactId;
	}


	public void setReactId(String reactId) {
		this.reactId = reactId;
	}


	public String getComplaintAnswer() {
		return complaintAnswer;
	}


	public void setComplaintAnswer(String complaintAnswer) {
		this.complaintAnswer = complaintAnswer;
	}


	public Date getAnswerTime() {
		return answerTime;
	}


	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}


	public String getAnswerPerson() {
		return answerPerson;
	}


	public void setAnswerPerson(String answerPerson) {
		this.answerPerson = answerPerson;
	}


	public ComplaintReact getComplaintReact() {
		return complaintReact;
	}


	public void setComplaintReact(ComplaintReact complaintReact) {
		this.complaintReact = complaintReact;
	}


	public JSONObject getComplaintInfoSave() {
		return ComplaintInfoSave;
	}


	public void setComplaintInfoSave(JSONObject complaintInfoSave) {
		ComplaintInfoSave = complaintInfoSave;
	}


	public JSONObject getComplaintReactSave() {
		return ComplaintReactSave;
	}


	public void setComplaintReactSave(JSONObject complaintReactSave) {
		ComplaintReactSave = complaintReactSave;
	}

	public JSONObject getComplaintInfoLoad() {
		return ComplaintInfoLoad;
	}

	public void setComplaintInfoLoad(JSONObject complaintInfoLoad) {
		ComplaintInfoLoad = complaintInfoLoad;
	}

	public String getComplaintIds() {
		return complaintIds;
	}

	public void setComplaintIds(String complaintIds) {
		this.complaintIds = complaintIds;
	}

	public JSONObject getComplaintInfoDelete() {
		return ComplaintInfoDelete;
	}

	public void setComplaintInfoDelete(JSONObject complaintInfoDelete) {
		ComplaintInfoDelete = complaintInfoDelete;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public JSONObject getComplaintInfoDisplay() {
		return ComplaintInfoDisplay;
	}

	public void setComplaintInfoDisplay(JSONObject complaintInfoDisplay) {
		ComplaintInfoDisplay = complaintInfoDisplay;
	}

	public JSONObject getComplaintReactDisplay() {
		return ComplaintReactDisplay;
	}

	public void setComplaintReactDisplay(JSONObject complaintReactDisplay) {
		ComplaintReactDisplay = complaintReactDisplay;
	}

	public JSONObject getComplaintReactDelete() {
		return ComplaintReactDelete;
	}

	public void setComplaintReactDelete(JSONObject complaintReactDelete) {
		ComplaintReactDelete = complaintReactDelete;
	}

	public String getReactIds() {
		return reactIds;
	}

	public void setReactIds(String reactIds) {
		this.reactIds = reactIds;
	}

	public JSONObject getCompanyList() {
		return companyList;
	}

	public void setCompanyList(JSONObject companyList) {
		this.companyList = companyList;
	}
	
	
}

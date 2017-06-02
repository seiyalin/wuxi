package org.wuxi.fudan.syfw.action.breed;

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
import org.wuxi.fudan.syfw.model.hibernate.BreedLog;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.FeedInfo;
import org.wuxi.fudan.syfw.model.hibernate.IllnessInfo;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.OutPond;
import org.wuxi.fudan.syfw.model.hibernate.QualityControl;
import org.wuxi.fudan.syfw.model.hibernate.VaccineInfo;
import org.wuxi.fudan.syfw.service.breed.BreedAreaService;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.breed.BreedStaffService;
import org.wuxi.fudan.syfw.service.breed.FeedInfoService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class BreedLogAction extends ActionSupport{

	/**
	 * 养殖企业的养殖区域管理，养殖人员管理
	 */
	private static final long serialVersionUID = 1L;
	
	private BreedLogService breedLogService;
	private BreedAreaService breedAreaService;
	private FeedInfoService feedInfoService;

	private UserService		 userService;
	

	//用户信息
	private String	userId;
	private String	companyId;
	private String  areaId;    //养殖区域id
	
	private BreedCompany breedCompany;		//养殖企业
	private BreedArea    breedArea;			//养殖区域
	
	//产品批号信息
	private String 		breedNo;        //养殖批次 （填写e.g. breedNo1）
	private NetCage 	netCage;		//网箱  （单选）
	private String 		netCageId;
	private Date 		inPondTime;		//入塘时间
	private String 		inPondNumber;	//投苗数量
	private String 		inPondMethod;	//投苗方式
	private String 		breedPerson;	   //投苗人
	private String 		breedSpecies;	   //投苗品种 
	private BreedNo 	breedBatch;    //封装产品批次信息
	
	//产品喂养信息
	private String  	logId;			// 日志编号
	private String  	breedId;			//养殖批次id
	private BreedStaff 	breedStaff;	//人员，不填
	private FeedInfo 	feedInfo;		//投喂原料
	private String 		feedId;			//原料id
	private String 		logType;		//日志分类（喂养，用药）
	private Date 		breedTime;		//投喂时间
	private String 		breedNumber;	//投喂数量
	private String 		breedMethod;	//投喂方法	
	private String 		logNote;		//结果或其他记录
	private BreedLog 	breedLog;  //封装喂养记录

	//疾病管理
	private String illnessId;  //疾病编号
	private Date illTime;		//生病时间
	private String symptom;	//症状
	private String prescription;	//处方
	private String doctor;		//医生
	private String note;		//记录及结果
	private IllnessInfo illnessInfo;
	
	//检疫防疫管理
	private String vaccineId;  //检疫编号
	private String type;		//分类（检疫，防疫）
	private String content;	//内容
	private Date vaccineTime;	//时间
	private VaccineInfo vaccineInfo;
	
	//出塘记录
	private String outPondId;   //出塘编号
	private Date outTime;			//出塘时间
	private String personInCharge;	//负责人
	private String outPondNos;  //出塘批次
	private OutPond outPond;	//封装出塘信息
	
	//质检管理
	private String qcId;		//质检编号 （自动生成）
	private String product;	//产品名称
	/*private String content;	//质检内容
*/	private String result;		//质检结果
	/*private String personInCharge;	//负责人
*/	private Date qcTime;		//质检时间
	private String qcInstitution;	//检测机构
	private QualityControl qualityControl; //封装qc
	
	
	//delete param
	private String breedNos;			//要删除的产品批次
	private String logIds;			//要删除的喂养记录
	private String illnessIds;
	private String vaccineIds;
	private String outPondIds;
	private String qcIds;
	
	//response
	private JSONObject OptionalNetCageList;			//可选的网箱响应
	private JSONObject BreedNoSave;				//保存产品批次响应
	private JSONObject BreedNoDisplay;			//显示产品批次响应
	private JSONObject BreedNoDelete;				//删除产品批次响应
	
	private JSONObject OptionalBreedNoList;    //可选的产品批次
	private JSONObject OptionalMaterialList;    //可选的原料
	private JSONObject BreedLogSave;				//保存产品喂养记录响应
	private JSONObject BreedLogDisplay;			//显示产品喂养记录响应
	private JSONObject BreedLogDelete;				//删除产品喂养记录响应
	private JSONObject IllnessInfoSave;				//保存疾病记录响应
	private JSONObject IllnessInfoDisplay;			//显示疾病记录响应
	private JSONObject IllnessInfoDelete;				//删除疾病记录响应
	private JSONObject VaccineInfoSave;				//保存检疫防疫记录响应
	private JSONObject VaccineInfoDisplay;			//显示检疫防疫记录响应
	private JSONObject VaccineInfoDelete;				//删除检疫防疫记录响应
	private JSONObject OptionalInPondNoList;	//可选的未出塘产品批次
	private JSONObject OutPondSave;
	private JSONObject OutPondDisplay;
	private JSONObject OutPondDelete;
	private JSONObject QualityControlSave;
	private JSONObject QualityControlDisplay;
	private JSONObject QualityControlDelete;
	
	
	//分页
	 private int 		iDisplayStart;//千万注意set和get方法名的写法，默认将收集不到参数
     private int 		iDisplayLength;
     private Integer 	sEcho;
	

     //获取可选的网箱（单选框的选项）
     public String getOptionalNetCage(){
    	 breedCompany = userService.findBreedCompanyById(companyId);
    	 Set<BreedArea> sets = new HashSet<BreedArea>();
		 sets = breedCompany.getBreedAreas();
		 List<NetCage> list = new ArrayList<NetCage>();
		
    	 for(BreedArea area:sets){
    		 Set<NetCage> netcages = area.getNetCages();
    		 list.addAll(netcages);
    	 }
    	 
    	 OptionalNetCageList = JsonUtils.toJSONResult(true,list);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     }
     
     //保存产品批次
     public String saveBreedNo(){
    	 breedBatch.setNetCage(breedAreaService.findNetCageById(netCageId));
    	 breedBatch.setInPondTime(new Date());
    	 breedLogService.save(breedBatch);
    	
    	 BreedNoSave = JsonUtils.toJSONResult(true);
    	 return SUCCESS;
    	 
     }
     
     //获取本企业的所有产品批次
     public String getListBreedNo(){
    	 breedCompany = userService.findBreedCompanyById(companyId);
    	 /*Set<BreedArea> sets = new HashSet<BreedArea>();
		 sets = breedCompany.getBreedAreas();
		 List<NetCage> list = new ArrayList<NetCage>();
		
    	 for(BreedArea area:sets){
    		 Set<NetCage> netcages = area.getNetCages();
    		 list.addAll(netcages);
    	 }
    	 
    	 List<BreedNo> breeds = new ArrayList<BreedNo>();
    	 for(NetCage netcage: list){
    		 Set<BreedNo> set = netcage.getBreedNos();
    		 breeds.addAll(set);
    		 
    	 }
    	 Collections.sort(breeds);
    	 List<BreedNo> breedList = breedLogService.getNoList(breeds, iDisplayStart, iDisplayLength);*/
    	 List<BreedNo> breedList = breedLogService.getBreedNo(iDisplayStart, iDisplayLength, breedCompany);
    	 int count = breedLogService.getCount();
    	
    	 BreedNoDisplay = JsonUtils.toJSONResult(count, breedList, sEcho);
    	 return SUCCESS;
    	 
     }
     
     //获取某个养殖区域的所有产品批次
     public String getListBreedNoByArea(){
    	 breedArea = breedAreaService.findObjectById(areaId);
    	 List<BreedNo> list = breedLogService.getBreedNo(iDisplayStart, iDisplayLength, breedArea);
    	 int count = breedLogService.getCount();
    	// sEcho = count;
    	 BreedNoDisplay = JsonUtils.toJSONResult(count, list, sEcho);
    	 return SUCCESS;
    	 
     }
     
     //删除产品批次
     public String deleteBreedNo(){
    	 try {
 			if(breedNos != null)
 			{
 				String[] delete_id = breedNos.split(",");
 				for(String id: delete_id){
 					breedLogService.delete(id);  //delete complaint
 				}
 			}
 			BreedNoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			BreedNoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
	
    //获取可选的养殖批次（单选框的选项）
     public String getOptionalBreedNo(){
    	 breedCompany = userService.findBreedCompanyById(companyId);
    	 Set<BreedArea> sets = new HashSet<BreedArea>();
		 sets = breedCompany.getBreedAreas();
		 List<NetCage> list = new ArrayList<NetCage>();
		
    	 for(BreedArea area:sets){
    		 Set<NetCage> netcages = area.getNetCages();
    		 list.addAll(netcages);
    	 }
    	 
    	 List<BreedNo> breeds = new ArrayList<BreedNo>();
    	 for(NetCage netcage: list){
    		 Set<BreedNo> set = netcage.getBreedNos();
    		 breeds.addAll(set);
    		 
    	 }
    	 OptionalBreedNoList = JsonUtils.toJSONResult(true,breeds);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     }
     
     //获取可选的原料（单选框的选项）
     public String getOptionalMaterial(){
    	 breedCompany = userService.findBreedCompanyById(companyId);
    	 Set<FeedInfo> sets = new HashSet<FeedInfo>();
		 sets = breedCompany.getFeedInfos();
		
    	 OptionalMaterialList = JsonUtils.toJSONResult(true, sets);
    	 
    	 return SUCCESS;
     }
     
   //保存产品喂养记录
     public String saveBreedLog(){
    	 try {
			breedLog.setBreedNo(breedLogService.findObjectById(breedId));
			 breedLog.setFeedInfo(feedInfoService.findObjectById(feedId));
			 if(breedLog.getBreedTime()==null){
				 breedLog.setBreedTime(new Date());
			 }
			
			 breedLogService.saveBreedLog(breedLog);
   
			 BreedLogSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BreedLogSave = JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //获取企业所有养殖记录
     public String getListBreedLog(){
    	 breedCompany = userService.findBreedCompanyById(companyId);
    	 List<BreedLog> log = breedLogService.getBreedLog(iDisplayStart, iDisplayLength, breedCompany);
    	 int count = breedLogService.getCount();
		 BreedLogDisplay= JsonUtils.toJSONResult(count, log, sEcho);
		 return SUCCESS;
     }
     
     //获取某批产品的喂养记录 
	public String getListBreedLogByNo(){
    	 try {
			Set<BreedLog> sets = new HashSet<BreedLog>();
			breedBatch = breedLogService.findObjectById(breedNo);
			sets = breedBatch.getBreedLogs();
			List<BreedLog> list = new ArrayList<BreedLog>(); 
			list.addAll(sets);
			Collections.sort(list);
			List<BreedLog> logList = breedLogService.getList(list, iDisplayStart, iDisplayLength);
			int count = breedLogService.getCount();
			// sEcho = count;
			 BreedLogDisplay= JsonUtils.toJSONResult(count, logList, sEcho);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BreedLogDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除产品的喂养记录
     public String deleteBreedLog(){
    	 try {
 			if(logIds != null)
 			{
 				String[] delete_id = logIds.split(",");
 				for(String id: delete_id){
 					breedLogService.deleteBreedLog(id); //delete log
 				}
 			}
 			BreedLogDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			BreedLogDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //添加疾病记录
     public String saveIllnessInfo(){
    	 try {
			illnessInfo.setBreedNo(breedLogService.findObjectById(breedId));
			if(illnessInfo.getIllTime()==null){
				illnessInfo.setIllTime(new Date());
			}
			 
			 breedLogService.saveIllnessInfo(illnessInfo);
			 IllnessInfoSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			IllnessInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
 	
     //获得某批产品的疾病记录
     public String getListIllnessInfo(){
    	 try {
			Set<IllnessInfo> sets = new HashSet<IllnessInfo>();
			breedBatch = breedLogService.findObjectById(breedNo);
			sets = breedBatch.getIllnessInfos();
			 
			 //int count = sets.size();
			// sEcho = count;
			 IllnessInfoDisplay= JsonUtils.toJSONResult(true, sets);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			IllnessInfoDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除产品的疾病记录
     public String deleteIllnessInfo(){
    	 try {
 			if(illnessIds != null)
 			{
 				String[] delete_id = illnessIds.split(",");
 				for(String id: delete_id){
 					breedLogService.deleteIllnessInfo(id); //delete illnessInfo
 				}
 			}
 			IllnessInfoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			IllnessInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //添加检疫防疫记录
     public String saveVaccineInfo(){
    	 try {
			vaccineInfo.setBreedNo(breedLogService.findObjectById(breedId));
			if(vaccineInfo.getVaccineTime()==null){
				vaccineInfo.setVaccineTime(new Date());
			}
			
			 breedLogService.saveVaccineInfo(vaccineInfo);
			 VaccineInfoSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			VaccineInfoSave = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
 	
     //获得某批产品的检疫防疫记录
     public String getListVaccineInfo(){
    	 try {
			Set<VaccineInfo> sets = new HashSet<VaccineInfo>();
			breedBatch = breedLogService.findObjectById(breedNo);
			sets = breedBatch.getVaccineInfos();
			 
			 //int count = sets.size();
			// sEcho = count;
			 VaccineInfoDisplay= JsonUtils.toJSONResult(true, sets);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			VaccineInfoDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除产品的疾病记录
     public String deleteVaccineInfo(){
    	 try {
 			if(vaccineIds != null)
 			{
 				String[] delete_id = vaccineIds.split(",");
 				for(String id: delete_id){
 					breedLogService.deleteVaccineInfo(id); //delete vaccineInfo
 				}
 			}
 			VaccineInfoDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			VaccineInfoDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //出塘时产品批次选项
     public String getOptionalInPondNos(){
    	 breedCompany = userService.findBreedCompanyById(companyId);
    	 List<BreedNo> list = breedLogService.getBreedNoInPond(breedCompany);
    	 OptionalInPondNoList = JsonUtils.toJSONResult(true, list);
    	 return SUCCESS;
    	 
     }
     
     //保存出塘记录
     public String saveOutPond(){
    	 outPond.setBreedCompany(userService.findBreedCompanyById(companyId));
    	 if(outPond.getOutTime()==null){
    		 outPond.setOutTime(new Date());
    	 }
    	 Set<BreedNo> breed = new HashSet<BreedNo>();
    	 BreedNo br = new BreedNo();
    	 if(outPondNos != null){
    		 String[] nos = outPondNos.split(",");
    		 for(String no:nos){
    			 br = breedLogService.findObjectById(no);
    			 br.setOutPond(outPond);
    			 breed.add(br);
    		 }
    	 }
    	 outPond.setBreedNos(breed);
    	 breedLogService.saveOutPond(outPond);
    	 OutPondSave = JsonUtils.toJSONResult(true);
    	 return SUCCESS;
    	 
     }
     
     //获得企业的所有出塘记录
     public String getListOutPond(){
    	 try {
			Set<OutPond> sets = new HashSet<OutPond>();
			breedCompany = userService.findBreedCompanyById(companyId);
			
			sets = breedCompany.getOutPonds();
			 
			 //int count = sets.size();
			// sEcho = count;
			 OutPondDisplay= JsonUtils.toJSONResult(true, sets);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			OutPondDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除产品的出塘记录
     public String deleteOutPond(){
    	 try {
 			if(outPondIds != null)
 			{
 				String[] delete_id = outPondIds.split(",");
 				for(String id: delete_id){
 					breedLogService.deleteOutPond(id); //delete outpond Info
 				}
 			}
 			OutPondDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			OutPondDelete = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
 		
     }
     
     //添加质检记录
     public String saveQualityControl(){
    	 try {
			qualityControl.setBreedNo(breedLogService.findObjectById(breedId));
			if(qualityControl.getQcTime()==null){
				qualityControl.setQcTime(new Date());
			}
			 
			 breedLogService.saveQualityControl(qualityControl);
			 QualityControlSave = JsonUtils.toJSONResult(true);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			QualityControlSave = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
		}
     }
 	
     //获得某批产品的质检记录
     public String getListQualityControl(){
    	 try {
			Set<QualityControl> sets = new HashSet<QualityControl>();
			breedBatch = breedLogService.findObjectById(breedNo);
			sets = breedBatch.getQualityControls();
			 
			 //int count = sets.size();
			// sEcho = count;
			QualityControlDisplay= JsonUtils.toJSONResult(true, sets);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			QualityControlDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除产品质检记录
     public String deleteQualityControl(){
    	 try {
 			if(qcIds != null)
 			{
 				String[] delete_id = qcIds.split(",");
 				for(String id: delete_id){
 					breedLogService.deleteQualityControl(id); //delete illnessInfo
 				}
 			}
 			QualityControlDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			QualityControlDelete = JsonUtils.toJSONResult(false, "发生未知错误");
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


	public BreedLogService getBreedLogService() {
		return breedLogService;
	}

	@Resource
	public void setBreedLogService(BreedLogService breedLogService) {
		this.breedLogService = breedLogService;
	}

	public BreedAreaService getBreedAreaService() {
		return breedAreaService;
	}

	@Resource
	public void setBreedAreaService(BreedAreaService breedAreaService) {
		this.breedAreaService = breedAreaService;
	}

	public FeedInfoService getFeedInfoService() {
		return feedInfoService;
	}

	@Resource
	public void setFeedInfoService(FeedInfoService feedInfoService) {
		this.feedInfoService = feedInfoService;
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

	
	public BreedCompany getBreedCompany() {
		return breedCompany;
	}

	public void setBreedCompany(BreedCompany breedCompany) {
		this.breedCompany = breedCompany;
	}

	public String getBreedNo() {
		return breedNo;
	}

	public void setBreedNo(String breedNo) {
		this.breedNo = breedNo;
	}

	public NetCage getNetCage() {
		return netCage;
	}

	public void setNetCage(NetCage netCage) {
		this.netCage = netCage;
	}

	public String getNetCageId() {
		return netCageId;
	}

	public void setNetCageId(String netCageId) {
		this.netCageId = netCageId;
	}

	public Date getInPondTime() {
		return inPondTime;
	}

	public void setInPondTime(Date inPondTime) {
		this.inPondTime = inPondTime;
	}

	public String getInPondNumber() {
		return inPondNumber;
	}

	public void setInPondNumber(String inPondNumber) {
		this.inPondNumber = inPondNumber;
	}

	public String getInPondMethod() {
		return inPondMethod;
	}

	public void setInPondMethod(String inPondMethod) {
		this.inPondMethod = inPondMethod;
	}

	public String getBreedPerson() {
		return breedPerson;
	}

	public void setBreedPerson(String breedPerson) {
		this.breedPerson = breedPerson;
	}

	public String getBreedSpecies() {
		return breedSpecies;
	}

	public void setBreedSpecies(String breedSpecies) {
		this.breedSpecies = breedSpecies;
	}

	public BreedNo getBreedBatch() {
		return breedBatch;
	}

	public void setBreedBatch(BreedNo breedBatch) {
		this.breedBatch = breedBatch;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getBreedId() {
		return breedId;
	}

	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

	public BreedStaff getBreedStaff() {
		return breedStaff;
	}

	public void setBreedStaff(BreedStaff breedStaff) {
		this.breedStaff = breedStaff;
	}

	public FeedInfo getFeedInfo() {
		return feedInfo;
	}

	public void setFeedInfo(FeedInfo feedInfo) {
		this.feedInfo = feedInfo;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public Date getBreedTime() {
		return breedTime;
	}

	public void setBreedTime(Date breedTime) {
		this.breedTime = breedTime;
	}

	public String getBreedNumber() {
		return breedNumber;
	}

	public void setBreedNumber(String breedNumber) {
		this.breedNumber = breedNumber;
	}

	public String getBreedMethod() {
		return breedMethod;
	}

	public void setBreedMethod(String breedMethod) {
		this.breedMethod = breedMethod;
	}

	public String getLogNote() {
		return logNote;
	}

	public void setLogNote(String logNote) {
		this.logNote = logNote;
	}

	public BreedLog getBreedLog() {
		return breedLog;
	}

	public void setBreedLog(BreedLog breedLog) {
		this.breedLog = breedLog;
	}

	public String getBreedNos() {
		return breedNos;
	}

	public void setBreedNos(String breedNos) {
		this.breedNos = breedNos;
	}

	public String getLogIds() {
		return logIds;
	}

	public void setLogIds(String logIds) {
		this.logIds = logIds;
	}

	public JSONObject getOptionalNetCageList() {
		return OptionalNetCageList;
	}

	public void setOptionalNetCageList(JSONObject optionalNetCageList) {
		OptionalNetCageList = optionalNetCageList;
	}

	public JSONObject getBreedNoSave() {
		return BreedNoSave;
	}

	public void setBreedNoSave(JSONObject breedNoSave) {
		BreedNoSave = breedNoSave;
	}

	public JSONObject getBreedNoDisplay() {
		return BreedNoDisplay;
	}

	public void setBreedNoDisplay(JSONObject breedNoDisplay) {
		BreedNoDisplay = breedNoDisplay;
	}

	public JSONObject getBreedNoDelete() {
		return BreedNoDelete;
	}

	public void setBreedNoDelete(JSONObject breedNoDelete) {
		BreedNoDelete = breedNoDelete;
	}

	public JSONObject getOptionalBreedNoList() {
		return OptionalBreedNoList;
	}

	public void setOptionalBreedNoList(JSONObject optionalBreedNoList) {
		OptionalBreedNoList = optionalBreedNoList;
	}

	public JSONObject getOptionalMaterialList() {
		return OptionalMaterialList;
	}

	public void setOptionalMaterialList(JSONObject optionalMaterialList) {
		OptionalMaterialList = optionalMaterialList;
	}

	public JSONObject getBreedLogSave() {
		return BreedLogSave;
	}

	public void setBreedLogSave(JSONObject breedLogSave) {
		BreedLogSave = breedLogSave;
	}

	public JSONObject getBreedLogDisplay() {
		return BreedLogDisplay;
	}

	public void setBreedLogDisplay(JSONObject breedLogDisplay) {
		BreedLogDisplay = breedLogDisplay;
	}

	public JSONObject getBreedLogDelete() {
		return BreedLogDelete;
	}

	public void setBreedLogDelete(JSONObject breedLogDelete) {
		BreedLogDelete = breedLogDelete;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public BreedArea getBreedArea() {
		return breedArea;
	}

	public void setBreedArea(BreedArea breedArea) {
		this.breedArea = breedArea;
	}

	public String getIllnessId() {
		return illnessId;
	}

	public void setIllnessId(String illnessId) {
		this.illnessId = illnessId;
	}

	public Date getIllTime() {
		return illTime;
	}

	public void setIllTime(Date illTime) {
		this.illTime = illTime;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public IllnessInfo getIllnessInfo() {
		return illnessInfo;
	}

	public void setIllnessInfo(IllnessInfo illnessInfo) {
		this.illnessInfo = illnessInfo;
	}

	public String getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(String vaccineId) {
		this.vaccineId = vaccineId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getVaccineTime() {
		return vaccineTime;
	}

	public void setVaccineTime(Date vaccineTime) {
		this.vaccineTime = vaccineTime;
	}

	public VaccineInfo getVaccineInfo() {
		return vaccineInfo;
	}

	public void setVaccineInfo(VaccineInfo vaccineInfo) {
		this.vaccineInfo = vaccineInfo;
	}

	public String getIllnessIds() {
		return illnessIds;
	}

	public void setIllnessIds(String illnessIds) {
		this.illnessIds = illnessIds;
	}

	public String getVaccineIds() {
		return vaccineIds;
	}

	public void setVaccineIds(String vaccineIds) {
		this.vaccineIds = vaccineIds;
	}

	public JSONObject getIllnessInfoSave() {
		return IllnessInfoSave;
	}

	public void setIllnessInfoSave(JSONObject illnessInfoSave) {
		IllnessInfoSave = illnessInfoSave;
	}

	public JSONObject getIllnessInfoDisplay() {
		return IllnessInfoDisplay;
	}

	public void setIllnessInfoDisplay(JSONObject illnessInfoDisplay) {
		IllnessInfoDisplay = illnessInfoDisplay;
	}

	public JSONObject getIllnessInfoDelete() {
		return IllnessInfoDelete;
	}

	public void setIllnessInfoDelete(JSONObject illnessInfoDelete) {
		IllnessInfoDelete = illnessInfoDelete;
	}

	public JSONObject getVaccineInfoSave() {
		return VaccineInfoSave;
	}

	public void setVaccineInfoSave(JSONObject vaccineInfoSave) {
		VaccineInfoSave = vaccineInfoSave;
	}

	public JSONObject getVaccineInfoDisplay() {
		return VaccineInfoDisplay;
	}

	public void setVaccineInfoDisplay(JSONObject vaccineInfoDisplay) {
		VaccineInfoDisplay = vaccineInfoDisplay;
	}

	public JSONObject getVaccineInfoDelete() {
		return VaccineInfoDelete;
	}

	public void setVaccineInfoDelete(JSONObject vaccineInfoDelete) {
		VaccineInfoDelete = vaccineInfoDelete;
	}

	public String getOutPondId() {
		return outPondId;
	}

	public void setOutPondId(String outPondId) {
		this.outPondId = outPondId;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public String getOutPondNos() {
		return outPondNos;
	}

	public void setOutPondNos(String outPondNos) {
		this.outPondNos = outPondNos;
	}

	public OutPond getOutPond() {
		return outPond;
	}

	public void setOutPond(OutPond outPond) {
		this.outPond = outPond;
	}

	public String getOutPondIds() {
		return outPondIds;
	}

	public void setOutPondIds(String outPondIds) {
		this.outPondIds = outPondIds;
	}

	public JSONObject getOptionalInPondNoList() {
		return OptionalInPondNoList;
	}

	public void setOptionalInPondNoList(JSONObject optionalInPondNoList) {
		OptionalInPondNoList = optionalInPondNoList;
	}

	public JSONObject getOutPondSave() {
		return OutPondSave;
	}

	public void setOutPondSave(JSONObject outPondSave) {
		OutPondSave = outPondSave;
	}

	public JSONObject getOutPondDisplay() {
		return OutPondDisplay;
	}

	public void setOutPondDisplay(JSONObject outPondDisplay) {
		OutPondDisplay = outPondDisplay;
	}

	public JSONObject getOutPondDelete() {
		return OutPondDelete;
	}

	public void setOutPondDelete(JSONObject outPondDelete) {
		OutPondDelete = outPondDelete;
	}

	public String getQcId() {
		return qcId;
	}

	public void setQcId(String qcId) {
		this.qcId = qcId;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getQcTime() {
		return qcTime;
	}

	public void setQcTime(Date qcTime) {
		this.qcTime = qcTime;
	}

	public String getQcInstitution() {
		return qcInstitution;
	}

	public void setQcInstitution(String qcInstitution) {
		this.qcInstitution = qcInstitution;
	}

	public QualityControl getQualityControl() {
		return qualityControl;
	}

	public void setQualityControl(QualityControl qualityControl) {
		this.qualityControl = qualityControl;
	}

	public String getQcIds() {
		return qcIds;
	}

	public void setQcIds(String qcIds) {
		this.qcIds = qcIds;
	}

	public JSONObject getQualityControlSave() {
		return QualityControlSave;
	}

	public void setQualityControlSave(JSONObject qualityControlSave) {
		QualityControlSave = qualityControlSave;
	}

	public JSONObject getQualityControlDisplay() {
		return QualityControlDisplay;
	}

	public void setQualityControlDisplay(JSONObject qualityControlDisplay) {
		QualityControlDisplay = qualityControlDisplay;
	}

	public JSONObject getQualityControlDelete() {
		return QualityControlDelete;
	}

	public void setQualityControlDelete(JSONObject qualityControlDelete) {
		QualityControlDelete = qualityControlDelete;
	}

	
	
	
	
}

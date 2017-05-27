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
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.FeedInfo;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.service.breed.BreedAreaService;
import org.wuxi.fudan.syfw.service.breed.BreedStaffService;
import org.wuxi.fudan.syfw.service.breed.FeedInfoService;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class FeedInfoAction extends ActionSupport{

	/**
	 * 养殖企业的养殖区域管理，养殖人员管理
	 */
	private static final long serialVersionUID = 1L;
	
	private FeedInfoService  feedInfoService;
	private UserService		 userService;
	

	//用户信息
	private String	userId;
	private String	companyId;
	
	//原料管理
	private String feedId;			//原料编号
	private BreedCompany breedCompany;		//养殖企业
	private String feedCompany;	//原料购买商
	private String contactNumber;	//联系电话
	private String address;		//地址
	private String amount;			//购买数量
	private String handler;		//经手人
	private String storehouse;		//存放库房位置
	private Date stockTime;		//存放日期
	private String stockInfo;		//入库时验收信息
	private String personInCharge;	//负责人
	private String materialType;	//原料类别
	private String materialName;	//原料名
	private FeedInfo feedInfo;		//封装原料信息
	
	private String feedIds;			//要删除的原料编号

	
	//response
	private JSONObject OptionalCompanyList;			//养殖企业列表响应
	private JSONObject MaterialSave;				//保存原料响应
	private JSONObject MaterialDisplay;			//显示原料列表响应
	private JSONObject MaterialDelete;				//删除原料响应
	
	
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
    	 OptionalCompanyList = JsonUtils.toJSONResult(true,breedCompanies);
    	 //OptionalCompanyList = JsonUtils.toJSONResult(true,list);
    	 
    	 return SUCCESS;
     }
     
     //保存原料信息
     public String saveMaterial(){
    	 feedInfo.setBreedCompany(userService.findBreedCompanyById(companyId));  //设置所属养殖企业
    	
    	 if(feedInfo.getStockTime()==null){
    		 feedInfo.setStockTime(new Date());
    	 }
    	 feedInfoService.save(feedInfo); //保存原料信息
    	 MaterialSave = JsonUtils.toJSONResult(true);
    	 return SUCCESS;
    	 
     }
	
     //获取本养殖企业下的所有原料，养殖企业调用
     @SuppressWarnings("unused")
	public String getListMaterial(){
    	 try {
			Set<FeedInfo> sets = new HashSet<FeedInfo>();
			 breedCompany = userService.findBreedCompanyById(companyId);
			 sets = breedCompany.getFeedInfos();
			 List<FeedInfo> list = new ArrayList<FeedInfo>();
			 list.addAll(sets);
			 Collections.sort(list);
			 List<FeedInfo> feedList = feedInfoService.getList(list, iDisplayStart, iDisplayLength);
			 int count = feedInfoService.getCount();
			// sEcho = count;
			 MaterialDisplay= JsonUtils.toJSONResult(count, feedList, sEcho);
			 return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MaterialDisplay= JsonUtils.toJSONResult(false, "发生未知错误");
    		return ERROR;		
		}
    	 
     }
     
     //删除养殖区域
     public String deleteMaterial(){
    	 try {
 			if(feedIds != null)
 			{
 				String[] delete_id = feedIds.split(",");
 				for(String id: delete_id){
 					feedInfoService.delete(id);  //delete complaint
 				}
 			}
 			MaterialDelete = JsonUtils.toJSONResult(true);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			MaterialDelete = JsonUtils.toJSONResult(false, "发生未知错误");
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

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public BreedCompany getBreedCompany() {
		return breedCompany;
	}

	public void setBreedCompany(BreedCompany breedCompany) {
		this.breedCompany = breedCompany;
	}

	public String getFeedCompany() {
		return feedCompany;
	}

	public void setFeedCompany(String feedCompany) {
		this.feedCompany = feedCompany;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public Date getStockTime() {
		return stockTime;
	}

	public void setStockTime(Date stockTime) {
		this.stockTime = stockTime;
	}

	public String getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(String stockInfo) {
		this.stockInfo = stockInfo;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public FeedInfo getFeedInfo() {
		return feedInfo;
	}

	public void setFeedInfo(FeedInfo feedInfo) {
		this.feedInfo = feedInfo;
	}

	public String getFeedIds() {
		return feedIds;
	}

	public void setFeedIds(String feedIds) {
		this.feedIds = feedIds;
	}

	public JSONObject getOptionalCompanyList() {
		return OptionalCompanyList;
	}

	public void setOptionalCompanyList(JSONObject optionalCompanyList) {
		OptionalCompanyList = optionalCompanyList;
	}

	public JSONObject getMaterialSave() {
		return MaterialSave;
	}

	public void setMaterialSave(JSONObject materialSave) {
		MaterialSave = materialSave;
	}

	public JSONObject getMaterialDisplay() {
		return MaterialDisplay;
	}

	public void setMaterialDisplay(JSONObject materialDisplay) {
		MaterialDisplay = materialDisplay;
	}

	public JSONObject getMaterialDelete() {
		return MaterialDelete;
	}

	public void setMaterialDelete(JSONObject materialDelete) {
		MaterialDelete = materialDelete;
	}

	
	
	
	
	
}

package org.wuxi.fudan.syfw.action.user;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;






import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.User;
import org.wuxi.fudan.syfw.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	/**
	 * 用户管理。处理登录，注册请求
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private String 		id;			//身份证号
	private String 		username;	//用户名
	private String 		password; 	//密码
	private String 		role;   	//角色：个人，监管者，养殖企业，监管企业
	private String 		phone; 		//电话
	private User   		user;		//封装注册信息
	
	/*养殖企业详细信息*/
	private String companyId; 			//养殖企业编码，需要界面输入
	private String companyName;		    //养殖企业名称
	private String legalPerson;			//养殖企业法人
	private String companyAddress;		//养殖企业地址
	private String contactNumber;		//企业联系电话
	private String breedSpecies;		//企业养殖品种
	private String safeGrade;			//养殖品种安全等级
	private BreedCompany breedCompany;  //封装养殖企业信息
	
	/*加工企业详细信息*/
	//private String PcompanyId; 				//加工企业编码，需要界面输入
	///private String PcompanyName;		    //加工企业名称
	//private String PlegalPerson;				//加工企业法人
	//private String PcompanyAddress;			//加工企业地址
	//private String PcontactNumber;			//企业联系电话
	private String processBatch;			    //加工批次
	private String environment;			    //加工环境
	//private String userId;					//用户id，根据登录用户名可以找到企业信息
	private ProcessCompany processCompany;	//封装加工企业信息


	
	private JSONObject isRegisterSuccess;  //注册响应
	private JSONObject isLoginSuccess;     //登录响应
	private JSONObject breedCompanySave;   //提交养殖企业信息响应
	private JSONObject processCompanySave;   //提交加工企业信息响应

	//用户注册
	public String register(){
		if(userService.findObjectById(id)==null){  //未注册过的新用户
			
			try {
				userService.save(user);  //储存用户信息
				isRegisterSuccess = JsonUtils.toJSONResult(true);
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRegisterSuccess = JsonUtils.toJSONResult(false, "发生未知错误");
				
    			return ERROR;				
			}			
		}	
		//注册过的用户
		isRegisterSuccess = JsonUtils.toJSONResult(false, "该用户已注册");
		return ERROR;
			
	}
	
	//save breedCompany information
	public String breedCompanySave(){
		if(userService.findBreedCompanyById(companyId)==null){  //未添加过企业详细信息，save
			try {
				breedCompany.setUserId(id);
				userService.saveBreedCompany(breedCompany);  //储存养殖企业信息
				breedCompanySave = JsonUtils.toJSONResult(true);
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				breedCompanySave = JsonUtils.toJSONResult(false, "发生未知错误");
				
    			return ERROR;				
			}			
		}	
		//添加过的企业，update
		userService.updateBreedCompany(breedCompany);
		breedCompanySave = JsonUtils.toJSONResult(true);
		return SUCCESS;
			
	}
	
	//save processCompany information
		public String processCompanySave(){
			if(userService.findProcessCompanyById(companyId)==null){  //未添加过企业详细信息，save
				try {
					processCompany.setUserId(id);
					userService.saveProcessCompany(processCompany);  //储存养殖企业信息
					processCompanySave = JsonUtils.toJSONResult(true);
					return SUCCESS;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					processCompanySave = JsonUtils.toJSONResult(false, "发生未知错误");
					
	    			return ERROR;				
				}			
			}	
			//添加过的企业，update
			userService.updateProcessCompany(processCompany);
			processCompanySave = JsonUtils.toJSONResult(true);
			return SUCCESS;
				
		}
	
	


	//用户登录
	public String login(){
		List<User> list = userService.findObjects();
		User u = new User();
		Iterator<User> it = list.iterator();
		//检查用户信息是否存在数据库
		while(it.hasNext()){
			u = it.next();
			if(username.trim().equals(u.getUsername()) && password.trim().equals(u.getPassword())){
				isLoginSuccess = JsonUtils.toJSONResult(true);
				isLoginSuccess.put("role", u.getRole());   //返回用户角色
				return SUCCESS;
			}				
			
		}
		isLoginSuccess = JsonUtils.toJSONResult(false, "failed");  //login failed
		return ERROR;
	}
	
	
	public UserService getUserService() {
		return userService;
	}

	//注入service
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject getIsRegisterSuccess() {
		return isRegisterSuccess;
	}

	public void setIsRegisterSuccess(JSONObject isRegisterSuccess) {
		this.isRegisterSuccess = isRegisterSuccess;
	}

	public JSONObject getIsLoginSuccess() {
		return isLoginSuccess;
	}

	public void setIsLoginSuccess(JSONObject isLoginSuccess) {
		this.isLoginSuccess = isLoginSuccess;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getBreedSpecies() {
		return breedSpecies;
	}

	public void setBreedSpecies(String breedSpecies) {
		this.breedSpecies = breedSpecies;
	}

	public String getSafeGrade() {
		return safeGrade;
	}

	public void setSafeGrade(String safeGrade) {
		this.safeGrade = safeGrade;
	}

	public BreedCompany getBreedCompany() {
		return breedCompany;
	}

	public void setBreedCompany(BreedCompany breedCompany) {
		this.breedCompany = breedCompany;
	}

	public JSONObject getBreedCompanySave() {
		return breedCompanySave;
	}

	public void setBreedCompanySave(JSONObject breedCompanySave) {
		this.breedCompanySave = breedCompanySave;
	}
	
	/*public String getPcompanyId() {
		return PcompanyId;
	}

	public void setPcompanyId(String pcompanyId) {
		PcompanyId = pcompanyId;
	}

	public String getPcompanyName() {
		return PcompanyName;
	}

	public void setPcompanyName(String pcompanyName) {
		PcompanyName = pcompanyName;
	}

	public String getPlegalPerson() {
		return PlegalPerson;
	}

	public void setPlegalPerson(String plegalPerson) {
		PlegalPerson = plegalPerson;
	}

	public String getPcompanyAddress() {
		return PcompanyAddress;
	}

	public void setPcompanyAddress(String pcompanyAddress) {
		PcompanyAddress = pcompanyAddress;
	}

	public String getPcontactNumber() {
		return PcontactNumber;
	}

	public void setPcontactNumber(String pcontactNumber) {
		PcontactNumber = pcontactNumber;
	}*/

	public String getProcessBatch() {
		return processBatch;
	}

	public void setProcessBatch(String processBatch) {
		this.processBatch = processBatch;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	public ProcessCompany getProcessCompany() {
		return processCompany;
	}

	public void setProcessCompany(ProcessCompany processCompany) {
		this.processCompany = processCompany;
	}

	public JSONObject getProcessCompanySave() {
		return processCompanySave;
	}

	public void setProcessCompanySave(JSONObject processCompanySave) {
		this.processCompanySave = processCompanySave;
	}
}

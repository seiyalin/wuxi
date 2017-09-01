package org.wuxi.fudan.syfw.service.user;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.breed.BreedCompanyDao;
import org.wuxi.fudan.syfw.dao.process.ProcessCompanyDao;
import org.wuxi.fudan.syfw.dao.user.UserDao;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.User;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("userService")
@WebService(endpointInterface = "org.wuxi.fudan.syfw.service.user.UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	private UserDao userDao;
	private BreedCompanyDao breedCompanyDao;
	private ProcessCompanyDao processCompanyDao;

	//construtor
	public UserServiceImpl(){
		
	}
	
	public String findRoleByNameAndPwd(String username, String pwd){
		
		return userDao.findRoleByNameAndPwd(username, pwd);
		// if "noUser", login failed
	}
	
	//save processCompany information
	public void saveProcessCompany(ProcessCompany processCompany){
		processCompanyDao.save(processCompany);
		
	}
	
	//find processCompany by companyId
	public ProcessCompany findProcessCompanyById(String companyId){
		return processCompanyDao.findObjectById(companyId);
	}
	
	//update processCompany information
	public void updateProcessCompany(ProcessCompany processCompany){
		processCompanyDao.update(processCompany);
	}
	
	//save breed Company information
	public void saveBreedCompany(BreedCompany breedCompany){
		breedCompanyDao.save(breedCompany);
			
	}
		
	//find breedCompany by companyId
	public BreedCompany findBreedCompanyById(String companyId){
		return breedCompanyDao.findObjectById(companyId);
	}
	
	//find all breedCompany
	public List<BreedCompany> findAllBreedCompany(){
		return breedCompanyDao.findObjects();
	}
	
	//find breedCompany by userId
	public BreedCompany findBreedCompanyByUserId(String userId){
		QueryHelper queryHelper = new QueryHelper(BreedCompany.class, "breedCompany");
		//add condition 要使用别名breedCompany
		queryHelper.addCondition("breedCompany.userId = ?", userId);
		return breedCompanyDao.findObjects(queryHelper).get(0);
	}
	
	//find all processCompany
	public List<ProcessCompany> findAllProcessCompany(){
		return processCompanyDao.findObjects();
	}
	
	//find processCompany by userId
	public ProcessCompany findProcessCompanyByUserId(String userId){
		QueryHelper queryHelper = new QueryHelper(ProcessCompany.class, "processCompany");
		//add condition
		queryHelper.addCondition("processCompany.userId = ?", userId);
		return processCompanyDao.findObjects(queryHelper).get(0);
		/*String hql = "FROM ProcessCompany WHERE userId = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(userId);
		List<ProcessCompany> list = processCompanyDao.findObjects(hql, param);
		return list.get(0);*/
	}
		
	//update breedCompany information
	public void updateBreedCompany(BreedCompany breedCompany){
		breedCompanyDao.update(breedCompany);
	}
		
		

	public UserDao getUserDao() {
		return userDao;
	}

	//@Resource: dao注入service
	@Resource	
	public void setUserDao(UserDao userDao) { //在UserServiceImpl中，通过自动注入userDao的同时，
		//将userDao传递给BaseServiceImpl转换为BaseDao
		super.setBaseDao(userDao);   //注入userDao
		this.userDao = userDao;
	}
	

	public BreedCompanyDao getBreedCompanyDao() {
		return breedCompanyDao;
	}

	@Resource
	public void setBreedCompanyDao(BreedCompanyDao breedCompanyDao) {
		this.breedCompanyDao = breedCompanyDao;
	}

	public ProcessCompanyDao getProcessCompanyDao() {
		return processCompanyDao;
	}

	@Resource
	public void setProcessCompanyDao(ProcessCompanyDao processCompanyDao) {
		this.processCompanyDao = processCompanyDao;
	}



}

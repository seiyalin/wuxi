package org.wuxi.fudan.syfw.service.user;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.User;
import org.wuxi.fudan.syfw.service.base.BaseService;

@WebService
public interface UserService extends BaseService<User> {

	//public String findRoleByNameAndPwd(String username, String pwd);
	public String findRoleByNameAndPwd(@WebParam(name="username") String username, @WebParam(name="pwd")String pwd);
	
	public String sayHello();
	//save processCompany information
	public void saveProcessCompany(ProcessCompany processCompany);
	//find processCompany by companyId
	public ProcessCompany findProcessCompanyById(String companyId);
	//update processCompany information
	public void updateProcessCompany(ProcessCompany processCompany);
	
	//save breedCompany information
	public void saveBreedCompany(BreedCompany breedCompany);
	
	//find breedCompany by companyId
	public BreedCompany findBreedCompanyById(String companyId);
	
	//find breedCompany by userId
	public BreedCompany findBreedCompanyByUserId(String userId);
	
	//update breedCompany information
	public void updateBreedCompany(BreedCompany breedCompany);
	
	//find processCompany by userId
	public ProcessCompany findProcessCompanyByUserId(String userId);
	
	//find all breedCompany
	public List<BreedCompany> findAllBreedCompany();
	
	//find all processCompany
	public List<ProcessCompany> findAllProcessCompany();
}

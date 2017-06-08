package org.wuxi.fudan.syfw.service.process;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.breed.BreedAreaDao;
import org.wuxi.fudan.syfw.dao.breed.NetCageDao;
import org.wuxi.fudan.syfw.dao.breed.WaterQualityDao;
import org.wuxi.fudan.syfw.dao.process.ProcessInfoDao;
import org.wuxi.fudan.syfw.dao.process.ProcessStaffDao;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessStaff;
import org.wuxi.fudan.syfw.model.hibernate.WaterQuality;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("processInfoService")
public class ProcessInfoServiceImpl extends BaseServiceImpl<ProcessInfo> implements ProcessInfoService{
	
	private ProcessInfoDao processInfoDao;
	private ProcessStaffDao processStaffDao;
	
	
	PageResult pageResult;
	
	//construtor
	public ProcessInfoServiceImpl(){
		
	}
	
	//find process staff by Id
	public ProcessStaff findProcessStaffById(String id){
		return processStaffDao.findObjectById(id);
	}
	
	//save process staff
	public void saveProcessStaff(ProcessStaff staff){
		processStaffDao.save(staff);
	}
	
	//delete process staff
	public void deleteProcessStaff(String id){
		processStaffDao.delete(id);
	}
	
	//get process staff list
	public List<ProcessStaff> getList(List<ProcessStaff> list, Integer start, Integer limit){
		pageResult = processStaffDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get process INFO list
	public List<ProcessInfo> getInfoList(List<ProcessInfo> list, Integer start, Integer limit){
			pageResult = processInfoDao.getPageResult(list, start/limit + 1, limit);

			return pageResult.getItems();
		}
	//get process info list
	public List<ProcessInfo> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(ProcessInfo.class, "processInfo");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = processInfoDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//获取公司下未出售的成品箱
	public List<ProcessInfo> getCagesInSell(ProcessCompany processCompany){
		QueryHelper queryHelper = new QueryHelper(ProcessInfo.class, "processInfo");
				//add condition
		queryHelper.addCondition("processInfo.processCompany = ?", processCompany);
		queryHelper.addCondition("processInfo.orderInfo is null");
		queryHelper.addOrderByProperty("processInfo.processTime", "DESC");  //降序排列
		return processInfoDao.findObjects(queryHelper);
			//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
			//return pageResult.getItems();
		}
	
	//获取公司下可运输的成品箱
	public List<ProcessInfo> getCagesToTrans(ProcessCompany processCompany){
		QueryHelper queryHelper = new QueryHelper(ProcessInfo.class, "processInfo");
					//add condition
		queryHelper.addCondition("processInfo.processCompany = ?", processCompany);
		queryHelper.addCondition("processInfo.transportationInfo is null");
		queryHelper.addOrderByProperty("processInfo.processTime", "DESC");  //降序排列
		return processInfoDao.findObjects(queryHelper);
				//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
				//return pageResult.getItems();
		}
		
	//get total number of breedArea
	public int getCount() {
		// TODO Auto-generated method stub

		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}

	public ProcessInfoDao getProcessInfoDao() {
		return processInfoDao;
	}

	@Resource
	public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
		super.setBaseDao(processInfoDao);
		this.processInfoDao = processInfoDao;
	}

	public ProcessStaffDao getProcessStaffDao() {
		return processStaffDao;
	}

	@Resource
	public void setProcessStaffDao(ProcessStaffDao processStaffDao) {
		this.processStaffDao = processStaffDao;
	}
	
	
	
	

}

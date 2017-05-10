package org.wuxi.fudan.syfw.service.process;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessInfo;
import org.wuxi.fudan.syfw.model.hibernate.ProcessStaff;
import org.wuxi.fudan.syfw.model.hibernate.WaterQuality;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface ProcessInfoService extends BaseService<ProcessInfo> {
	
	//find process staff by Id
	public ProcessStaff findProcessStaffById(String id);
	
	//save process staff
	public void saveProcessStaff(ProcessStaff staff);

	//delete process staff
	public void deleteProcessStaff(String id);
	
	//获取公司下未出售的成品箱
	public List<ProcessInfo> getCagesInSell(ProcessCompany processCompany);
	
	//获取公司下可运输的成品箱
	public List<ProcessInfo> getCagesToTrans(ProcessCompany processCompany);
		
	//get process info list
	public List<ProcessInfo> getList(Integer start, Integer limit);
	
	//get total number of breedArea
	public int getCount();
	
}
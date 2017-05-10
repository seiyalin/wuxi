package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface BreedStaffService extends BaseService<BreedStaff> {
	
	
	
	//get breedStaff list
	public List<BreedStaff> getList(Integer start, Integer limit);
	
	//get total number of breedStaff
	public int getCount();
	
}

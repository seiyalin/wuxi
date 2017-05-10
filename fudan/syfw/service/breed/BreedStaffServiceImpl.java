package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.breed.BreedAreaDao;
import org.wuxi.fudan.syfw.dao.breed.BreedStaffDao;
import org.wuxi.fudan.syfw.dao.breed.NetCageDao;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("breedStaffService")
public class BreedStaffServiceImpl extends BaseServiceImpl<BreedStaff> implements BreedStaffService{
	
	private BreedStaffDao breedStaffDao;
	
	PageResult pageResult;
	
	//construtor
	public BreedStaffServiceImpl(){
		
	}
	
	
	//get breedStaff list
	public List<BreedStaff> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(BreedStaff.class, "breedStaff");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = breedStaffDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
		
	//get total number of breedStaff
	public int getCount() {
		// TODO Auto-generated method stub

		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}

	public BreedStaffDao getBreedStaffDao() {
		return breedStaffDao;
	}

	@Resource
	public void setBreedStaffDao(BreedStaffDao breedStaffDao) {
		super.setBaseDao(breedStaffDao);
		this.breedStaffDao = breedStaffDao;
	}
	
	
	
	
	

}

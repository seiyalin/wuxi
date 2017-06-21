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
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
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
	
	//get breed staff list 分页
	public List<BreedStaff> getList(List<BreedStaff> list, Integer start, Integer limit){
		pageResult = breedStaffDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//获取公司下的所有员工
	public List<BreedStaff> getBreedStaff(Integer start, Integer limit, BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(BreedStaff.class, "breedStaff");
			/*QueryHelper queryHelper = new QueryHelper("BreedNo breedNo left join fetch breedNo.netCage netcage left join fetch "
					+ "netcage.breedArea breedArea left join fetch breedArea.breedCompany" , "breedCompany");*/
			//add condition
			/*queryHelper.addCondition("breedCompany.companyId = ?", companyId);*/
			//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("breedStaff.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("breedStaff.idcard", "DESC");  //降序排列
		pageResult = breedStaffDao.getPageResult(queryHelper, start/limit+1, limit);
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

package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.breed.BreedAreaDao;
import org.wuxi.fudan.syfw.dao.breed.NetCageDao;
import org.wuxi.fudan.syfw.dao.breed.WaterQualityDao;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.WaterQuality;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("breedAreaService")
public class BreedAreaServiceImpl extends BaseServiceImpl<BreedArea> implements BreedAreaService{
	
	private BreedAreaDao breedAreaDao;
	private NetCageDao 	 netCageDao;
	private WaterQualityDao waterQualityDao;
	
	PageResult pageResult;
	
	//construtor
	public BreedAreaServiceImpl(){
		
	}
	
	//find netcage by netcageId
	public NetCage findNetCageById(String id){
		return netCageDao.findObjectById(id);
	}
	
	//save netcage
	public void saveNetCage(NetCage netCage){
		netCageDao.save(netCage);
	}
	
	//delete netcage
	public void deleteNetCage(String netcageId){
		netCageDao.delete(netcageId);
	}
	
	//save waterQuality info
	public void saveWaterQuality(WaterQuality waterQuality){
		waterQualityDao.save(waterQuality);
	}
		
	//delete waterQuality info
	public void deleteWaterQuality(String waterId){
		waterQualityDao.delete(waterId);
	}
	
	//get breedArea list
	public List<BreedArea> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(BreedArea.class, "breedArea");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = breedAreaDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get breedArea list 分页
	public List<BreedArea> getList(List<BreedArea> list, Integer start, Integer limit){
		pageResult = breedAreaDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
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
	
	
	public BreedAreaDao getBreedAreaDao() {
		return breedAreaDao;
	}
	
	@Resource
	public void setBreedAreaDao(BreedAreaDao breedAreaDao) {
		super.setBaseDao(breedAreaDao);
		this.breedAreaDao = breedAreaDao;
	}
	
	public NetCageDao getNetCageDao() {
		return netCageDao;
	}
	
	@Resource
	public void setNetCageDao(NetCageDao netCageDao) {
		this.netCageDao = netCageDao;
	}

	public WaterQualityDao getWaterQualityDao() {
		return waterQualityDao;
	}

	@Resource
	public void setWaterQualityDao(WaterQualityDao waterQualityDao) {
		this.waterQualityDao = waterQualityDao;
	}
	
	

}

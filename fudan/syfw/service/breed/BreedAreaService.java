package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.WaterQuality;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface BreedAreaService extends BaseService<BreedArea> {
	
	//find netcage by netcageId
	public NetCage findNetCageById(String id);
	
	//save netcage
	public void saveNetCage(NetCage netCage);

	//delete netcage
	public void deleteNetCage(String netcageId);
	
	//save waterQuality info
	public void saveWaterQuality(WaterQuality waterQuality);
	
	//delete waterQuality info
	public void deleteWaterQuality(String waterId);
	
	//get breedArea list
	public List<BreedArea> getList(Integer start, Integer limit);
	
	//get breedArea list 分页
	public List<BreedArea> getList(List<BreedArea> list, Integer start, Integer limit);
	
	//get total number of breedArea
	public int getCount();
	
}

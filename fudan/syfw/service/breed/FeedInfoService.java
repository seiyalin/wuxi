package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.FeedInfo;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface FeedInfoService extends BaseService<FeedInfo> {
	
	
	
	//get material list
	public List<FeedInfo> getList(Integer start, Integer limit);
	
	//get feedInfo list
	public List<FeedInfo> getList(List<FeedInfo> list, Integer start, Integer limit);
	
	//get total number of feedInfo
	public int getCount();
	
}

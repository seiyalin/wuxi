package org.wuxi.fudan.syfw.service.transport;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.TransportationInfo;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface TransportationInfoService extends BaseService<TransportationInfo> {
	

	//save transportation info
	public void saveTrans(TransportationInfo trans, String cages);
	//get trans info list
	public List<TransportationInfo> getList(List<TransportationInfo> list, Integer start, Integer limit);
	
	//get trans info list
	public List<TransportationInfo> getList(Integer start, Integer limit);
	
	//get total number of breedArea
	public int getCount();
	
}

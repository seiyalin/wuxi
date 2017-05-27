package org.wuxi.fudan.syfw.service.transport;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.sale.OrderInfoDao;
import org.wuxi.fudan.syfw.dao.sale.RestaurantCompanyDao;
import org.wuxi.fudan.syfw.dao.transport.TransportationInfoDao;
import org.wuxi.fudan.syfw.model.hibernate.OrderInfo;
import org.wuxi.fudan.syfw.model.hibernate.RestaurantCompany;
import org.wuxi.fudan.syfw.model.hibernate.TransportationInfo;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("transportationInfoService")
public class TransportationInfoServiceImpl extends BaseServiceImpl<TransportationInfo> implements TransportationInfoService{
	
	private TransportationInfoDao transportationInfoDao;
	
	
	PageResult pageResult;
	
	//construtor
	public TransportationInfoServiceImpl(){
		
	}
	
	//get trans info list
	public List<TransportationInfo> getList(List<TransportationInfo> list, Integer start, Integer limit){
		pageResult = transportationInfoDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get trans info list
	public List<TransportationInfo> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(TransportationInfo.class, "transportationInfo");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = transportationInfoDao.getPageResult(queryHelper, start/limit + 1, limit);

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


	public TransportationInfoDao getTransportationInfoDao() {
		return transportationInfoDao;
	}


	@Resource
	public void setTransportationInfoDao(TransportationInfoDao transportationInfoDao) {
		super.setBaseDao(transportationInfoDao);
		this.transportationInfoDao = transportationInfoDao;
	}

	
	
	
	

}

package org.wuxi.fudan.syfw.service.traceGi;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.dao.traceGi.TraceDao;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("traceService")
public class TraceServiceImpl extends BaseServiceImpl<Trace> implements TraceService{
	
	private TraceDao traceDao;
	
	
	
	//construtor
	public TraceServiceImpl(){
		
	}



	public TraceDao getTraceDao() {
		return traceDao;
	}



	@Resource
	public void setTraceDao(TraceDao traceDao) {
		super.setBaseDao(traceDao);
		this.traceDao = traceDao;
	}
	
	

}

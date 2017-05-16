package org.wuxi.fudan.syfw.service.traceGi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.traceGi.CrabGiDao;
import org.wuxi.fudan.syfw.dao.traceGi.TraceDao;
import org.wuxi.fudan.syfw.dao.traceGi.TrepangGiDao;
import org.wuxi.fudan.syfw.model.hibernate.CrabGi;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.model.hibernate.TrepangGi;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("traceService")
public class TraceServiceImpl extends BaseServiceImpl<Trace> implements TraceService{
	
	private TraceDao traceDao;
	private CrabGiDao crabGiDao;
	private TrepangGiDao trepangGiDao;
	
	//where子句
	private String whereClause = "";
	
	//construtor
	public TraceServiceImpl(){
		
	}

	//save crab gi
	public void saveCrabGi(CrabGi crab){
		crabGiDao.save(crab);
	}
	
	//delete crab gi
	public void deleteCrabGi(String crabId){
		crabGiDao.delete(crabId);
	}
	
	//get crabGi by crab type 
	public List<CrabGi> findCrabByType(String type){
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crab");
		queryHelper.addCondition("CrabGi.type=?", type);
		return crabGiDao.findObjects(queryHelper);
	}
	
	public String addWhereClause(String condition){
		if (whereClause.length() > 1) {//非第一个查询条件
			whereClause += " AND " + condition;
		} else {//第一个查询条件
			whereClause += " WHERE " + condition;
		}
		return whereClause;
	}
	
	//get crabGi by appearance
	public List<CrabGi> findCrabByAppearance(Map<String, String> appear){
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crab");
		List<String> params = new ArrayList<String>();
		whereClause = "";
		
		String hql = "CrabGi.app=?";
		for(Map.Entry<String, String> entry:appear.entrySet()){
			hql.replace("app", entry.getKey());
			whereClause = addWhereClause(hql);
			params.add(entry.getValue());
		}
		
		queryHelper.addCondition(whereClause, params);
		return crabGiDao.findObjects(queryHelper);
	}
	
	
	public List<CrabGi> findCrabByAppearance( String crust, String belly, String claw, String fair, String clamp, String back, String paw){
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crab");
		List<String> params = new ArrayList<String>();
		whereClause = "";
		
		/*String hql = "CrabGi.app=?";
		for(Map.Entry<String, String> entry:appear.entrySet()){
			hql.replace("app", entry.getKey());
			whereClause = addWhereClause(hql);
			params.add(entry.getValue());
		}*/
		
		if(crust != null){
			whereClause = addWhereClause("CrabGi.crust=?");
			params.add(crust);
		}
		if(belly != null){
			whereClause = addWhereClause("CrabGi.belly=?");
			params.add(belly);
		}
		if(claw != null){
			whereClause = addWhereClause("CrabGi.claw=?");
			params.add(claw);
		}
		if(fair != null){
			whereClause = addWhereClause("CrabGi.fair=?");
			params.add(fair);
		}
		if(clamp != null){
			whereClause = addWhereClause("CrabGi.clamp=?");
			params.add(clamp);
		}
		if(back != null){
			whereClause = addWhereClause("CrabGi.back=?");
			params.add(back);
		}
		if(paw != null){
			whereClause = addWhereClause("CrabGi.paw=?");
			params.add(paw);
		}
		
		queryHelper.addCondition(whereClause, params);
		return crabGiDao.findObjects(queryHelper);
	}
	
	
	
	//save trepang gi
	public void saveTrepangGi(TrepangGi trepang){
		trepangGiDao.save(trepang);
	}
	
	//delete trepang gi
	public void deleteTrepangGi(String trepangId){
		trepangGiDao.delete(trepangId);
	}
	
	//get trepangGi by brand 
	public List<TrepangGi> findTrepangByBrand(String brand){
		QueryHelper queryHelper = new QueryHelper(TrepangGi.class, "trepang");
		queryHelper.addCondition("TrepangGi.trepangBrand=?", brand);
		return trepangGiDao.findObjects(queryHelper);
	}
	
	//get trepangGi by type 
	public List<TrepangGi> findTrepangByType(String type){
		QueryHelper queryHelper = new QueryHelper(TrepangGi.class, "trepang");
		queryHelper.addCondition("TrepangGi.trepangType=?", type);
		return trepangGiDao.findObjects(queryHelper);
	}
	
	//find trepang by appearance
	public List<TrepangGi> findTrepangByAppearance(String color, String shape){
		QueryHelper queryHelper = new QueryHelper(TrepangGi.class, "trepang");
		List<String> params = new ArrayList<String>();
		whereClause = "";
		if(color != null){
			whereClause = addWhereClause("TrepangGi.color=?");
			params.add(color);
		}
		if(shape != null){
			whereClause = addWhereClause("TrepangGi.shape=?");
			params.add(shape);
		}
		queryHelper.addCondition(whereClause, params);
		return trepangGiDao.findObjects(queryHelper);
	}

	public TraceDao getTraceDao() {
		return traceDao;
	}



	@Resource
	public void setTraceDao(TraceDao traceDao) {
		super.setBaseDao(traceDao);
		this.traceDao = traceDao;
	}



	public CrabGiDao getCrabGiDao() {
		return crabGiDao;
	}


	@Resource
	public void setCrabGiDao(CrabGiDao crabGiDao) {
		this.crabGiDao = crabGiDao;
	}



	public TrepangGiDao getTrepangGiDao() {
		return trepangGiDao;
	}


	@Resource
	public void setTrepangGiDao(TrepangGiDao trepangGiDao) {
		this.trepangGiDao = trepangGiDao;
	}
	
	

}

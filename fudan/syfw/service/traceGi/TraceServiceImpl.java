package org.wuxi.fudan.syfw.service.traceGi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.traceGi.CrabGiDao;
import org.wuxi.fudan.syfw.dao.traceGi.TraceDao;
import org.wuxi.fudan.syfw.dao.traceGi.TrepangGiDao;
import org.wuxi.fudan.syfw.model.hibernate.CrabGi;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.model.hibernate.TransportationInfo;
import org.wuxi.fudan.syfw.model.hibernate.TrepangGi;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("traceService")
public class TraceServiceImpl extends BaseServiceImpl<Trace> implements TraceService{
	
	private TraceDao traceDao;
	private CrabGiDao crabGiDao;
	private TrepangGiDao trepangGiDao;
	
	PageResult pageResult;
	
	//where子句
	private String whereClause = "";
	
	//construtor
	public TraceServiceImpl(){
		
	}

	//save crab gi
	public void saveCrabGi(CrabGi crab){
		crabGiDao.save(crab);
	}
	
	//find all crab
	public List<CrabGi> getCrab(Integer start, Integer limit) {
		
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crabGi");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = crabGiDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//find all trepang
	public List<TrepangGi> getTrepang(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(TrepangGi.class, "trepangGi");
				//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = trepangGiDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//find epcis food by cageId
	public List<Trace> traceByCage(Integer start, Integer limit, String cageId){
		QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
		queryHelper.addCondition("trace.processInfo.cageId=?", cageId);
		queryHelper.addOrderByProperty("trace.epcis", "DESC");
		pageResult = traceDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//find epcis food by breedId
	public List<Trace> traceByBreed(Integer start, Integer limit, String breedId){
		QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
		queryHelper.addCondition("trace.breedNo.breedNo =?", breedId);
		queryHelper.addOrderByProperty("trace.epcis", "DESC");
		pageResult = traceDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//find epcis food by transId
	public List<Trace> traceByTrans(Integer start, Integer limit, String transId){
		QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
		queryHelper.addCondition("trace.transportationInfo.transId =?", transId);
		queryHelper.addOrderByProperty("trace.epcis", "DESC");
		pageResult = traceDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
		
	//find epcis food by restaurant
	public List<Trace> traceByRest(Integer start, Integer limit, String restaurant){
		QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
		queryHelper.addCondition("trace.restaurant =?", restaurant);
		queryHelper.addOrderByProperty("trace.epcis", "DESC");
		pageResult = traceDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	/*不分页*/
	//find epcis food by cageId
		public List<Trace> traceByCage(String cageId){
			QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
			queryHelper.addCondition("trace.processInfo.cageId=?", cageId);
			queryHelper.addOrderByProperty("trace.epcis", "DESC");
	//		pageResult = traceDao.getPageResult(queryHelper, start/limit + 1, limit);

			return traceDao.findObjects(queryHelper);
		}
		
		//find epcis food by breedId
		public List<Trace> traceByBreed(String breedId){
			QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
			queryHelper.addCondition("trace.breedNo.breedNo =?", breedId);
			queryHelper.addOrderByProperty("trace.epcis", "DESC");
			return traceDao.findObjects(queryHelper);
		}
		
		//find epcis food by transId
		public List<Trace> traceByTrans(String transId){
			QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
			queryHelper.addCondition("trace.transportationInfo.transId =?", transId);
			queryHelper.addOrderByProperty("trace.epcis", "DESC");
			return traceDao.findObjects(queryHelper);
		}
			
		//find epcis food by restaurant
		public List<Trace> traceByRest(String restaurant){
			QueryHelper queryHelper = new QueryHelper(Trace.class, "trace");
			queryHelper.addCondition("trace.restaurant =?", restaurant);
			queryHelper.addOrderByProperty("trace.epcis", "DESC");
			return traceDao.findObjects(queryHelper);
		}
		
	//get total number
	public int getCount() {
			// TODO Auto-generated method stub
		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}
	//delete crab gi
	public void deleteCrabGi(String crabId){
		crabGiDao.delete(crabId);
	}
	
	//get crabGi by crab type 
	public List<CrabGi> findCrabByType(String type){
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crab");
		queryHelper.addCondition("crab.type=?", type);
		return crabGiDao.findObjects(queryHelper);
	}
	
	public String addWhereClause(String condition){
		if (whereClause.length() > 1) {//非第一个查询条件
			whereClause += " AND " + condition;
		} else {//第一个查询条件
			whereClause += condition;
		}
		return whereClause;
	}
	
	//get crabGi by appearance
	public List<CrabGi> findCrabByAppearance(Map<String, String> appear){
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crab");
		List<String> params = new ArrayList<String>();
		whereClause = "";
		
		StringBuffer hql = new StringBuffer("crab.");
		for(Map.Entry<String, String> entry:appear.entrySet()){
			hql.replace(5, hql.length(), entry.getKey());
			hql.append("=?");
			
			whereClause = addWhereClause(hql.toString());
			params.add(entry.getValue());
		}
		
		queryHelper.addCondition(whereClause, params);
		return crabGiDao.findObjects(queryHelper);
	}
	
	
	public List<CrabGi> findCrabByAppearance( String crust, String belly, String claw, String fair, String clamp, String back, String paw){
		QueryHelper queryHelper = new QueryHelper(CrabGi.class, "crab");
		//List<String> params = new ArrayList<String>();
		whereClause = "";
		
		/*String hql = "CrabGi.app=?";
		for(Map.Entry<String, String> entry:appear.entrySet()){
			hql.replace("app", entry.getKey());
			whereClause = addWhereClause(hql);
			params.add(entry.getValue());
		}*/
		
		if(crust != null && !(crust.equals("unknown"))){
			whereClause = addWhereClause("(crab.crust like '%"+ crust+ "%' or crab.crust='其他')");
			//params.add(crust);
		}
		if(belly != null && !(belly.equals("unknown"))){
			whereClause = addWhereClause("(crab.belly like '%"+ belly+ "%' or crab.belly='其他')");
			//params.add(belly);
		}
		if(claw != null && !(claw.equals("unknown"))){
			whereClause = addWhereClause("(crab.claw like '%"+ claw+ "%' or crab.claw='其他')");
			//params.add(claw);
		}
		if(fair != null && !(fair.equals("unknown"))){
			whereClause = addWhereClause("(crab.fair like '%"+ fair+ "%' or crab.fair='其他')");
			//params.add(fair);
		}
		if(clamp != null && !(clamp.equals("unknown"))){
			whereClause = addWhereClause("(crab.clamp like '%"+ clamp+ "%' or crab.clamp='其他')");
			//params.add(clamp);
		}
		if(back != null && !(back.equals("unknown"))){
			whereClause = addWhereClause("(crab.back like '%"+ back+ "%' or crab.back='其他')");
			//params.add(back);
		}
		if(paw != null && !(paw.equals("unknown"))){
			whereClause = addWhereClause("(crab.paw like '%"+ paw+ "%' or crab.paw='其他')");
			//params.add(paw);
		}
		
		queryHelper.addCondition(whereClause);
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
		queryHelper.addCondition("trepang.trepangBrand=?", brand);
		return trepangGiDao.findObjects(queryHelper);
	}
	
	//get trepangGi by type 
	public List<TrepangGi> findTrepangByType(String type){
		QueryHelper queryHelper = new QueryHelper(TrepangGi.class, "trepang");
		queryHelper.addCondition("trepang.trepangType=?", type);
		return trepangGiDao.findObjects(queryHelper);
	}
	
	//find trepang by appearance
	public List<TrepangGi> findTrepangByAppearance(String color, String shape){
		QueryHelper queryHelper = new QueryHelper(TrepangGi.class, "trepang");
		//List<String> params = new ArrayList<String>();
		whereClause = "";
		if(color != null && !(color.equals("unknown"))){
			whereClause = addWhereClause("(trepang.color like '%"+ color+ "%' or trepang.color='其他')");
			//params.add(color);
		}
		if(shape != null && !(shape.equals("unknown"))){
			whereClause = addWhereClause("(trepang.shape like '%"+ shape+ "%' or trepang.shape='其他')");
			//params.add(shape);
		}
		queryHelper.addCondition(whereClause);
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

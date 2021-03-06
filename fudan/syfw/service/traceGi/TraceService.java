package org.wuxi.fudan.syfw.service.traceGi;

import java.util.List;
import java.util.Map;

import org.wuxi.fudan.syfw.model.hibernate.CrabGi;
import org.wuxi.fudan.syfw.model.hibernate.Trace;
import org.wuxi.fudan.syfw.model.hibernate.TrepangGi;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface TraceService extends BaseService<Trace> {
	
	//save crab gi
	public void saveCrabGi(CrabGi crab);
	//delete crab gi
	public void deleteCrabGi(String crabId);
	//get crabGi by crab type 
	public List<CrabGi> findCrabByType(String type);
	
	public String addWhereClause(String condition);
	
	//get crabGi by appearance
	public List<CrabGi> findCrabByAppearance(Map<String, String> appear);
	public List<CrabGi> findCrabByAppearance( String crust, String belly, String claw, String fair, String clamp, String back, String paw);
	
	//find all crab
	public List<CrabGi> getCrab(Integer start, Integer limit);
	
	//find all trepang
	public List<TrepangGi> getTrepang(Integer start, Integer limit);
	
	//find epcis food by cageId
	public List<Trace> traceByCage(Integer start, Integer limit, String cageId);
		
	//find epcis food by breedId
	public List<Trace> traceByBreed(Integer start, Integer limit, String breedId);
	
	//find epcis food by transId
	public List<Trace> traceByTrans(Integer start, Integer limit, String transId);
	
	//find epcis food by restaurant
	public List<Trace> traceByRest(Integer start, Integer limit, String restaurant);
	
	//find epcis food by cageId
	public List<Trace> traceByCage(String cageId);
	
	//find epcis food by breedId
	public List<Trace> traceByBreed(String breedId);
	
	//find epcis food by transId
	public List<Trace> traceByTrans(String transId);
	
	//find epcis food by restaurant
	public List<Trace> traceByRest(String restaurant);
			
		
	//get total number
	public int getCount();
	
	//save trepang gi
	public void saveTrepangGi(TrepangGi trepang);
	//delete trepang gi
	public void deleteTrepangGi(String trepangId);
	
	//get trepangGi by brand 
	public List<TrepangGi> findTrepangByBrand(String brand);
	//get trepangGi by type 
	public List<TrepangGi> findTrepangByType(String type);
	//find trepang by appearance
	public List<TrepangGi> findTrepangByAppearance(String color, String shape);
	
}

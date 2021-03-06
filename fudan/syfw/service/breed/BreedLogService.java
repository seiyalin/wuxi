package org.wuxi.fudan.syfw.service.breed;

import java.util.List;
import java.util.Set;

import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedLog;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.IllnessInfo;
import org.wuxi.fudan.syfw.model.hibernate.OutPond;
import org.wuxi.fudan.syfw.model.hibernate.QualityControl;
import org.wuxi.fudan.syfw.model.hibernate.VaccineInfo;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface BreedLogService extends BaseService<BreedNo> {
	
	//save breed log
	public void saveBreedLog(BreedLog breedLog);

	//delete breedLog
	public void deleteBreedLog(String logId);
	
	//save illness info
	public void saveIllnessInfo(IllnessInfo illnessInfo);
	
	//delete illness info
	public void deleteIllnessInfo(String illnessId);
	
	//save vaccine info
	public void saveVaccineInfo(VaccineInfo vaccineInfo);
	
	//delete vaccine info
	public void deleteVaccineInfo(String vaccineId);
	
	//save outPond info
	public void saveOutPond(OutPond outPond);
	
	//save outPond info
	public void saveOutPondAndNo(OutPond outPond, String set);
	
	//delete outPond info
	public void deleteOutPond(String outPondId);
	
/*	//session缓存中逐出该对象
	public void deleteOutPondPersist(OutPond outPond);*/
	
	//save qc info
	public void saveQualityControl(QualityControl qualityControl);
	
	//delete qc info
	public void deleteQualityControl(String qcId);
	
	//获取公司下的所有投苗记录
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedCompany breedCompany);
	
	//获取公司下的所有投苗记录,不分页
	public List<BreedNo> getBreedNo(BreedCompany breedCompany);
	
	//获取养殖区域的所有投苗记录
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedArea breedArea);
	
	//获取公司下未出塘的产品批次
	public List<BreedNo> getBreedNoInPond(BreedCompany breedCompany);
		
	//get breedNo list
	public List<BreedNo> getList(Integer start, Integer limit);
	
	//get breedNo list
	public List<BreedNo> getNoList(List<BreedNo> list, Integer start, Integer limit);
		
	//get breedLog list
	public List<BreedLog> getList(List<BreedLog> list, Integer start, Integer limit);
	//获取某批产品下的所有喂养记录
	public List<BreedLog> getBreedLog(Integer start, Integer limit, BreedNo breedNo);
	//get outpond list
	public List<OutPond> getOutPondList(List<OutPond> list, Integer start, Integer limit);
	
	//get outpond list
	public List<OutPond> getOutPondList(Integer start, Integer limit);
		
	//获取公司下的所有喂养记录
	public List<BreedLog> getBreedLog(Integer start, Integer limit, BreedCompany breedCompany);
	
	//获取某批产品的所有疾病记录,不分页
	public List<IllnessInfo> getIllness(BreedNo breedBatch);
	
	//获取某批产品的所有检疫记录,不分页
	public List<VaccineInfo> getVaccine(BreedNo breedBatch);
	
	//获取某批产品的所有质检记录,不分页
	public List<QualityControl> getQc(BreedNo breedBatch);
	
	//获取公司下的所有出塘记录
	public List<OutPond> getOutPond(Integer start, Integer limit, BreedCompany breedCompany);
	
	//get total number of breedNo
	public int getCount();
	
}

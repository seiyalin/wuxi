package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

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
	
	//delete outPond info
	public void deleteOutPond(String outPondId);
	
	//save qc info
	public void saveQualityControl(QualityControl qualityControl);
	
	//delete qc info
	public void deleteQualityControl(String qcId);
	
	//获取公司下的所有投苗记录
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedCompany breedCompany);
	
	//获取养殖区域的所有投苗记录
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedArea breedArea);
	
	//获取公司下未出塘的产品批次
	public List<BreedNo> getBreedNoInPond(BreedCompany breedCompany);
		
	//get breedNo list
	public List<BreedNo> getList(Integer start, Integer limit);
	
	//get breedLog list
	public List<BreedLog> getList(List<BreedLog> list, Integer start, Integer limit);
	
	//get total number of breedNo
	public int getCount();
	
}

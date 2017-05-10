package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.breed.BreedLogDao;
import org.wuxi.fudan.syfw.dao.breed.BreedNoDao;
import org.wuxi.fudan.syfw.dao.breed.BreedStaffDaoImpl;
import org.wuxi.fudan.syfw.dao.breed.IllnessInfoDao;
import org.wuxi.fudan.syfw.dao.breed.OutPondDao;
import org.wuxi.fudan.syfw.dao.breed.QualityControlDao;
import org.wuxi.fudan.syfw.dao.breed.VaccineInfoDao;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedLog;
import org.wuxi.fudan.syfw.model.hibernate.BreedNo;
import org.wuxi.fudan.syfw.model.hibernate.IllnessInfo;
import org.wuxi.fudan.syfw.model.hibernate.OutPond;
import org.wuxi.fudan.syfw.model.hibernate.QualityControl;
import org.wuxi.fudan.syfw.model.hibernate.VaccineInfo;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("breedLogService")
public class BreedLogServiceImpl extends BaseServiceImpl<BreedNo> implements BreedLogService{
	
	private BreedNoDao  breedNoDao;
	private BreedLogDao breedLogDao;
	private IllnessInfoDao illnessInfoDao;
	private VaccineInfoDao vaccineInfoDao;
	private OutPondDao   outPondDao;
	private QualityControlDao qualityControlDao;
	
	
	PageResult pageResult;
	
	//construtor
	public BreedLogServiceImpl(){
		
	}
	
	//save breed log
	public void saveBreedLog(BreedLog breedLog){
		breedLogDao.save(breedLog);
	}
	
	//delete breedLog
	public void deleteBreedLog(String logId){
		breedLogDao.delete(logId);
	}
	
	//save illness info
	public void saveIllnessInfo(IllnessInfo illnessInfo){
		illnessInfoDao.save(illnessInfo);
	}
		
	//delete illness info
	public void deleteIllnessInfo(String illnessId){
		illnessInfoDao.delete(illnessId);
	}
		
	//save vaccine info
	public void saveVaccineInfo(VaccineInfo vaccineInfo){
		vaccineInfoDao.save(vaccineInfo);
	}
		
	//delete vaccine info
	public void deleteVaccineInfo(String vaccineId){
		vaccineInfoDao.delete(vaccineId);
	}
	
	//save outPond info
	public void saveOutPond(OutPond outPond){
		outPondDao.save(outPond);
	}
			
		//delete outPond info
	public void deleteOutPond(String outPondId){
		outPondDao.delete(outPondId);
	}
	
	//save qc info
	public void saveQualityControl(QualityControl qualityControl){
		qualityControlDao.save(qualityControl);
	}
				
	//delete qc info
	public void deleteQualityControl(String qcId){
		qualityControlDao.delete(qcId);
	}
	
	//获取公司下的所有投苗记录
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
		//add condition
		queryHelper.addCondition("BreedNo.netCage.breedArea.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("BreedNo.inPondTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//获取养殖区域的所有投苗记录
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedArea breedArea){
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			//add condition
		queryHelper.addCondition("BreedNo.netCage.breedArea = ?", breedArea);
		queryHelper.addOrderByProperty("BreedNo.inPondTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//获取公司下未出塘的产品批次
	public List<BreedNo> getBreedNoInPond(BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			//add condition
		queryHelper.addCondition("BreedNo.netCage.breedArea.breedCompany = ?", breedCompany);
		queryHelper.addCondition("BreedNo.outPond = ?",(Object)null );
		queryHelper.addOrderByProperty("BreedNo.inPondTime", "DESC");  //降序排列
		return breedNoDao.findObjects(queryHelper);
		//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		//return pageResult.getItems();
	}
	
	
	//get breedNo list
	public List<BreedNo> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			//pageNo=start/limit + 1       起始页从第一页开始的
		queryHelper.addOrderByProperty("BreedNo.inPondTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
		
	//get total number of breedNo
	public int getCount() {
		// TODO Auto-generated method stub

		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}
	
	
	

	public BreedNoDao getBreedNoDao() {
		return breedNoDao;
	}

	@Resource
	public void setBreedNoDao(BreedNoDao breedNoDao) {
		super.setBaseDao(breedNoDao);
		this.breedNoDao = breedNoDao;
	}

	public BreedLogDao getBreedLogDao() {
		return breedLogDao;
	}

	@Resource
	public void setBreedLogDao(BreedLogDao breedLogDao) {
		this.breedLogDao = breedLogDao;
	}

	public IllnessInfoDao getIllnessInfoDao() {
		return illnessInfoDao;
	}

	@Resource
	public void setIllnessInfoDao(IllnessInfoDao illnessInfoDao) {
		this.illnessInfoDao = illnessInfoDao;
	}

	public VaccineInfoDao getVaccineInfoDao() {
		return vaccineInfoDao;
	}

	@Resource
	public void setVaccineInfoDao(VaccineInfoDao vaccineInfoDao) {
		this.vaccineInfoDao = vaccineInfoDao;
	}

	public OutPondDao getOutPondDao() {
		return outPondDao;
	}

	@Resource
	public void setOutPondDao(OutPondDao outPondDao) {
		this.outPondDao = outPondDao;
	}

	public QualityControlDao getQualityControlDao() {
		return qualityControlDao;
	}

	@Resource
	public void setQualityControlDao(QualityControlDao qualityControlDao) {
		this.qualityControlDao = qualityControlDao;
	}
	
	

}

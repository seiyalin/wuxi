package org.wuxi.fudan.syfw.service.breed;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
import org.wuxi.fudan.syfw.model.hibernate.FeedInfo;
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
	
	//save outPond info
	public void saveOutPondAndNo(OutPond outPond, String outPondNos){
		Serializable outPondId = outPondDao.save(outPond);
		OutPond outpond = outPondDao.findObjectById(outPondId);
		if(outPondNos != null){
			 String[] nos = outPondNos.split(",");
			 for(String no:nos){
				 BreedNo br = new BreedNo();
				 br = breedNoDao.findObjectById(no);
				 br.setOutPond(outpond);
				 breedNoDao.update(br);
			 
			 }
		 }
			
	}
			
		//delete outPond info
	public void deleteOutPond(String outPondId){
		outPondDao.delete(outPondId);
	}
	
	/*//session缓存中逐出该对象
	public void deleteOutPondPersist(OutPond outPond){
		outPondDao.deletePersist(outPond);
	}
	*/
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
		/*QueryHelper queryHelper = new QueryHelper("BreedNo breedNo left join fetch breedNo.netCage netcage left join fetch "
				+ "netcage.breedArea breedArea left join fetch breedArea.breedCompany" , "breedCompany");*/
		//add condition
		/*queryHelper.addCondition("breedCompany.companyId = ?", companyId);*/
		//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("breedNo.netCage.breedArea.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("breedNo.inPondTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//获取公司下的所有投苗记录,不分页
	public List<BreedNo> getBreedNo(BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			
			//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("breedNo.netCage.breedArea.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("breedNo.inPondTime", "DESC");  //降序排列
			//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return breedNoDao.findObjects(queryHelper);
	}
		
	//获取某批产品的所有疾病记录,不分页
	public List<IllnessInfo> getIllness(BreedNo breedBatch){
		QueryHelper queryHelper = new QueryHelper(IllnessInfo.class, "illnessInfo");
				
				//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("illnessInfo.breedNo = ?", breedBatch);
		queryHelper.addOrderByProperty("illnessInfo.illTime", "DESC");  //降序排列
				//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return illnessInfoDao.findObjects(queryHelper);
	}
	
	//获取某批产品的所有检疫记录,不分页
	public List<VaccineInfo> getVaccine(BreedNo breedBatch){
		QueryHelper queryHelper = new QueryHelper(VaccineInfo.class, "vaccineInfo");
					
					//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("vaccineInfo.breedNo = ?", breedBatch);
		queryHelper.addOrderByProperty("vaccineInfo.vaccineTime", "DESC");  //降序排列
					//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return vaccineInfoDao.findObjects(queryHelper);
	}
	
	//获取某批产品的所有质检记录,不分页
	public List<QualityControl> getQc(BreedNo breedBatch){
			QueryHelper queryHelper = new QueryHelper(QualityControl.class, "qualityControl");
						
						//隐藏内连接，需要再配置文件多方设置lazy=false
			queryHelper.addCondition("qualityControl.breedNo = ?", breedBatch);
			queryHelper.addOrderByProperty("qualityControl.qcTime", "DESC");  //降序排列
						//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
			return qualityControlDao.findObjects(queryHelper);
		}
	
	
	//获取养殖区域的所有投苗记录，分页
	public List<BreedNo> getBreedNo(Integer start, Integer limit, BreedArea breedArea){
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			//add condition
		queryHelper.addCondition("breedNo.netCage.breedArea = ?", breedArea);
		queryHelper.addOrderByProperty("breedNo.inPondTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//获取公司下未出塘的产品批次
	public List<BreedNo> getBreedNoInPond(BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			//add condition
		queryHelper.addCondition("breedNo.netCage.breedArea.breedCompany = ?", breedCompany);
		/*queryHelper.addCondition("breedNo.outPond = ?",(Object)null );*/
		queryHelper.addCondition("breedNo.outPond is null");
		queryHelper.addOrderByProperty("breedNo.inPondTime", "DESC");  //降序排列
		return breedNoDao.findObjects(queryHelper);
		//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		//return pageResult.getItems();
	}
	
	//获取公司下的所有喂养记录
	public List<BreedLog> getBreedLog(Integer start, Integer limit, BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(BreedLog.class, "breedLog");
			
		//隐藏内连接，需要在配置文件多方设置lazy=false
		queryHelper.addCondition("breedLog.breedNo.netCage.breedArea.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("breedLog.breedTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
		}
	
	//获取某批产品下的所有喂养记录
	public List<BreedLog> getBreedLog(Integer start, Integer limit, BreedNo breedNo){
		QueryHelper queryHelper = new QueryHelper(BreedLog.class, "breedLog");
				
			//隐藏内连接，需要在配置文件多方设置lazy=false
		queryHelper.addCondition("breedLog.breedNo = ?", breedNo);
		queryHelper.addOrderByProperty("breedLog.breedTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//get breedNo list
	public List<BreedNo> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(BreedNo.class, "breedNo");
			//pageNo=start/limit + 1       起始页从第一页开始的
		queryHelper.addOrderByProperty("breedNo.inPondTime", "DESC");  //降序排列
		pageResult = breedNoDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
		
	//get breedNo list
	public List<BreedNo> getNoList(List<BreedNo> list, Integer start, Integer limit){
			pageResult = breedNoDao.getPageResult(list, start/limit + 1, limit);

			return pageResult.getItems();
	}
		
	//get breedLog list
	public List<BreedLog> getList(List<BreedLog> list, Integer start, Integer limit){
		pageResult = breedLogDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get outpond list
	public List<OutPond> getOutPondList(List<OutPond> list, Integer start, Integer limit){
		pageResult = outPondDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get outpond list
	public List<OutPond> getOutPondList(Integer start, Integer limit){
		QueryHelper queryHelper = new QueryHelper(OutPond.class, "outPond");
		queryHelper.addOrderByProperty("outPond.outTime", "DESC");  //降序排列
		pageResult = outPondDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
	
	//获取公司下的所有出塘记录
	public List<OutPond> getOutPond(Integer start, Integer limit, BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(OutPond.class, "outPond");
				
			//隐藏内连接，需要在配置文件多方设置lazy=false
		queryHelper.addCondition("outPond.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("outPond.outTime", "DESC");  //降序排列
		pageResult = outPondDao.getPageResult(queryHelper, start/limit+1, limit);
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

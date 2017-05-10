package org.wuxi.fudan.syfw.service.supervision;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.supervision.ComplaintInfoDao;
import org.wuxi.fudan.syfw.dao.supervision.ComplaintReactDao;
import org.wuxi.fudan.syfw.model.hibernate.ComplaintInfo;
import org.wuxi.fudan.syfw.model.hibernate.ComplaintReact;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("supervisionService")
public class SupervisionServiceImpl extends BaseServiceImpl<ComplaintInfo> implements SupervisionService{
	
	private ComplaintInfoDao complaintInfoDao;
	private ComplaintReactDao complaintReactDao;

	PageResult pageResult;
	
	//construtor
	public SupervisionServiceImpl(){
		
	}
	
	//save complaint reaction
	public void addComplaintReact(ComplaintReact complaintReact){
		complaintReactDao.save(complaintReact);
	}
	
	//delete complaint reaction
	public void deleteComplaintReact(String reactId){
		complaintReactDao.delete(reactId);
	}
	
	//get complaintInfo list
	public List<ComplaintInfo> getList(Integer start, Integer limit) {
		
		QueryHelper queryHelper = new QueryHelper(ComplaintInfo.class, "complaintInfo");
		//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = complaintInfoDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get total number of complaintInfo
	public int getCount() {
		// TODO Auto-generated method stub

		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}



	public ComplaintInfoDao getComplaintInfoDao() {
		return complaintInfoDao;
	}



	//@Resource: dao注入service
	@Resource
	public void setComplaintInfoDao(ComplaintInfoDao complaintInfoDao) {
		//将complaintInfoDao传递给BaseServiceImpl转换为BaseDao
		super.setBaseDao(complaintInfoDao);
		this.complaintInfoDao = complaintInfoDao;
	}




	public ComplaintReactDao getComplaintReactDao() {
		return complaintReactDao;
	}


	@Resource
	public void setComplaintReactDao(ComplaintReactDao complaintReactDao) {
		this.complaintReactDao = complaintReactDao;
	}

}

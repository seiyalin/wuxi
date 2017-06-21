package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wuxi.fudan.syfw.common.PageResult;
import org.wuxi.fudan.syfw.common.QueryHelper;
import org.wuxi.fudan.syfw.dao.breed.BreedAreaDao;
import org.wuxi.fudan.syfw.dao.breed.BreedStaffDao;
import org.wuxi.fudan.syfw.dao.breed.FeedInfoDao;
import org.wuxi.fudan.syfw.dao.breed.NetCageDao;
import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.BreedStaff;
import org.wuxi.fudan.syfw.model.hibernate.FeedInfo;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.service.base.BaseServiceImpl;

@Service("feedInfoService")
public class FeedInfoServiceImpl extends BaseServiceImpl<FeedInfo> implements FeedInfoService{
	

	private FeedInfoDao   feedInfoDao;
	
	public FeedInfoDao getFeedInfoDao() {
		return feedInfoDao;
	}


	@Resource
	public void setFeedInfoDao(FeedInfoDao feedInfoDao) {
		super.setBaseDao(feedInfoDao);
		this.feedInfoDao = feedInfoDao;
	}

	PageResult pageResult;
	
	//construtor
	public FeedInfoServiceImpl(){
		
	}
	
	
	//get feedInfo list
	public List<FeedInfo> getList(Integer start, Integer limit) {
			
		QueryHelper queryHelper = new QueryHelper(BreedStaff.class, "breedStaff");
			//pageNo=start/limit + 1       起始页从第一页开始的
		pageResult = feedInfoDao.getPageResult(queryHelper, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//get feedInfo list
	public List<FeedInfo> getList(List<FeedInfo> list, Integer start, Integer limit) {
		pageResult = feedInfoDao.getPageResult(list, start/limit + 1, limit);

		return pageResult.getItems();
	}
	
	//获取公司下的所有原料,不分页
	public List<FeedInfo> getFeed(BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(FeedInfo.class, "feedInfo");
				
				//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("feedInfo.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("feedInfo.stockTime", "DESC");  //降序排列
				//pageResult = breedNoDao.getPageResult(queryHelper, start/limit+1, limit);
		return feedInfoDao.findObjects(queryHelper);
	
	}
	
	//获取公司下的所有原料,分页
	public List<FeedInfo> getFeed( Integer start, Integer limit, BreedCompany breedCompany){
		QueryHelper queryHelper = new QueryHelper(FeedInfo.class, "feedInfo");
					
					//隐藏内连接，需要再配置文件多方设置lazy=false
		queryHelper.addCondition("feedInfo.breedCompany = ?", breedCompany);
		queryHelper.addOrderByProperty("feedInfo.stockTime", "DESC");  //降序排列
		pageResult = feedInfoDao.getPageResult(queryHelper, start/limit+1, limit);
		return pageResult.getItems();
	}
		
	//get total number of breedStaff
	public int getCount() {
		// TODO Auto-generated method stub

		if(pageResult != null){
			return (int) pageResult.getTotalCount();
		}else{
			return 0;
		}
	}

	
	
	
	
	
	

}

package org.wuxi.fudan.syfw.action.statistic;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import net.sf.json.JSONObject;
import org.wuxi.fudan.syfw.common.JsonUtils;
import org.wuxi.fudan.syfw.model.hibernate.ComplaintInfo;
import org.wuxi.fudan.syfw.model.hibernate.OutPond;
import org.wuxi.fudan.syfw.service.breed.BreedLogService;
import org.wuxi.fudan.syfw.service.supervision.SupervisionService;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticAction extends ActionSupport{

	/**
	 * 投诉，监管管理
	 */
	private static final long serialVersionUID = 1L;

	private SupervisionService supervisionService;
	
	private BreedLogService breedLogService;

	private JSONObject ComplaintStatDisplay;   //投诉数据响应
	private JSONObject OutPondStatDisplay;   //产量数据响应
	private JSONObject statDisplay;
	
	HashMap<Integer, String> map = new HashMap<>();
	
	public String getStat(){
		List<OutPond> list = breedLogService.getOutPondList(0, 7);
		String[] exclude = new String[]{"handler","hibernateLazyInitializer","outPondId","breedCompany","breedNos","personInCharge"};
		statDisplay = JsonUtils.toJSONResult(true, list, exclude);
		List<ComplaintInfo> clist = supervisionService.getList();
		int count = clist.size();
		List<ComplaintInfo> unhandled_list = supervisionService.getUnhandledComplaint();
		int unhandled_count = unhandled_list.size();
		statDisplay.put("total_complaint", count);
		statDisplay.put("unhandled_complaint", unhandled_count);
		return SUCCESS;
	}
	
	
     public String getComplaintStat(){
    	 try {
    		
 			List<ComplaintInfo> list = supervisionService.getList();
 			int count = list.size();
 			List<ComplaintInfo> unhandled_list = supervisionService.getUnhandledComplaint();
 			int unhandled_count = unhandled_list.size();
 			ComplaintStatDisplay = JsonUtils.toJSONResult(true);
 			ComplaintStatDisplay.put("total_complaint", count);
 			ComplaintStatDisplay.put("unhandled_complaint", unhandled_count);
 			return SUCCESS;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			ComplaintStatDisplay = JsonUtils.toJSONResult(false, "发生未知错误");
     		return ERROR;		
 		}
     }

	public String getOutPondStat(){
		
		List<OutPond> list = breedLogService.getOutPondList(0, 7);
		String[] exclude = new String[]{"handler","hibernateLazyInitializer","outPondId","breedCompany","breedNos","personInCharge"};
		setOutPondStatDisplay(JsonUtils.toJSONResult(true, list, exclude));
		return SUCCESS;
	}
     
	public SupervisionService getSupervisionService() {
		return supervisionService;
	}


	@Resource
	public void setSupervisionService(SupervisionService supervisionService) {
		this.supervisionService = supervisionService;
	}



	public BreedLogService getBreedLogService() {
		return breedLogService;
	}


	@Resource
	public void setBreedLogService(BreedLogService breedLogService) {
		this.breedLogService = breedLogService;
	}



	public JSONObject getComplaintStatDisplay() {
		return ComplaintStatDisplay;
	}



	public void setComplaintStatDisplay(JSONObject complaintStatDisplay) {
		ComplaintStatDisplay = complaintStatDisplay;
	}

	public JSONObject getOutPondStatDisplay() {
		return OutPondStatDisplay;
	}

	public void setOutPondStatDisplay(JSONObject outPondStatDisplay) {
		OutPondStatDisplay = outPondStatDisplay;
	}


	public JSONObject getStatDisplay() {
		return statDisplay;
	}


	public void setStatDisplay(JSONObject statDisplay) {
		this.statDisplay = statDisplay;
	}



	
}

package org.wuxi.fudan.syfw.service.supervision;

import java.util.List;

import org.wuxi.fudan.syfw.model.hibernate.ComplaintInfo;
import org.wuxi.fudan.syfw.model.hibernate.ComplaintReact;
import org.wuxi.fudan.syfw.service.base.BaseService;

public interface SupervisionService extends BaseService<ComplaintInfo> {

	//save complaint reaction
	public void addComplaintReact(ComplaintReact complaintReact);
	
	//delete complaint reaction
	public void deleteComplaintReact(String reactId);
	
	//get accepted complaintInfo list
	public List<ComplaintInfo> getList(Integer start, Integer limit, String companyId);
	
	//get launched complaintInfo list
	public List<ComplaintInfo> getLaunchedComplaint(Integer start, Integer limit, String userId);
		
	//get complaintInfo list
	public List<ComplaintInfo> getList(Integer start, Integer limit);
	
	//get total number of complaintInfo
	public int getCount();
}

package org.wuxi.fudan.syfw.service.breed;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.wuxi.fudan.syfw.model.hibernate.BreedArea;
import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.NetCage;
import org.wuxi.fudan.syfw.model.hibernate.WaterQuality;
import org.wuxi.fudan.syfw.service.base.BaseService;

@Path("/BreedAreaService")
@Produces({ "application/json", "application/xml" })
public interface BreedAreaService extends BaseService<BreedArea> {
	
	//find netcage by netcageId
	@GET
	@Path("/getNetCageById/{id}")
	@Produces({ "application/json", "application/xml" })
	public NetCage findNetCageById(@PathParam("id")String id);
	
	//save netcage
	@POST
	@Path("/saveNetCage")
	@Consumes({ "application/json", "application/xml" })
	public void saveNetCage(NetCage netCage);

	//delete netcage
	@DELETE
	@Path("/deleteNetCage/{id}")
	public void deleteNetCage(@PathParam("id")String netcageId);
	
	//save waterQuality info
	@POST
	@Path("/saveWaterQuality")
	@Consumes({ "application/json", "application/xml" })
	public void saveWaterQuality(WaterQuality waterQuality);
	
	//delete waterQuality info
	@DELETE
	@Path("/deleteWaterQuality/{id}")
	public void deleteWaterQuality(@PathParam("id")String waterId);
	
	//get breedArea list
	@GET
	@Path("/getAllBreedArea/{start}/{limit}")
	@Produces({ "application/json", "application/xml" })
	public List<BreedArea> getList(@PathParam("start")Integer start, @PathParam("limit")Integer limit);
	
	//获取公司下的所有养殖区域
	public List<BreedArea> getBreedArea(Integer start, Integer limit, BreedCompany breedCompany);
	
	//获取公司下的所有养殖区域，不分页
	
	public List<BreedArea> getBreedArea(BreedCompany breedCompany);
	
	//get breedArea list 分页
	public List<BreedArea> getList(List<BreedArea> list, Integer start, Integer limit);
	
	//get total number of breedArea
	public int getCount();
	
}

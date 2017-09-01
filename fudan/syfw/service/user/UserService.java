package org.wuxi.fudan.syfw.service.user;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.wuxi.fudan.syfw.model.hibernate.BreedCompany;
import org.wuxi.fudan.syfw.model.hibernate.ProcessCompany;
import org.wuxi.fudan.syfw.model.hibernate.User;
import org.wuxi.fudan.syfw.service.base.BaseService;

@WebService
@Path("/UserService")
@Produces({ "application/json", "application/xml" })
//@Produces注释用来指定将要返回给client端的数据标识类型（MIME）。
//@Produces可以作为class注释，也可以作为方法注释，方法的@Produces注释将会覆盖class的注释。
//覆盖的意思是假如方法声明了自己的Produce，那么以方法的为准，class的仅供参考
public interface UserService extends BaseService<User> {

	//public String findRoleByNameAndPwd(String username, String pwd);
	@GET
	@Path("/getRole/{username}/{password}")
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	/*@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.MULTIPART_FORM_DATA,MediaType.APPLICATION_JSON})*/
	public String findRoleByNameAndPwd(@PathParam("username") String username, @PathParam("password")String pwd);
	
	//save processCompany information
	@POST
	@Path("/saveProcessCompany")
	 @Consumes({ "application/json", "application/xml" })
	public void saveProcessCompany(ProcessCompany processCompany);
	//find processCompany by companyId
	@GET
	@Path("/getProcessCompanyById/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public ProcessCompany findProcessCompanyById(@PathParam("id") String companyId);
	//update processCompany information
	@PUT
	@Path("/updateProcessCompany")
	@Consumes({ "application/json", "application/xml" })
	public void updateProcessCompany(ProcessCompany processCompany);
	
	//save breedCompany information
	@POST
	@Path("/saveBreedCompany")
	//@Consumes与@Produces相反，用来指定可以接受client发送过来的MIME类型，
    //同样可以用于class或者method，也可以指定多个MIME类型,一般用于@PUT，@POST。
    @Consumes({ "application/json", "application/xml" })
	public void saveBreedCompany(BreedCompany breedCompany);
	
	//find breedCompany by companyId
	@GET
	@Path("/getBreedCompanyById/{id}")
/*	@Produces(MediaType.APPLICATION_JSON)*/
	public BreedCompany findBreedCompanyById(@PathParam("id") String companyId);
	
	//find breedCompany by userId
	@GET
	@Path("/getBreedCompany/{id}")
/*	@Produces(MediaType.APPLICATION_JSON)*/
	public BreedCompany findBreedCompanyByUserId(@PathParam("id") String userId);
	
	//update breedCompany information
	@PUT
	@Path("/updateBreedCompany")
	@Consumes({ "application/json", "application/xml" })
	public void updateBreedCompany(BreedCompany breedCompany);
	
	//find processCompany by userId
	@GET
	@Path("/getProcessCompany/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public ProcessCompany findProcessCompanyByUserId(@PathParam("id") String userId);
	
	//find all breedCompany
	@GET
	@Path("/getAllBreedCompany")
	@Produces(MediaType.APPLICATION_XML)
	public List<BreedCompany> findAllBreedCompany();
	
	//find all processCompany
	@GET
	@Path("/getAllProcessCompany")
	@Produces(MediaType.APPLICATION_XML)
	public List<ProcessCompany> findAllProcessCompany();
}

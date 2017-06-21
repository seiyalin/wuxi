package org.wuxi.fudan.syfw.model.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * ProcessCompany entity. @author MyEclipse Persistence Tools
 * 加工企业具体信息
 */

public class ProcessCompany  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private String companyId; 				//加工企业编码
     private String companyName;			//加工企业名称
     private String legalPerson;			//加工企业法人
     private String companyAddress;			//企业地址
     private String contactNumber;			//联系电话
     private String processBatch;			//加工批次
     private String environment;			//加工环境
     private String userId;					//用户id，根据登录用户名可以找到企业信息
 //    private Set processInfos = new HashSet(0);
    // private Set processStaffs = new HashSet(0);
   //  private Set<OrderInfo> orderInfos = new HashSet<OrderInfo>(0);
  //   private Set<TransportationInfo> transportationInfos = new HashSet<TransportationInfo>(0);


    // Constructors

    /** default constructor */
    public ProcessCompany() {
    }

	/** minimal constructor */
    public ProcessCompany(String companyName, String legalPerson, String companyAddress, String contactNumber) {
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.companyAddress = companyAddress;
        this.contactNumber = contactNumber;
    }
    
    /** full constructor */
    public ProcessCompany(String companyName, String legalPerson, String companyAddress, String contactNumber, String processBatch, String environment) {
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.companyAddress = companyAddress;
        this.contactNumber = contactNumber;
        this.processBatch = processBatch;
        this.environment = environment;
      //  this.processInfos = processInfos;
      //  this.processStaffs = processStaffs;
     /*   this.orderInfos = orderInfos;
        this.transportationInfos = transInfos;*/
    }

   
    // Property accessors

    public String getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalPerson() {
        return this.legalPerson;
    }
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getCompanyAddress() {
        return this.companyAddress;
    }
    
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProcessBatch() {
        return this.processBatch;
    }
    
    public void setProcessBatch(String processBatch) {
        this.processBatch = processBatch;
    }

    public String getEnvironment() {
        return this.environment;
    }
    
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

  /*  public Set getProcessInfos() {
        return this.processInfos;
    }
    
    public void setProcessInfos(Set processInfos) {
        this.processInfos = processInfos;
    }
*/
    /*public Set getProcessStaffs() {
        return this.processStaffs;
    }
    
    public void setProcessStaffs(Set processStaffs) {
        this.processStaffs = processStaffs;
    }*/

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*public Set<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(Set<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}


	public Set<TransportationInfo> getTransportationInfos() {
		return transportationInfos;
	}

	public void setTransportationInfos(Set<TransportationInfo> transportationInfos) {
		this.transportationInfos = transportationInfos;
	}*/
   








}
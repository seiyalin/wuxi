package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * ComplaintInfo entity. @author MyEclipse Persistence Tools
 * 投诉信息
 */

public class ComplaintInfo  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String complaintId;		//投诉编号，自动生成
     private String complaintCompany;	//投诉公司
     private String complaintCompanyId;	//投诉公司编号
     private String complaintContent;	//投诉内容 
     private Date complaintTime;		//投诉时间，自动生成
     private String complaintant;		//投诉人
     private String userId;		//投诉人编号
     private String complaintPhone;		//投诉人电话
     private String complaintStatus;	//投诉状态
     private String complaintRole;		//投诉人角色
     private Set complaintReacts = new HashSet(0);


    // Constructors

    /** default constructor */
    public ComplaintInfo() {
    }

    
    /** full constructor */
    public ComplaintInfo(String complaintCompany, String complaintCompanyId, String complaintContent, Date complaintTime, String complaintant, String complaintPhone, String complaintStatus, String complaintRole, Set complaintReacts) {
        this.complaintCompany = complaintCompany;
        this.complaintCompanyId = complaintCompanyId;
        this.complaintContent = complaintContent;
        this.complaintTime = complaintTime;
        this.complaintant = complaintant;
        this.complaintPhone = complaintPhone;
        this.complaintStatus = complaintStatus;
        this.complaintRole = complaintRole;
        this.complaintReacts = complaintReacts;
    }

   
    // Property accessors

    public String getComplaintId() {
        return this.complaintId;
    }
    
    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintCompany() {
        return this.complaintCompany;
    }
    
    public void setComplaintCompany(String complaintCompany) {
        this.complaintCompany = complaintCompany;
    }

    public String getComplaintCompanyId() {
		return complaintCompanyId;
	}


	public void setComplaintCompanyId(String complaintCompanyId) {
		this.complaintCompanyId = complaintCompanyId;
	}


	public String getComplaintContent() {
        return this.complaintContent;
    }
    
    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public Date getComplaintTime() {
        return this.complaintTime;
    }
    
    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getComplaintant() {
        return this.complaintant;
    }
    
    public void setComplaintant(String complaintant) {
        this.complaintant = complaintant;
    }

    public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getComplaintPhone() {
        return this.complaintPhone;
    }
    
    public void setComplaintPhone(String complaintPhone) {
        this.complaintPhone = complaintPhone;
    }

    public String getComplaintStatus() {
        return this.complaintStatus;
    }
    
    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintRole() {
        return this.complaintRole;
    }
    
    public void setComplaintRole(String complaintRole) {
        this.complaintRole = complaintRole;
    }

    public Set getComplaintReacts() {
        return this.complaintReacts;
    }
    
    public void setComplaintReacts(Set complaintReacts) {
        this.complaintReacts = complaintReacts;
    }
   








}
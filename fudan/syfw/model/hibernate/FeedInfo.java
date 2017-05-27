package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * FeedInfo entity. @author MyEclipse Persistence Tools
 * 原料管理（购买饲料，药物，种苗时录入信息）
 */

public class FeedInfo  implements java.io.Serializable, Comparable<FeedInfo> {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feedId;			//原料编号
	 private BreedCompany breedCompany;		//养殖企业
     private String feedCompany;	//原料购买商
     private String contactNumber;	//联系电话
     private String address;		//地址
     private String amount;			//购买数量
     private String handle;		//经手人
     private String storehouse;		//存放库房位置
     private Date stockTime;		//存放日期
     private String stockInfo;		//入库时验收信息
     private String personInCharge;	//负责人
     private String materialType;	//原料类别
     private String materialName;	//原料名
     private Set breedLogs = new HashSet(0);


    // Constructors

    /** default constructor */
    public FeedInfo() {
    }

	/** minimal constructor */
    public FeedInfo(BreedCompany breedCompany, String feedCompany, String contactNumber, String address, String amount, String handler) {
        this.breedCompany = breedCompany;
    	this.feedCompany = feedCompany;
        this.contactNumber = contactNumber;
        this.address = address;
        this.amount = amount;
        this.handle = handler;
    }
    
    /** full constructor */
    public FeedInfo(BreedCompany breedCompany, String feedCompany, String contactNumber, String address, String amount, String handler, String storehouse, Date stockTime, String stockInfo, String personInCharge, String materialType, String materialName, Set breedLogs) {
        this.breedCompany = breedCompany;
    	this.feedCompany = feedCompany;
        this.contactNumber = contactNumber;
        this.address = address;
        this.amount = amount;
        this.handle = handler;
        this.storehouse = storehouse;
        this.stockTime = stockTime;
        this.stockInfo = stockInfo;
        this.personInCharge = personInCharge;
        this.materialType = materialType;
        this.materialName = materialName;
        this.breedLogs = breedLogs;
    }

    //分页
    public int compareTo(FeedInfo feed){
    	return feedId.compareTo(feed.getFeedId());
    }
   
    // Property accessors

    public String getFeedId() {
        return this.feedId;
    }
    
    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public BreedCompany getBreedCompany() {
		return breedCompany;
	}

	public void setBreedCompany(BreedCompany breedCompany) {
		this.breedCompany = breedCompany;
	}

	public String getFeedCompany() {
        return this.feedCompany;
    }
    
    public void setFeedCompany(String feedCompany) {
        this.feedCompany = feedCompany;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return this.amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHandle() {
        return this.handle;
    }
    
    public void setHandle(String handler) {
        this.handle = handler;
    }

    public String getStorehouse() {
        return this.storehouse;
    }
    
    public void setStorehouse(String storehouse) {
        this.storehouse = storehouse;
    }

    public Date getStockTime() {
        return this.stockTime;
    }
    
    public void setStockTime(Date stockTime) {
        this.stockTime = stockTime;
    }

    public String getStockInfo() {
        return this.stockInfo;
    }
    
    public void setStockInfo(String stockInfo) {
        this.stockInfo = stockInfo;
    }

    public String getPersonInCharge() {
        return this.personInCharge;
    }
    
    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getMaterialType() {
        return this.materialType;
    }
    
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialName() {
        return this.materialName;
    }
    
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Set getBreedLogs() {
        return this.breedLogs;
    }
    
    public void setBreedLogs(Set breedLogs) {
        this.breedLogs = breedLogs;
    }
   








}
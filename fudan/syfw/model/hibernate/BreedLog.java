package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * BreedLog entity. @author MyEclipse Persistence Tools
 * 成长记录表（饲料喂养、用药）
 */

public class BreedLog  implements java.io.Serializable, Comparable<BreedLog> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private String logId;			// 日志编号
     private BreedNo breedNo;		//养殖批次
     private BreedStaff breedStaff;	//人员，不填
     private FeedInfo feedInfo;		//投喂原料
     private String logType;		//日志分类（喂养，用药）
     private Date breedTime;		//投喂时间
     private String breedNumber;	//投喂数量
     private String breedMethod;	//投喂方法
     private String breedPerson;	//投喂人
     private String breedSpecies;	//投喂品种（什么饲料/药物）
     private String logNote;		//结果或其他记录


    // Constructors

    /** default constructor */
    public BreedLog() {
    }

	/** minimal constructor */
    public BreedLog(BreedNo breedNo, FeedInfo feedInfo, String logType) {
        this.breedNo = breedNo;
        this.feedInfo = feedInfo;
        this.logType = logType;
    }
    
    /** full constructor */
    public BreedLog(BreedNo breedNo, BreedStaff breedStaff, FeedInfo feedInfo, String logType, Date breedTime, String breedNumber, String breedMethod, String breedPerson, String breedSpecies, String logNote) {
        this.breedNo = breedNo;
        this.breedStaff = breedStaff;
        this.feedInfo = feedInfo;
        this.logType = logType;
        this.breedTime = breedTime;
        this.breedNumber = breedNumber;
        this.breedMethod = breedMethod;
        this.breedPerson = breedPerson;
        this.breedSpecies = breedSpecies;
        this.logNote = logNote;
    }
    
    //分页
    public int compareTo(BreedLog log){
    	return logId.compareTo(log.getLogId());
    }

   
    // Property accessors

    public String getLogId() {
        return this.logId;
    }
    
    public void setLogId(String logId) {
        this.logId = logId;
    }

    public BreedNo getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }

    public BreedStaff getBreedStaff() {
        return this.breedStaff;
    }
    
    public void setBreedStaff(BreedStaff breedStaff) {
        this.breedStaff = breedStaff;
    }

    public FeedInfo getFeedInfo() {
        return this.feedInfo;
    }
    
    public void setFeedInfo(FeedInfo feedInfo) {
        this.feedInfo = feedInfo;
    }

    public String getLogType() {
        return this.logType;
    }
    
    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getBreedTime() {
        return this.breedTime;
    }
    
    public void setBreedTime(Date breedTime) {
        this.breedTime = breedTime;
    }

    public String getBreedNumber() {
        return this.breedNumber;
    }
    
    public void setBreedNumber(String breedNumber) {
        this.breedNumber = breedNumber;
    }

    public String getBreedMethod() {
        return this.breedMethod;
    }
    
    public void setBreedMethod(String breedMethod) {
        this.breedMethod = breedMethod;
    }

    public String getBreedPerson() {
        return this.breedPerson;
    }
    
    public void setBreedPerson(String breedPerson) {
        this.breedPerson = breedPerson;
    }

    public String getBreedSpecies() {
        return this.breedSpecies;
    }
    
    public void setBreedSpecies(String breedSpecies) {
        this.breedSpecies = breedSpecies;
    }

    public String getLogNote() {
        return this.logNote;
    }
    
    public void setLogNote(String logNote) {
        this.logNote = logNote;
    }
   








}
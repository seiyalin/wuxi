package org.wuxi.fudan.syfw.model.hibernate;



/**
 * ProcessStaff entity. @author MyEclipse Persistence Tools
 * 加工人员管理
 */

public class ProcessStaff  implements java.io.Serializable, Comparable<ProcessStaff> {


    // Fields    

     private String idcard;					//身份证号
     private ProcessCompany processCompany; //所属加工企业
     private String staffName;				//姓名
     private String position;				//职位
     private String health;					//健康情况
     private String experience;				//培训经验


    // Constructors

    /** default constructor */
    public ProcessStaff() {
    }

	/** minimal constructor */
    public ProcessStaff(ProcessCompany processCompany, String staffName) {
        this.processCompany = processCompany;
        this.staffName = staffName;
    }
    
    /** full constructor */
    public ProcessStaff(ProcessCompany processCompany, String staffName, String position, String health, String experience) {
        this.processCompany = processCompany;
        this.staffName = staffName;
        this.position = position;
        this.health = health;
        this.experience = experience;
    }

    public int compareTo(ProcessStaff staff){
    	return idcard.compareTo(staff.getIdcard());
    }
   
    // Property accessors

    public String getIdcard() {
        return this.idcard;
    }
    
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public ProcessCompany getProcessCompany() {
        return this.processCompany;
    }
    
    public void setProcessCompany(ProcessCompany processCompany) {
        this.processCompany = processCompany;
    }

    public String getStaffName() {
        return this.staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }

    public String getHealth() {
        return this.health;
    }
    
    public void setHealth(String health) {
        this.health = health;
    }

    public String getExperience() {
        return this.experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
   








}
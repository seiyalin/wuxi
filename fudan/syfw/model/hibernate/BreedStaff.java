package org.wuxi.fudan.syfw.model.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * BreedStaff entity. @author MyEclipse Persistence Tools
 * 养殖人员管理
 */

public class BreedStaff  implements java.io.Serializable, Comparable<BreedStaff> {


    // Fields    

     private String idcard;				//身份证号
     private BreedCompany breedCompany; //所述养殖企业
     private String staffName;			//姓名
     private String breedAreas;			//负责的养殖区域
     private String position;			//职位
     private String breedSpecies;		//负责养殖品种
     private String experience;			//相关资质和养殖经历
   //  private Set breedLogs = new HashSet(0);


    // Constructors

    /** default constructor */
    public BreedStaff() {
    }

	/** minimal constructor */
    public BreedStaff(BreedCompany breedCompany, String staffName) {
        this.breedCompany = breedCompany;
        this.staffName = staffName;
    }
    
    /** full constructor */
    public BreedStaff(BreedCompany breedCompany, String staffName, String breedArea, String position, String breedSpecies, String experience) {
        this.breedCompany = breedCompany;
        this.staffName = staffName;
        this.breedAreas = breedArea;
        this.position = position;
        this.breedSpecies = breedSpecies;
        this.experience = experience;
        //this.breedLogs = breedLogs;
    }
    
    //排序
    public int compareTo(BreedStaff staff){
    	return idcard.compareTo(staff.getIdcard());
    }
   
    // Property accessors

    public String getIdcard() {
        return this.idcard;
    }
    
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public BreedCompany getBreedCompany() {
        return this.breedCompany;
    }
    
    public void setBreedCompany(BreedCompany breedCompany) {
        this.breedCompany = breedCompany;
    }

    public String getStaffName() {
        return this.staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getBreedAreas() {
        return this.breedAreas;
    }
    
    public void setBreedAreas(String breedArea) {
        this.breedAreas = breedArea;
    }

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }

    public String getBreedSpecies() {
        return this.breedSpecies;
    }
    
    public void setBreedSpecies(String breedSpecies) {
        this.breedSpecies = breedSpecies;
    }

    public String getExperience() {
        return this.experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }

  /*  public Set getBreedLogs() {
        return this.breedLogs;
    }
    
    public void setBreedLogs(Set breedLogs) {
        this.breedLogs = breedLogs;
    }
   */








}
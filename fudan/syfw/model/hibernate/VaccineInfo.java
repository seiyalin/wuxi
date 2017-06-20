package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * VaccineInfo entity. @author MyEclipse Persistence Tools
 * 防疫与检疫管理
 */

public class VaccineInfo  implements java.io.Serializable,Comparable<VaccineInfo> {


    // Fields    

     private String vaccineId;  //检疫编号
     private BreedNo breedNo;	//养殖批次
     private String type;		//分类（检疫，防疫）
     private String content;	//内容
     private Date vaccineTime;	//时间
     private String doctor;		//医生
     private String note;		//记录及结果


    // Constructors

    /** default constructor */
    public VaccineInfo() {
    }

	/** minimal constructor */
    public VaccineInfo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }
    
    /** full constructor */
    public VaccineInfo(BreedNo breedNo, String type, String content, Date vaccineTime, String doctor, String note) {
        this.breedNo = breedNo;
        this.type = type;
        this.content = content;
        this.vaccineTime = vaccineTime;
        this.doctor = doctor;
        this.note = note;
    }

    public int compareTo(VaccineInfo vac){
    	return vaccineTime.compareTo(vac.getVaccineTime());
    }
   
    // Property accessors

    public String getVaccineId() {
        return this.vaccineId;
    }
    
    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public BreedNo getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getVaccineTime() {
        return this.vaccineTime;
    }
    
    public void setVaccineTime(Date vaccineTime) {
        this.vaccineTime = vaccineTime;
    }

    public String getDoctor() {
        return this.doctor;
    }
    
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
   








}
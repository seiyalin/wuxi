package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * IllnessInfo entity. @author MyEclipse Persistence Tools
 * 疾病管理（进行疾病检查时录入信息）
 */

public class IllnessInfo  implements java.io.Serializable {


    // Fields    

     private String illnessId;  //疾病编号
     private BreedNo breedNo;	//养殖批次
     private Date illTime;		//生病时间
     private String symptom;	//症状
     private String prescription;	//处方
     private String doctor;		//医生
     private String note;		//记录及结果


    // Constructors

    /** default constructor */
    public IllnessInfo() {
    }

	/** minimal constructor */
    public IllnessInfo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }
    
    /** full constructor */
    public IllnessInfo(BreedNo breedNo, Date illTime, String symptom, String prescription, String doctor, String note) {
        this.breedNo = breedNo;
        this.illTime = illTime;
        this.symptom = symptom;
        this.prescription = prescription;
        this.doctor = doctor;
        this.note = note;
    }

   
    // Property accessors

    public String getIllnessId() {
        return this.illnessId;
    }
    
    public void setIllnessId(String illnessId) {
        this.illnessId = illnessId;
    }

    public BreedNo getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }

    public Date getIllTime() {
        return this.illTime;
    }
    
    public void setIllTime(Date illTime) {
        this.illTime = illTime;
    }

    public String getSymptom() {
        return this.symptom;
    }
    
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getPrescription() {
        return this.prescription;
    }
    
    public void setPrescription(String prescription) {
        this.prescription = prescription;
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
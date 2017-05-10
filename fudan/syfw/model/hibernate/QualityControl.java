package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * QualityControl entity. @author MyEclipse Persistence Tools
 * 产品质检管理
 */

public class QualityControl  implements java.io.Serializable {


    // Fields    

     private String qcId;		//质检编号
     private BreedNo breedNo;	//产品批次
     private String product;	//产品名称
     private String content;	//质检内容
     private String result;		//质检结果
     private String personInCharge;	//负责人
     private Date qcTime;		//质检时间
     private String qcInstitution;	//检测机构


    // Constructors

    /** default constructor */
    public QualityControl() {
    }

	/** minimal constructor */
    public QualityControl(BreedNo breedNo) {
        this.breedNo = breedNo;
    }
    
    /** full constructor */
    public QualityControl(BreedNo breedNo, String product, String content, String result, String personInCharge, Date qcTime, String qcInstitution) {
        this.breedNo = breedNo;
        this.product = product;
        this.content = content;
        this.result = result;
        this.personInCharge = personInCharge;
        this.qcTime = qcTime;
        this.qcInstitution = qcInstitution;
    }

   
    // Property accessors

    public String getQcId() {
        return this.qcId;
    }
    
    public void setQcId(String qcId) {
        this.qcId = qcId;
    }

    public BreedNo getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }

    public String getProduct() {
        return this.product;
    }
    
    public void setProduct(String product) {
        this.product = product;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return this.result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }

    public String getPersonInCharge() {
        return this.personInCharge;
    }
    
    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public Date getQcTime() {
        return this.qcTime;
    }
    
    public void setQcTime(Date qcTime) {
        this.qcTime = qcTime;
    }

    public String getQcInstitution() {
        return this.qcInstitution;
    }
    
    public void setQcInstitution(String qcInstitution) {
        this.qcInstitution = qcInstitution;
    }
   








}
package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * Trace entity. @author MyEclipse Persistence Tools
 * 溯源信息
 */

public class Trace  implements java.io.Serializable {


    // Fields    

     private String epcis;			//追溯码
     private BreedNo breedNo;		//养殖批次
     private ProcessInfo processInfo;	//加工信息
     private TransportationInfo transportationInfo; //运输信息
     private OrderInfo orderInfo;		//对应订单信息
     private String breedCompany;		//养殖企业
     private String processCompany;		//加工企业
     private Date outPondTime;			//出塘时间
     private String restaurant;			//餐饮企业
     private String breedSpecies;		//品种


    // Constructors

    /** default constructor */
    public Trace() {
    }

	/** minimal constructor */
    public Trace(BreedNo breedNo, ProcessInfo processInfo, TransportationInfo transportationInfo, OrderInfo orderInfo) {
        this.breedNo = breedNo;
        this.processInfo = processInfo;
        this.transportationInfo = transportationInfo;
        this.orderInfo = orderInfo;
    }
    
    /** full constructor */
    public Trace(BreedNo breedNo, ProcessInfo processInfo, TransportationInfo transportationInfo, OrderInfo orderInfo, String breedCompany, String processCompany, Date outPondTime, String restaurant, String breedSpecies) {
        this.breedNo = breedNo;
        this.processInfo = processInfo;
        this.transportationInfo = transportationInfo;
        this.orderInfo = orderInfo;
        this.breedCompany = breedCompany;
        this.processCompany = processCompany;
        this.outPondTime = outPondTime;
        this.restaurant = restaurant;
        this.breedSpecies = breedSpecies;
    }

   
    // Property accessors

    public String getEpcis() {
        return this.epcis;
    }
    
    public void setEpcis(String epcis) {
        this.epcis = epcis;
    }

    public BreedNo getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }

    public ProcessInfo getProcessInfo() {
        return this.processInfo;
    }
    
    public void setProcessInfo(ProcessInfo processInfo) {
        this.processInfo = processInfo;
    }

    public TransportationInfo getTransportationInfo() {
        return this.transportationInfo;
    }
    
    public void setTransportationInfo(TransportationInfo transportationInfo) {
        this.transportationInfo = transportationInfo;
    }

    public OrderInfo getOrderInfo() {
        return this.orderInfo;
    }
    
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getBreedCompany() {
        return this.breedCompany;
    }
    
    public void setBreedCompany(String breedCompany) {
        this.breedCompany = breedCompany;
    }

    public String getProcessCompany() {
        return this.processCompany;
    }
    
    public void setProcessCompany(String processCompany) {
        this.processCompany = processCompany;
    }

    public Date getOutPondTime() {
        return this.outPondTime;
    }
    
    public void setOutPondTime(Date outPondTime) {
        this.outPondTime = outPondTime;
    }

    public String getRestaurant() {
        return this.restaurant;
    }
    
    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getBreedSpecies() {
        return this.breedSpecies;
    }
    
    public void setBreedSpecies(String breedSpecies) {
        this.breedSpecies = breedSpecies;
    }
   








}
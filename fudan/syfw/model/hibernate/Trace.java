package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * Trace entity. @author MyEclipse Persistence Tools
 * 溯源信息
 */

public class Trace  implements java.io.Serializable {


    // Fields    

     private String epcis;
     private BreedNo breedNo;
     private ProcessInfo processInfo;
     private TransportationInfo transportationInfo;
     private OrderInfo orderInfo;
     private String breedCompany;
     private String processCompany;
     private Date outPondTime;
     private String restaurant;
     private String breedSpecies;


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
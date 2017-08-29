package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * ProcessInfo entity. @author MyEclipse Persistence Tools
 */

public class ProcessInfo  implements java.io.Serializable, Comparable<ProcessInfo> {


    // Fields    

     private String cageId; 		//成品箱编号
     private BreedNo breedNo;		//养殖批次
     private ProcessCompany processCompany; //加工企业
     private TransportationInfo transportationInfo; //运输信息
     private OrderInfo orderInfo;	//订单信息
     private Date processTime;		//加工日期
     private String personInCharge; //负责人
     private String cageWeight; //成品箱重量
//     private Set traces = new HashSet(0);


    // Constructors

    /** default constructor */
    public ProcessInfo() {
    }

	/** minimal constructor */
    public ProcessInfo(BreedNo breedNo, ProcessCompany processCompany) {
        this.breedNo = breedNo;
        this.processCompany = processCompany;
    }
    
    /** full constructor */
    public ProcessInfo(BreedNo breedNo, ProcessCompany processCompany, TransportationInfo transportationInfo, OrderInfo orderInfo, Date processTime, String personInCharge) {
        this.breedNo = breedNo;
        this.processCompany = processCompany;
        this.transportationInfo = transportationInfo;
        this.orderInfo = orderInfo;
        this.processTime = processTime;
        this.personInCharge = personInCharge;
 //       this.traces = traces;
    }

    //分页
    public int compareTo(ProcessInfo process){
    	return cageId.compareTo(process.getCageId());
    }
   
    // Property accessors

    public String getCageId() {
        return this.cageId;
    }
    
    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public BreedNo getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(BreedNo breedNo) {
        this.breedNo = breedNo;
    }

    public ProcessCompany getProcessCompany() {
        return this.processCompany;
    }
    
    public void setProcessCompany(ProcessCompany processCompany) {
        this.processCompany = processCompany;
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

    public Date getProcessTime() {
        return this.processTime;
    }
    
    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getPersonInCharge() {
        return this.personInCharge;
    }
    
    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

	public String getCageWeight() {
		return cageWeight;
	}

	public void setCageWeight(String cageWeight) {
		this.cageWeight = cageWeight;
	}

   /* public Set getTraces() {
        return this.traces;
    }
    
    public void setTraces(Set traces) {
        this.traces = traces;
    }*/
   








}
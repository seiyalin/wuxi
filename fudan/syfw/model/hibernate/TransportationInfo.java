package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TransportationInfo entity. @author MyEclipse Persistence Tools
 * 运输信息管理
 */

public class TransportationInfo  implements java.io.Serializable, Comparable<TransportationInfo> {


    // Fields    

     private String transId; 		//运输编号
     private RestaurantCompany restaurantCompany; //餐饮企业
     private ProcessCompany processCompany; //发货的加工企业
     private String cages;			//运输的成品箱
     private String product;		//运输的产品
     private Double number;		//运输数量
     private String transPerson;	//运输人
     private String transVehicle;	//运输车辆信息
     private Date transTime;		//运输时间
     private String transMethod;	//运输方式
     private String receiver;		//收货人
     private String toAddress;		//运送地址
     private String receiverPhone;  //联系方式
     //private Set traces = new HashSet(0);
     private Set processInfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public TransportationInfo() {
    }

	/** minimal constructor */
    public TransportationInfo(RestaurantCompany restaurantCompany) {
        this.restaurantCompany = restaurantCompany;
    }
    
    /** full constructor */
    public TransportationInfo(RestaurantCompany restaurantCompany, ProcessCompany processCompany,String cages, String product, Double number, String transPerson, String transVehicle, Date transTime, String transMethod, String receiver, String toAddress, String receiverPhone, Set processInfos) {
        this.restaurantCompany = restaurantCompany;
        this.processCompany = processCompany;
        this.cages = cages;
        this.product = product;
        this.number = number;
        this.transPerson = transPerson;
        this.transVehicle = transVehicle;
        this.transTime = transTime;
        this.transMethod = transMethod;
        this.receiver = receiver;
        this.toAddress = toAddress;
        this.receiverPhone = receiverPhone;
        //this.traces = traces;
        this.processInfos = processInfos;
    }

    public int compareTo(TransportationInfo transportation){
    	return transId.compareTo(transportation.getTransId());
    }
   
    // Property accessors

    public String getTransId() {
        return this.transId;
    }
    
    public void setTransId(String transId) {
        this.transId = transId;
    }

    public RestaurantCompany getRestaurantCompany() {
        return this.restaurantCompany;
    }
    
    public void setRestaurantCompany(RestaurantCompany restaurantCompany) {
        this.restaurantCompany = restaurantCompany;
    }

    public ProcessCompany getProcessCompany() {
		return processCompany;
	}

	public void setProcessCompany(ProcessCompany processCompany) {
		this.processCompany = processCompany;
	}

	public String getCages() {
        return this.cages;
    }
    
    public void setCages(String cages) {
        this.cages = cages;
    }

    public String getProduct() {
        return this.product;
    }
    
    public void setProduct(String product) {
        this.product = product;
    }

    public Double getNumber() {
        return this.number;
    }
    
    public void setNumber(Double number) {
        this.number = number;
    }

    public String getTransPerson() {
        return this.transPerson;
    }
    
    public void setTransPerson(String transPerson) {
        this.transPerson = transPerson;
    }

    public String getTransVehicle() {
        return this.transVehicle;
    }
    
    public void setTransVehicle(String transVehicle) {
        this.transVehicle = transVehicle;
    }

    public Date getTransTime() {
        return this.transTime;
    }
    
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getTransMethod() {
        return this.transMethod;
    }
    
    public void setTransMethod(String transMethod) {
        this.transMethod = transMethod;
    }

    public String getReceiver() {
        return this.receiver;
    }
    
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getToAddress() {
        return this.toAddress;
    }
    
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getReceiverPhone() {
        return this.receiverPhone;
    }
    
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

  /*  public Set getTraces() {
        return this.traces;
    }
    
    public void setTraces(Set traces) {
        this.traces = traces;
    }*/

    public Set getProcessInfos() {
        return this.processInfos;
    }
    
    public void setProcessInfos(Set processInfos) {
        this.processInfos = processInfos;
    }
   








}
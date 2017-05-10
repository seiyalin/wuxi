package org.wuxi.fudan.syfw.model.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * RestaurantCompany entity. @author MyEclipse Persistence Tools
 * 餐饮企业管理
 */

public class RestaurantCompany  implements java.io.Serializable {


    // Fields    

     private String restaurantId; 	//餐厅工商号
     private String restaurantName;	//餐厅名称
     private String legalPerson;	//法人
     private String restaurantAddress;//餐厅地址
     private String contactNumber;	//联系电话
     private String environment;	//餐厅环境
     private Set transportationInfos = new HashSet(0);
     private Set orderInfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public RestaurantCompany() {
    }

	/** minimal constructor */
    public RestaurantCompany(String restaurantName, String legalPerson, String restaurantAddress, String contactNumber) {
        this.restaurantName = restaurantName;
        this.legalPerson = legalPerson;
        this.restaurantAddress = restaurantAddress;
        this.contactNumber = contactNumber;
    }
    
    /** full constructor */
    public RestaurantCompany(String restaurantName, String legalPerson, String restaurantAddress, String contactNumber, String environment, Set transportationInfos, Set orderInfos) {
        this.restaurantName = restaurantName;
        this.legalPerson = legalPerson;
        this.restaurantAddress = restaurantAddress;
        this.contactNumber = contactNumber;
        this.environment = environment;
        this.transportationInfos = transportationInfos;
        this.orderInfos = orderInfos;
    }

   
    // Property accessors

    public String getRestaurantId() {
        return this.restaurantId;
    }
    
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }
    
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getLegalPerson() {
        return this.legalPerson;
    }
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getRestaurantAddress() {
        return this.restaurantAddress;
    }
    
    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEnvironment() {
        return this.environment;
    }
    
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Set getTransportationInfos() {
        return this.transportationInfos;
    }
    
    public void setTransportationInfos(Set transportationInfos) {
        this.transportationInfos = transportationInfos;
    }

    public Set getOrderInfos() {
        return this.orderInfos;
    }
    
    public void setOrderInfos(Set orderInfos) {
        this.orderInfos = orderInfos;
    }
   








}
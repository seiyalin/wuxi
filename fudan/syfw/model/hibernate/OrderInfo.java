package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * OrderInfo entity. @author MyEclipse Persistence Tools
 * 销售订单信息管理
 */

public class OrderInfo  implements java.io.Serializable {


    // Fields    

     private String orderId;		//订单编号（自动生成）
     private RestaurantCompany restaurantCompany; //卖给的餐饮企业
     private ProcessCompany processCompany; //卖家，加工企业
     private String cages;			//卖出的成品箱（多选）
     private String product;		//产品名称
     private Double number;		//产品数量(单位kg）
     private String handle;		//经手人
     private Date orderTime;		//订单时间
     private String buyerPhone;		//买家电话
     private String buyer;			//买家
     private Set traces = new HashSet(0);
     private Set processInfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public OrderInfo() {
    }

	/** minimal constructor */
    public OrderInfo(RestaurantCompany restaurantCompany) {
        this.restaurantCompany = restaurantCompany;
    }
    
    /** full constructor */
    public OrderInfo(RestaurantCompany restaurantCompany, ProcessCompany processCompany, String cages, String product, Double number, String handler, Date orderTime, String buyerPhone, String buyer, Set traces, Set processInfos) {
        this.restaurantCompany = restaurantCompany;
        this.processCompany = processCompany;
        this.cages = cages;
        this.product = product;
        this.number = number;
        this.handle = handler;
        this.orderTime = orderTime;
        this.buyerPhone = buyerPhone;
        this.buyer = buyer;
        this.traces = traces;
        this.processInfos = processInfos;
    }

   
    // Property accessors

    public String getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getHandle() {
        return this.handle;
    }
    
    public void setHandle(String handler) {
        this.handle = handler;
    }

    public Date getOrderTime() {
        return this.orderTime;
    }
    
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getBuyerPhone() {
        return this.buyerPhone;
    }
    
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyer() {
        return this.buyer;
    }
    
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Set getTraces() {
        return this.traces;
    }
    
    public void setTraces(Set traces) {
        this.traces = traces;
    }

    public Set getProcessInfos() {
        return this.processInfos;
    }
    
    public void setProcessInfos(Set processInfos) {
        this.processInfos = processInfos;
    }
   








}
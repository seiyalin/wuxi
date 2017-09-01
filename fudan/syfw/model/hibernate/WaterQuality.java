package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * WaterQuility entity. @author MyEclipse Persistence Tools
 * 水质管理
 */
@XmlRootElement(name="WaterQuality")
public class WaterQuality  implements java.io.Serializable,Comparable<WaterQuality> {


    // Fields    

     private String waterId;  //水检id
     private NetCage netCage;	//检测网箱
     private String color;		//水体颜色
     private Float temperature;	//温度
     private Float ph;			//ph
     private Float oxygen;		//含氧量
     private Date testTime;		//检测时间
     private String personInCharge; //负责人


     public int compareTo(WaterQuality w){
    	 return testTime.compareTo(w.getTestTime());
     }
    // Constructors

    /** default constructor */
    public WaterQuality() {
    }

	/** minimal constructor */
    public WaterQuality(NetCage netCage) {
        this.netCage = netCage;
    }
    
    /** full constructor */
    public WaterQuality(NetCage netCage, String color, Float temperature, Float ph, Float oxygen, Date testTime, String personInCharge) {
        this.netCage = netCage;
        this.color = color;
        this.temperature = temperature;
        this.ph = ph;
        this.oxygen = oxygen;
        this.testTime = testTime;
        this.personInCharge = personInCharge;
    }

   
    // Property accessors

    public String getWaterId() {
        return this.waterId;
    }
    
    public void setWaterId(String waterId) {
        this.waterId = waterId;
    }

    @XmlTransient
    public NetCage getNetCage() {
        return this.netCage;
    }
    
    public void setNetCage(NetCage netCage) {
        this.netCage = netCage;
    }

    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public Float getTemperature() {
        return this.temperature;
    }
    
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getPh() {
        return this.ph;
    }
    
    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getOxygen() {
        return this.oxygen;
    }
    
    public void setOxygen(Float oxygen) {
        this.oxygen = oxygen;
    }

    public Date getTestTime() {
        return this.testTime;
    }
    
    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getPersonInCharge() {
        return this.personInCharge;
    }
    
    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }
   








}
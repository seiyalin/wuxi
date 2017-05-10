package org.wuxi.fudan.syfw.model.hibernate;



/**
 * TrepangGi entity. @author MyEclipse Persistence Tools
 * 海参地理标识数据库
 */

public class TrepangGi  implements java.io.Serializable {


    // Fields    

     private String trepangId;
     private String trepangBrand;
     private String trepangType;
     private String appearance;
     private Long protein;
     private Long water;
     private Long salt;
     private Long sand;
     private String otherDescr;
     private String color;
     private String shape;


    // Constructors

    /** default constructor */
    public TrepangGi() {
    }

    
    /** full constructor */
    public TrepangGi(String trepangBrand, String trepangType, String appearance, Long protein, Long water, Long salt, Long sand, String otherDescr, String color, String shape) {
        this.trepangBrand = trepangBrand;
        this.trepangType = trepangType;
        this.appearance = appearance;
        this.protein = protein;
        this.water = water;
        this.salt = salt;
        this.sand = sand;
        this.otherDescr = otherDescr;
        this.color = color;
        this.shape = shape;
    }

   
    // Property accessors

    public String getTrepangId() {
        return this.trepangId;
    }
    
    public void setTrepangId(String trepangId) {
        this.trepangId = trepangId;
    }

    public String getTrepangBrand() {
        return this.trepangBrand;
    }
    
    public void setTrepangBrand(String trepangBrand) {
        this.trepangBrand = trepangBrand;
    }

    public String getTrepangType() {
        return this.trepangType;
    }
    
    public void setTrepangType(String trepangType) {
        this.trepangType = trepangType;
    }

    public String getAppearance() {
        return this.appearance;
    }
    
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public Long getProtein() {
        return this.protein;
    }
    
    public void setProtein(Long protein) {
        this.protein = protein;
    }

    public Long getWater() {
        return this.water;
    }
    
    public void setWater(Long water) {
        this.water = water;
    }

    public Long getSalt() {
        return this.salt;
    }
    
    public void setSalt(Long salt) {
        this.salt = salt;
    }

    public Long getSand() {
        return this.sand;
    }
    
    public void setSand(Long sand) {
        this.sand = sand;
    }

    public String getOtherDescr() {
        return this.otherDescr;
    }
    
    public void setOtherDescr(String otherDescr) {
        this.otherDescr = otherDescr;
    }

    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return this.shape;
    }
    
    public void setShape(String shape) {
        this.shape = shape;
    }
   








}
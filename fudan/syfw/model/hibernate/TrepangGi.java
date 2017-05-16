package org.wuxi.fudan.syfw.model.hibernate;



/**
 * TrepangGi entity. @author MyEclipse Persistence Tools
 * 海参地理标识数据库
 */

public class TrepangGi  implements java.io.Serializable {


    // Fields    

     private String trepangId;  //海参编号
     private String thumbnail;	//缩略图
     private String trepangBrand;	//海参品牌（獐子岛，大连，烟台）
     private String trepangType;	//海参种类（淡干，盐渍，活，干，盐干）
     private String appearance;	//感官特色
     private Float protein;		//蛋白质
     private Float water;		//水分
     private Float salt;			//盐分
     private Float sand;			//含砂量
     private String otherDescr;	//其他
     private String color;		//色泽
     private String shape;		//体型


    // Constructors

    /** default constructor */
    public TrepangGi() {
    }

    
    /** full constructor */
    public TrepangGi(String trepangBrand, String trepangType, String appearance, Float protein, Float water, Float salt, Float sand, String otherDescr, String color, String shape) {
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

    public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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

    public Float getProtein() {
        return this.protein;
    }
    
    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getWater() {
        return this.water;
    }
    
    public void setWater(Float water) {
        this.water = water;
    }

    public Float getSalt() {
        return this.salt;
    }
    
    public void setSalt(Float salt) {
        this.salt = salt;
    }

    public Float getSand() {
        return this.sand;
    }
    
    public void setSand(Float sand) {
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
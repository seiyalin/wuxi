package org.wuxi.fudan.syfw.model.hibernate;



/**
 * CrabGi entity. @author MyEclipse Persistence Tools
 * 蟹地理标识数据库
 */

public class CrabGi  implements java.io.Serializable {


    // Fields    

     private String crabId;		//蟹编号
     private String thumbnail;  //缩略图
     private String type;		//品种
     private String habitat;		//产地
     private String sex;		//性别
     private String appearance;	//感官特色
     private String crabRoe;	//蟹黄
     private String crudeProtein;	//粗蛋白(百分比）
     private String crudeFat;		//粗脂肪(百分比）
     private String otherDescr;	//其他
     private String crust;		//壳
     private String belly;		//肚（腹）
     private String claw;		//爪
     private String fair;		//毛
     private String clamp;		//钳
     private String back;		//背
     private String paw;		//螯


    // Constructors

    /** default constructor */
    public CrabGi() {
    }

    
    /** full constructor */
    public CrabGi(String type, String sex, String appearance, String crabRoe, String crudeProtein, String crudeFat, String otherDescr, String crust, String belly, String claw, String fair, String clamp, String back, String paw) {
        this.type = type;
        this.sex = sex;
        this.appearance = appearance;
        this.crabRoe = crabRoe;
        this.crudeProtein = crudeProtein;
        this.crudeFat = crudeFat;
        this.otherDescr = otherDescr;
        this.crust = crust;
        this.belly = belly;
        this.claw = claw;
        this.fair = fair;
        this.clamp = clamp;
        this.back = back;
        this.paw = paw;
    }

   
    // Property accessors

    public String getCrabId() {
        return this.crabId;
    }
    
    public void setCrabId(String crabId) {
        this.crabId = crabId;
    }

    public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getHabitat() {
		return habitat;
	}


	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}


	public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAppearance() {
        return this.appearance;
    }
    
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getCrabRoe() {
        return this.crabRoe;
    }
    
    public void setCrabRoe(String crabRoe) {
        this.crabRoe = crabRoe;
    }

    public String getCrudeProtein() {
        return this.crudeProtein;
    }
    
    public void setCrudeProtein(String crudeProtein) {
        this.crudeProtein = crudeProtein;
    }

    public String getCrudeFat() {
        return this.crudeFat;
    }
    
    public void setCrudeFat(String crudeFat) {
        this.crudeFat = crudeFat;
    }

    public String getOtherDescr() {
        return this.otherDescr;
    }
    
    public void setOtherDescr(String otherDescr) {
        this.otherDescr = otherDescr;
    }

    public String getCrust() {
        return this.crust;
    }
    
    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getBelly() {
        return this.belly;
    }
    
    public void setBelly(String belly) {
        this.belly = belly;
    }

    public String getClaw() {
        return this.claw;
    }
    
    public void setClaw(String claw) {
        this.claw = claw;
    }

    public String getFair() {
        return this.fair;
    }
    
    public void setFair(String fair) {
        this.fair = fair;
    }

    public String getClamp() {
        return this.clamp;
    }
    
    public void setClamp(String clamp) {
        this.clamp = clamp;
    }

    public String getBack() {
        return this.back;
    }
    
    public void setBack(String back) {
        this.back = back;
    }

    public String getPaw() {
        return this.paw;
    }
    
    public void setPaw(String paw) {
        this.paw = paw;
    }
   








}
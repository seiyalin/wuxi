package org.wuxi.fudan.syfw.model.hibernate;



/**
 * CrabGi entity. @author MyEclipse Persistence Tools
 * 蟹地理标识数据库
 */

public class CrabGi  implements java.io.Serializable {


    // Fields    

     private String crabId;
     private String type;
     private String sex;
     private String appearance;
     private String crabRoe;
     private Long crudeProtein;
     private Long crudeFat;
     private String otherDescr;
     private String crust;
     private String belly;
     private String claw;
     private String fair;
     private String clamp;
     private String back;
     private String paw;


    // Constructors

    /** default constructor */
    public CrabGi() {
    }

    
    /** full constructor */
    public CrabGi(String type, String sex, String appearance, String crabRoe, Long crudeProtein, Long crudeFat, String otherDescr, String crust, String belly, String claw, String fair, String clamp, String back, String paw) {
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

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
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

    public Long getCrudeProtein() {
        return this.crudeProtein;
    }
    
    public void setCrudeProtein(Long crudeProtein) {
        this.crudeProtein = crudeProtein;
    }

    public Long getCrudeFat() {
        return this.crudeFat;
    }
    
    public void setCrudeFat(Long crudeFat) {
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
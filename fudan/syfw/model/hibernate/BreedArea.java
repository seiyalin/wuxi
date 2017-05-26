package org.wuxi.fudan.syfw.model.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * BreedArea entity. @author MyEclipse Persistence Tools
 * 养殖区域
 */

public class BreedArea  implements java.io.Serializable, Comparable<BreedArea> {


    // Fields    

     private String areaId;					//养殖区域编号
     private BreedCompany breedCompany;		//养殖企业
     private String areaName;				//养殖区域名称
     private String breedScale;				//养殖规模
     private String waterQuality;			//水质
     private String breedEnvironment;		//养殖环境
     private Set netCages = new HashSet(0); //网箱


    // Constructors

    /** default constructor */
    public BreedArea() {
    }

	/** minimal constructor */
    public BreedArea(BreedCompany breedCompany, String breedScale, String breedEnvironment) {
        this.breedCompany = breedCompany;
        this.breedScale = breedScale;
        this.breedEnvironment = breedEnvironment;
    }
    
    /** full constructor */
    public BreedArea(BreedCompany breedCompany, String areaName, String breedScale, String waterQuality, String breedEnvironment, Set netCages) {
        this.breedCompany = breedCompany;
        this.areaName = areaName;
        this.breedScale = breedScale;
        this.waterQuality = waterQuality;
        this.breedEnvironment = breedEnvironment;
        this.netCages = netCages;
    }

    //排序
    public int compareTo(BreedArea area){
    	return this.getAreaId().compareTo(area.getAreaId());
    }
   
    // Property accessors

    public String getAreaId() {
        return this.areaId;
    }
    
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public BreedCompany getBreedCompany() {
        return this.breedCompany;
    }
    
    public void setBreedCompany(BreedCompany breedCompany) {
        this.breedCompany = breedCompany;
    }

    public String getAreaName() {
        return this.areaName;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBreedScale() {
        return this.breedScale;
    }
    
    public void setBreedScale(String breedScale) {
        this.breedScale = breedScale;
    }

    public String getWaterQuality() {
        return this.waterQuality;
    }
    
    public void setWaterQuality(String waterQuality) {
        this.waterQuality = waterQuality;
    }

    public String getBreedEnvironment() {
        return this.breedEnvironment;
    }
    
    public void setBreedEnvironment(String breedEnvironment) {
        this.breedEnvironment = breedEnvironment;
    }

    public Set getNetCages() {
        return this.netCages;
    }
    
    public void setNetCages(Set netCages) {
        this.netCages = netCages;
    }
   








}
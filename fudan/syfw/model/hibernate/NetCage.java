package org.wuxi.fudan.syfw.model.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;


/**
 * NetCage entity. @author MyEclipse Persistence Tools
 * 网箱管理（一个养殖区域对应多个网箱）
 */
@XmlRootElement(name="NetCage")
@XmlSeeAlso({BreedArea.class})
public class NetCage  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String netcageId;					//网箱编号
     private BreedArea breedArea;				//属于的养殖区域

     //private Set<BreedNo> breedNos = new HashSet<BreedNo>(0);		//水产品批次
     
     private Set<WaterQuality> waterQualities = new HashSet<WaterQuality>(0);	//水质监测记录


    // Constructors

    /** default constructor */
    public NetCage() {
    }
    
    
   /* public NetCage(String netCageId){
    	this.netcageId = netCageId;
    	
    }*/

	/** minimal constructor */
    public NetCage(BreedArea breedArea) {
        this.breedArea = breedArea;
    }
    
    /** full constructor */
    public NetCage(BreedArea breedArea,  Set<WaterQuality> waterQuilities) {
        this.breedArea = breedArea;
  
       // this.breedNos = breedNos;
        this.waterQualities = waterQuilities;
    }

   
    // Property accessors

    public String getNetcageId() {
        return this.netcageId;
    }
    
    public void setNetcageId(String netcageId) {
        this.netcageId = netcageId;
    }

    @XmlTransient
    public BreedArea getBreedArea() {
        return this.breedArea;
    }
    
    public void setBreedArea(BreedArea breedArea) {
        this.breedArea = breedArea;
    }

   

   /* public Set<BreedNo> getBreedNos() {
        return this.breedNos;
    }
    
    public void setBreedNos(Set<BreedNo> breedNos) {
        this.breedNos = breedNos;
    }*/
    @XmlElementWrapper(name="waterQuality")
    @XmlElement(name="wq")
    public Set<WaterQuality> getWaterQualities() {
        return this.waterQualities;
    }
    
    public void setWaterQualities(Set<WaterQuality> waterQuilities) {
        this.waterQualities = waterQuilities;
    }
   








}
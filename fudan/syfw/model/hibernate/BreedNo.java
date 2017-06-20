package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * BreedNo entity. @author MyEclipse Persistence Tools
 * 养殖批次管理（投入种苗时录入）
 */

public class BreedNo  implements java.io.Serializable, Comparable<BreedNo> {


    // Fields    

     private String breedNo;        //养殖批次
     private OutPond outPond;		//出塘记录
     private NetCage netCage;		//网箱
     private Date inPondTime;		//入塘时间
     private String inPondNumber;	//投苗数量
     private String inPondMethod;	//投苗方式
     private String breedPerson;	//投苗人
     private String breedSpecies;	//投苗品种
     private Set vaccineInfos = new HashSet(0);
     private Set illnessInfos = new HashSet(0);
     private Set qualityControls = new HashSet(0);
     private Set breedLogs = new HashSet(0);
     /*private Set traces = new HashSet(0);*/
     //private Set processInfos = new HashSet(0);


    // Constructors

    /** default constructor */
    public BreedNo() {
    }

	/** minimal constructor */
    public BreedNo(NetCage netCage, Date inPondTime) {
        this.netCage = netCage;
        this.inPondTime = inPondTime;
    }
    
    /** full constructor */
    public BreedNo(OutPond outPond, NetCage netCage, Date inPondTime, String inPondNumber, String inPondMethod, String breedPerson, String breedSpecies, Set vaccineInfos, Set illnessInfos, Set qualityControls, Set breedLogs) {
        this.outPond = outPond;
        this.netCage = netCage;
        this.inPondTime = inPondTime;
        this.inPondNumber = inPondNumber;
        this.inPondMethod = inPondMethod;
        this.breedPerson = breedPerson;
        this.breedSpecies = breedSpecies;
        this.vaccineInfos = vaccineInfos;
        this.illnessInfos = illnessInfos;
        this.qualityControls = qualityControls;
        this.breedLogs = breedLogs;
        //this.traces = traces;
       // this.processInfos = processInfos;
    }

   
    public int compareTo(BreedNo breed){
    	return breedNo.compareTo(breed.getBreedNo());
    }
    // Property accessors

    public String getBreedNo() {
        return this.breedNo;
    }
    
    public void setBreedNo(String breedNo) {
        this.breedNo = breedNo;
    }

    public OutPond getOutPond() {
        return this.outPond;
    }
    
    public void setOutPond(OutPond outPond) {
        this.outPond = outPond;
    }

    public NetCage getNetCage() {
        return this.netCage;
    }
    
    public void setNetCage(NetCage netCage) {
        this.netCage = netCage;
    }

    public Date getInPondTime() {
        return this.inPondTime;
    }
    
    public void setInPondTime(Date inPondTime) {
        this.inPondTime = inPondTime;
    }

    public String getInPondNumber() {
        return this.inPondNumber;
    }
    
    public void setInPondNumber(String inPondNumber) {
        this.inPondNumber = inPondNumber;
    }

    public String getInPondMethod() {
        return this.inPondMethod;
    }
    
    public void setInPondMethod(String inPondMethod) {
        this.inPondMethod = inPondMethod;
    }

    public String getBreedPerson() {
        return this.breedPerson;
    }
    
    public void setBreedPerson(String breedPerson) {
        this.breedPerson = breedPerson;
    }

    public String getBreedSpecies() {
        return this.breedSpecies;
    }
    
    public void setBreedSpecies(String breedSpecies) {
        this.breedSpecies = breedSpecies;
    }

    public Set getVaccineInfos() {
        return this.vaccineInfos;
    }
    
    public void setVaccineInfos(Set vaccineInfos) {
        this.vaccineInfos = vaccineInfos;
    }

    public Set getIllnessInfos() {
        return this.illnessInfos;
    }
    
    public void setIllnessInfos(Set illnessInfos) {
        this.illnessInfos = illnessInfos;
    }

    public Set getQualityControls() {
        return this.qualityControls;
    }
    
    public void setQualityControls(Set qualityControls) {
        this.qualityControls = qualityControls;
    }

    public Set getBreedLogs() {
        return this.breedLogs;
    }
    
    public void setBreedLogs(Set breedLogs) {
        this.breedLogs = breedLogs;
    }

   /* public Set getTraces() {
        return this.traces;
    }
    
    public void setTraces(Set traces) {
        this.traces = traces;
    }*/

   /* public Set getProcessInfos() {
        return this.processInfos;
    }
    
    public void setProcessInfos(Set processInfos) {
        this.processInfos = processInfos;
    }
   */








}
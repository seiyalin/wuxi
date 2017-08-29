package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * OutPond entity. @author MyEclipse Persistence Tools
 * 出塘记录表
 */

public class OutPond  implements java.io.Serializable,Comparable<OutPond> {


    // Fields    

     private String outPondId;   //出塘编号
     private BreedCompany breedCompany; //养殖企业
     private Date outTime;			//出塘时间
     private String personInCharge;	//负责人
     private String outPondNumber; //出塘数量
     /*private String breedNos;*/ 
     private Set breedNos = new HashSet(0); //出塘批次


    // Constructors

    /** default constructor */
    public OutPond() {
    }

    
    /** full constructor */
    public OutPond(Date outTime, BreedCompany breedCompany, String personInCharge, Set breedNos) {
        this.outTime = outTime;
        this.setBreedCompany(breedCompany);
        this.personInCharge = personInCharge;
      /*  this.breedNos = breedNos;*/
        this.breedNos = breedNos;
    }

   public int compareTo(OutPond out){
	   return outPondId.compareTo(out.getOutPondId());
			   
   }
    // Property accessors

    public String getOutPondId() {
        return this.outPondId;
    }
    
    public void setOutPondId(String outPondId) {
        this.outPondId = outPondId;
    }

    public BreedCompany getBreedCompany() {
		return breedCompany;
	}


	public void setBreedCompany(BreedCompany breedCompany) {
		this.breedCompany = breedCompany;
	}


	public Date getOutTime() {
        return this.outTime;
    }
    
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getPersonInCharge() {
        return this.personInCharge;
    }
    
    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

   /* public String getBreedNos() {
        return this.breedNos;
    }
    
    public void setBreedNos(String breedNos) {
        this.breedNos = breedNos;
    }
*/
    public Set getBreedNos() {
        return this.breedNos;
    }
    
    public void setBreedNos(Set breedNos) {
        this.breedNos = breedNos;
    }


	public String getOutPondNumber() {
		return outPondNumber;
	}


	public void setOutPondNumber(String outPondNumber) {
		this.outPondNumber = outPondNumber;
	}
   








}
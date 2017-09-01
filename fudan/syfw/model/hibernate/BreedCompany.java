package org.wuxi.fudan.syfw.model.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * BreedCompany entity. @author MyEclipse Persistence Tools
 * 养殖企业信息
 */
@XmlRootElement(name="BreedCompany")
public class BreedCompany  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyId; 				//养殖企业编码
    private String companyName;				//养殖企业名称
    private String legalPerson;				//养殖企业法人
    private String companyAddress;			//养殖企业地址
    private String contactNumber;			//企业联系电话
    private String breedSpecies;			//企业养殖品种
    private String safeGrade;				//养殖品种安全等级
    private String userId;					//用户id，根据登录用户名可以找到企业信息
    //private Set breedStaffs = new HashSet(0);  //初始容量为0
  //  private Set breedAreas = new HashSet(0);
    /*private Set feedInfos = new HashSet(0);*/
   // private Set outPonds = new HashSet(0);


    // Constructors

    /** default constructor */
    public BreedCompany() {
    }

	/** minimal constructor */
    public BreedCompany(String companyName, String legalPerson, String companyAddress, String contactNumber, String breedSpecies) {
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.companyAddress = companyAddress;
        this.contactNumber = contactNumber;
        this.breedSpecies = breedSpecies;
    }
    
    /** full constructor */
    public BreedCompany(String companyName, String legalPerson, String companyAddress, String contactNumber, String breedSpecies, String safeGrade) {
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.companyAddress = companyAddress;
        this.contactNumber = contactNumber;
        this.breedSpecies = breedSpecies;
        this.safeGrade = safeGrade;
       // this.breedStaffs = breedStaffs;
      //  this.breedAreas = breedAreas;
       // this.feedInfos = feedInfos;
       // this.outPonds = outPonds;
    }

   
    // Property accessors

    public String getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalPerson() {
        return this.legalPerson;
    }
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getCompanyAddress() {
        return this.companyAddress;
    }
    
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBreedSpecies() {
        return this.breedSpecies;
    }
    
    public void setBreedSpecies(String breedSpecies) {
        this.breedSpecies = breedSpecies;
    }

    public String getSafeGrade() {
        return this.safeGrade;
    }
    
    public void setSafeGrade(String safeGrade) {
        this.safeGrade = safeGrade;
    }

   /* public Set getBreedStaffs() {
        return this.breedStaffs;
    }
    
    public void setBreedStaffs(Set breedStaffs) {
        this.breedStaffs = breedStaffs;
    }
*/
  /*  public Set getBreedAreas() {
        return this.breedAreas;
    }
    
    public void setBreedAreas(Set breedAreas) {
        this.breedAreas = breedAreas;
    }*/

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*public Set getFeedInfos() {
		return feedInfos;
	}

	public void setFeedInfos(Set feedInfos) {
		this.feedInfos = feedInfos;
	}*/

	/*public Set getOutPonds() {
		return outPonds;
	}

	public void setOutPonds(Set outPonds) {
		this.outPonds = outPonds;
	}*/
   








}
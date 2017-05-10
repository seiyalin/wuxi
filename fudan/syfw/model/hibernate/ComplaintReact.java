package org.wuxi.fudan.syfw.model.hibernate;

import java.util.Date;


/**
 * ComplaintReact entity. @author MyEclipse Persistence Tools
 * 投诉反馈管理，每一条投诉的反馈记录一条信息
 */

public class ComplaintReact  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reactId;				//反馈编号
     private ComplaintInfo complaintInfo;	//投诉，自动生成
     private String complaintAnswer;		//投诉反馈
     private Date answerTime;				//回复时间，自动生成
     private String answerPerson;			//回复人


    // Constructors

    /** default constructor */
    public ComplaintReact() {
    }

	/** minimal constructor */
    public ComplaintReact(ComplaintInfo complaintInfo) {
        this.complaintInfo = complaintInfo;
    }
    
    /** full constructor */
    public ComplaintReact(ComplaintInfo complaintInfo, String complaintAnswer, Date answerTime, String answerPerson) {
        this.complaintInfo = complaintInfo;
        this.complaintAnswer = complaintAnswer;
        this.answerTime = answerTime;
        this.answerPerson = answerPerson;
    }

   
    // Property accessors

    public String getReactId() {
        return this.reactId;
    }
    
    public void setReactId(String reactId) {
        this.reactId = reactId;
    }

    public ComplaintInfo getComplaintInfo() {
        return this.complaintInfo;
    }
    
    public void setComplaintInfo(ComplaintInfo complaintInfo) {
        this.complaintInfo = complaintInfo;
    }

    public String getComplaintAnswer() {
        return this.complaintAnswer;
    }
    
    public void setComplaintAnswer(String complaintAnswer) {
        this.complaintAnswer = complaintAnswer;
    }

    public Date getAnswerTime() {
        return this.answerTime;
    }
    
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerPerson() {
        return this.answerPerson;
    }
    
    public void setAnswerPerson(String answerPerson) {
        this.answerPerson = answerPerson;
    }
   








}
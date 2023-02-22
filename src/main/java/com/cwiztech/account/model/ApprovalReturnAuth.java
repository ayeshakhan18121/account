package com.cwiztech.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBLACCOUNT")

public class ApprovalReturnAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long APPROVALRETURNAUTH_ID;

	@Column(name = "RETURNAUTH_ID")
	private Long RETURNAUTH_ID;
	
	@Transient
	private String RETURNAUTH_DETAIL;
	
	@Column(name = "APPROVALRETURNAUTH_AMOUNT")
	private Double APPROVALRETURNAUTH_AMOUNT;
	
	@Column(name = "APPROVALRETURNAUTH_DATE")
	private String APPROVALRETURNAUTH_DATE;
	
	@Column(name = "ISAPPROVED")
	private String ISAPPROVED;
	
	@Column(name = "CURRENCY_ID")
	private Long CURRENCY_ID;
	
	@Transient
	private String CURRENCY_DETAIL;
	
	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getAPPROVALRETURNAUTH_ID() {
		return APPROVALRETURNAUTH_ID;
	}

	public void setAPPROVALRETURNAUTH_ID(long aPPROVALRETURNAUTH_ID) {
		APPROVALRETURNAUTH_ID = aPPROVALRETURNAUTH_ID;
	}

	
	public Long getRETURNAUTH_ID() {
		return RETURNAUTH_ID;
	}

	public void setRETURNAUTH_ID(Long rETURNAUTH_ID) {
		RETURNAUTH_ID = rETURNAUTH_ID;
	}

	public void setCURRENCY_ID(Long cURRENCY_ID) {
		CURRENCY_ID = cURRENCY_ID;
	}

	public String getRETURNAUTH_DETAIL() {
		return RETURNAUTH_DETAIL;
	}

	public void setRETURNAUTH_DETAIL(String rETURNAUTH_DETAIL) {
		RETURNAUTH_DETAIL = rETURNAUTH_DETAIL;
	}

	public Double getAPPROVALRETURNAUTH_AMOUNT() {
		return APPROVALRETURNAUTH_AMOUNT;
	}

	public void setAPPROVALRETURNAUTH_AMOUNT(Double aPPROVALRETURNAUTH_AMOUNT) {
		APPROVALRETURNAUTH_AMOUNT = aPPROVALRETURNAUTH_AMOUNT;
	}

	public String getAPPROVALRETURNAUTH_DATE() {
		return APPROVALRETURNAUTH_DATE;
	}

	public void setAPPROVALRETURNAUTH_DATE(String aPPROVALRETURNAUTH_DATE) {
		APPROVALRETURNAUTH_DATE = aPPROVALRETURNAUTH_DATE;
	}

	public String getISAPPROVED() {
		return ISAPPROVED;
	}

	public void setISAPPROVED(String iSAPPROVED) {
		ISAPPROVED = iSAPPROVED;
	}

	public Long getCURRENCY_ID() {
		return CURRENCY_ID;
	}

	public String getCURRENCY_DETAIL() {
		return CURRENCY_DETAIL;
	}

	public void setCURRENCY_DETAIL(String cURRENCY_DETAIL) {
		CURRENCY_DETAIL = cURRENCY_DETAIL;
	}

	public String getISACTIVE() {
		return ISACTIVE;
	}

	public void setISACTIVE(String iSACTIVE) {
		ISACTIVE = iSACTIVE;
	}

	public Long getMODIFIED_BY() {
		return MODIFIED_BY;
	}

	public void setMODIFIED_BY(Long mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}

	public String getMODIFIED_WHEN() {
		return MODIFIED_WHEN;
	}

	public void setMODIFIED_WHEN(String mODIFIED_WHEN) {
		MODIFIED_WHEN = mODIFIED_WHEN;
	}

	public String getMODIFIED_WORKSTATION() {
		return MODIFIED_WORKSTATION;
	}

	public void setMODIFIED_WORKSTATION(String mODIFIED_WORKSTATION) {
		MODIFIED_WORKSTATION = mODIFIED_WORKSTATION;
	}

}


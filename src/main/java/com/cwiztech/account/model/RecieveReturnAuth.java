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

public class RecieveReturnAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long RECIEVERETURNAUTH_ID;

	@Column(name = "RETURNAUTH_ID")
	private Long RETURNAUTH_ID;
	
	@Transient
	private String RETURNAUTH_DETAIL;
	
	@Column(name = "RECIEVERETURNAUTH_AMOUNT")
	private Double RECIEVERETURNAUTH_AMOUNT;
	
	@Column(name = "RECIEVERETURNAUTH_DATE")
	private String RECIEVERETURNAUTH_DATE;
	
	@Column(name = "ISRECIEVED")
	private String ISRECIEVED;
	
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

	public long getRECIEVERETURNAUTH_ID() {
		return RECIEVERETURNAUTH_ID;
	}

	public void setRECIEVERETURNAUTH_ID(long rECIEVERETURNAUTH_ID) {
		RECIEVERETURNAUTH_ID = rECIEVERETURNAUTH_ID;
	}

	public Long getRETURNAUTH_ID() {
		return RETURNAUTH_ID;
	}

	public void setRETURNAUTH_ID(Long rETURNAUTH_ID) {
		RETURNAUTH_ID = rETURNAUTH_ID;
	}

	public String getRETURNAUTH_DETAIL() {
		return RETURNAUTH_DETAIL;
	}

	public void setRETURNAUTH_DETAIL(String rETURNAUTH_DETAIL) {
		RETURNAUTH_DETAIL = rETURNAUTH_DETAIL;
	}

	public Double getRECIEVERETURNAUTH_AMOUNT() {
		return RECIEVERETURNAUTH_AMOUNT;
	}

	public void setRECIEVERETURNAUTH_AMOUNT(Double rECIEVERETURNAUTH_AMOUNT) {
		RECIEVERETURNAUTH_AMOUNT = rECIEVERETURNAUTH_AMOUNT;
	}

	public String getRECIEVERETURNAUTH_DATE() {
		return RECIEVERETURNAUTH_DATE;
	}

	public void setRECIEVERETURNAUTH_DATE(String rECIEVERETURNAUTH_DATE) {
		RECIEVERETURNAUTH_DATE = rECIEVERETURNAUTH_DATE;
	}

	public String getISRECIEVED() {
		return ISRECIEVED;
	}

	public void setISRECIEVED(String iSRECIEVED) {
		ISRECIEVED = iSRECIEVED;
	}

	public Long getCURRENCY_ID() {
		return CURRENCY_ID;
	}

	public void setCURRENCY_ID(Long cURRENCY_ID) {
		CURRENCY_ID = cURRENCY_ID;
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
	public static long getDatabaseTableID() {
		return (long) 9;
	}
}


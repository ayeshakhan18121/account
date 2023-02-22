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
@Table(name = "TBLACCOUNTTYPE")

public class AccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ACCOUNTTYPE_ID;

	@Column(name = "ACCOUNTTYPE_CODE")
	private String ACCOUNTTYPE_CODE;
	
	@Column(name = "ACCOUNTTYPE_NAME")
	private String ACCOUNTTYPE_NAME;
	
	@Column(name = "ACCOUNTTYPE_DESCRIPTION")
	private String ACCOUNTTYPE_DESCRIPTION;
	
	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getACCOUNTTYPE_ID() {
		return ACCOUNTTYPE_ID;
	}

	public void setACCOUNTTYPE_ID(long aCCOUNTTYPE_ID) {
		ACCOUNTTYPE_ID = aCCOUNTTYPE_ID;
	}

	public String getACCOUNTTYPE_CODE() {
		return ACCOUNTTYPE_CODE;
	}

	public void setACCOUNTTYPE_CODE(String aCCOUNTTYPE_CODE) {
		ACCOUNTTYPE_CODE = aCCOUNTTYPE_CODE;
	}

	public String getACCOUNTTYPE_NAME() {
		return ACCOUNTTYPE_NAME;
	}

	public void setACCOUNTTYPE_NAME(String aCCOUNTTYPE_NAME) {
		ACCOUNTTYPE_NAME = aCCOUNTTYPE_NAME;
	}

	public String getACCOUNTTYPE_DESCRIPTION() {
		return ACCOUNTTYPE_DESCRIPTION;
	}

	public void setACCOUNTTYPE_DESCRIPTION(String aCCOUNTTYPE_DESCRIPTION) {
		ACCOUNTTYPE_DESCRIPTION = aCCOUNTTYPE_DESCRIPTION;
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
		return (long) 2;
	}

}


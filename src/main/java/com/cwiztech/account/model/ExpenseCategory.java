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

public class ExpenseCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long EXPENSECATEGORY_ID;
	
	@Column(name = "EXPENSECATEGORY_CODE")
	private String EXPENSECATEGORY_CODE;
	
	@Column(name = "EXPENSECATEGORY_NAME")
	private String EXPENSECATEGORY_NAME;
	
	@Column(name = "EXPENSECATEGORY_DESCRIPTION")
	private String EXPENSECATEGORY_DESCRIPTION;
	
	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getEXPENSECATEGORY_ID() {
		return EXPENSECATEGORY_ID;
	}

	public void setEXPENSECATEGORY_ID(long eXPENSECATEGORY_ID) {
		EXPENSECATEGORY_ID = eXPENSECATEGORY_ID;
	}

	public String getEXPENSECATEGORY_CODE() {
		return EXPENSECATEGORY_CODE;
	}

	public void setEXPENSECATEGORY_CODE(String eXPENSECATEGORY_CODE) {
		EXPENSECATEGORY_CODE = eXPENSECATEGORY_CODE;
	}

	public String getEXPENSECATEGORY_NAME() {
		return EXPENSECATEGORY_NAME;
	}

	public void setEXPENSECATEGORY_NAME(String eXPENSECATEGORY_NAME) {
		EXPENSECATEGORY_NAME = eXPENSECATEGORY_NAME;
	}

	public String getEXPENSECATEGORY_DESCRIPTION() {
		return EXPENSECATEGORY_DESCRIPTION;
	}

	public void setEXPENSECATEGORY_DESCRIPTION(String eXPENSECATEGORY_DESCRIPTION) {
		EXPENSECATEGORY_DESCRIPTION = eXPENSECATEGORY_DESCRIPTION;
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

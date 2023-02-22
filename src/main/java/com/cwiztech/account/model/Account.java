package com.cwiztech.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Example;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBLACCOUNT")

public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ACCOUNT_ID;

	@Column(name = "ACCOUNTPARENT_ID")
	private Long ACCOUNTPARENT_ID;
	
	@Transient
	private String ACCOUNTPARENT_DETAIL;
	
	@Column(name = "ACCOUNTTYPE_ID")
	private Long ACCOUNTTYPE_ID;
	
	@Transient
	private String ACCOUNTTYPE_DETAIL;

	@Column(name = "ACCOUNT_DATE")
	private String ACCOUNT_DATE;
	
	@Column(name = "ACCOUNT_CODE")
	private String ACCOUNT_CODE;
	
	@Column(name = "ACCOUNT_NAME")
	private String ACCOUNT_NAME;
	
	@Column(name = "ACCOUNT_DESCRIPTION")
	private String ACCOUNT_DESCRIPTION;
	
	@Column(name = "BANKACCOUNT_NUMBER")
	private String BANKACCOUNT_NUMBER;
	
	@Column(name = "GENERALRATETYPE_ID")
	private Long GENERALRATETYPE_ID;
	
	@Transient
	private String GENERALRATETYPE_DETAIL;
	
	@Column(name = "CASEFLOWRATETYPE_ID")
	private Long CASEFLOWRATETYPE_ID;
	
	@Transient
	private String CASEFLOWRATETYPE_DETAIL;

	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public void setACCOUNT_ID(long aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}

	public Long getACCOUNTPARENT_ID() {
		return ACCOUNTPARENT_ID;
	}

	public void setACCOUNTPARENT_ID(Long aCCOUNTPARENT_ID) {
		ACCOUNTPARENT_ID = aCCOUNTPARENT_ID;
	}

	public String getACCOUNTPARENT_DETAIL() {
		return ACCOUNTPARENT_DETAIL;
	}

	public void setACCOUNTPARENT_DETAIL(String aCCOUNTPARENT_DETAIL) {
		ACCOUNTPARENT_DETAIL = aCCOUNTPARENT_DETAIL;
	}

	public Long getACCOUNTTYPE_ID() {
		return ACCOUNTTYPE_ID;
	}

	public void setACCOUNTTYPE_ID(Long aCCOUNTTYPE_ID) {
		ACCOUNTTYPE_ID = aCCOUNTTYPE_ID;
	}

	public String getACCOUNTTYPE_DETAIL() {
		return ACCOUNTTYPE_DETAIL;
	}

	public void setACCOUNTTYPE_DETAIL(String aCCOUNTTYPE_DETAIL) {
		ACCOUNTTYPE_DETAIL = aCCOUNTTYPE_DETAIL;
	}

	public String getACCOUNT_DATE() {
		return ACCOUNT_DATE;
	}

	public void setACCOUNT_DATE(String aCCOUNT_DATE) {
		ACCOUNT_DATE = aCCOUNT_DATE;
	}

	public String getACCOUNT_CODE() {
		return ACCOUNT_CODE;
	}

	public void setACCOUNT_CODE(String aCCOUNT_CODE) {
		ACCOUNT_CODE = aCCOUNT_CODE;
	}

	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}

	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}

	public String getACCOUNT_DESCRIPTION() {
		return ACCOUNT_DESCRIPTION;
	}

	public void setACCOUNT_DESCRIPTION(String aCCOUNT_DESCRIPTION) {
		ACCOUNT_DESCRIPTION = aCCOUNT_DESCRIPTION;
	}

	public String getBANKACCOUNT_NUMBER() {
		return BANKACCOUNT_NUMBER;
	}

	public void setBANKACCOUNT_NUMBER(String bANKACCOUNT_NUMBER) {
		BANKACCOUNT_NUMBER = bANKACCOUNT_NUMBER;
	}

	public Long getGENERALRATETYPE_ID() {
		return GENERALRATETYPE_ID;
	}

	public void setGENERALRATETYPE_ID(Long gENERALRATETYPE_ID) {
		GENERALRATETYPE_ID = gENERALRATETYPE_ID;
	}

	public String getGENERALRATETYPE_DETAIL() {
		return GENERALRATETYPE_DETAIL;
	}

	public void setGENERALRATETYPE_DETAIL(String gENERALRATETYPE_DETAIL) {
		GENERALRATETYPE_DETAIL = gENERALRATETYPE_DETAIL;
	}

	public Long getCASEFLOWRATETYPE_ID() {
		return CASEFLOWRATETYPE_ID;
	}

	public void setCASEFLOWRATETYPE_ID(Long cASEFLOWRATETYPE_ID) {
		CASEFLOWRATETYPE_ID = cASEFLOWRATETYPE_ID;
	}

	public String getCASEFLOWRATETYPE_DETAIL() {
		return CASEFLOWRATETYPE_DETAIL;
	}

	public void setCASEFLOWRATETYPE_DETAIL(String cASEFLOWRATETYPE_DETAIL) {
		CASEFLOWRATETYPE_DETAIL = cASEFLOWRATETYPE_DETAIL;
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
		return (long) 1;
	}

}

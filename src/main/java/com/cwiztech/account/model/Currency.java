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

public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CURRENCY_ID;

	@Column(name = "COUNTRY_ID")
	private Long COUNTRY_ID;
	
	@Transient
	private String COUNTRY_DETAIL;

	@Column(name = "ISO_CODE")
	private String ISO_CODE;
	
	@Column(name = "EXCHANGE_RATE")
	private Double EXCHANGE_RATE;
	
	@Column(name = "CURRENCY_FORMAT")
	private String CURRENCY_FORMAT;
	
	@Column(name = "CURRENCY_SYMBOL")
	private String CURRENCY_SYMBOL;
	
	@Column(name = "CURRENCYSYMBOLPLACEMENT_ID")
	private Long CURRENCYSYMBOLPLACEMENT_ID;
	
	@Transient
	private String CURRENCYSYMBOLPLACEMENT_DETAIL;

	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getCURRENCY_ID() {
		return CURRENCY_ID;
	}

	public void setCURRENCY_ID(long cURRENCY_ID) {
		CURRENCY_ID = cURRENCY_ID;
	}

	public Long getCOUNTRY_ID() {
		return COUNTRY_ID;
	}

	public void setCOUNTRY_ID(Long cOUNTRY_ID) {
		COUNTRY_ID = cOUNTRY_ID;
	}

	public String getCOUNTRY_DETAIL() {
		return COUNTRY_DETAIL;
	}

	public void setCOUNTRY_DETAIL(String cOUNTRY_DETAIL) {
		COUNTRY_DETAIL = cOUNTRY_DETAIL;
	}

	public String getISO_CODE() {
		return ISO_CODE;
	}

	public void setISO_CODE(String iSO_CODE) {
		ISO_CODE = iSO_CODE;
	}

	public Double getEXCHANGE_RATE() {
		return EXCHANGE_RATE;
	}

	public void setEXCHANGE_RATE(Double eXCHANGE_RATE) {
		EXCHANGE_RATE = eXCHANGE_RATE;
	}

	public String getCURRENCY_FORMAT() {
		return CURRENCY_FORMAT;
	}

	public void setCURRENCY_FORMAT(String cURRENCY_FORMAT) {
		CURRENCY_FORMAT = cURRENCY_FORMAT;
	}

	public String getCURRENCY_SYMBOL() {
		return CURRENCY_SYMBOL;
	}

	public void setCURRENCY_SYMBOL(String cURRENCY_SYMBOL) {
		CURRENCY_SYMBOL = cURRENCY_SYMBOL;
	}

	public Long getCURRENCYSYMBOLPLACEMENT_ID() {
		return CURRENCYSYMBOLPLACEMENT_ID;
	}

	public void setCURRENCYSYMBOLPLACEMENT_ID(Long cURRENCYSYMBOLPLACEMENT_ID) {
		CURRENCYSYMBOLPLACEMENT_ID = cURRENCYSYMBOLPLACEMENT_ID;
	}

	public String getCURRENCYSYMBOLPLACEMENT_DETAIL() {
		return CURRENCYSYMBOLPLACEMENT_DETAIL;
	}

	public void setCURRENCYSYMBOLPLACEMENT_DETAIL(String cURRENCYSYMBOLPLACEMENT_DETAIL) {
		CURRENCYSYMBOLPLACEMENT_DETAIL = cURRENCYSYMBOLPLACEMENT_DETAIL;
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

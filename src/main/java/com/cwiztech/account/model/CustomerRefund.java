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

public class CustomerRefund {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CUSTOMERREFUND_ID;

	@Column(name = "CUSTOMER_ID")
	private Long CUSTOMER_ID;
	
	@Transient
	private String CUSTOMER_DETAIL;
	
	@Column(name = "ACCOUNT_ID")
	private Long ACCOUNT_ID;
	
	@Transient
	private String ACCOUNT_DETAIL;

	@Column(name = "CUSTOMERREFUND_CODE")
	private String CUSTOMERREFUND_CODE;
	
	@Column(name = "CUSTOMERREFUND_DATE")
	private String CUSTOMERREFUND_DATE;
	
	@Column(name = "CUSTOMER_BALANCE")
	private Double CUSTOMER_BALANCE;
	
	@Column(name = "CUSTOMERREFUND_AMOUNT")
	private Double CUSTOMERREFUND_AMOUNT;
	
	@Column(name = "CURRENCY_ID")
	private Long CURRENCY_ID;
	
	@Transient
	private String CURRENCY_DETAIL;
	
	@Column(name = "EXCHANGE_RATE")
	private Double EXCHANGE_RATE;
	
	@Column(name = "POSTINGPERIOD_ID")
	private Long POSTINGPERIOD_ID;
	
	@Transient
	private String POSTINGPERIOD_DETAIL;
	
	@Column(name = "REFUNDMETHOD_ID")
	private Long REFUNDMETHOD_ID;
	
	@Transient
	private String REFUNDMETHOD_DETAIL;
	
	@Column(name = "CHECK_NUMBER")
	private String CHECK_NUMBER;
	
	@Column(name = "CREDITCARD_NUMBER")
	private String CREDITCARD_NUMBER;
	
	@Column(name = "EXPIRE_DATE")
	private String EXPIRE_DATE;
	
	@Column(name = "NAME_ONCARD")
	private String NAME_ONCARD;
	
	@Column(name = "CARD_STREET")
	private String CARD_STREET;
	
	@Column(name = "CARD_ZIPCODE")
	private String CARD_ZIPCODE;
	
	@Column(name = "ISCCAPPROVED")
	private String ISCCAPPROVED;

	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getCUSTOMERREFUND_ID() {
		return CUSTOMERREFUND_ID;
	}

	public void setCUSTOMERREFUND_ID(long cUSTOMERREFUND_ID) {
		CUSTOMERREFUND_ID = cUSTOMERREFUND_ID;
	}

	public Long getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(Long cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public String getCUSTOMER_DETAIL() {
		return CUSTOMER_DETAIL;
	}

	public void setCUSTOMER_DETAIL(String cUSTOMER_DETAIL) {
		CUSTOMER_DETAIL = cUSTOMER_DETAIL;
	}

	public Long getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public void setACCOUNT_ID(Long aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}

	public String getACCOUNT_DETAIL() {
		return ACCOUNT_DETAIL;
	}

	public void setACCOUNT_DETAIL(String aCCOUNT_DETAIL) {
		ACCOUNT_DETAIL = aCCOUNT_DETAIL;
	}

	public String getCUSTOMERREFUND_CODE() {
		return CUSTOMERREFUND_CODE;
	}

	public void setCUSTOMERREFUND_CODE(String cUSTOMERREFUND_CODE) {
		CUSTOMERREFUND_CODE = cUSTOMERREFUND_CODE;
	}

	public String getCUSTOMERREFUND_DATE() {
		return CUSTOMERREFUND_DATE;
	}

	public void setCUSTOMERREFUND_DATE(String cUSTOMERREFUND_DATE) {
		CUSTOMERREFUND_DATE = cUSTOMERREFUND_DATE;
	}

	public Double getCUSTOMER_BALANCE() {
		return CUSTOMER_BALANCE;
	}

	public void setCUSTOMER_BALANCE(Double cUSTOMER_BALANCE) {
		CUSTOMER_BALANCE = cUSTOMER_BALANCE;
	}

	public Double getCUSTOMERREFUND_AMOUNT() {
		return CUSTOMERREFUND_AMOUNT;
	}

	public void setCUSTOMERREFUND_AMOUNT(Double cUSTOMERREFUND_AMOUNT) {
		CUSTOMERREFUND_AMOUNT = cUSTOMERREFUND_AMOUNT;
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

	public Double getEXCHANGE_RATE() {
		return EXCHANGE_RATE;
	}

	public void setEXCHANGE_RATE(Double eXCHANGE_RATE) {
		EXCHANGE_RATE = eXCHANGE_RATE;
	}

	public Long getPOSTINGPERIOD_ID() {
		return POSTINGPERIOD_ID;
	}

	public void setPOSTINGPERIOD_ID(Long pOSTINGPERIOD_ID) {
		POSTINGPERIOD_ID = pOSTINGPERIOD_ID;
	}

	public String getPOSTINGPERIOD_DETAIL() {
		return POSTINGPERIOD_DETAIL;
	}

	public void setPOSTINGPERIOD_DETAIL(String pOSTINGPERIOD_DETAIL) {
		POSTINGPERIOD_DETAIL = pOSTINGPERIOD_DETAIL;
	}

	public Long getREFUNDMETHOD_ID() {
		return REFUNDMETHOD_ID;
	}

	public void setREFUNDMETHOD_ID(Long rEFUNDMETHOD_ID) {
		REFUNDMETHOD_ID = rEFUNDMETHOD_ID;
	}

	public String getREFUNDMETHOD_DETAIL() {
		return REFUNDMETHOD_DETAIL;
	}

	public void setREFUNDMETHOD_DETAIL(String rEFUNDMETHOD_DETAIL) {
		REFUNDMETHOD_DETAIL = rEFUNDMETHOD_DETAIL;
	}

	public String getCHECK_NUMBER() {
		return CHECK_NUMBER;
	}

	public void setCHECK_NUMBER(String cHECK_NUMBER) {
		CHECK_NUMBER = cHECK_NUMBER;
	}

	public String getCREDITCARD_NUMBER() {
		return CREDITCARD_NUMBER;
	}

	public void setCREDITCARD_NUMBER(String cREDITCARD_NUMBER) {
		CREDITCARD_NUMBER = cREDITCARD_NUMBER;
	}

	public String getEXPIRE_DATE() {
		return EXPIRE_DATE;
	}

	public void setEXPIRE_DATE(String eXPIRE_DATE) {
		EXPIRE_DATE = eXPIRE_DATE;
	}

	public String getNAME_ONCARD() {
		return NAME_ONCARD;
	}

	public void setNAME_ONCARD(String nAME_ONCARD) {
		NAME_ONCARD = nAME_ONCARD;
	}

	public String getCARD_STREET() {
		return CARD_STREET;
	}

	public void setCARD_STREET(String cARD_STREET) {
		CARD_STREET = cARD_STREET;
	}

	public String getCARD_ZIPCODE() {
		return CARD_ZIPCODE;
	}

	public void setCARD_ZIPCODE(String cARD_ZIPCODE) {
		CARD_ZIPCODE = cARD_ZIPCODE;
	}

	public String getISCCAPPROVED() {
		return ISCCAPPROVED;
	}

	public void setISCCAPPROVED(String iSCCAPPROVED) {
		ISCCAPPROVED = iSCCAPPROVED;
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
		return (long) 7;
	}
}

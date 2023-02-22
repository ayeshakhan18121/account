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
@Table(name = "TBLCREDITCARDTRANSACTION")

public class CreditCardTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CREDITCARDTRANSACTION_ID;

	@Column(name = "CUSTOMER_ID")
	private Long CUSTOMER_ID;
	
	@Transient
	private String CUSTOMER_DETAIL;
	
	@Column(name = "TRANSACTION_DATE")
	private String TRANSACTION_DATE;
	
	@Column(name = "TRANSACTION_AMOUNT")
	private Double TRANSACTION_AMOUNT;
	
	@Column(name = "TRANSACTION_STATUS")
	private String TRANSACTION_STATUS;
	
	@Column(name = "NAME_ONCARD")
	private String NAME_ONCARD;
	
	@Column(name = "CARD_NUMBER")
	private String CARD_NUMBER;
	
	@Column(name = "CARDTYPE_ID")
	private Long CARDTYPE_ID;
	
	@Transient
	private String CARDTYPE_DETAIL;
	
	@Column(name = "AUTHCODE")
	private Long AUTHCODE;

	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getCREDITCARDTRANSACTION_ID() {
		return CREDITCARDTRANSACTION_ID;
	}

	public void setCREDITCARDTRANSACTION_ID(long cREDITCARDTRANSACTION_ID) {
		CREDITCARDTRANSACTION_ID = cREDITCARDTRANSACTION_ID;
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

	public String getTRANSACTION_DATE() {
		return TRANSACTION_DATE;
	}

	public void setTRANSACTION_DATE(String tRANSACTION_DATE) {
		TRANSACTION_DATE = tRANSACTION_DATE;
	}

	public Double getTRANSACTION_AMOUNT() {
		return TRANSACTION_AMOUNT;
	}

	public void setTRANSACTION_AMOUNT(Double tRANSACTION_AMOUNT) {
		TRANSACTION_AMOUNT = tRANSACTION_AMOUNT;
	}

	public String getTRANSACTION_STATUS() {
		return TRANSACTION_STATUS;
	}

	public void setTRANSACTION_STATUS(String tRANSACTION_STATUS) {
		TRANSACTION_STATUS = tRANSACTION_STATUS;
	}

	public String getNAME_ONCARD() {
		return NAME_ONCARD;
	}

	public void setNAME_ONCARD(String nAME_ONCARD) {
		NAME_ONCARD = nAME_ONCARD;
	}

	public String getCARD_NUMBER() {
		return CARD_NUMBER;
	}

	public void setCARD_NUMBER(String cARD_NUMBER) {
		CARD_NUMBER = cARD_NUMBER;
	}

	public Long getCARDTYPE_ID() {
		return CARDTYPE_ID;
	}

	public void setCARDTYPE_ID(Long cARDTYPE_ID) {
		CARDTYPE_ID = cARDTYPE_ID;
	}

	public String getCARDTYPE_DETAIL() {
		return CARDTYPE_DETAIL;
	}

	public void setCARDTYPE_DETAIL(String cARDTYPE_DETAIL) {
		CARDTYPE_DETAIL = cARDTYPE_DETAIL;
	}

	public Long getAUTHCODE() {
		return AUTHCODE;
	}

	public void setAUTHCODE(Long aUTHCODE) {
		AUTHCODE = aUTHCODE;
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
		return (long) 4;
	}

}

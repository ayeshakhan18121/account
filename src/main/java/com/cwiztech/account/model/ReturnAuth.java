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
@Table(name = "TBLRETURNAUTH")

public class ReturnAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long RETURNAUTH_ID;
	
	@Column(name = "CUSTOMERREFUND_ID")
	private Long CUSTOMERREFUND_ID;
	
	@Transient
	private String CUSTOMERREFUND_DETAIL;

	@Column(name = "CUSTOMER_ID")
	private Long CUSTOMER_ID;
	
	@Transient
	private String CUSTOMER_DETAIL;
	
	@Column(name = "PRODUCT_ID")
	private Long PRODUCT_ID;
	
	@Transient
	private String PRODUCT_DETAIL;

	@Column(name = "RETURNAUTH_CODE")
	private String RETURNAUTH_CODE;
	
	@Column(name = "RETURNAUTH_DATE")
	private String RETURNAUTH_DATE;
	
	@Column(name = "DELIVERY_DATE")
	private Double DELIVERY_DATE;
	
	@Column(name = "CURRENCY_ID")
	private Long CURRENCY_ID;
	
	@Transient
	private String CURRENCY_DETAIL;
	
	@Column(name = "EXCHANGE_RATE")
	private Double EXCHANGE_RATE;
	
	@Column(name = "RATE")
	private Double RATE;
	
	@Column(name = "DISCOUNT")
	private Double DISCOUNT;
	
	@Column(name = "RETURNSTATUS_ID")
	private Long RETURNSTATUS_ID;
	
	@Transient
	private String RETURNSTATUS_DETAIL;
	
	@Column(name = "SALEORDERTYPE_ID")
	private Long SALEORDERTYPE_ID;
	
	@Transient
	private String SALEORDERTYPE_DETAIL;

	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getRETURNAUTH_ID() {
		return RETURNAUTH_ID;
	}

	public void setRETURNAUTH_ID(long rETURNAUTH_ID) {
		RETURNAUTH_ID = rETURNAUTH_ID;
	}

	public Long getCUSTOMERREFUND_ID() {
		return CUSTOMERREFUND_ID;
	}

	public void setCUSTOMERREFUND_ID(Long cUSTOMERREFUND_ID) {
		CUSTOMERREFUND_ID = cUSTOMERREFUND_ID;
	}

	public String getCUSTOMERREFUND_DETAIL() {
		return CUSTOMERREFUND_DETAIL;
	}

	public void setCUSTOMERREFUND_DETAIL(String cUSTOMERREFUND_DETAIL) {
		CUSTOMERREFUND_DETAIL = cUSTOMERREFUND_DETAIL;
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

	public Long getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(Long pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}

	public String getPRODUCT_DETAIL() {
		return PRODUCT_DETAIL;
	}

	public void setPRODUCT_DETAIL(String pRODUCT_DETAIL) {
		PRODUCT_DETAIL = pRODUCT_DETAIL;
	}

	public String getRETURNAUTH_CODE() {
		return RETURNAUTH_CODE;
	}

	public void setRETURNAUTH_CODE(String rETURNAUTH_CODE) {
		RETURNAUTH_CODE = rETURNAUTH_CODE;
	}

	public String getRETURNAUTH_DATE() {
		return RETURNAUTH_DATE;
	}

	public void setRETURNAUTH_DATE(String rETURNAUTH_DATE) {
		RETURNAUTH_DATE = rETURNAUTH_DATE;
	}

	public Double getDELIVERY_DATE() {
		return DELIVERY_DATE;
	}

	public void setDELIVERY_DATE(Double dELIVERY_DATE) {
		DELIVERY_DATE = dELIVERY_DATE;
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

	public Double getRATE() {
		return RATE;
	}

	public void setRATE(Double rATE) {
		RATE = rATE;
	}

	public Double getDISCOUNT() {
		return DISCOUNT;
	}

	public void setDISCOUNT(Double dISCOUNT) {
		DISCOUNT = dISCOUNT;
	}

	public Long getRETURNSTATUS_ID() {
		return RETURNSTATUS_ID;
	}

	public void setRETURNSTATUS_ID(Long rETURNSTATUS_ID) {
		RETURNSTATUS_ID = rETURNSTATUS_ID;
	}

	public String getRETURNSTATUS_DETAIL() {
		return RETURNSTATUS_DETAIL;
	}

	public void setRETURNSTATUS_DETAIL(String rETURNSTATUS_DETAIL) {
		RETURNSTATUS_DETAIL = rETURNSTATUS_DETAIL;
	}

	public Long getSALEORDERTYPE_ID() {
		return SALEORDERTYPE_ID;
	}

	public void setSALEORDERTYPE_ID(Long sALEORDERTYPE_ID) {
		SALEORDERTYPE_ID = sALEORDERTYPE_ID;
	}

	public String getSALEORDERTYPE_DETAIL() {
		return SALEORDERTYPE_DETAIL;
	}

	public void setSALEORDERTYPE_DETAIL(String sALEORDERTYPE_DETAIL) {
		SALEORDERTYPE_DETAIL = sALEORDERTYPE_DETAIL;
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
		return (long) 10;
	}
}

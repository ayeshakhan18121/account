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
@Table(name = "TBLBANKOPENINGBALANCE")

public class BankOpeningBalance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long BANKOPENINGBALANCE_ID;

	@Column(name = "BANKACCOUNT_ID")
	private String BANKACCOUNT_ID;
	
	@Transient
	private String BANKACCOUNT_DETAIL;
	
	@Column(name = "TRANSACTION_ID")
	private String TRANSACTION_ID;
	
	@Transient
	private String TRANSACTION_DETAIL;
	
	@Column(name = "TRANSACTIONTYPE_ID")
	private String TRANSACTIONTYPE_ID;
	
	@Transient
	private String TRANSACTIONTYPE_DETAIL;
	
	@Column(name = "BANKOPENINGBALANCE_NAME")
	private String BANKOPENINGBALANCE_NAME;
	
	@Column(name = "BANKOPENINGBALANCE_DESC")
	private String BANKOPENINGBALANCE_DESC;
	
	@Column(name = "BANKOPENINGBALANCE_DATE")
	private String BANKOPENINGBALANCE_DATE;
	
	@Column(name = "BANKOPENINGBALANCE_DEBIT")
	private Long BANKOPENINGBALANCE_DEBIT;
	
	@Column(name = "BANKOPENINGBALANCE_CREDIT")
	private Long BANKOPENINGBALANCE_CREDIT;
	
	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;
	
	
	public long getBANKOPENINGBALANCE_ID() {
		return BANKOPENINGBALANCE_ID;
	}

	public void setBANKOPENINGBALANCE_ID(long bANKOPENINGBALANCE_ID) {
		BANKOPENINGBALANCE_ID = bANKOPENINGBALANCE_ID;
	}

	public String getBANKACCOUNT_ID() {
		return BANKACCOUNT_ID;
	}

	public void setBANKACCOUNT_ID(String bANKACCOUNT_ID) {
		BANKACCOUNT_ID = bANKACCOUNT_ID;
	}

	public String getBANKACCOUNT_DETAIL() {
		return BANKACCOUNT_DETAIL;
	}

	public void setBANKACCOUNT_DETAIL(String bANKACCOUNT_DETAIL) {
		BANKACCOUNT_DETAIL = bANKACCOUNT_DETAIL;
	}

	public String getTRANSACTION_ID() {
		return TRANSACTION_ID;
	}

	public void setTRANSACTION_ID(String tRANSACTION_ID) {
		TRANSACTION_ID = tRANSACTION_ID;
	}

	public String getTRANSACTION_DETAIL() {
		return TRANSACTION_DETAIL;
	}

	public void setTRANSACTION_DETAIL(String tRANSACTION_DETAIL) {
		TRANSACTION_DETAIL = tRANSACTION_DETAIL;
	}

	public String getTRANSACTIONTYPE_ID() {
		return TRANSACTIONTYPE_ID;
	}

	public void setTRANSACTIONTYPE_ID(String tRANSACTIONTYPE_ID) {
		TRANSACTIONTYPE_ID = tRANSACTIONTYPE_ID;
	}

	public String getTRANSACTIONTYPE_DETAIL() {
		return TRANSACTIONTYPE_DETAIL;
	}

	public void setTRANSACTIONTYPE_DETAIL(String tRANSACTIONTYPE_DETAIL) {
		TRANSACTIONTYPE_DETAIL = tRANSACTIONTYPE_DETAIL;
	}

	public String getBANKOPENINGBALANCE_NAME() {
		return BANKOPENINGBALANCE_NAME;
	}

	public void setBANKOPENINGBALANCE_NAME(String bANKOPENINGBALANCE_NAME) {
		BANKOPENINGBALANCE_NAME = bANKOPENINGBALANCE_NAME;
	}

	public String getBANKOPENINGBALANCE_DESC() {
		return BANKOPENINGBALANCE_DESC;
	}

	public void setBANKOPENINGBALANCE_DESC(String bANKOPENINGBALANCE_DESC) {
		BANKOPENINGBALANCE_DESC = bANKOPENINGBALANCE_DESC;
	}

	public String getBANKOPENINGBALANCE_DATE() {
		return BANKOPENINGBALANCE_DATE;
	}

	public void setBANKOPENINGBALANCE_DATE(String bANKOPENINGBALANCE_DATE) {
		BANKOPENINGBALANCE_DATE = bANKOPENINGBALANCE_DATE;
	}

	public Long getBANKOPENINGBALANCE_DEBIT() {
		return BANKOPENINGBALANCE_DEBIT;
	}

	public void setBANKOPENINGBALANCE_DEBIT(Long bANKOPENINGBALANCE_DEBIT) {
		BANKOPENINGBALANCE_DEBIT = bANKOPENINGBALANCE_DEBIT;
	}

	public Long getBANKOPENINGBALANCE_CREDIT() {
		return BANKOPENINGBALANCE_CREDIT;
	}

	public void setBANKOPENINGBALANCE_CREDIT(Long bANKOPENINGBALANCE_CREDIT) {
		BANKOPENINGBALANCE_CREDIT = bANKOPENINGBALANCE_CREDIT;
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


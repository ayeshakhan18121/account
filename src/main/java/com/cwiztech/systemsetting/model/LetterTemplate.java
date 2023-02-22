package com.cwiztech.systemsetting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBLSYSTEMSETTINGLETTERTEMPLATE")
public class LetterTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long LETTER_ID;

	@OneToOne
	@JoinColumn(name = "LETTERCATEGORY_ID")
	private Lookup LETTERCATEGORY_ID;

	@OneToOne
	@JoinColumn(name = "LETTERHEAD_ID")
	private Lookup LETTERHEAD_ID;

	@Column(name = "LETTER_CODE")
	private String LETTER_CODE;

	@Column(name = "LETTER_TITLE")
	private String LETTER_TITLE;

	@Column(name = "LETTER_SUBJECT")
	private String LETTER_SUBJECT;

	@Column(name = "LETTER_TEXT")
	private String LETTER_TEXT;

	@Column(name = "LETTER_PARAMETERS")
	private String LETTER_PARAMETERS;
	
	@Column(name = "ISACTIVE")
	private String ISACTIVE;

	@JsonIgnore
	@Column(name = "MODIFIED_BY")
	private Long MODIFIED_BY;

	@Column(name = "MODIFIED_WHEN")
	private String MODIFIED_WHEN;

	@Column(name = "MODIFIED_WORKSTATION")
	private String MODIFIED_WORKSTATION;

	public long getLETTER_ID() {
		return LETTER_ID;
	}

	public void setLETTER_ID(long lETTER_ID) {
		LETTER_ID = lETTER_ID;
	}

	public Lookup getLETTERCATEGORY_ID() {
		return LETTERCATEGORY_ID;
	}

	public void setLETTERCATEGORY_ID(Lookup lETTERCATEGORY_ID) {
		LETTERCATEGORY_ID = lETTERCATEGORY_ID;
	}

	public Lookup getLETTERHEAD_ID() {
		return LETTERHEAD_ID;
	}

	public void setLETTERHEAD_ID(Lookup lETTERHEAD_ID) {
		LETTERHEAD_ID = lETTERHEAD_ID;
	}

	public String getLETTER_CODE() {
		return LETTER_CODE;
	}

	public void setLETTER_CODE(String lETTER_CODE) {
		LETTER_CODE = lETTER_CODE;
	}

	public String getLETTER_TITLE() {
		return LETTER_TITLE;
	}

	public void setLETTER_TITLE(String lETTER_TITLE) {
		LETTER_TITLE = lETTER_TITLE;
	}

	public String getLETTER_SUBJECT() {
		return LETTER_SUBJECT;
	}

	public void setLETTER_SUBJECT(String lETTER_SUBJECT) {
		LETTER_SUBJECT = lETTER_SUBJECT;
	}

	public String getLETTER_TEXT() {
		return LETTER_TEXT;
	}

	public void setLETTER_TEXT(String lETTER_TEXT) {
		LETTER_TEXT = lETTER_TEXT;
	}

	public String getLETTER_PARAMETERS() {
		return LETTER_PARAMETERS;
	}

	public void setLETTER_PARAMETERS(String lETTER_PARAMETERS) {
		LETTER_PARAMETERS = lETTER_PARAMETERS;
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
		return (long) 35;
	}

}

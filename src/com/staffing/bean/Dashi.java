package com.staffing.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_dashi")
public class Dashi {
	
	private String dashiId;
	private String dashiNo;
	private String dashiTitle;
	private String dashiContent;
	private Timestamp dashiDate;
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getDashiId() {
		return dashiId;
	}
	public void setDashiId(String dashiId) {
		this.dashiId = dashiId;
	}
	public String getDashiNo() {
		return dashiNo;
	}
	public void setDashiNo(String dashiNo) {
		this.dashiNo = dashiNo;
	}
	public String getDashiTitle() {
		return dashiTitle;
	}
	public void setDashiTitle(String dashiTitle) {
		this.dashiTitle = dashiTitle;
	}
	public String getDashiContent() {
		return dashiContent;
	}
	public void setDashiContent(String dashiContent) {
		this.dashiContent = dashiContent;
	}
	public Timestamp getDashiDate() {
		return dashiDate;
	}
	public void setDashiDate(Timestamp dashiDate) {
		this.dashiDate = dashiDate;
	}

}

package com.staffing.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_employ")
public class Employ {

	private String employId;
	private String employNo;
	private String employTitle;
	private String employContent;
	private Timestamp employDate;
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getEmployId() {
		return employId;
	}
	public void setEmployId(String employId) {
		this.employId = employId;
	}
	public String getEmployNo() {
		return employNo;
	}
	public void setEmployNo(String employNo) {
		this.employNo = employNo;
	}
	public String getEmployTitle() {
		return employTitle;
	}
	public void setEmployTitle(String employTitle) {
		this.employTitle = employTitle;
	}
	public String getEmployContent() {
		return employContent;
	}
	public void setEmployContent(String employContent) {
		this.employContent = employContent;
	}
	public Timestamp getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Timestamp employDate) {
		this.employDate = employDate;
	}
	
}

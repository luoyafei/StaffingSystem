package com.staffing.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_salary")
public class Salary {
	
	private String salaryId;
	private String salaryNo;
	private String salaryTitle;
	private String salaryContent;
	private Timestamp salaryDate;
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}
	public String getSalaryNo() {
		return salaryNo;
	}
	public void setSalaryNo(String salaryNo) {
		this.salaryNo = salaryNo;
	}
	public String getSalaryTitle() {
		return salaryTitle;
	}
	public void setSalaryTitle(String salaryTitle) {
		this.salaryTitle = salaryTitle;
	}
	public String getSalaryContent() {
		return salaryContent;
	}
	public void setSalaryContent(String salaryContent) {
		this.salaryContent = salaryContent;
	}
	public Timestamp getSalaryDate() {
		return salaryDate;
	}
	public void setSalaryDate(Timestamp salaryDate) {
		this.salaryDate = salaryDate;
	}
}

package com.staffing.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_organization")
public class Organization {

	private String organizationId;
	private String organizationNo;
	private String organizationTitle;
	private String organizationContent;
	private Timestamp organizationDate;
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationNo() {
		return organizationNo;
	}
	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}
	public String getOrganizationTitle() {
		return organizationTitle;
	}
	public void setOrganizationTitle(String organizationTitle) {
		this.organizationTitle = organizationTitle;
	}
	public String getOrganizationContent() {
		return organizationContent;
	}
	public void setOrganizationContent(String organizationContent) {
		this.organizationContent = organizationContent;
	}
	public Timestamp getOrganizationDate() {
		return organizationDate;
	}
	public void setOrganizationDate(Timestamp organizationDate) {
		this.organizationDate = organizationDate;
	}
	
}

package main.java.com.intellect.springboot.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private String id;
	private String fname;
	private String lname;
	private String email;
	private Integer pinCode;
	private Date birthDate;
	private boolean isActive;
	
	
	public User(Integer pinCode, String lname, String id, boolean isActive,
			String email, Date birthDate, String fname) {
		super();
		this.pinCode = pinCode;
		this.lname = lname;
		this.id = id;
		this.isActive = isActive;
		this.email = email;
		this.birthDate = birthDate;
		this.fname = fname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname
				+ ", email=" + email + ", pinCode=" + pinCode + ", birthDate="
				+ birthDate + ", isActive=" + isActive + "]";
	}	
}
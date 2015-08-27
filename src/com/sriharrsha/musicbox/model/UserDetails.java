package com.sriharrsha.musicbox.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="USER_DETAILS")
public class UserDetails {
	private Date birthDate;
	private String emailId;
	private String gender;
	@Embedded
	private Name name;
	private String password;
	
	@OneToMany
	@JoinColumn(name="USER_ID")
	private List<Bookings> bookings;

	public List<Bookings> getBookings() {
		return bookings;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}

	private long phoneNumber;

	@Column(unique=true)
	private String regNo;

	private String type;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;

	public Date getBirthDate() {
		return birthDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getGender() {
		return gender;
	}

	public int getId() {
		return userId;
	}

	public Name getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRegNo() {
		return regNo;
	}

	public String getType() {
		return type;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return  name.getFirstName() +" "+ name.getLastName() +": RegNo:" +regNo+ "";
		
	}
	
	

}

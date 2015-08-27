package com.sriharrsha.musicbox.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
@Entity
@Table(name="BOOKINGS_TABLE")
public class Bookings {

	@ElementCollection
	private List<UserDetails> allottedMembers = new LinkedList<UserDetails>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date creationTime;
	
	
	@ManyToOne
	@JoinColumn(name="BOOKING_ID")
	private UserDetails userDetails;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}



	private String groupName;

	private String instrumentName;

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}



	private int slotId;

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long bookingId;
	
	private Date practiceDate;

	public Date getPracticeDate() {
		return practiceDate;
	}

	public void setPracticeDate(Date practiceDate) {
		this.practiceDate = practiceDate;
	}

	public List<UserDetails> getAllottedMembers() {
		return allottedMembers;
	}
	
	
	
	public String approvalStatus;

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getGroupName() {
		return groupName;
	}

	public int getSlotId() {
		return slotId;
	}



	public void setAllottedMembers(List<UserDetails> allottedMembers) {
		this.allottedMembers = allottedMembers;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}



}

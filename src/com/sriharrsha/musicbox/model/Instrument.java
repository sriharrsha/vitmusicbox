package com.sriharrsha.musicbox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="INVENTORY")
public class Instrument {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int instrumentId;
	public int getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfInstruments() {
		return numberOfInstruments;
	}

	public void setNumberOfInstruments(int numberOfInstruments) {
		this.numberOfInstruments = numberOfInstruments;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	private String name;
	private int numberOfInstruments;
	
	private int availability;
}

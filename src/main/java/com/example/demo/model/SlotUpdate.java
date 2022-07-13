package com.example.demo.model;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SlotUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer serviceCenterId;
	private LocalDate appointmentDate;
	private Time slot;
	private Integer availableCapacity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(Integer serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	public Time getSlot() {
		return slot;
	}
	public void setSlot(Time slot) {
		this.slot = slot;
	}
	public Integer getAvailableCapacity() {
		return availableCapacity;
	}
	public void setAvailableCapacity(Integer availableCapacity) {
		this.availableCapacity = availableCapacity;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	
	

}

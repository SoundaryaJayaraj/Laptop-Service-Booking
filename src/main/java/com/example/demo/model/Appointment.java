package com.example.demo.model;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmentId;
	private String lapCategory; 
	private String modelType;
	private String  defectInfo;
	private LocalDate  appointmentDate;
	private  Time appointmentTime;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customerId;
	
	@ManyToOne
	@JoinColumn(name = "serviceCenterId")
	private ServiceCentres serviceCenterId;
	

	public ServiceCentres getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(ServiceCentres serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Customer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}
	public String getLapCategory() {
		return lapCategory;
	}
	public void setLapCategory(String lapCategory) {
		this.lapCategory = lapCategory;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getDefectInfo() {
		return defectInfo;
	}
	public void setDefectInfo(String defectInfo) {
		this.defectInfo = defectInfo;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Time getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	
}

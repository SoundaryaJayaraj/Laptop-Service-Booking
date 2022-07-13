package com.example.demo.Dto;

import java.sql.Time;
import java.time.LocalDate;

public class AppointmentDto {

	private Integer appointmentId;
	private String lapCategory; 
	private String modelType;
	private String  defectInfo;
	private LocalDate  appointmentDate;
	private Time appointmentTime;
	private Integer customerId;
	private Integer serviceCenterId;
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
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
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(Integer serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	
}

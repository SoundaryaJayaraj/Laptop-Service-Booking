package com.example.demo.model;

import java.sql.Time;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "serviceCenters")
public class ServiceCentres {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serviceCenterId;
	private String centerName;
	private String contactPerson;
	private String contactNum;
    private String email;
	private String  city;
    private String state;
    private String country;
    private Time openingTime;
    private Time closingTime;
    private Integer slotCapacity;
    
    @ManyToMany
    @JoinTable(name = "servicecenter_holidays",
        joinColumns =  {@JoinColumn(name = "service_center_id", referencedColumnName = "serviceCenterId")},
        inverseJoinColumns = {@JoinColumn(name = "holiday_id", referencedColumnName = "holidayId")})
    private Set<Holiday> holidays;
    
    
	public Set<Holiday> getHolidays() {
		return holidays;
	}
	public void setHolidays(Set<Holiday> holidays) {
		this.holidays = holidays;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactNum() {
		return contactNum;
	}
	public Integer getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(Integer serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Time getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}
	public Time getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}
	public Integer getSlotCapacity() {
		return slotCapacity;
	}
	public void setSlotCapacity(Integer slotCapacity) {
		this.slotCapacity = slotCapacity;
	}
	
    
}

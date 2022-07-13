package com.example.demo.Dto;

public class AuthenticateResponse {
	
	private String accessToken;
	private Boolean status;
	private CustomerDto customerInfo;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public CustomerDto getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerDto customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	

}

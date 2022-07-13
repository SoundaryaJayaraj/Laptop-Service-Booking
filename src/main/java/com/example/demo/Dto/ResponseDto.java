package com.example.demo.Dto;

import java.util.List;

import com.example.demo.model.ServiceCentres;

public class ResponseDto {

	private int currentPage;
	private int totalPages;
	private long totalRecords;
	private List<ServiceCentres> centres ;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<ServiceCentres> getCentres() {
		return centres;
	}
	public void setCentres(List<ServiceCentres> centres) {
		this.centres = centres;
	}

}

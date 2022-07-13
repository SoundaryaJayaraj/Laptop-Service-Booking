package com.example.demo.Dto;

import java.time.LocalDate;
import java.util.List;

public class TimeDto {

	private LocalDate date;
	private List<SlotDto> slotdto;
	private boolean isHoliday;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<SlotDto> getSlotdto() {
		return slotdto;
	}

	public void setSlotdto(List<SlotDto> slotdto) {
		this.slotdto = slotdto;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	

	
}

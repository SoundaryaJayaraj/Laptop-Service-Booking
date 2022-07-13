package com.example.demo.service;

import com.example.demo.Dto.AppointmentDto;
import com.example.demo.model.Appointment;
import com.example.demo.model.Customer;
import com.example.demo.model.ServiceCentres;

public interface AppointmentService {

	Appointment appointmentCreation(Customer customer, ServiceCentres centre, AppointmentDto dto);

	
}

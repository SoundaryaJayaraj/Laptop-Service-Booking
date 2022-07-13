package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AppointmentDto;
import com.example.demo.model.Appointment;
import com.example.demo.model.Customer;
import com.example.demo.model.ServiceCentres;
import com.example.demo.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public Appointment appointmentCreation(Customer customer, ServiceCentres centre, AppointmentDto dto) {
		// Appointment creation ->
				Appointment appointment = new Appointment();
				appointment.setCustomerId(customer);
				appointment.setServiceCenterId(centre);
				appointment.setLapCategory(dto.getLapCategory());
				appointment.setModelType(dto.getModelType());
				appointment.setDefectInfo(dto.getDefectInfo());
				appointment.setAppointmentDate(dto.getAppointmentDate());
				appointment.setAppointmentTime(dto.getAppointmentTime());

				return appointmentRepository.save(appointment);
	}

}

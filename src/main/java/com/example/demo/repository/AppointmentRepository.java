package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Appointment;
import com.example.demo.model.Customer;
import com.example.demo.model.ServiceCentres;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	List<Appointment> findAllByCustomerId(Customer customer);

	List<Appointment> findAllByserviceCenterId(ServiceCentres centres);

	
	@Query("SELECT a FROM Appointment a WHERE a.serviceCenterId = ?1 and a.appointmentDate between ?2 and ?3 ")
	List<Appointment> findAllByserviceCenterIdAndappointmentDateBetween(ServiceCentres centres, LocalDate fromDate,
			LocalDate toDate);
	
	@Query("SELECT a FROM Appointment a WHERE a.customerId = ?1 and a.appointmentDate between ?2 and ?3 ")
	List<Appointment> findAllByCustomerIdAndappointmentDateBetween(Customer customer, LocalDate fromDate,
			LocalDate toDate);


}

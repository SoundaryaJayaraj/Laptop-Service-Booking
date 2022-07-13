package com.example.demo.repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SlotUpdate;

@Repository
public interface SlotUpdateRepository extends JpaRepository<SlotUpdate,Integer>{

	Optional<SlotUpdate> findByServiceCenterIdAndAppointmentDateAndSlot(Integer serviceCenterId,
			LocalDate appointmentDate, Time appointmentTime);

}

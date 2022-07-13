package com.example.demo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AppointmentDto;
import com.example.demo.excel.ExcelExporter;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.message.ResponseMessage;
import com.example.demo.model.Appointment;
import com.example.demo.model.Customer;
import com.example.demo.model.ServiceCentres;
import com.example.demo.model.SlotUpdate;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ServiceCentreRepo;
import com.example.demo.repository.SlotUpdateRepository;
import com.example.demo.service.AppointmentService;

@RestController
@RequestMapping("/api/v1")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ServiceCentreRepo serviceCentreRepo;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SlotUpdateRepository slotUpdateRepository;

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/appointment/{customerId}")
	public ResponseEntity<ResponseMessage> createAppointment(@PathVariable(value = "customerId") Integer customerId,
			@RequestBody AppointmentDto dto) {
		String message = "";

		ServiceCentres centre = serviceCentreRepo.findByServiceCenterId(dto.getServiceCenterId()).get();

		Customer customer = customerRepository.findById(customerId).get();
		// Find by date and slot time
		Optional<SlotUpdate> slots = slotUpdateRepository.findByServiceCenterIdAndAppointmentDateAndSlot(
				centre.getServiceCenterId(), dto.getAppointmentDate(), dto.getAppointmentTime());
		SlotUpdate slotUpdate = new SlotUpdate();

		// Slot update creation
		if (slots.isEmpty()) {
			slotUpdate.setServiceCenterId(centre.getServiceCenterId());
			slotUpdate.setAppointmentDate(dto.getAppointmentDate());
			slotUpdate.setSlot(dto.getAppointmentTime());
			slotUpdate.setAvailableCapacity(centre.getSlotCapacity() - 1);
			slotUpdateRepository.save(slotUpdate);
			// Appointment creation
			Appointment appointment = appointmentService.appointmentCreation(customer, centre, dto);
			message = "Appointment Successfully registered";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

		}
		if (slots.isPresent()) {
			slotUpdate = slots.get();
			if (slotUpdate.getAvailableCapacity() > 0) {
				slotUpdate.setAvailableCapacity(slotUpdate.getAvailableCapacity() - 1);
				slotUpdateRepository.save(slotUpdate);
				// Appointment creation
				Appointment appointment = appointmentService.appointmentCreation(customer, centre, dto);
				message = "Appointment Successfully registered";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

			}

			else {
				message = "Slot is Unavailable,Please Choose Another slot";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

			}
		}

		message = "completed";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

	}

	@PutMapping("/update_appointment/{appointmentId}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "appointmentId") Integer appointmentId,
			@RequestBody AppointmentDto dto) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("No records to update"));
		if (dto.getServiceCenterId() != null) {
			ServiceCentres centres = serviceCentreRepo.findByServiceCenterId(dto.getServiceCenterId()).get();
			appointment.setServiceCenterId(centres);
		}
		if (dto.getLapCategory() != null) {
			appointment.setLapCategory(dto.getLapCategory());
		}
		if (dto.getModelType() != null) {
			appointment.setModelType(dto.getModelType());
		}
		if (dto.getDefectInfo() != null) {
			appointment.setDefectInfo(dto.getDefectInfo());
		}
		if (dto.getAppointmentDate() != null) {
			appointment.setAppointmentDate(dto.getAppointmentDate());
		}
		if (dto.getAppointmentTime() != null) {
			appointment.setAppointmentTime(dto.getAppointmentTime());
		}
		final Appointment updatedAppointment = appointmentRepository.save(appointment);
		return ResponseEntity.ok(updatedAppointment);

	}

	@GetMapping("/appointments/{customerId}")
	public List<Appointment> getAppointments(@PathVariable(value = "customerId") Integer customerId) {
		try {
			// finding the Appointments
			Customer customer = customerRepository.findById(customerId).get();
			List<Appointment> Appointments = appointmentRepository.findAllByCustomerId(customer);
			return Appointments;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@GetMapping("/Export/list/AppointmentsByCus/{customerId}")
	 public void exportToExcel(HttpServletResponse response,@PathVariable(value = "customerId") Integer customerId,
			 @RequestParam(value = "fromDate",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
				@RequestParam(value = "toDate",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	     // finding the Appointments
	     			Customer customer = customerRepository.findById(customerId).get();
	     			if(fromDate == null && toDate ==null) {
	     			List<Appointment> listAppointments = appointmentRepository.findAllByCustomerId(customer);
	     			ExcelExporter excelExporter = new ExcelExporter(listAppointments); 
	     	        excelExporter.export(response); 
	     			}
	     			if(fromDate != null && toDate !=null) {
	     				List<Appointment> listAppointments = appointmentRepository.findAllByCustomerIdAndappointmentDateBetween(customer,fromDate,toDate);
		     			ExcelExporter excelExporter = new ExcelExporter(listAppointments); 
		     	        excelExporter.export(response); 
	     			}
	}
	
	@GetMapping("/Export/list/Appointments/{serviceCenterId}")
	 public void exportToExcelSheet(HttpServletResponse response,@PathVariable(value = "serviceCenterId") Integer serviceCenterId,
			 @RequestParam(value = "fromDate",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
				@RequestParam(value = "toDate",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        
	        // finding the Appointments
	     			ServiceCentres centres = serviceCentreRepo.findById(serviceCenterId).get();
	     			if(fromDate != null && toDate != null) {
	     			List<Appointment> listAppointments = appointmentRepository.findAllByserviceCenterIdAndappointmentDateBetween(centres,fromDate,toDate);
	     			ExcelExporter excelExporter = new ExcelExporter(listAppointments);
	     			excelExporter.export(response);
	     			}
	     			if(fromDate == null && toDate ==null) {
	     				List<Appointment> listAppointments = appointmentRepository.findAllByserviceCenterId(centres);
		     			ExcelExporter excelExporter = new ExcelExporter(listAppointments);
		     			excelExporter.export(response);
	     			}
			
	}
}

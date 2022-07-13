package com.example.demo.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.ResponseDto;
import com.example.demo.Dto.SlotDto;
import com.example.demo.Dto.TimeDto;
import com.example.demo.model.Holiday;
import com.example.demo.model.ServiceCentres;
import com.example.demo.repository.ServiceCentreRepo;
import com.example.demo.service.centreService;

@RestController
@RequestMapping("/api/v1")
public class ServiceCentreController {

	@Autowired
	private ServiceCentreRepo serviceCentreRepo;

	@Autowired
	private centreService centreService;
	
//	Logger logger = LoggerFactory.getLogger(ServiceCentreController.class);

	@GetMapping("/service_centres")
	public ResponseDto getAllCategories(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
		ResponseDto responsedto = new ResponseDto();
		Page<ServiceCentres> page = centreService.findPaginated(pageNo, pageSize);
		List<ServiceCentres> listCentres = page.getContent();
		responsedto.setCentres(listCentres);
		responsedto.setCurrentPage(pageNo);
		responsedto.setTotalPages(page.getTotalPages());
		responsedto.setTotalRecords(page.getTotalElements());
//		 logger.trace("A TRACE Message");
//	        logger.debug("A DEBUG Message");
//	        logger.info("An INFO Message");
//	        logger.warn("A WARN Message");
//	        logger.error("An ERROR Message");
		return responsedto;
	}

	@GetMapping("/location/{city}")
	public List<ServiceCentres> getByCity(@PathVariable(value = "city") String city) {
		List<ServiceCentres> centres = serviceCentreRepo.findByCity(city);
		return centres;
	}
	
	@GetMapping("/timeslots/{serviceCenterId}")
	public List<SlotDto> getTimeSlots2(@PathVariable(value = "serviceCenterId") Integer serviceCenterId) {
		List<SlotDto> slotDto = new ArrayList<SlotDto>();
		try {
			ServiceCentres centre = serviceCentreRepo.findByServiceCenterId(serviceCenterId).get();
			Date dateObj1 = new Date(centre.getOpeningTime().getTime());
			Date dateObj2 = new Date(centre.getClosingTime().getTime());
			long dif = dateObj1.getTime();
			while (dif < dateObj2.getTime()) {
				SlotDto dto = new SlotDto();
				Date slot = new Date(dif);
				dif += 3600000;
				System.out.println(slot);
				dto.setSlotTime(new Time(slot.getTime()));
				slotDto.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return slotDto;
	}
	
}







































//	@GetMapping("/timeslot/{serviceCenterId}")
//	public List<Date> getTimeSlots(@PathVariable(value = "serviceCenterId") Integer serviceCenterId) {
//		List<Date> slotDto = new ArrayList<Date>();
//		// Dates
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = new Date();
//		String firstDate = formatter.format(date);
//		String secondDate = formatter.format(date);
//		// Time
//		ServiceCentres centre = serviceCentreRepo.findByServiceCenterId(serviceCenterId).get();
//		Time oTime = centre.getOpeningTime();
//		Time cTime = centre.getClosingTime();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//		String firstTime = simpleDateFormat.format(oTime);
//		String secondTime = simpleDateFormat.format(cTime);
//
//		String format = "dd/MM/yyyy hh:mm:ss";
//
//		SimpleDateFormat sdf = new SimpleDateFormat(format);
//
//		Date dateObj1, dateObj2;
//		try {
//			dateObj1 = sdf.parse(firstDate + " " + firstTime);
//			dateObj2 = sdf.parse(secondDate + " " + secondTime);
//			long dif = dateObj1.getTime();
//			while (dif < dateObj2.getTime()) {
//				Date slot = new Date(dif);
//				dif += 3600000;
//				System.out.println(slot);
//				slotDto.add(slot);
//				System.out.println(slotDto);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return slotDto;
//	}
//
//	
//
//	@GetMapping("/slot/{serviceCenterId}")
//	public List<TimeDto> getTimeSlot(@PathVariable(value = "serviceCenterId") Integer serviceCenterId,
//			@RequestParam(value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
//			@RequestParam(value = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
//		List<TimeDto> timedto = new ArrayList<>();
//		ServiceCentres centre = serviceCentreRepo.findByServiceCenterId(serviceCenterId).get();
//		Set<Holiday> holidays = centre.getHolidays();
//		// to take list of holiday dates
//		for (Holiday holidayObj : holidays) {
//			LocalDate dates = holidayObj.getHolidayDate();
//			List<LocalDate> Dates = new ArrayList<>();
//			Dates.add(dates);
//
//			Date dateObj1 = new Date(centre.getOpeningTime().getTime());
//			Date dateObj2 = new Date(centre.getClosingTime().getTime());
//			long numofDaysBetween = ChronoUnit.DAYS.between(fromDate, toDate);
//
//			List<LocalDate> localDates = IntStream.iterate(0, i -> i + 1).limit(numofDaysBetween)
//					.mapToObj(i -> fromDate.plusDays(i)).collect(Collectors.toList());
//
//			localDates.forEach(date -> {
//				TimeDto tymdto = new TimeDto();
//				tymdto.setDate(date);
//
//				// to check whether date is in list of holiday dates
//				if (Dates.contains(date)) {
//					tymdto.setHoliday(true);
//				} else {
//					tymdto.setHoliday(false);
//				}
//
//				List<SlotDto> dto = new ArrayList<SlotDto>();
//				SlotDto slotdto = new SlotDto();
//				long dif = dateObj1.getTime();
//
//				while (dif < dateObj2.getTime()) {
//					Date slot = new Date(dif);
//					dif += 3600000;
//					slotdto.setSlotTime(new Time(slot.getTime()));
//					dto.add(slotdto);
//				}
//
//				tymdto.setSlotdto(dto);
//				timedto.add(tymdto);
//			});
//
//		}
//		return timedto;
//
//	}


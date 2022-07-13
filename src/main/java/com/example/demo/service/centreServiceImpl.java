package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServiceCentres;
import com.example.demo.repository.ServiceCentreRepo;

@Service
public class centreServiceImpl implements centreService {

	@Autowired
	ServiceCentreRepo serviceCentreRepo;

	@Override
	public Page<ServiceCentres> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.serviceCentreRepo.findAll(pageable);
	}
}

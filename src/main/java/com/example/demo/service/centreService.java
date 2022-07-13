package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.model.ServiceCentres;

public interface centreService {

	Page<ServiceCentres> findPaginated(int pageNo, int pageSize);

}

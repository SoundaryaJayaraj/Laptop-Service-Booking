package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceCentres;


@Repository
public interface ServiceCentreRepo extends JpaRepository<ServiceCentres, Integer> {


	@Query("SELECT c FROM ServiceCentres c WHERE c.city LIKE %?1%")
	List<ServiceCentres> findByCity(String city);

	Optional<ServiceCentres> findByServiceCenterId(Integer serviceCenterId);

}

package com.ad.server.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ad.server.domain.Advertise;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface AdvertiseRepository extends PagingAndSortingRepository<Advertise, String> {
	
	Advertise findAdvertiseByPartnerId(String partnerId);

}

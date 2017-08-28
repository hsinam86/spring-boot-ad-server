package com.ad.server.api.rest;

import java.net.URI;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ad.server.domain.Advertise;
import com.ad.server.service.AdvertiseService;

/*
 * Demonstrates of POST and GET calls for Advertise
 */

@RestController
@RequestMapping(value = "/Ad")

public class AdvertiseController extends AbstractRestHandler {

	@Autowired
	private AdvertiseService advertiseService;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })

	public ResponseEntity<String> createAdvertise(@RequestBody Advertise advertise, HttpServletRequest request) {
		Advertise createdAdvertise = this.advertiseService.createAdvertise(advertise);
		if (createdAdvertise != null && createdAdvertise.getPartnerId() != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(URI.create(
					request.getContextPath() + request.getServletPath() + "/" + createdAdvertise.getPartnerId()));

			return new ResponseEntity<String>("Ad Compaign created successfully.", httpHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Ad Compaign creation failed. Internal server error.", null,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })

	public ResponseEntity<?> getAdvertise(@PathVariable("id") String id) throws Exception {
		Advertise advertise = this.advertiseService.getAdvertise(id);
		if (advertise != null && advertise.getCreatedDate() != null && advertise.getDuration() > 0) {
			int validatity = (int) advertise.getCreatedDate().toInstant().toEpochMilli() + advertise.getDuration();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			int currentime = (int) timestamp.toInstant().toEpochMilli();
			if (validatity <= currentime) {
				return new ResponseEntity<Advertise>(advertise, HttpStatus.OK);
			}
		} else
			return new ResponseEntity<String>("This ad Compaign has expired.", null, HttpStatus.NOT_FOUND);

		return new ResponseEntity<String>("This object does not exist.", null, HttpStatus.NOT_FOUND);

	}

}

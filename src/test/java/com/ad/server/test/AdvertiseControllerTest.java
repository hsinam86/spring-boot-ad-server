package com.ad.server.test;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ad.server.api.rest.AdvertiseController;
import com.ad.server.dao.jpa.AdvertiseRepository;
import com.ad.server.domain.Advertise;
import com.ad.server.service.AdvertiseService;

import junit.framework.Assert;

@PrepareForTest(AdvertiseController.class)
public class AdvertiseControllerTest extends PowerMockTestCase {

	@InjectMocks
	private AdvertiseController advertiseController;
	
	@Mock
	private AdvertiseRepository advertiseRepository;
	
	@Mock
	private AdvertiseService advertiseService;
	
	private Advertise advertise;
	
	@BeforeTest
    public void setUp() throws Exception {
		this.advertise = new Advertise();
		this.advertise.setId(8);
		this.advertise.setDuration(6);
		this.advertise.setPartnerId("Amazon");
		this.advertise.setCreatedDate(new Timestamp(new Long("1503870797115")));
		this.advertiseRepository= PowerMockito.mock(AdvertiseRepository.class);
		this.advertiseService = PowerMockito.mock(AdvertiseService.class);
		this.advertiseController = new AdvertiseController();
    }
	
	
	@AfterTest
	public void tearDown(){
		this.advertiseRepository = null;
    	this.advertise = null;
    	this.advertiseController = null;
    	this.advertiseService =null;
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testAdvertiseGet() throws Exception{
		PowerMockito.when(advertiseRepository.findAdvertiseByPartnerId(Matchers.anyString()))
			.thenReturn(advertise);
		PowerMockito.when(advertiseService.getAdvertise(Matchers.anyString()))
		.thenReturn(advertise);
		
		ResponseEntity<Advertise> response  = (ResponseEntity<Advertise>) advertiseController.getAdvertise("Amazon");
		
		Assert.assertEquals("Amazon", ((Advertise) response.getBody()).getPartnerId());
		Assert.assertEquals(200, response.getStatusCodeValue());
	}
	
	
}

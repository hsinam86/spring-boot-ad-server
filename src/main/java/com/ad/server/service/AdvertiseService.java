package com.ad.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.server.dao.jpa.AdvertiseRepository;
import com.ad.server.domain.Advertise;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class AdvertiseService {

    @Autowired
    private AdvertiseRepository advertiseRepository;


    public AdvertiseService() {
    }

    public Advertise createAdvertise(Advertise advertise) {
        return advertiseRepository.save(advertise);
    }

    public Advertise getAdvertise(String partnerId) {
        return advertiseRepository.findAdvertiseByPartnerId(partnerId);
    }

}

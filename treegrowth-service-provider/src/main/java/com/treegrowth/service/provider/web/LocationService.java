package com.treegrowth.service.provider.web;

import com.treegrowth.model.bo.LocationAnalysisResponse;
import com.treegrowth.service.provider.config.LocationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("service/location")
@EnableConfigurationProperties(value = LocationConfiguration.class)
public class LocationService {

    @Autowired
    private LocationConfiguration locationConfiguration;

    @RequestMapping(method = GET)
    public LocationAnalysisResponse analysisAddress(@RequestParam("ip") String ip) {
        return new LocationAnalysisResponse(this.getRestTemplate().getForObject(locationConfiguration.getIpLocation(), String.class, ip));
    }

    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

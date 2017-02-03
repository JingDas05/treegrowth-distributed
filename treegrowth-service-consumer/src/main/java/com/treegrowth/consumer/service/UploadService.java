package com.treegrowth.consumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("service-provider")
@RequestMapping("service/upload")
public interface UploadService {

    @RequestMapping(method = GET)
    String getUploadToken();
}

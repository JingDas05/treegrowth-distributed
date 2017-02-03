package com.treegrowth.consumer.service;

import com.treegrowth.model.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient("service-provider")
@RequestMapping("service/mail")
public interface MailService {

    @RequestMapping(method = POST)
    void send(@RequestBody User user);
}

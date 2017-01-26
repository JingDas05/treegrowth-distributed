package com.treegrowth.consumer.service;

import com.treegrowth.model.bo.AmendedUser;
import com.treegrowth.model.bo.UserDetail;
import com.treegrowth.model.bo.UserDetailBasic;
import com.treegrowth.model.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient("service-consumer")
@RequestMapping("/service/users")
public interface UserService {

    @RequestMapping(value = "/post", method = POST)
    UserDetailBasic create(@RequestBody User user);

    @RequestMapping(value = "/{userId}",method = DELETE)
    void delete(@PathVariable("userId") String userId);

    @RequestMapping(value = "/put", method = PUT)
    UserDetailBasic update(@RequestParam("userId") String userId,
                           @RequestBody AmendedUser amendedUser);

    @RequestMapping(value = "/get", method = GET)
    UserDetailBasic get(@RequestParam("loginUserId") String loginUserId,
                        @RequestParam("userId") String userId);

    @RequestMapping(value = "/email", method = GET)
    Optional<UserDetail> findByEmail(@RequestParam("email") String email);
}

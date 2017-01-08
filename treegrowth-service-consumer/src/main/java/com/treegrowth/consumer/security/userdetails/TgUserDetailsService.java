package com.treegrowth.consumer.security.userdetails;

import com.treegrowth.common.exception.NotFoundException;
import com.treegrowth.consumer.service.UserService;
import com.treegrowth.model.bo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.treegrowth.common.exception.NotFoundException.Message.USER;


@Service()
public class TgUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDetail userDetail = userService.findByEmail(userName).orElseThrow(() -> new NotFoundException(USER));
        return new TgUserDetails(userDetail);
    }
}
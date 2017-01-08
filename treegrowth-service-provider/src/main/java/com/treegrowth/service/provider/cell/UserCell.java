package com.treegrowth.service.provider.cell;

import com.treegrowth.common.exception.NotFoundException;
import com.treegrowth.dao.jparepository.UserRepository;
import com.treegrowth.model.bo.UserDetailBasic;
import com.treegrowth.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@EnableCaching
public class UserCell {

    @Autowired
    private UserRepository userRepository;

   // @UserBasicCache
    public UserDetailBasic getBasic(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(NotFoundException.Message.USER));
        UserDetailBasic userDetailBasic = new UserDetailBasic();
        userDetailBasic.setId(user.getId());
        userDetailBasic.setName(user.getName());
        userDetailBasic.setEmail(user.getEmail());
        userDetailBasic.setPhoneNumber(user.getPhoneNumber());
        userDetailBasic.setRegistrationTime(user.getRegistrationTime());
        userDetailBasic.setLastAccessTime(user.getLastAccessTime());
        userDetailBasic.setUserConfirmStatus(user.getUserConfirmStatus());
        return userDetailBasic;
    }
}

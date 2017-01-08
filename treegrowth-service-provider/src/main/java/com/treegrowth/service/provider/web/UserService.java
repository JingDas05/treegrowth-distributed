package com.treegrowth.service.provider.web;

import com.treegrowth.common.exception.ConflictException;
import com.treegrowth.common.exception.ForbiddenException;
import com.treegrowth.common.exception.NotFoundException;
import com.treegrowth.dao.jparepository.UserRepository;
import com.treegrowth.message.quene.core.Sender;
import com.treegrowth.message.quene.message.UserMessage;
import com.treegrowth.model.bo.AmendedUser;
import com.treegrowth.model.bo.UserDetail;
import com.treegrowth.model.bo.UserDetailBasic;
import com.treegrowth.model.entity.User;
import com.treegrowth.service.provider.cell.UserCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

import static com.treegrowth.common.exception.ConflictException.Message.USER_EXIST;
import static com.treegrowth.common.exception.ForbiddenException.Message.USER_DETAIL;
import static com.treegrowth.common.exception.NotFoundException.Message.USER;
import static com.treegrowth.common.utils.Conditions.checkState;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController()
@RequestMapping("service/users")
public class UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserCell userCell;
    @Autowired
    private MailService mailService;
    @Autowired
    private Sender sender;

    @RequestMapping(method = POST)
    public UserDetailBasic create(@RequestBody User user) {
        checkState(!userRepository.findByEmail(user.getEmail()).isPresent(), () -> new ConflictException(USER_EXIST));
        user.setRegistrationTime(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        mailService.send(savedUser);
        return new UserDetailBasic().from(savedUser);
    }

    @RequestMapping(value = "/{userId}",method = DELETE)
    public void delete(@RequestParam String userId) {
        checkState(userRepository.findById(userId).isPresent(), () -> new NotFoundException(USER));
        userRepository.delete(userId);
    }

    @RequestMapping(method = PUT)
    public UserDetailBasic update(@RequestParam String userId,
                                  @RequestBody AmendedUser amendedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER));
        user.setName(amendedUser.getName());
        User savedUser = userRepository.save(user);
        return new UserDetailBasic().from(savedUser);
    }

    @RequestMapping(method = GET)
    public UserDetailBasic get(@RequestParam String loginUserId,
                               @RequestParam String userId) {
        checkState(loginUserId.equals(userId), () -> new ForbiddenException(USER_DETAIL));
        UserDetailBasic userDetailBasic = userCell.getBasic(userId);

        UserMessage userMessage = new UserMessage();
        userMessage.setName(userDetailBasic.getEmail());
        sender.send(MessageBuilder.withPayload(userMessage)
                .setHeader(KafkaHeaders.TOPIC,"treegrowth.a")
                .build());
        return userDetailBasic;
    }

    @RequestMapping(value = "/email", method = GET)
    public Optional<UserDetail> findByEmail(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User savedUser = user.get();
            UserDetail userDetail = new UserDetail();
            userDetail.setId(savedUser.getId());
            userDetail.setUsername(savedUser.getName());
            userDetail.setPassword(savedUser.getPassword());
            userDetail.setPhone(savedUser.getPhoneNumber());
            userDetail.setUserConfirmStatus(savedUser.getUserConfirmStatus());
            userDetail.setAuthorities(savedUser.getAuthorities());
            return Optional.of(userDetail);
        }
        return Optional.empty();
    }
}

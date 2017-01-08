package com.treegrowth.service.provider.web;


import com.treegrowth.model.entity.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("service/mail")
@EnableConfigurationProperties(MailProperties.class)
public class MailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Autowired
    Template template;
    @Autowired
    MailProperties mailProperties;

    @RequestMapping(method = POST)
    public void send(@RequestBody User user) {

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setSubject("Tree_Growth");
            messageHelper.setText(textFromDateModel(user), true);
            messageHelper.setTo(user.getEmail());
        };

        javaMailSender.send(mimeMessagePreparator);
    }

    private String textFromDateModel(Object dataModel) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        template.process(dataModel, stringWriter);
        return stringWriter.toString();
    }
}

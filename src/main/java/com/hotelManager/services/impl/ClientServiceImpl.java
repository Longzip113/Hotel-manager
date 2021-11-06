package com.hotelManager.services.impl;

import com.hotelManager.dtos.DataMailDTO;
import com.hotelManager.services.ClientService;
import com.hotelManager.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

import static com.hotelManager.constants.Constants.CLIENT_REGISTER;
import static com.hotelManager.constants.Constants.FORGOT_PASSWORD;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(String email, String verification) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(email);
            dataMail.setSubject(FORGOT_PASSWORD);

            Map<String, Object> props = new HashMap<>();
            props.put("verification", verification);
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }
}

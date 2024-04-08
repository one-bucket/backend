package com.onebucket.domain.emailManage.service;

import com.onebucket.domain.emailManage.dto.EmailMessageDto;

public interface EmailService {

    String sendMail(EmailMessageDto emailMessageDto, String type);

    boolean isRightEmailAddress(String email, String univ_name);

}

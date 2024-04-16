package com.onebucket.domain.emailManage.service;

import com.onebucket.domain.emailManage.dto.EmailMessageDto;

/**
 * Currently, implement class of this interface of bean factory is {@link EmailServiceImpl}, also have only tho method.
 * <pre>
 *     String sendMail(EmailMessageDto)
 *     boolean isRightEmailAddress(email, univ_name)
 * </pre>
 */
public interface EmailService {

    String sendMail(EmailMessageDto emailMessageDto, String type);

    boolean isRightEmailAddress(String email, String univ_name);

}

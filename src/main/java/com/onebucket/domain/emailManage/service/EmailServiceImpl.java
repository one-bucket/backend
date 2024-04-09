package com.onebucket.domain.emailManage.service;

import com.onebucket.domain.emailManage.dto.EmailMessageDto;
import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.univInfoManage.dao.UniversityRepository;
import jakarta.mail.MessageRemovedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.swing.text.html.parser.Entity;
import java.util.Random;

/**
 * Provide mail service to send, check and else. <br>
 * Use JavaMailSender to send email.
 *
 * @author SangHyeok
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final UniversityRepository universityRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    /**
     * Send email by type(email, password). <br>
     * Password type send user to tempt password email(not implement yet)<br>
     * email type send user to verified code email. <br>
     * @param emailMessageDto
     * @param type
     * @return code(authNum)
     *
     *
     */
    @Override
    public String sendMail(EmailMessageDto emailMessageDto, String type) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String authNum = switch (type) {
            case "email" -> createCode(8);
            case "password" -> createCode(12);
            default -> null;
        };
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setTo(emailMessageDto.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessageDto.getSubject()); // 메일 제목
            mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            return authNum;

        } catch(MessagingException e) {
            log.info("fail to send {} message", type);
            throw new RuntimeException();
        }
    }

    /**
     * Check that Email and university name by use <b>university</b> database table.
     * @param email
     * @param univ_name
     * @return boolean
     */
    @Override
    public boolean isRightEmailAddress(String email, String univ_name) {
        String emailForm = universityRepository.findByName(univ_name).orElseThrow(() -> new EntityNotFoundException("no university name"))
                .getEmailForm();

        return email.contains(emailForm);
    }


    /**
     * Private method, create random code by size param.
     * Big, small alphabet, 0~8 number.
     * @param size
     * @return String random code
     */
    private String createCode(int size) {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i = 0; i < size; i++) {
            int index = random.nextInt(4);
            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    /**
     * private method to get html file from <b>resources/template</b> <br>
     * Get type.html and set variable to code param.
     * @param code
     * @param type
     * @return html file
     */
    private String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return springTemplateEngine.process(type, context);
    }

}

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

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final UniversityRepository universityRepository;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final MemberRepository memberRepository;
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

    @Override
    public boolean isRightEmailAddress(String email, String univ_name) {
        String emailForm = universityRepository.findByName(univ_name).orElseThrow(() -> new EntityNotFoundException("no university name"))
                .getEmailForm();

        return email.contains(emailForm);
    }

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

    private String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return springTemplateEngine.process(type, context);
    }

}

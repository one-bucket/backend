package com.onebucket.domain.testApi.mailTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final EmailService emailService;

    @PostMapping("/password")
    public ResponseEntity<?> sendPasswordEmail(@RequestBody EmailPostDto emailPostDto) {
        EmailMessageEntity emailMessage = EmailMessageEntity.builder()
                .to(emailPostDto.getEmail())
                .subject("[one-bucket] 임시 비밀번호 발급")
                .build();

        emailService.sendMail(emailMessage, "password");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/email")
    public ResponseEntity<?> sendJoinEmail(@RequestBody EmailPostDto emailPostDto) {
        EmailMessageEntity emailMessage = EmailMessageEntity.builder()
                .to(emailPostDto.getEmail())
                .subject("[one-bucket] 인증 번호 발급")
                .build();

        String code = emailService.sendMail(emailMessage, "email");
        EmailResponseDto emailResponseDto = new EmailResponseDto();
        emailResponseDto.setCode(code);

        return ResponseEntity.ok(emailResponseDto);
    }
}

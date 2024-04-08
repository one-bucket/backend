package com.onebucket.domain.emailManage.api;

import com.onebucket.domain.emailManage.dto.EmailCodeRequestDto;
import com.onebucket.domain.emailManage.dto.EmailCodeResponseDto;
import com.onebucket.domain.emailManage.dto.EmailMessageDto;
import com.onebucket.domain.emailManage.dto.EmailSendDto;
import com.onebucket.domain.emailManage.service.EmailService;
import com.onebucket.domain.univInfoManage.service.UniversityService;
import com.onebucket.global.exeptionManage.exceptions.verifiedExceptions.InvalidEmailFormException;
import com.onebucket.global.exeptionManage.exceptions.verifiedExceptions.InvalidVerifiedCodeException;
import com.onebucket.global.exeptionManage.exceptions.verifiedExceptions.UnSupportedUniversityException;
import com.onebucket.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;
    private final UniversityService universityService;
    private final RedisService redisService;

    @PostMapping("/register/email")
    public ResponseEntity<?> sendVerifiedEmail(@RequestBody EmailSendDto emailSendDto) {

        if(!universityService.isExistByName(emailSendDto.getUnivName())) {
            throw new UnSupportedUniversityException();
        }
        if(!emailService.isRightEmailAddress(emailSendDto.getEmail(), emailSendDto.getUnivName())) {
            throw new InvalidEmailFormException();
        }
        EmailMessageDto emailMessageDto = EmailMessageDto.builder()
                .to(emailSendDto.getEmail())
                .subject("[one-bucket] 인증 코드 발송")
                .build();
        String code = emailService.sendMail(emailMessageDto, "email");

        redisService.setData("verified: " + emailSendDto.getUsername(), code, 5L);
        EmailCodeResponseDto emailCodeResponseDto = new EmailCodeResponseDto();
        emailCodeResponseDto.setCode(code);

        return ResponseEntity.ok(emailCodeResponseDto);
    }

    @PostMapping("/register/code")
    public ResponseEntity<?> verifiedCode(@RequestBody EmailCodeRequestDto emailCodeRequestDto) {
        String key = "verified: " + emailCodeRequestDto.getUsername();
        String value = emailCodeRequestDto.getCode();

        if(emailCodeRequestDto.getCode().equals(redisService.getData(key))) {
            return ResponseEntity.ok("{ \"value\" : \"success\"}");
        } else {
            throw new InvalidVerifiedCodeException();
        }
    }
}

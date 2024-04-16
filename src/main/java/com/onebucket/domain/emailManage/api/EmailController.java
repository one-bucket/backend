package com.onebucket.domain.emailManage.api;

import com.onebucket.domain.emailManage.dto.EmailCodeRequestDto;
import com.onebucket.domain.emailManage.dto.EmailCodeResponseDto;
import com.onebucket.domain.emailManage.dto.EmailMessageDto;
import com.onebucket.domain.emailManage.dto.EmailSendDto;
import com.onebucket.domain.emailManage.service.EmailService;
import com.onebucket.domain.univInfoManage.service.UniversityService;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.InvalidEmailFormException;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.InvalidVerifiedCodeException;
import com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.UnSupportedUniversityException;
import com.onebucket.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *     provide endpoint <b>"/register/email"</b>, <b>"register/code"</b>. <br>
 *     With Json body(RestController), also response by <b>ResponseEntity</b>.
 * </p>
 * Also, throw Exception if value is invalid
 * which is
 * {@link com.onebucket.global.exceptionManage.exceptions.verifiedExceptions.VerifiedException VerifiedException}
 * and other implementing exception.
 * @author SangHyoek
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;
    private final UniversityService universityService;
    private final RedisService redisService;

    /**
     * Client request to server that send verified email include code.
     * Method check if school is supported, and email is valid. If not, throw exception
     * <br>
     * endpoint : /register/email <br>
     * method : post <br>
     * <pre>
     * RequestBody:
     *         String username;
     *         String email;
     *         String univName;
     * ResponseBody:
     *         String code;
     * </pre>
     * @param emailSendDto
     * @return EmailCodeResponseDto
     */
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

        return ResponseEntity.ok(EmailCodeResponseDto.builder().code(code).build());
    }


    /**
     * After user get code from email and submit, method check code and username by redis,
     * and return success or if not, return exception.<br>
     * endpoint : /register/code <br>
     * method : post <br>
     * <pre>
     * RequestBody:
     *         String username;
     *         String code;
     * ResponseBody:
     *         String value;
     * </pre>
     * @param emailCodeRequestDto
     * @return boolean {if success}
     */
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

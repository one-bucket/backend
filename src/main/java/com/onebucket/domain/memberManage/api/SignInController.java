package com.onebucket.domain.memberManage.api;

import com.onebucket.domain.memberManage.dto.SignInRequestDto;
import com.onebucket.domain.memberManage.service.SignInService;
import com.onebucket.global.auth.jwtAuth.entity.JwtToken;
import com.onebucket.global.auth.jwtAuth.refreshToken.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provide endpoint /sign-in and /test-test. <br>
 * /test-test endpoint is just for test, to show that aws ec2 server is successfully update to new version of this process.<br>
 *
 * Dependence on SignInService, RefreshTokenService
 *
 * @author SangHyeok
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class SignInController {
    private final SignInService signInService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(path = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signIn(@Valid @RequestBody
                           SignInRequestDto signInRequestDto) {
        String username = signInRequestDto.getUsername();
        String password = signInRequestDto.getPassword();


        JwtToken jwtToken = signInService.signIn(username, password);
        refreshTokenService.createRefreshToken(username, jwtToken.getRefreshToken());
        return ResponseEntity.ok(jwtToken);

    }

    @PostMapping("/test-test")
    public ResponseEntity<?> testMethod() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("test");
    }
}

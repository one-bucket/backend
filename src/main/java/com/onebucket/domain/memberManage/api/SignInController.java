package com.onebucket.domain.memberManage.api;

import com.onebucket.domain.memberManage.dto.SignInRequestDto;
import com.onebucket.domain.memberManage.service.SignInService;
import com.onebucket.global.auth.jwtAuth.entity.JwtToken;
import com.onebucket.global.auth.jwtAuth.refreshToken.RefreshTokenService;
import com.onebucket.global.utils.ExceptionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignInController {
    private final SignInService signInService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@Valid @RequestBody
                           SignInRequestDto signInRequestDto) {
        String username = signInRequestDto.getUsername();
        String password = signInRequestDto.getPassword();
        ExceptionResponse response = new ExceptionResponse("11","init");
        try {
            JwtToken jwtToken = signInService.signIn(username, password);
            refreshTokenService.createRefreshToken(username, jwtToken.getRefreshToken());
            return ResponseEntity.ok(jwtToken);
        } catch(UsernameNotFoundException | BadCredentialsException | AccountExpiredException e) {
            response.setStatus("1001");
            response.setMessage("message");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);//ExceptionResponse.builder().status("1001").build() );
        } catch(DisabledException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(ExceptionResponse.builder().status("1011").build());
        } catch(RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(ExceptionResponse.builder().status("1040").message("unsupported").build());
        }
        return ResponseEntity.ok("11");
    }
}

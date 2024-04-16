package com.onebucket.domain.memberManage.api;


import com.onebucket.domain.memberManage.dto.RefreshTokenRequestDto;
import com.onebucket.global.auth.jwtAuth.entity.JwtToken;
import com.onebucket.global.auth.jwtAuth.refreshToken.RefreshTokenService;
import com.onebucket.global.auth.jwtAuth.utils.JwtTokenProvider;
import com.onebucket.global.auth.jwtAuth.utils.JwtTokenValidator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provide endpoint of /token-refresh which for get new token when access token is expired. <br>
 * Dependence on RefreshTokenService, JwtTokenValidator, JwtTokenProvider.
 *
 * @author SangHyeok
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/token-refresh")
    public ResponseEntity<JwtToken> tokenRefresh(HttpServletRequest request,
                                                 @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {

        //get authorization from http message, header to get authentication in access token, ignore expiration.
        String accessToken = request.getHeader("Authorization");


        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
        }
        try {
            jwtTokenValidator.validToken(accessToken);
        } catch(ExpiredJwtException ignore) {
            //ignore expiration of access token.
        } catch(JwtException e) {
            throw  e;
        }

        String token = refreshTokenRequestDto.getRefreshToken();
        if(token != null & jwtTokenValidator.validToken(token) & refreshTokenService.isTokenExist(token)) {
            Authentication authentication = jwtTokenValidator.getAuthentication(accessToken);
            //create new tokens and return;
            return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

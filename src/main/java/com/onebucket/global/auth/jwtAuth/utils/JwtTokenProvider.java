package com.onebucket.global.auth.jwtAuth.utils;

import com.onebucket.global.auth.jwtAuth.entity.JwtToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;
    private final long expireDateAccessToken;
    private final long expireDateRefreshToken;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expireDate.accessToken}") long expireDateAccessToken,
                            @Value("${jwt.expireDate.refreshToken}") long expireDateRefreshToken) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        this.expireDateAccessToken = expireDateAccessToken;
        this.expireDateRefreshToken = expireDateRefreshToken;
    }

    public JwtToken generateToken(Authentication authentication) {
        String accessToken = generateAccessToken(authentication);
        String refreshToken = generateRefreshToken();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }




    private String generateAccessToken(Authentication authentication) {
        long nowDate  = (new Date()).getTime();

        Date tokenExpiration = new Date(nowDate + expireDateAccessToken);

        String authorities = getAuthoritiesFromAuthentication(authentication);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .setExpiration(tokenExpiration)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    private String generateRefreshToken() {
        long nowDate  = (new Date()).getTime();

        Date tokenExpiration = new Date(nowDate + expireDateRefreshToken);

        return Jwts.builder()
                .setExpiration(tokenExpiration)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    /**
     * get Authentication and return it to {@code String}. <br>
     *
     * @Param Authentication
     */
    private String getAuthoritiesFromAuthentication(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining("."));
    }
}

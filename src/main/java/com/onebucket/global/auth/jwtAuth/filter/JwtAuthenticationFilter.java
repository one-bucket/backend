package com.onebucket.global.auth.jwtAuth.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebucket.global.auth.jwtAuth.utils.JwtTokenValidator;
import com.onebucket.global.exeptionManage.errorCode.JwtErrorCode;
import com.onebucket.global.exeptionManage.exceptionHandler.ErrorResponseGenerator;
import com.onebucket.global.exeptionManage.exceptions.AuthorityNotFoundException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenValidator jwtTokenValidator;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = resolveToken((HttpServletRequest) request);
        String url = ((HttpServletRequest) request).getRequestURI();

        //일부 주소 필터 적용 제거
        if ("/sign-in".equals(url) || "/token-refresh".equals(url)
                || "/register".equals(url) || "/test-test".equals(url)) {
            log.error("why {}", url);
            chain.doFilter(request, response);
            return;
        }
        try {
            if(token != null & jwtTokenValidator.validToken(token)){
                Authentication authentication = jwtTokenValidator.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            }
        } catch (ExpiredJwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write(convertErrorToJson(JwtErrorCode.EXPIRED_TOKEN));
        } catch (SignatureException e) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write(convertErrorToJson(JwtErrorCode.INVALID_SIGNATURE_TOKEN));
        } catch (MalformedJwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write(convertErrorToJson(JwtErrorCode.INVALID_FORMED_TOKEN));
        } catch (ClaimJwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write(convertErrorToJson(JwtErrorCode.INVALID_CLAIM_TOKEN));
        } catch (AuthorityNotFoundException e) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write(convertErrorToJson(JwtErrorCode.AUTHORITY_NOT_FOUND_TOKEN));
        } catch (JwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.getWriter().write(convertErrorToJson(JwtErrorCode.UNSUPPORTED_JWT_ERROR));
        }




    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    private String convertErrorToJson(JwtErrorCode errorCode) throws JsonProcessingException {
        if (errorCode == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(ErrorResponseGenerator.makeErrorResponse(errorCode));
    }
}

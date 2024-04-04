package com.onebucket.global.auth.jwtAuth.filter;

import com.onebucket.global.auth.jwtAuth.utils.JwtTokenValidator;
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
        if ("/sign-in".equals(url)
                || "/register".equals(url) || "/test-test".equals(url)) {
            log.error("why {}", url);
            chain.doFilter(request, response);
            return;
        }
        try {
            jwtTokenValidator.validToken(token);
        } catch(Exception e) {
            //ExceptionHandler.handleJwtException(e, httpResponse);
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = jwtTokenValidator.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

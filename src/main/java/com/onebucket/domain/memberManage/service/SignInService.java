package com.onebucket.domain.memberManage.service;

import com.onebucket.global.auth.jwtAuth.entity.JwtToken;
import org.springframework.security.core.AuthenticationException;

public interface SignInService {
    public JwtToken signIn(String username, String password) throws AuthenticationException;
}

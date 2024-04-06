package com.onebucket.global.auth.jwtAuth.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    boolean existsByUsernameAndRefreshToken(String username, String refreshToken);
}

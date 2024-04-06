package com.onebucket.global.auth.jwtAuth.refreshToken;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * Checks if a refresh token exists for the given username and refresh token.
     * If the token exists, it deletes the token by the provided username and returns true.
     *
     * @param refreshToken  the refresh token to check for existence
     * @return              {@code true} if the token exists and was deleted, {@code false} otherwise
     */
    public boolean isTokenExist(String refreshToken) {
        if(refreshTokenRepository.existsById(refreshToken)) {
            refreshTokenRepository.deleteById(refreshToken);
            return true;
        } else
            return false;
    }

    /**
     * Overloaded method to check for the existence of a refresh token for a given username and refresh token.
     * Optionally deletes the token if it exists and {@code needDelete} is true.
     *
     * @param refreshToken  the refresh token to check for existence
     * @param needDelete    flag indicating whether to delete the token if it exists
     * @return              {@code true} if the token exists, {@code false} otherwise. If {@code needDelete} is true and the token exists, it will be deleted.
     */
    public boolean isTokenExist(String refreshToken, boolean needDelete) {
        if(needDelete) {
            return isTokenExist(refreshToken);
        } else {
            return refreshTokenRepository.existsById(refreshToken);
        }
    }

    /**
     * Add {@link RefreshToken} method. If same data already exist so occur exception,
     * delete and re-save data.
     * @param username      the username associated with the refresh token
     * @param refreshToken  the refresh token to check for existence
     */
    @Transactional
    public void  createRefreshToken(String username, String refreshToken) {

        RefreshToken token = RefreshToken.builder()
                .username(username)
                .refreshToken(refreshToken)
                .build();
        log.error("{}{}", token.getRefreshToken(), token.getUsername());

        refreshTokenRepository.save(token);
        try {
            log.error("try");
            refreshTokenRepository.save(token);
        } catch (DataIntegrityViolationException e){
            log.error("catch");
            refreshTokenRepository.deleteById(refreshToken);
            refreshTokenRepository.save(token);
        }

    }

    public String getUsername(String token) {
        return refreshTokenRepository.findById(token).orElseThrow(() ->
                new UsernameNotFoundException("no users")).getUsername();
    }
}

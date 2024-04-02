package com.onebucket.global.auth.jwtAuth.refreshToken;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * Checks if a refresh token exists for the given username and refresh token.
     * If the token exists, it deletes the token by the provided username and returns true.
     *
     * @param username      the username associated with the refresh token
     * @param refreshToken  the refresh token to check for existence
     * @return              {@code true} if the token exists and was deleted, {@code false} otherwise
     */
    public boolean isTokenExist(String username, String refreshToken) {
        if(refreshTokenRepository.existsByUsernameAndRefreshToken(username, refreshToken)) {
            refreshTokenRepository.deleteById(username);
            return true;
        } else
            return false;
    }

    /**
     * Overloaded method to check for the existence of a refresh token for a given username and refresh token.
     * Optionally deletes the token if it exists and {@code needDelete} is true.
     *
     * @param username      the username associated with the refresh token
     * @param refreshToken  the refresh token to check for existence
     * @param needDelete    flag indicating whether to delete the token if it exists
     * @return              {@code true} if the token exists, {@code false} otherwise. If {@code needDelete} is true and the token exists, it will be deleted.
     */
    public boolean isTokenExist(String username, String refreshToken, boolean needDelete) {
        if(needDelete) {
            return isTokenExist(username, refreshToken);
        } else {
            return refreshTokenRepository.existsByUsernameAndRefreshToken(username, refreshToken);
        }
    }

    /**
     * Add {@link RefreshToken} method. If same data already exist so occur exception,
     * delete and re-save data.
     * @param username      the username associated with the refresh token
     * @param refreshToken  the refresh token to check for existence
     */
    public void  createRefreshToken(String username, String refreshToken) {

        RefreshToken token = RefreshToken.builder()
                .username(username)
                .refreshToken(refreshToken)
                .build();
        try {
            refreshTokenRepository.save(token);
        } catch (DataIntegrityViolationException e){
            refreshTokenRepository.deleteById(username);
            refreshTokenRepository.save(token);
        }
    }
}

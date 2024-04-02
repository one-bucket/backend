package com.onebucket.global.auth.jwtAuth.refreshToken;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * JWT refresh token entity class. Saved in DB.<br>
 * arguments are
 * <pre>
 *     String refreshToken;
 *     String username;
 * </pre>
 * protect level의 constructor(no params) 생성 및 {@code AllArgsConstructor} 사용
 *
 * @author SangHyeok
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    /**
     * p.k of RefreshToken Entity. <br>
     * for validating refresh token by searching in DB.
     */
    @Id
    @Column(updatable = false)
    private String username;

    /**
     * return username with refreshToken to validate token.
     */
    @Column(updatable = false, nullable = false)
    private String refreshToken;
}

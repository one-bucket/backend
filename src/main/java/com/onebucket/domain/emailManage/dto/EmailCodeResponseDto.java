package com.onebucket.domain.emailManage.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

/**
 * path: controller -> client <br>
 * endpoint: /register/email <br>
 * <pre>
 variable:
 {@code private String code;}
 * </pre>
 * @author SangHyeok
 * @version 0.0.1
 */
@Builder
@Getter
public class EmailCodeResponseDto {
    private String code;
}

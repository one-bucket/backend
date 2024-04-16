package com.onebucket.domain.emailManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * path: client -> controller <br>
 * endpoint: /register/email <br>
 * <pre>
 variable:
 {@code private String username;}
 {@code private String email;}
 {@code private String univName;}
 * </pre>
 * @author SangHyeok
 * @version 0.0.1
 */
@Getter
public class EmailSendDto {
    private String username;
    private String email;
    private String univName;
}

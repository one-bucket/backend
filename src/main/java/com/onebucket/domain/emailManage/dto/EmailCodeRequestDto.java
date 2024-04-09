package com.onebucket.domain.emailManage.dto;

import lombok.Getter;

/**
 * path: client -> controller <br>
 * endpoint: /email/code <br>
 * <pre>
variable:
    {@code private String username;}
    {@code private String code;}
 * </pre>
 * @author SangHyeok
 * @version 0.0.1
 */
@Getter
public class EmailCodeRequestDto {
    private String username;
    private String code;
}

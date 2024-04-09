package com.onebucket.domain.emailManage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * path: controller -> service <br>
 * <pre>
 variable:
 {@code private String to;}
 {@code private String subject;}
 {@code private String message;}
 * </pre>
 * @author SangHyeok
 * @version 0.0.1
 */
@Getter
@Builder
public class EmailMessageDto {

    private String to;
    private String subject;
    private String message;
}

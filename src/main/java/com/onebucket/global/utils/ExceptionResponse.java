package com.onebucket.global.utils;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String status;
    private String message;
}

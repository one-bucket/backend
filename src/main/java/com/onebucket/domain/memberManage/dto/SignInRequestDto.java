package com.onebucket.domain.memberManage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {

    @NotBlank(message = "username must not be empty.")
    String username;

    @NotBlank(message = "password must not be empty.")
    String password;
}

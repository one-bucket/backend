package com.onebucket.domain.memberManage.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDto {

    //@NotBlank(message = "refreshToken must not be null.")
    private String refreshToken;
}

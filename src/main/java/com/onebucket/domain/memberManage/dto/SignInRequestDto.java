package com.onebucket.domain.memberManage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Class Name : SignRequestDto  <br>
 * usage : DTO
 * <pre>
 *    EndPoint : "/sign-in"
 *    contain {@code String username, String password}
 * </pre>
 * <p>
 * Pattern and Annotation :
 *   Getter, Setter
 * </p>
 * @author SangHyeok
 * @version 24.4.6
 * @see com.onebucket.domain.memberManage.api.MemberController MemberController
 * @see com.onebucket.domain.memberManage.service.MemberService MemberService
 */
@Getter
@Setter
public class SignInRequestDto {

    @NotBlank(message = "username must not be empty.")
    private String username;

    @NotBlank(message = "password must not be empty.")
     private String password;
}

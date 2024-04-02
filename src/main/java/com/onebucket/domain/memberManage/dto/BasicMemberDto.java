package com.onebucket.domain.memberManage.dto;

import com.onebucket.global.DtoMarkerInterface.CreateCheck;
import com.onebucket.global.DtoMarkerInterface.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for Member entity. Used at register, response, else <br>
 * <pre>
 *     {@code private String username;}
 *     {@code private String password;}
 *     {@code private String nickName;}
 *
 * </pre>
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class BasicMemberDto {

    @NotBlank(groups = CreateCheck.class,
            message = "username must not be empty")
    @Null(groups = UpdateCheck.class,
            message = "username cannot change.")
    private String username;

    @NotBlank(groups = CreateCheck.class,
            message = "password must not be empty")
    private String password;

    @NotBlank(groups = CreateCheck.class,
            message = "nickName must not be empty")
    private String nickName;
}

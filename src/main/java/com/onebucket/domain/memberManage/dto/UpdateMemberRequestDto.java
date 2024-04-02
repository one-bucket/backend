package com.onebucket.domain.memberManage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UpdateMemberRequestDto {

    private String nickName;
}

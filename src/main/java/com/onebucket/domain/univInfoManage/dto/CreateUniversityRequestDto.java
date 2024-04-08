package com.onebucket.domain.univInfoManage.dto;

import lombok.Getter;

@Getter
public class CreateUniversityRequestDto {
    private Long id;
    private String name;
    private String address;
    private String emailForm;
}

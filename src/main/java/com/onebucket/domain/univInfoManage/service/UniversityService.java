package com.onebucket.domain.univInfoManage.service;

import com.onebucket.domain.univInfoManage.domain.University;
import com.onebucket.domain.univInfoManage.dto.CreateUniversityRequestDto;

import java.util.List;

public interface UniversityService {

    void saveAllUniversity(List<CreateUniversityRequestDto> createUniversityRequestDtos);

    boolean isExistByName(String name);
}

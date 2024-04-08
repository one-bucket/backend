package com.onebucket.domain.univInfoManage.service;

import com.onebucket.domain.univInfoManage.dao.UniversityRepository;
import com.onebucket.domain.univInfoManage.domain.University;
import com.onebucket.domain.univInfoManage.dto.CreateUniversityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    public void saveAllUniversity(List<CreateUniversityRequestDto> createUniversityRequestDtos) {
        List<University> universities = createUniversityRequestDtos.stream()
                .map(dto->  new University(dto.getId(), dto.getName(), dto.getAddress(), dto.getEmailForm()))
                .toList();

        universityRepository.saveAll(universities);
    }

    @Override
    public boolean isExistByName(String name) {
        return universityRepository.existsByName(name);
    }
}

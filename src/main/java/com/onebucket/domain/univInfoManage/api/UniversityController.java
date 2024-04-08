package com.onebucket.domain.univInfoManage.api;

import com.onebucket.domain.univInfoManage.dto.CreateUniversityRequestDto;
import com.onebucket.domain.univInfoManage.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @PostMapping("/universities")
    public ResponseEntity<?> saveUniversities(@RequestBody List<CreateUniversityRequestDto> dtos) {
        universityService.saveAllUniversity(dtos);
        return ResponseEntity.ok("success");
    }
}

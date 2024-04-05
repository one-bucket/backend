package com.onebucket.domain.memberManage.api;

import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.UpdateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static com.onebucket.global.utils.SecurityUtils.getCurrentUsername;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateMemberRequestDto createMemberRequestDto) {
        memberService.createMember(createMemberRequestDto);
        return ResponseEntity.ok("success register");
    }

    @GetMapping("/member/info")
    public ResponseEntity<?> memberInfo() {
        String username = getCurrentUsername();
        return ResponseEntity.ok(memberService.readMember(username));
    }

    @PostMapping("/member/update")
    public ResponseEntity<?> memberUpdate(@RequestBody UpdateMemberRequestDto updateMemberRequestDto) {
        String username = getCurrentUsername();
//        try {
//            memberService.updateMember(username, updateMemberRequestDto);
//        } catch (EntityNotFoundException e) {
//            responseEntityCreator.createResponseEntity(ErrorCode_pre.UNKNOWN_USER);
//        } catch (Exception e) {
//            responseEntityCreator.createResponseEntity(ErrorCode_pre.UNSUPPORTED_AUTHENTICATION_ERROR);
//        }
        return ResponseEntity.ok("success update");
    }

}

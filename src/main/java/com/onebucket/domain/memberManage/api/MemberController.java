package com.onebucket.domain.memberManage.api;

import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.UpdateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.onebucket.global.utils.SecurityUtils.getCurrentUsername;

/**
 * Provide 4 endpoints which are
 * <pre>
 *     /register/base
 *     /member/info
 *     /member/update
 *     /member/quit
 * </pre>
 * Also provide user the CRUD process, none for admin. This class is for user.
 * Custom Exception may require or provide, especially in {@link com.onebucket.global.exceptionManage.errorCode.UserErrorCode UserErrorCode} <br>
 * Dependence on MemberService, return ResponseEntity of JSON
 *
 * @author SangHyeok
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    //need to make new responseMessage, not just String
    @PostMapping("/register/base")
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
        memberService.updateMember(username, updateMemberRequestDto);
        return ResponseEntity.ok("success update");
    }

    @PostMapping("/member/quit")
    public ResponseEntity<?> memberQuit() {
        String username = getCurrentUsername();
        memberService.deleteMember(username);
        return ResponseEntity.ok("success delete");
    }

}

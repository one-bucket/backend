package com.onebucket.domain.memberManage.api;

import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.UpdateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static com.onebucket.global.utils.SecurityUtils.getCurrentUsername;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@Slf4j
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

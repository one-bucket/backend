package com.onebucket.domain.memberManage.service;

import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.ReadMemberResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("[MemberService.class] create, get Member 검증")
    public void testCreateMember() {
        //given
        CreateMemberRequestDto memberDto = CreateMemberRequestDto.builder()
                .username("user")
                .password("password")
                .nickName("john doe")
                .build();

        //when
        try{
            memberService.createMember(memberDto);
        } catch(Exception e) {
            fail("fail to create Member");
        }
        ReadMemberResponseDto savedMember = memberService.readMember("user");

    }
}
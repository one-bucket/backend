package com.onebucket.domain.memberManage.service;

import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.BasicMemberDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("[MemberService.class] create, get Member 검증")
    public void testCreateMember() {
        //given
        BasicMemberDto memberDto = BasicMemberDto.builder()
                .username("user")
                .password("password")
                .nickName("john doe")
                .build();

        //when
        try{
            memberService.createMember(memberDto);
        } catch(Exception e) {
            fail("create Member fail, perhaps EntityNotFoundException");
        }
        BasicMemberDto savedMember = memberService.readMember("user");
        //then
        Assertions.assertThat(savedMember).usingRecursiveComparison()
                .ignoringFields("password")
                .isEqualTo(memberDto);
    }

}
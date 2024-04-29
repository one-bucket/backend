package com.onebucket.domain.memberManage.domain;

import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberTest {

    private Member member;
    @BeforeEach
    void beforeEach() {
        member = Member.builder()
                .username("hongik")
                .password("12341234")
                .nickName("hong")
                .build();
    }

    @Test
    void 멤버_생성_테스트() {
        //given

        //when, then
        assertThat(member.getUsername()).isEqualTo("hongik");
        assertThat(member.getPassword()).isEqualTo("12341234");
        assertThat(member.getNickName()).isEqualTo("hong");
    }

    @Test
    void 멤버_정보_수정_테스트() {
        //when
        member.setUsername("홍길동");
        member.setPassword("98769876");
        member.setNickName("hahaha");
        //then
        assertThat(member.getUsername()).isEqualTo("홍길동");
        assertThat(member.getPassword()).isEqualTo("98769876");
        assertThat(member.getNickName()).isEqualTo("hahaha");
    }
}
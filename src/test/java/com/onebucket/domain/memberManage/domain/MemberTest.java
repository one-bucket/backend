package com.onebucket.domain.memberManage.domain;

import com.onebucket.domain.memberManage.dao.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.SpliteratorAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("[MemberRepository.class] Member 저장 검증")
    public void testMemberBuilder() {
        //given
        Member member = Member.builder()
                .username("user")
                .password("password")
                .nickName("john doe")
                .build();

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(member).isSameAs(savedMember);
        assertThat(savedMember.getId()).isNotNull();
        assertThat(memberRepository.count()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DisplayName("[MemberRepository.class] Member 조회 검증")
    public void testMemberGet() {
        //given
        Member member = Member.builder()
                .username("user")
                .password("password")
                .nickName("john doe")
                .build();

        //when
        memberRepository.save(member);

        Member getMember = memberRepository.findByUsername("user").get();

        //then
        assertThat(member).isSameAs(getMember);
        assertThat(getMember.getId()).isNotNull();
        assertThat(getMember.getUsername()).isEqualTo(member.getUsername());
    }



}
package com.onebucket.domain.memberManage.repository;

import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {

    private Member member1;
    private Member member2;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        member1 = Member.builder()
                .username("hongik")
                .password("12341234")
                .nickName("hahaha")
                .build();
        member2 = Member.builder()
                .username("computer")
                .password("1234123423124")
                .nickName("hahaha111")
                .build();
    }
    @Test
    void findByUsername테스트성공() {
        //given
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        Member findMember1 = memberRepository.findByUsername("hongik").get();
        Member findMember2 = memberRepository.findByUsername("computer").get();
        //then
        assertThat(findMember1.getNickName()).isEqualTo("hahaha");
        assertThat(findMember2.getNickName()).isEqualTo("hahaha111");
    }

    @Test
    void existsByUsername테스트성공() {
        //when
        memberRepository.save(member1);
        //then
        boolean findMember1 = memberRepository.existsByUsername("hongik");
        boolean findMember2 = memberRepository.existsByUsername("computer");
        assertThat(findMember1).isTrue();
        assertThat(findMember2).isFalse();
    }

    @Test
    void deleteByUsername테스트성공() {
        //when
        memberRepository.save(member1);
        //then
        memberRepository.deleteByUsername(member1.getUsername());
        boolean findMember1 = memberRepository.existsByUsername("hongik");
        assertThat(findMember1).isFalse();
    }
}
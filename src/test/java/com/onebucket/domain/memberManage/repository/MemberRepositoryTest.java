package com.onebucket.domain.memberManage.repository;

import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

/**
 * <h1>Test the MemberRepository!</h1>
 There are three methods in the member repository.
 <ul>
    <li>findByUsername()</li>
    <li>existsByUsername()</li>
    <li>deleteByUsername()</li>
 </ul>

 <h2>Test : findByUsername()</h2>
 After creating and saving two members, make sure each user is saved well with each username.

 <h2>Test : existsByUsername()</h2>
 After creating and saving one member, test existsByUsername() with a total of two usernames: that member's username and a non-existent virtual username.
 In this test, a user with a "Hongik" name must exist, and a user with a "computer" name must not exist.
 By the same logic, a user with the nickname "ha ha" exists and a user with the nickname "ha ha111" must not exist.
 <h2>Test : deleteByUsername()</h2>
 After creating and saving one user, delete it to confirm whether the user has been deleted.
 */
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
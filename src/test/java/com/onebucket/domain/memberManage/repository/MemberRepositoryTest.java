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
 There are three methods in the memberRepository. Test using {@code @DataJpaTest}
 <ol>
    <li>{@code findByUsername()}</li>
    <li>{@code existsByUsername()}</li>
    <li>{@code deleteByUsername()}</li>
 </ol>
 @author Han Seung Hoon
 @version 0.0.1
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

    /**
     * <h2>Test : {@code findByUsername()}</h2>
     *  Make sure each user is saved well with each username.
     *  The {@code getNickName()} return value for each member must be correct.
     */
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

    /**
     * <h2>Test : {@code existsByUsername()}</h2>
     *  The {@code existsByUsername()} method must return <b>true</b> for existing members and <b>false</b> for non-existent members.
     */
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

    /**
     * <h2>Test : {@code deleteByUsername()}</h2>
     *  Delete member and confirm whether the user is deleted.
     */
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
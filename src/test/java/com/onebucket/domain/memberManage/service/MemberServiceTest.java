package com.onebucket.domain.memberManage.service;

import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.ReadMemberResponseDto;
import com.onebucket.domain.memberManage.dto.UpdateMemberRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 <h1>Test the MemberService!</h1>
 <hr><hr>
 <h2>How to test MemberService?</h2>
 The test is using the {@code doReturn()} method and the {@code assertThat()} method.
 <ol>
    <li>{@code doReturn()} : {@code doReturn()} is a method used in a mocking framework such as Mockito, which is used to specify a return value for a particular method call.</li>
    <li>{@code assertThat()} : {@code assertThat()} is used to verify certain conditions in the test code. For example, it can be used in a variety of situations, such as verifying that the return value of a method call matches the expected value, or that the size of the list is the same as the expected value.</li>
 </ol>
 <h2>What methods we test?</h2>
 Among the methods in MemberService, we test the following two methods.
 <ul>
     <li>{@code createMember(CreateMemberRequestDto createMemberRequestDto)}</li>
     <li>{@code updateMember(String username, UpdateMemberRequestDto updateMemberRequestDto)}</li>
 </ul>
 @author Han Seung Hoon
 @version 0.0.1
 */
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member() {
        return Member.builder()
                .username("hongik")
                .password("12341234")
                .nickName("hahaha")
                .build();
    }

    /**
     * <h2>Test: {@code createMember}</h2>
     * create a member using the {@code createMember()} method. After that, check whether the memberRepository is empty or not. The client does not need any other authentication.
     */
    @Test
    void 멤버생성성공() {
        CreateMemberRequestDto createMemberRequestDto = CreateMemberRequestDto.builder()
                .username("korea")
                .password("22222222")
                .nickName("ananan")
                .build();
        memberService.createMember(createMemberRequestDto);
        assertThat(memberRepository.findAll()).isNotNull();
    }

    /**
     * <h2>Test : {@code updateMember}</h2>
     * Change the member's username "hongik" to "changedNickName" and see if it's changed well.
     * */
    @Test
    void 멤버수정성공() {
        Optional<Member> memberOptional = Optional.of(member());
        doReturn(memberOptional).when(memberRepository).findByUsername("hongik");

        UpdateMemberRequestDto updateMemberRequestDto = new UpdateMemberRequestDto("changedNickName");
        memberService.updateMember("hongik", updateMemberRequestDto);
        assertThat(memberOptional.get().getNickName()).isEqualTo("changedNickName");
    }
}
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

    @Test
    void 멤버수정성공() {
        doReturn(Optional.of(member())).when(memberRepository).findByUsername("hongik");

        UpdateMemberRequestDto updateMemberRequestDto = new UpdateMemberRequestDto("yonsei");
        memberService.updateMember("hongik", updateMemberRequestDto);
        assertThat(memberRepository.findByUsername("yonsei")).isNotNull();
    }
}
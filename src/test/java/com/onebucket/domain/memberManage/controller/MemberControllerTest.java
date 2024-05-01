package com.onebucket.domain.memberManage.controller;

import com.google.gson.Gson;
import com.onebucket.domain.memberManage.api.MemberController;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;
import com.onebucket.global.utils.SecurityUtils;
import jdk.dynalink.beans.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;
    private Gson gson;

    @BeforeEach
    void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    private CreateMemberRequestDto member() {
        return CreateMemberRequestDto.builder()
                .username("hongik")
                .password("12341234")
                .nickName("hahaha")
                .build();
    }

    private CreateMemberRequestDto memberEmpty() {
        return CreateMemberRequestDto.builder()
                .username("")
                .password("12341234")
                .nickName("hahaha")
                .build();
    }
    // 현재 dto 객체의 @NotBlank 유효성 검사가 진행이 안되는듯. 어떻게 빈칸을 에러로 발생시키나?
    @Test
    void 회원가입실패_입력값에빈칸이있음() throws Exception {
        //given
        final String url = "/register/base";
        //when
        System.out.println(gson.toJson(memberEmpty()));
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(memberEmpty()))
                        .contentType(MediaType.APPLICATION_JSON));
        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void 회원가입성공() throws Exception {
        //given
        final String url = "/register/base";
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(member()))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void 멤버조회실패_권한이없음() {
        final String url = "/member/info";

        assertThrows(Exception.class, () ->
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
        );
    }

    @Test
    void 멤버조회성공() throws Exception {
        final String url = "/member/info";
        String testUsername = "testUser";

        // Mock SecurityContext
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(testUsername);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        final ResultActions resultActions = mockMvc.perform(
                get(url)
        );
        resultActions.andExpect(status().isOk());
    }
}

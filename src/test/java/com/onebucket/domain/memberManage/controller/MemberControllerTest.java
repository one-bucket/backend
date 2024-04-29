package com.onebucket.domain.memberManage.controller;

import com.google.gson.Gson;
import com.onebucket.domain.memberManage.api.MemberController;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    private CreateMemberRequestDto notMember() {
        return CreateMemberRequestDto.builder()
                .password("12341234")
                .nickName("hahaha")
                .build();
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
    void 회원가입실패_입력값에빈칸이있음() throws Exception {
        //given
        final String url = "/register/base";
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(notMember()))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        resultActions.andExpect(status().isBadRequest());
    }
}

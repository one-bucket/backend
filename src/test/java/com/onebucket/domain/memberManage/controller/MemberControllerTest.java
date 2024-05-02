package com.onebucket.domain.memberManage.controller;

import com.google.gson.Gson;
import com.onebucket.domain.memberManage.api.MemberController;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;
import com.onebucket.global.utils.SecurityUtils;
import jdk.dynalink.beans.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
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
/**
 <h1>Test the MemberController!</h1>
 <hr><hr>
 <h2>How to test MemberController?</h2>
 <ol>
    <li>Set Up MockMvc: MockMvc is the main entry point for server-side Spring MVC test support. You can create an instance of MockMvc by using MockMvcBuilders.standaloneSetup() and pass your controller instance to it.</li>
    <li>Inject Mocks: Use @MockBean annotation to inject mocked dependencies (in this case, MemberService) into the Spring application context.</li>
    <li>Write Test Cases: Write test cases using the mockMvc.perform() method to perform HTTP requests and assertions to verify the expected behavior.</li>
 </ol>
 <hr><hr>
 Among the methods in MemberController, we test the following two methods.
 <ul>
    <li>register</li>
    <li>memberInfo</li>
 </ul>
<h2>1. Test : register </h2>
 During membership registration, there was a blank in the user input value, so a test with a 400 BadRequest error and a normal membership test were conducted.
 <h2>2. Test : memberInfo</h2>
 In the process of outputting the information of the currently logged-in user, a RuntimeException error occurred by accessing an account other than his or her account and successfully printing the information of his or her account were written as tests.
 At this time, fake SecurityContext and fake authentication must be created for authentication to pass the test, and the securityContext must be initialized before each test starts to not affect other tests.

 */
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
        SecurityContextHolder.clearContext();
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
                .username(" ")
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

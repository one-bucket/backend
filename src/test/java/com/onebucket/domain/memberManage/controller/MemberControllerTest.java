package com.onebucket.domain.memberManage.controller;

import com.google.gson.Gson;
import com.onebucket.domain.memberManage.api.MemberController;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.service.MemberService;
import com.onebucket.global.utils.SecurityUtils;
import jakarta.servlet.ServletException;
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
    <li>Set Up MockMvc: MockMvc is the main entry point for server-side Spring MVC test support. You can create an instance of MockMvc by using {@code MockMvcBuilders.standaloneSetup()} and pass your controller instance to it.</li>
    <li>Inject Mocks: Use {@code @MockBean} annotation to inject mocked dependencies (in this case, MemberService) into the Spring application context.</li>
    <li>Write Test Cases: Write test cases using the {@code mockMvc.perform()} method to perform HTTP requests and assertions to verify the expected behavior.</li>
 </ol>
 <h2>About {@code mockMvc.perform()}</h2>
 {@code mockMvc.perform()} is a method that performs HTTP requests using MockMvc, a testing framework for Spring MVC, which allows you to test the controller of a web application.
 When invoking {@code mockMvc.perform()}, you can:
 <ol>
    <li>Configuring HTTP requests (using methods such as get, post, put, delete, etc.)</li>
    <li>Set the request's header</li>
    <li>Set the body of the request (JSON, form parameters, etc.)</li>
    <li>Verification of the request (response code, header, body, etc.)</li>
 </ol>
 @author Han Seung Hoon
 @version 0.0.1
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

    private CreateMemberRequestDto memberNoUserName() {
        return CreateMemberRequestDto.builder()
                .username(" ")
                .password("12341234")
                .nickName("hahaha")
                .build();
    }

    /**
     * <h2> Test : {@code Register()} </h2>
     *  During member registration, there was a blank in the {@code CreateMemberRequestDto}, so a test with a 400 BadRequest error and a normal membership test were conducted.
     * @throws Exception
     *         An exception occurs when there is a blank space in the {@code CreateMemberRequestDto} value.
     *
     */
    @Test
    void 회원가입실패_입력값에빈칸이있음() throws Exception {
        //given
        final String url = "/register/base";
        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(memberNoUserName()))
                        .contentType(MediaType.APPLICATION_JSON));
        // then
        resultActions.andExpect(status().isBadRequest());
    }

    /**
     * <h2> Test : {@code Register()} </h2>
     * It is a test that receives the correct user input and succeeds.
     * @throws Exception
     *         An exception occurs when there is a blank space in the {@code CreateMemberRequestDto} value.
     */
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

    /**
     * <h2> Test : {@code memberInfo()} </h2>
     *  A servletExceptionError occurs by calling memberInfo() without logging in.
     */
    @Test
    void 멤버조회실패_권한이없음() {
        final String url = "/member/info";

        assertThrows(ServletException.class, () ->
                mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
        );
    }

    /**
     * <h2> Test : {@code memberInfo()} </h2>
     * To pass the test, fake SecurityContext and fake authentication must be created for authentication.
     * @throws Exception
     *         An exception occurs when there is no authentication in SecurityContext.
     */
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

package com.onebucket.global.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;

public class ExceptionHandler {

    public static void handleJwtException(Exception e, HttpServletResponse response) throws IOException {
        // 예외 유형에 따라 HTTP 상태 코드를 결정
        int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 기본값은 500 Internal Server Error
        String errorMessage = "Unsupported exception";

        if (e instanceof ExpiredJwtException) {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
            errorMessage = "expired token : " + e.getMessage();
        } else if (e instanceof MalformedJwtException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
            errorMessage = "Invalid token : " + e.getMessage();
        } else if (e instanceof SignatureException) {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
            errorMessage = "Invalid token : " + e.getMessage();
        } else if(e instanceof IllegalArgumentException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
            errorMessage = "Invalid value : " + e.getMessage();
        } else if(e instanceof JwtException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
            errorMessage = "Unsupported jwt exception : " + e.getMessage();
        }


        // HTTP 상태 코드 설정
        response.setStatus(statusCode);

        // 응답 본문에 오류 메시지 작성
        response.setContentType("application/json"); // JSON 형식의 응답을 설정
        PrintWriter out = response.getWriter();
        out.print("{\"error\": \"" + errorMessage + "\"}"); // JSON 형식의 오류 메시지
        out.flush(); // 스트림을 플러시하여 모든 내용이 전송되도록 합니다.
    }

}

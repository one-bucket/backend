package com.onebucket.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<?> testMethod() {
        return ResponseEntity.ok("test at 24-04-01");
    }
}

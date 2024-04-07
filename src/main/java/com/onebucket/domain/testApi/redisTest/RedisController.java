package com.onebucket.domain.testApi.redisTest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedisService redisService;

    @PostMapping("redis/set")
    public ResponseEntity<?> setData(@RequestBody RedisDto redisDto) {
        redisService.setData(redisDto.getKey(), redisDto.getValue(), 5L);
        return ResponseEntity.ok("success redis set");
    }

    @PostMapping("/redis/get")
    public ResponseEntity<?> getData(@RequestBody RedisDto redisDto) {
        String value = redisService.getData(redisDto.getKey());
        if(value != null) {
            return ResponseEntity.ok("Retrieved value : " + value);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("data not found");
        }

    }

    @DeleteMapping("/redis/delete")
    public ResponseEntity<?> deleteData(@RequestBody RedisDto redisDto) {
        redisService.deleteData(redisDto.getKey());
        return ResponseEntity.ok("delete success");
    }
}

package com.onebucket.domain.testApi.redisTest;

import com.onebucket.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class RedisController {
    private final RedisService redisService;

    @PostMapping("/redis/set")
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
    public ResponseEntity<?> deleteData(@RequestParam("key") String key) {
        redisService.deleteData(key);
        return ResponseEntity.ok("delete success");
    }
}

package com.onebucket.domain.testApi.redisTest;


import lombok.*;

@Builder
@Getter
public class RedisDto {
    private final String key;
    private final String value;
}

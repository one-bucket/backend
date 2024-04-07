package com.onebucket.domain.testApi.mailTest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessageEntity {

    private String to;
    private String subject;
    private String message;
}

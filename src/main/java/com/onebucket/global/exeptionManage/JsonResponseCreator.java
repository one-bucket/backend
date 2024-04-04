package com.onebucket.global.exeptionManage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class JsonResponseCreator {
    private final ObjectMapper objectMapper;

    public JsonResponseCreator() {
        this.objectMapper = new ObjectMapper();
    }

    public String createJsonResponse(String status) {
        return createJsonResponse(status, null, null);
    }

    public String createJsonResponse(String status, String type) {
        return createJsonResponse(status, type, null);
    }

    public String createJsonResponse(String status, String type, String message) {
        ExceptionJsonMessage jsonMessage = new ExceptionJsonMessage(status, type, message);
        try {
            return objectMapper.writeValueAsString(jsonMessage);
        } catch (JsonProcessingException e) {
            return "{\"error\":\"Error converting to JSON\"}";
        }
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ExceptionJsonMessage {
        private String status;
        private String type;
        private String message;

        public ExceptionJsonMessage(String status, String type, String message) {
            this.status = status;
            this.type = type;
            this.message = message;
        }

    }
}


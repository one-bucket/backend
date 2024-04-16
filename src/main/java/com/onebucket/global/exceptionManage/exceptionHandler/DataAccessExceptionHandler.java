package com.onebucket.global.exceptionManage.exceptionHandler;

import com.onebucket.global.exceptionManage.errorCode.DataAccessErrorCode;
import com.onebucket.global.exceptionManage.errorCode.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.onebucket.global.exceptionManage.exceptionHandler.ErrorResponseGenerator.generateResponse;

@Order(1)
@RestControllerAdvice
public class DataAccessExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorCode errorCode = DataAccessErrorCode.CONSTRAINT_VIOLATION;
        return generateResponse(errorCode);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorCode errorCode = DataAccessErrorCode.ENTITY_NOT_FOUND;
        return generateResponse(errorCode);
    }

    @ExceptionHandler(DataRetrievalFailureException.class)
    public ResponseEntity<Object> handleDataRetrievalFailureException(DataRetrievalFailureException ex) {
        ErrorCode errorCode = DataAccessErrorCode.DATA_SEARCH_FAILURE;
        return generateResponse(errorCode);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        ErrorCode errorCode = DataAccessErrorCode.UNSUPPORTED_DATA_ACCESS_ERROR;
        return generateResponse(errorCode);
    }
}

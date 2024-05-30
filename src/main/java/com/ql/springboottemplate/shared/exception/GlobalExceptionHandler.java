package com.ql.springboottemplate.shared.exception;



import com.ql.springboottemplate.application.dto.response.ApiResponse;
import com.ql.springboottemplate.shared.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    private ResponseEntity<ApiResponse> userNotReadableException(ApiException ex) {
        log.info("In ApiException");
        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).status(Constant.BAD_REQUEST).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ApiResponse> httpRequestNotReadable(HttpMessageNotReadableException ex) {
        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).status(Constant.FAILED_REQUEST).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiResponse> otherExceptions(Exception ex) {
        log.info("Exception handled using Exception class"+ex.getMessage());
        ApiResponse apiResponse= ApiResponse.builder().message(ex.getMessage()).status(Constant.FAILED_REQUEST).success(false).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenExpiredException.class)
    private ResponseEntity<ApiResponse> expiredJwtException(TokenExpiredException ex) {
        ApiResponse apiResponse= ApiResponse.builder().message(ex.getMessage()).status(Constant.FAILED_REQUEST).success(false).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        log.info("MethodArgumentNotValidException generated");
        ApiResponse apiResponse= ApiResponse.builder().message(ex.getMessage()).status(Constant.FAILED_REQUEST).success(false).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ApiResponse> userNotFoundException(UserNotFoundException ex){
        log.info("UserNotFoundException generated");
        ApiResponse apiResponse= ApiResponse.builder().message(ex.getMessage()).status(Constant.FAILED_REQUEST).success(false).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


}


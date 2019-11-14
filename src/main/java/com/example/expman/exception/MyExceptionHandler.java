package com.example.expman.exception;

import com.example.expman.entity.ResponseResult;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName MyExceptionHandler
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/13 11:27
 * @Version 1.0
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseResult defaultErrorHandler(Exception e){
        e.printStackTrace();
        return ResponseResult.builder().state(1).msg("生成失败").build();
    }
}

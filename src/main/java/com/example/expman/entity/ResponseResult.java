package com.example.expman.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResponseResult
 * @Description 返回结果
 * @Author 程方园
 * @Date 2019/11/4 14:07
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResult<T> {

    private int state;
    private String msg;
    private T data;

}

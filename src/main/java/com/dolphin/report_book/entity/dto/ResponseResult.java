package com.dolphin.report_book.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult <T> {

    private int code;
    private String message;
    private T data;
    public static <E> ResponseResult<E> success(E data){
        return new ResponseResult<>(200,"success",data);
    }
    public static ResponseResult success(){
        return new ResponseResult<>(200,"success",null);
    }
    public static ResponseResult error(String message){
        return new ResponseResult<>(404,message,null);
    }
}
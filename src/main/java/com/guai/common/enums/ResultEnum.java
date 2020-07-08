package com.guai.common.enums;

import lombok.Getter;

/**
 * @author gqw
 * @date 2020-07-06
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    ;

    private int code ;
    private String message;

    ResultEnum(int code,String message){
        this.code=code;
        this.message=message;
    }
}

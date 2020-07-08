package com.guai.common.utils;

import com.guai.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author gqw
 * @date 2020-07-06
 */
@Data
@ApiModel(value="响应消息主体", description="接口返回对象")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回状态码；标准来自ResultEnum类")
    private int code;
    @ApiModelProperty(value = "返回状态信息")
    private String message;
    @ApiModelProperty(value = "返回的数据对象")
    private T data;


    public R(){
        this(ResultEnum.SUCCESS,"success");
    }

    public R(T t){
        this(ResultEnum.SUCCESS,"success",t);
    }

    public R(ResultEnum e, String message){
        this(e,message,null);
    }

    public R(ResultEnum e ,String message,T data){
        this.code = e.getCode();
        this.data = data;
        this.message= message;
    }

    public static <T> R ok(){
        return  new R();
    }

    public static <T> R ok(T data){
        return new R(data);
    }

    public static <T> R oke(String message){
        return new R(ResultEnum.SUCCESS,message);
    }

    public static <T> R error(){
        return new R(ResultEnum.FAIL,"error");
    }

    public static <T> R error(String message){
        return new R(ResultEnum.FAIL,message);
    }

    public static <T> R error(T data){
        return new R(ResultEnum.FAIL,"error",data);
    }

    public static <T> R error(String message,T data){
        return new R(ResultEnum.FAIL,message,data);
    }


    public static <T> R result(ResultEnum e,String message){
        return new R(e,message);
    }

    public static <T> R result(ResultEnum e,String message,T data){
        return new R(e,message,data);
    }
}

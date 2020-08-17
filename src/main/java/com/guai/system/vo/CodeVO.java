package com.guai.system.vo;

import lombok.Data;

/**
 * @author gqw
 * @date 2020-08-06
 */
@Data
public class CodeVO {
    private String src; //验证码图片链接
    private String key; //验证码表示
}

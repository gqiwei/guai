package com.guai.system.dto;

import lombok.Data;

/**
 * @author gqw
 * @date 2020-07-17
 */
@Data
public class LoginMessageDTO {

    private String username;
    private String password;
    private String code;
}
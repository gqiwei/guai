package com.guai.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author gqw
 * @date 2020-07-14
 */
@Data
public class SysUser {
    private Integer userId;
    private String userName;
    private String password;
    private String nickName;
    private Integer sex;
    private Date birth;
    private String pic;
    private Integer deptId;
    private String email;
    private String mobile;
    private Integer status;
    private Integer isDel;
    private Integer userIdCreate;
    private Date gmtCreate;
    private Date gmtCodified;
}

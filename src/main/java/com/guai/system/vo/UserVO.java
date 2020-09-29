package com.guai.system.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author gqw
 * @date 2020-08-18
 */
@Data
public class UserVO {
    private Integer userId;
    private String userName;
    private String nickName;
    private Integer sex;
    private Date birth;
    private String pic;
    private Integer deptId;
    private String email;
    private String mobile;
}

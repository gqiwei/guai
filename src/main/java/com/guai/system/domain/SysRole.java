package com.guai.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author gqw
 * @date 2020-07-14
 */
@Data
public class SysRole {
    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String remark;
    private Integer userIdCreate;
    private Date gmtCreate;
    private Date gmtModified;
}

package com.guai.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author gqw
 * @date 2020-07-14
 */
@Data
public class SysMenu {
    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private Integer isFrame;
    private Integer type;
    private String perms;
    private Integer status;
    private String icon;
    private Date gmtCreate;
    private Date gmtModified;
}

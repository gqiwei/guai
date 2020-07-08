package com.guai;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author gqw
 * @date 2020-07-07
 */
@Data
@ApiModel(value="测试数据对象", description="测试数据")
public class TestDO {
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
}

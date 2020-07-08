package com.guai.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author gqw
 * @date 2020-07-07
 */
@Mapper
public interface TestDAO {
    @Select("select count(*) from life_plan")
    public int select();
}

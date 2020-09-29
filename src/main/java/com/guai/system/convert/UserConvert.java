package com.guai.system.convert;

import com.guai.system.domain.SysUser;
import com.guai.system.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author gqw
 * @date 2020-08-18
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convert (SysUser bean);
}

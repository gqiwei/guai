package com.guai.system.service;

//import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author gqw
 * @date 2020-07-14
 */
public interface ISysMenuService {

    List<String> selectPermsByUserId(Integer userId);

}

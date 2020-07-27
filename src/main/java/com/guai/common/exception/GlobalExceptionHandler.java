package com.guai.common.exception;

import com.guai.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author gqw
 * @date 2020-07-07
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e)
    {
        LOG.error(e.getMessage(), e);
        return R.error("路径不存在");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public R UsernameNotFoundException(Exception e){
        LOG.error(e.getMessage(), e);
        return R.error(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public R BaseException(Exception e){
        LOG.error(e.getMessage(), e);
        return R.error(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public R BadCredentialsException(Exception e){
        LOG.error(e.getMessage(), e);
        return R.error("用户名或者密码错误");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        LOG.error(e.getMessage()+"2", e);
        return R.error(e.getMessage());
    }
}

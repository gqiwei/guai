package com.guai.common.exception;

import com.guai.common.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        LOG.error(e.getMessage(), e);
        return R.error(e.getMessage());
    }
}

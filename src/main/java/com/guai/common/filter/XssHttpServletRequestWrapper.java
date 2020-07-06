package com.guai.common.filter;

import com.guai.common.utils.JsoupUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author gqw
 * @date 2020-07-02
 * @desc 将所有请求参数进行xss过滤
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        name = JsoupUtil.clean(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = JsoupUtil.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if(arr != null){
            for (int i=0;i<arr.length;i++) {
                arr[i] = JsoupUtil.clean(arr[i]);
            }
        }
        return arr;
    }

    @Override
    public String getHeader(String name) {
        name = JsoupUtil.clean(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = JsoupUtil.clean(value);
        }
        return value;
    }
}

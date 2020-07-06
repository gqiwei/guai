package com.guai.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gqw
 * @date 2020-07-02
 */
public class XssFilter implements Filter {

    public List<String> excludes = new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String temp = filterConfig.getInitParameter("excludes");
        if(temp != null){
            String[] url = temp.split(",");
            for(int i=0;url!=null && i<url.length;i++){
                excludes.add(url[i]); //添加白名单
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(handleExcludeURL(request,response)){
            filterChain.doFilter(request,response);
            return ;
        }

        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(xssRequest,response);
    }

    /**
     * 判断连接是否在白名单里
     * @param request
     * @param response
     * @return
     */
    public boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response){
        if(excludes.isEmpty()||excludes ==null){
            return false;
        }

        String url = request.getServletPath();
        for(String pattern : excludes){
            Pattern p = Pattern.compile("^"+pattern);
            Matcher m = p.matcher(url);
            if(m.find()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}

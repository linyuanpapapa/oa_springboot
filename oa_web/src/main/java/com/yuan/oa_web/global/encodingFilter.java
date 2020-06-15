package com.yuan.oa_web.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码过滤器
 */
public class encodingFilter implements Filter {
    private String encoding="UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig.getInitParameter("encoding")!=null){
            encoding=filterConfig.getInitParameter("encoding");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

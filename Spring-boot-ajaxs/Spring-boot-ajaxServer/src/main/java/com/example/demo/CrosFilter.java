package com.example.demo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CrosFilter
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/7 17:29
 * @Version 1.0
 */
public class CrosFilter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("Access-Control-Allow-Origin","http://localhost:8091");
        res.addHeader("Access-Control-Allow-Methods","GET");

        filterChain.doFilter(servletRequest,servletResponse);
    }
}

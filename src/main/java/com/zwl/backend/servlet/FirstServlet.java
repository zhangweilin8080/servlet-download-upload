package com.zwl.backend.servlet;


import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zwl
 * @date 2020/9/10 16:54
 * @describe 创建Servlet的第一种方式：1.实现Servlet接口...
 *                                 2.web.xml中配置映射关系
 */
public class FirstServlet implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

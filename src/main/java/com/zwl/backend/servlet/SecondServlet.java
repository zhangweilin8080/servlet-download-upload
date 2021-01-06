package com.zwl.backend.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zwl
 * @date 2020/9/10 17:14
 * @describe 创建Servlet的第二种方式：1.继承GenericServlet类并重写service()方法...
 *                                 2.web.xml中配置映射关系
 */
public class SecondServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //解决乱码问题
        //是设置从request中取得的值bai或从数据库中取出的值的编码
        servletRequest.setCharacterEncoding("utf-8");
        //指定 HTTP 响应的编码,同时指定了浏览器显示的编码（即文档类型为html）
        servletResponse.setContentType("text/html;charset=utf-8");
        //设置HTTP 响应的编码,如果之前使用response.setContentType设置了编码格式,则使用response.setCharacterEncoding指定的编码格式覆盖之前的设置
        servletResponse.setCharacterEncoding("utf-8");
        System.out.println("service it");
        PrintWriter pw = servletResponse.getWriter();
        pw.write("香香的烤面筋你吃过没");
    }
}

package com.zwl.backend.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zwl
 * @date 2020/9/11 9:48
 * @describe 创建Servlet的第三种方式：1.继承HttpServlet类(HttpServlet类继承了GenericServlet类)...
 *                                 2.web.xml中配置映射关系
 */
public class ThirdServlet extends HttpServlet {
    //不建议重写service方法，否则，他自带的service中的doPost doGet方法就没有起到作用了。主要是重写doGet doPost方法。使用HttpServlet其实主要就是要重写doget doPost，因为在HttpServlet中的service最后就是调用doget dopost
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        PrintWriter pw = resp.getWriter();
        pw.println("hello world doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
        PrintWriter pw = resp.getWriter();
        pw.println("hello world doPost");
    }
}

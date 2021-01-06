package com.zwl.backend.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zwl
 * @date 2020/9/11 9:48
 * @describe 创建Servlet的第三种方式：1.继承HttpServlet类(HttpServlet类继承了GenericServlet类)...
 *                                 2.web.xml中配置映射关系
 */
@WebServlet( name = "firstUdservlet" , urlPatterns = "/servlet/firstUdservlet")
public class FirstUDServlet extends HttpServlet {
    //不建议重写service方法，否则，他自带的service中的doPost doGet方法就没有起到作用了。主要是重写doGet doPost方法。使用HttpServlet其实主要就是要重写doget doPost，因为在HttpServlet中的service最后就是调用doget dopost
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
        //获取要下载的文件名
        String fileName=req.getParameter("fileName");
        //生成文件地址
        String path = this.getServletContext().getRealPath("download"+ File.separator+fileName);
        System.out.println("文件路径："+path);
        /**
         * 获得该文件的输入，输出流  通过response获得的输出流，用于向客户端写内容
         */
        InputStream in = new FileInputStream(path);
        ServletOutputStream out = resp.getOutputStream();
        /**
         * 文件拷贝代码（可以封装成模板）多次使用
         */
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer,0,len);
        }
        //关闭输入输出流
        in.close();
        out.close();
    }
}

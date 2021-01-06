package com.zwl.backend.servlet;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.catalina.core.ApplicationPart;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author zwl
 * @date 2020/9/11 9:48
 * @describe Servlet3文件上传
 */
@WebServlet(name = "fifthUdservlet", urlPatterns = "/servlet/fifthUdservlet")
@MultipartConfig//使用注解@MultipartConfig将一个Servlet标识为支持文件上传
public class FifthUDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost...");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //存储路径
        String savePath = request.getServletContext().getRealPath("/WEB-INF/uploadFile");
        //获取上传的文件集合
        Collection<Part> parts = request.getParts();
        //上传单个文件
        if (parts.size() == 1) {
            //Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
            //通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            Part part = request.getPart("file");

            //Servlet3直接获取文件名
            String fileName = part.getSubmittedFileName();
            System.out.println("文件名："+fileName);

            //把文件写到指定路径
            part.write(savePath + File.separator + fileName);
        } else {
            //一次性上传多个文件
            for (Part part : parts) {//循环处理上传的文件
                //获取文件名
                String fileName = part.getSubmittedFileName();
                System.out.println("文件名："+fileName);
                //把文件写到指定路径
                part.write(savePath + File.separator + fileName);
            }
        }
        PrintWriter out = response.getWriter();
        out.println("上传成功");
        out.flush();
        out.close();
    }



}

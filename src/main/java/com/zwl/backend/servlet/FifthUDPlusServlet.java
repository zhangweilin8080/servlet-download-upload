package com.zwl.backend.servlet;

import org.apache.catalina.core.ApplicationPart;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author zwl
 * @date 2020/9/11 9:48
 * @describe Servlet3文件上传，多种方式获取文件名，判断是否是文件
 */
@WebServlet(name = "fifthUdplusServlet", urlPatterns = "/servlet/fifthUdplusServlet")
@MultipartConfig//使用注解@MultipartConfig将一个Servlet标识为支持文件上传
public class FifthUDPlusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost...");
        //Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //存储路径
        String savePath = request.getServletContext().getRealPath("/WEB-INF/uploadFile");
        //获取上传的文件集合
        Collection<Part> parts = request.getParts();
        //上传单个文件
        if (parts.size() == 1) {
            //通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
            Part part = request.getPart("file");

            //获取fileItem对象
            ApplicationPart applicationPart=(ApplicationPart)part;
            org.apache.tomcat.util.http.fileupload.FileItem fileItem=getFileItem(applicationPart);

            if(fileItem.isFormField()){//是普通表单属性
                //处理...
            }else {//是文件
                //获取文件名并输出文件到指定位置
                outputFile(part,savePath);
            }


        } else {
            //一次性上传多个文件
            for (Part part : parts) {//循环处理上传的文件
                //获取fileItem对象
                ApplicationPart applicationPart=(ApplicationPart)part;
                org.apache.tomcat.util.http.fileupload.FileItem fileItem=getFileItem(applicationPart);

                if(fileItem.isFormField()){//是普通表单属性
                    //处理...
                }else {//是文件
                    //获取文件名并输出文件到指定位置
                    outputFile(part,savePath);
                }

            }
        }
        PrintWriter out = response.getWriter();
        out.println("上传成功");
        out.flush();
        out.close();
    }

    /**
     * 根据请求头解析出文件名
     * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
     * IE浏览器下：form-data; name="file"; filename="E:\snmp4j--api.zip"
     *
     * @param part
     * @return 文件名
     */
    public String getFileName(Part part) {
        //获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
        String header = part.getHeader("content-disposition");
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }

    private org.apache.tomcat.util.http.fileupload.FileItem getFileItem(ApplicationPart applicationPart){
        org.apache.tomcat.util.http.fileupload.FileItem fileItem;
        try {
            Class c = applicationPart.getClass();
            Field field = c.getDeclaredField("fileItem");
            field.setAccessible(true);
            fileItem = (org.apache.tomcat.util.http.fileupload.FileItem) field.get(applicationPart);
        }catch (Exception e){
            //不推荐使用e.printStackTrace()来打印异常栈
            e.printStackTrace();
            return null;
        }
        return fileItem;
    }

    /**
     * 获取文件名并输出文件到指定位置
     * @param part
     * @param savePath
     */
    private void outputFile(Part part,String savePath)throws IOException{
        //Servlet3获取文件名方式一：从请求头中解析出来文件名
        /* String fileName = getFileName(part);*/

        //Servlet3获取文件名方式二：直接获取文件名
        String fileName = part.getSubmittedFileName();
        System.out.println("文件名："+fileName);

        //把文件写到指定路径
        part.write(savePath + File.separator + fileName);
    }

}

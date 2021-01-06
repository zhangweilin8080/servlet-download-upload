package com.zwl.backend.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author zwl
 * @date 2020/9/11 9:48
 * @describe 文件上传
 */
@WebServlet( name = "thirdUdservlet" , urlPatterns = "/servlet/thirdUdservlet")
public class ThirdUDServlet extends HttpServlet {
    //不建议重写service方法，否则，他自带的service中的doPost doGet方法就没有起到作用了。主要是重写doGet doPost方法。使用HttpServlet其实主要就是要重写doget doPost，因为在HttpServlet中的service最后就是调用doget dopost
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
        //解决上传文件名的中文乱码
        req.setCharacterEncoding("utf-8");

        //创建一个解析器工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置工厂的内存缓冲区大小，默认是10K
        // factory.setSizeThreshold(1024*1024);
        //设置工厂的临时文件目录：当上传文件的大小大于缓冲区大小时，将使用临时文件目录缓存上传的文件
        factory.setRepository(new File("E:\\Java\\upload\\temp\\"));
        //文件上传解析器
        ServletFileUpload upload=new ServletFileUpload(factory);
        //设置所有上传数据的最大值，单位字节long  1M
        upload.setSizeMax(1024*1024);
        //设置单个文件上传的最大值
        upload.setFileSizeMax(1024*1024);
        //设置编码格式
        upload.setHeaderEncoding("UTF-8");

        try {
            //解析请求，将表单中每个输入项封装成一个FileItem对象
            List<FileItem> itemList=upload.parseRequest(req);
            for(FileItem item:itemList){
                //判断输入的类型是 普通输入项 还是文件
                if(item.isFormField()){
                    //普通输入项 ,得到input中的name属性的值
                    String name=item.getFieldName();
                    //得到输入项中的值
                    String value=item.getString("UTF-8");
                    System.out.println("name="+name+"  value="+value);
                }else{
                    //上传的是文件，获得文件上传字段中的文件名
                    //注意IE或FireFox中获取的文件名是不一样的，IE中是绝对路径，FireFox中只是文件名。
                    String fileName=item.getName();
                    System.out.println("文件名："+fileName);
                    //返回表单标签name属性的值
                    String namede=item.getFieldName();
                    System.out.println(namede);

                    //方法一：保存上传文件到指定的文件路径
                   /* uploadMethod1(item,fileName);*/

                    //方法二：保存到指定的路径
                    //将FileItem对象中保存的主体内容保存到某个指定的文件中。
                    // 如果FileItem对象中的主体内容是保存在某个临时文件中，该方法顺利完成后，临时文件有可能会被清除
                    uploadMethod2(item,fileName);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传方式一：手动删除临时文件
     * @param item
     */
    private void uploadMethod1(FileItem item,String fileName)throws Exception{
        InputStream is=item.getInputStream();
        FileOutputStream fos=new FileOutputStream("E:\\Java\\upload"+File.separator+fileName);
        byte[] buff=new byte[1024];
        int len=0;
        while((len=is.read(buff))>0){
            fos.write(buff);
        }
        //关闭输入输出流
        is.close();
        fos.close();

        //删除临时文件
        //为了方便观察临时文件生成到删除的过程我们让线程休眠5s
        Thread.sleep(5000);
        //清空FileItem类对象中存放的主体内容，如果主体内容被保存在临时文件中，delete方法将删除该临时文件
        item.delete();
    }

    /**
     * 上传方式二：会删除临时文件
     * @param item
     * @param fileName
     * @throws Exception
     */
    private void uploadMethod2(FileItem item,String fileName)throws Exception{
        Thread.sleep(3000);
        //方法二：保存到指定的路径
        //将FileItem对象中保存的主体内容保存到某个指定的文件中。
        // 如果FileItem对象中的主体内容是保存在某个临时文件中，该方法顺利完成后，临时文件有可能会被清除
        item.write(new File("E:\\Java\\upload2"+File.separator+fileName));

    }
}

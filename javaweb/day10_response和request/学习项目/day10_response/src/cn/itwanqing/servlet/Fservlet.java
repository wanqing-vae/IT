package cn.itwanqing.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * 演示响应字节数据
 * Servlet implementation class Fservlet
 */
@WebServlet("/Fservlet")
public class Fservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String string=new String("hello world");
//		byte[] b=string.getBytes();
//		response.getOutputStream().write(b);
		
		//将一个图片转化为字节数据
		String path="E:/我的壁纸/7.jpg";
//		通过打开与实际文件的连接来创建 FileInputStream ，该文件由文件系统中的路径名 name命名。 
		FileInputStream inputStream=new FileInputStream(path);
//		读取输入流内容的字节到字节数组中
		byte[] bs= IOUtils.toByteArray(inputStream);
		response.getOutputStream().write(bs);
	}

}

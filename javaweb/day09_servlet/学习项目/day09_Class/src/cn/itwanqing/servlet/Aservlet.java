package cn.itwanqing.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;


/**
 * Servlet implementation class Aservlet
 * 获取类路径下的资源
 */
@WebServlet("/Aservlet")
public class Aservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 用classLoader
		 * 1.得到class，在得到classLoader
		 * 2.再调用getResourceAsStream(),得到inputStream
		 */
//		ClassLoader classLoader=this.getClass().getClassLoader();
//		//classLoader相对于的是/classes
//		InputStream inputStream= classLoader.getResourceAsStream("a.txt");
//		String aString=IOUtils.toString(inputStream);
//		//用commons-io-27.jar中的ioutils将流对象转换成string
//		System.out.println(aString);
		
		/*
		 * 用class
		 * 
		 */
//		Class class1=this.getClass();
//		//相对于当前的.class文件所在的目录
//		//InputStream inputStream= class1.getResourceAsStream("b.txt");
//		
//		//加了/相对于classes下
//		InputStream inputStream=class1.getResourceAsStream("/a.txt");
//		String string=IOUtils.toString(inputStream);
//		System.out.println(string);
		
		
		//获取index.jsp的内容
		Class class1=this.getClass();
		InputStream inputStream= class1.getResourceAsStream("/../../index.jsp");
		String string =IOUtils.toString(inputStream);
		System.out.println(string);
		
		
	}

}

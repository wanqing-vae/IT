package cn.itwanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示request请求编码
 * Servlet implementation class Aservlet
 */
@WebServlet("/Aservlet")
public class Aservlet extends HttpServlet {

	/**
	 * get请求编码
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 由于tomcat8.0以上都是默认utf-8编码，所以注释内容不需要，要是tomcat为7则需要
		 */
		//1.先获取使用iso的错误字符串
		//2.回退，用utf-8重编
		String name=request.getParameter("username");
//		byte[] bs=name.getBytes("iso-8859-1");
//		name=new String(bs,"utf-8");
		
		System.out.println(name);
		
	}

	/*post请求编码
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.在获取参数之前，要调用request.setcharacterEncoding("utf-8")，将处理参数的编码编程utf-8
		request.setCharacterEncoding("utf-8");
		//2.使用getparameter()来获取参数
		System.out.println(request.getParameter("username"));
	}

}

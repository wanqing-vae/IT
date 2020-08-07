package cn.itwanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示重定向
 * 用户请求Bservlet，然后Bservlet响应302，并给出Location头
 * Servlet implementation class Bservlet
 */
@WebServlet("/Bservlet")
public class Bservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 重定向的两个步骤
		 * 1.设置Location
		 * 2.发送302状态码
		 */
//		response.setHeader("Location", "/day10_stateID/Cservlet");
//		response.setStatus(302);
//		System.out.println("Bservlet");
		
		//使用sendredirect方法快速重定向
		response.sendRedirect("http://www.baidu.com");
	}

}

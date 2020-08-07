package it.wanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示获取客户端的IP地址，请求方式，获取User-Agent,得到客户端的信息（操作系统和浏览器）
 * Servlet implementation class Aservlet
 */
@WebServlet("/Aservlet")
public class Aservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端的IP地址
		String IP=request.getRemoteAddr();
		System.out.println("您的IP地址为:"+IP);
		//获取请求方式
		System.out.println("您请求的方式为:"+request.getMethod());
		//获取客户端信息，用User-Agent请求头完成
		String userAgent=request.getHeader("User-Agent");
		System.out.println(userAgent);
	}
}

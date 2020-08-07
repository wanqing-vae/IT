package it.wanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过request获取URL得相关方法
 * Servlet implementation class Bservlet
 */
@WebServlet("/Bservlet")
public class Bservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//360浏览器中要加这个响应头才能实现下面得<br/>换行，应该是360浏览器没有设置响应头
		//而在IE浏览器中则就不用加这个直接换行，IE也没设置响应头，应该是浏览器得差异把。
		response.setHeader("Content-type", "text/html");
		
		
		response.getWriter().print(request.getScheme()+"<br/>");//获取协议：http
		response.getWriter().print(request.getServerName()+"<br/>");//获取服务器名称：localhost
		response.getWriter().print(request.getServerPort()+"<br/>");//获取服务器
		response.getWriter().print(request.getContextPath()+"<br/>");//获取项目名day10_request
		response.getWriter().print(request.getServletPath()+"<br/>");//获取servlet路径/Aservlet
		response.getWriter().print(request.getQueryString()+"<br/>");//获取参数
		response.getWriter().print(request.getRequestURI()+"<br/>");//获取URI，项目名加servlet路径
		response.getWriter().print(request.getRequestURL()+"<br/>");//获取URL
	}

}

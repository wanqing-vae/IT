package it.wanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用referer请求头，来设置防盗链
 * Servlet implementation class Cservlet
 */
@WebServlet("/Cservlet")
public class Cservlet extends HttpServlet {
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referer=request.getHeader("referer");
		System.out.println(referer);
		if(referer==null||!referer.contains("localhost")) {
			response.sendRedirect("http://www.Baidu.com");
		}
		else {
			System.out.println("hello");
		}
		
	}

	

}

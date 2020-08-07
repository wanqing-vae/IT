package cn.itwanqing.servlet.include;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示请求包含
 * Servlet implementation class Cservlet
 */
@WebServlet("/Cservlet")
public class Cservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Cservlet");
		//设置响应头
		response.setHeader("aaa", "bbb");
		//设置响应体
		response.getWriter().print("hello this is Cservlet");
		//请求包含
		request.getRequestDispatcher("/Dservlet").include(request, response);
		
	}

}

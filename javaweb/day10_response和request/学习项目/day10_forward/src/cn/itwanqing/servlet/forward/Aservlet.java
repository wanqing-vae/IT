package cn.itwanqing.servlet.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示请求转发
 * 演示request域对象
 * Servlet implementation class Aservlet
 */
@WebServlet("/Aservlet")
public class Aservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Aservlet");
		//设置一个响应头(无用)
		response.setHeader("aaa", "AAA");
		//设置响应体
		response.getWriter().print("hello this is Aservlet");
		//向request域中添加数据
		request.setAttribute("username", "wanqing");
		//请求转发
		request.getRequestDispatcher("/Bservlet").forward(request, response);
		
	}

}

package cn.itwanqing.servlet.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bservlet
 */
@WebServlet("/Bservlet")
public class Bservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//读取域中的数据
		System.out.println(request.getAttribute("username"));
		System.out.println("/Bservlet");
		response.getWriter().print("hello this is Bservlet");
		
	}

}

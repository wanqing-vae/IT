package cn.itwanqing.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cservlet
 * 用ServletContext来获取web.xml文件中的参数
 */
@WebServlet("/Cservlet")
public class Cservlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ServletContext context=this.getServletContext();
	    String value=context.getInitParameter("wanqing");
	    response.getWriter().write(value);
	}

	

}

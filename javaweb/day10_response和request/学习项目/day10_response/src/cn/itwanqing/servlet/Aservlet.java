package cn.itwanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Aservlet
 * 演示这三个方法
 * sendError(int sc)-->发送错误的状态码，如404，500
   sendError(int sc ,String msg)-->发送错误的状态码，还可以带一个错误信息
   setstatus(int sc)-->发送成功的状态码，也可以用来发302
 */
@WebServlet("/Aservlet")
public class Aservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//404错误
//		response.sendError(404, "请求的资源存在，但是就是不给你看，哈哈哈！");
//		//500错误
//		response.sendError(500, "服务器的错误，不是我的问题，憨憨！");
		//成功
		response.setStatus(200);
		
	}

}

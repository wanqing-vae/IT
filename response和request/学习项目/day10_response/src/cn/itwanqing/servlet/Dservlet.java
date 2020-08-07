package cn.itwanqing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 演示定时刷新，用refresh头
 * Servlet implementation class Dservlet
 */
@WebServlet("/Dservlet")
public class Dservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 发送响应体
		 */
		response.getWriter().print("测试是不是乱码");
		/*
		 * 设置refresh头，定时刷新
		 * 格式为
		 */
		response.setHeader("Refresh","5;URL=/day10_stateID/Eservlet");
	}

}

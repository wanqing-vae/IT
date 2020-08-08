package cn.itwanqing.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fservlet
 */
@WebServlet("/Fservlet")
/*
 * 这是Servlet3.0新特性(得Tomcat7.0版本及以上)，@WebServlet 用于将一个类声明为 Servlet，
 * 该注解将会在部署时被容器处理，容器将根据具体的属性配置将相应的类部署为 Servlet。
 * Eclipse不会自动在web.xml中生成该Servlet对应的mapping信息，而是在Servlet代码中加入注解@WebServlet
 */
public class Fservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Fservlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

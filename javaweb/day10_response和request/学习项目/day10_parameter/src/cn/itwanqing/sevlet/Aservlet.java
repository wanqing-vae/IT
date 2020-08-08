package cn.itwanqing.sevlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *演示获取请求参数
 * Servlet implementation class Aservlet
 */
@WebServlet("/Aservlet")
public class Aservlet extends HttpServlet {
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 通过get方法，即通过地址栏访问
		 */
		response.getWriter().print(request.getParameter("xxx"));
		response.getWriter().print(request.getParameter("yyy"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过表单访问
//		String name=request.getParameter("username");
//		String pass=request.getParameter("password");
//		String[] sex=request.getParameterValues("sex");
//		System.out.println(name+","+pass+","+Arrays.toString(sex));
		
		/*
		 * 获取所有请参数的名称
		 */
//		Enumeration enumeration=request.getParameterNames();
//		while (enumeration.hasMoreElements()) {
//			System.out.println(enumeration.nextElement());
//			
//		}
		//使用map来获取所用请求参数
		Map<String,String[]> map=request.getParameterMap();
		for(String name:map.keySet()) {
			String[] values=map.get(name);
			System.out.println(name+"="+Arrays.toString(values));
		}
		
	}

}

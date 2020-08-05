package cn.itwanqing.web.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Aservlet implements Servlet {
/*
 * 它是servlet生命周期方法，它会在servlet被销毁之前调用，并且它只会被调用一次（临死之前，遗言的方法）
 * @see javax.servlet.Servlet#destroy()
 */
	@Override
	public void destroy() {
		System.out.println("destroy()...");
	}
/*
 * 可以用来获取servlet配置信息
 * @see javax.servlet.Servlet#getServletConfig()
 */
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig()...");
		return null;
	}
/*
 * 获取servlet信息（基本不用）
 * @see javax.servlet.Servlet#getServletInfo()
 */
	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()...");
		return null;
	}
/*
 * 它是servlet生命周期方法，在被tomcat（服务器）创建servlet对象之后马上执行！（出生之后调用）
 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
 */
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		System.out.println("init()...");
		/*
		 * 获取初始化参数
		 */
		System.out.println(servletConfig.getInitParameter("p1"));
		System.out.println(servletConfig.getInitParameter("p2"));
		/*
		 * 获取初始化名称
		 */
		Enumeration enumeration=servletConfig.getInitParameterNames();
		while (enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
			}
			
		}
/*
 * 它是生命周期方法，他会被调用多次，每次处理请求时都会被调用
 * @see javax.servlet.Servlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
 */
	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		System.out.println("service()...");
	}

}

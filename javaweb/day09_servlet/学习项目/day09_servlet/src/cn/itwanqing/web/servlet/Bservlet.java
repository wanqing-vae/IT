package cn.itwanqing.web.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.org.apache.xml.internal.security.Init;

/*
 * 模拟GenericServlet
 */

public class Bservlet implements Servlet {
	private ServletConfig config;
	/*
	 * 需要就写，不需要就不写
	 * @see javax.servlet.Servlet#destroy()
	 */
	@Override
	public void destroy() {
	

	}

	@Override
	public ServletConfig getServletConfig() {
		return config;
	}
/*
 * 无用的方法
 * @see javax.servlet.Servlet#getServletInfo()
 */
	@Override
	public String getServletInfo() {
		return null;
	}
	/*
	 * 首先执行的生命周期方法，在创建对象之后就被调用
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config=config;
		init();

	}
	public void init() {
		
	}
/*
 * 每次处理请求时调用
 * @see javax.servlet.Servlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
 */
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("每次处理请求时调用");
	}
	public ServletContext servletContext() {
		return config.getServletContext();
	}
	public String getserletName() {
		return config.getServletName();
		
	}
	public String getInitparameter(String name) {
		return config.getInitParameter(name);
	}

}

package cn.itwanqing.web.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Dservlet extends GenericServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("出生了");
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		String value=getInitParameter("p1");
		System.out.println(value);

	}

}

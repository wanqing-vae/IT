package cn.itwanqing.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Cservlet extends Bservlet {
	@Override
	public void init() {
		System.out.println("出生了");
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String value=getInitparameter("p1");
		System.out.println(value);
	}
}

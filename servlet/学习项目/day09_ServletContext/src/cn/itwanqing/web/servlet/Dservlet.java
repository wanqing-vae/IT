package cn.itwanqing.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ScatteringByteChannel;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dservlet
 * 用ServletContext获取资源路径
 */
@WebServlet("/Dservlet")
public class Dservlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * getRealPath:获取带有盘符的路径，如F:/xxx/xxx/xxx
	 * C:\Users\79191\eclipse-workspace\apache-tomcat-8.5.13\wtpwebapps\day09_ServletContext\index.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=this.getServletContext().getRealPath("/index.jsp");
		System.out.println(path);
		//获取资源的路径后，在创建输入流对象
		InputStream iStream=this.getServletContext().getResourceAsStream("/index.jsp");
		
		//获取当前路径下所有资源的路径
		Set<String>pt=this.getServletContext().getResourcePaths("/WEB-INF");
		System.out.println(pt);
	}

}

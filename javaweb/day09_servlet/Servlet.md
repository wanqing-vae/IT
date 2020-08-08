# 1.什么是servlet #
   servlet是javaweb的三大组件之一，它属于动态资源。servlet的作用是处理请求，
   服务器会把接收到的请求交给servlet来处理
   在servlet中通常需要：
           接收请求数据
    	   处理请求
    	   完成响应
    	   例如客户端发出登录请求，或者输出注册请求，这些请求都应该由servlet来完成处理！
    	   servlet需要我们自己来编写，每个servlet必须实现javax.servlet.Servlet接口。
     javaweb三大组件为（Servlet,Filter,Listener）
servlet功能图：
![](https://wx2.sinaimg.cn/mw1024/0077Gv1hgy1gh6qm14ykzj30w50dumyt.jpg)
# 2.实现servlet的方法（有我们自己来写） #
实现servlet有三种方式：
   ## 1.实现javax.servlet.Servlet接口： ##

       public class Aservlet implements Servlet {
    /*
     * 它是servlet生命周期方法，它会在servlet被销毁之前调用，并且它只会被调用一次（临死之前，遗言的方法）
     * 服务器关闭时调用
     * @see javax.servlet.Servlet#destroy()
     */
    	@Override
    	public void destroy() {
    
    	}
    /*
     * 可以用来获取servlet配置信息
     * @see javax.servlet.Servlet#getServletConfig()
     */
    	@Override
    	public ServletConfig getServletConfig() {
    		return null;
    	}
    /*
     * 获取servlet信息（基本不用）
     * @see javax.servlet.Servlet#getServletInfo()
     */
    	@Override
    	public String getServletInfo() {
    		return null;
    	}
    /*
     * 它是servlet生命周期方法，在被tomcat（服务器）创建servlet对象之后马上执行！（出生之后调用）
     * 他在第一次被访问时被创建，即被调用
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
    	public void service(ServletRequest arg0, ServletResponse servletResponse) throws ServletException, IOException {
    
    	}
    
    }

结果：
![](https://wx4.sinaimg.cn/mw1024/0077Gv1hgy1gh6zwp9kvnj30wa06bmxb.jpg)

## 2.继承javax.servlet.GenericSrevlet类： ##
创建一个Bservlet来模拟GenericServlet类：
      
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
然后再创建一个Cservlet去继承实现GenericServlet的效果：
     
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
如果字类继承了GenericServlet，就可以直接的调用GenericServlet中的方法

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
## 3.继承javax.servlet.http.HttpServlet类（用的最多）： ##
通常我们会去继承HttpServlet类来完成我们的servlet！
HttpServlet原理图
![](https://wx3.sinaimg.cn/mw1024/0077Gv1hgy1gh7yr1ftl7j30ro0gcad8.jpg)
HttpServlet时序图
![](https://wx3.sinaimg.cn/mw1024/0077Gv1hgy1gh7yt080jdj30xv0gx0u7.jpg)
### 1.对HttpServlet的演示 ###
首先再下web.xml配置文件中加入Eservlet配置信息用来演示Httpservlet的效果
     
     <servlet-name>Eservlet</servlet-name>
      <servlet-class>cn.itwanqing.web.servlet.Eservlet</servlet-class>
      </servlet>
      <servlet-mapping>
      <servlet-name>Eservlet</servlet-name>
      <url-pattern>/Eservlet</url-pattern>
     </servlet-mapping>
然后再创建一个Eservlet来重写doGet()方法和doPost()方法

     public class Eservlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("这是doGet()方法....");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("这是doPost()方法....");
	}
    }
在地址栏里输入路径测试doGet()方法
![](https://wx1.sinaimg.cn/mw1024/0077Gv1hgy1gh806pqwoqj30im04mmxc.jpg)
通过页面表单测试doPost()方法，创建一个html文件
     
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    </head>
    <body>
    这是doPost方法
    <!-- action中的格式，首先加个/，然后在输入项目名，最后在输入再web.xml中的路径
     例如此次的为：/day09_servlet/Eservlet -->
    <form action="/day09_servlet/Eservlet" method="post">
    <input type="submit" name="提交">
    </form>
    </body>
    </html>
点击提交
![](https://wx4.sinaimg.cn/mw1024/0077Gv1hgy1gh80fauzyzj30iu07tjs0.jpg)
### 2.通过eclipse直接创建servlet ###
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
### 3.在tomcat启动的时候创建servlet ###
在servlet配置中添加`<load-on-startup>非负整数</load-on-startup>`，servlet会在
tomcat启动的时候创建，不是在第一次请求的时候创建，这个非负整数代表启动的优先级，0
代表优先级最高。随着数字增大，优先级越小。

    <servlet>
     <servlet-name>Eservlet</servlet-name>
     <servlet-class>cn.itwanqing.web.servlet.Eservlet</servlet-class>
     <load-on-startup>0</load-on-startup>
    </servlet>
    <servlet-mapping>
     <servlet-name>Eservlet</servlet-name>
     <url-pattern>/Eservlet</url-pattern>
    </servlet-mapping>
# 3.如何让浏览器访问servlet #
     1.给servlet一个指定的servlet路径（让servlet和一个路径绑定在一起）
     2.浏览器访问servlet路径

      给servlet配置servlet路径
	  这需要在web.xml中对servlet进行配置
	  web.xml中
	      <servlet>
    	    <servlet-name>Aservlet</servlet-name>
    	    <servlet-class>cn.itwanqing.web.Aservlet</servlet-class>
    	  </servlet>

    	  <servlet-mapping>
    	    <servlet-name>Aservlet</servlet-name>
    	    <url-pattern>/Aservlet</url-pattern>
	      </servlet-mapping>

      3.ServletConfig配置信息：
      <servlet>

        <servlet-name>Aservlet</servlet-name>
        <servlet-class>cn.itwanqing.web.servlet.Aservlet</servlet-class>

        <init-param>
          <param-name>p1</param-name>
          <param-value>v1</param-value>
        </init-param>

        <init-param>
          <param-name>p2</param-name>
          <param-value>v2</param-value>
        </init-param>

      </servlet>

# 4.servlet生命周期方法：（servlet有五个方法，由三个生命周期方法） #
    void init (servletConfig):出生之后（1次）；
	void service（servletRequest request,servletResponse response）:每次处理请求时都会被调用
	void destroy（）：临死之前（1次）；
# 5.特性： #
     单例，一个类只有一个对象；当然可能存在多个servlet类
	 线程不安全的，所以它的效率是高的
	 servlet类由我们来写，但对象由服务器创建，并且由服务器来调用相应的方法。
# 6.servletConfig的介绍 #
它是一个接口
![](https://wx3.sinaimg.cn/large/0077Gv1hgy1gh6z3alfqaj311e0fpwh9.jpg)
# 7.servletContext（域对象） #
![](https://wx3.sinaimg.cn/mw1024/0077Gv1hgy1gh92fy3o61j30ur0e6q7z.jpg)
   一个项目只有一个servletContext对象
   作用：我们可以在N多个servlet中来获取这个唯一的对象，使用它可以给多个servlet传递数据！
   可以通过它完成共享数据
   特性：与天地同寿！！：这个对象在tomcat创建时就创建，在tomcat关闭时才会死去。
   获取servletcontext
     1.servletConfig #getservletContext();
	 2.GenericSrevlet #getservletContext();
	 3.Httpsession #getservletContext();
	 4.servletContextEvent #getservletContext();
## 1.实现ServletContext的读取数据的操作 ##
先创建一个Aservlet用来存储数据
    
     /**
     * Servlet implementation class Aservlet
     * 用ServletContext存数据
     */
    @WebServlet("/Aservlet")
    public class Aservlet extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		ServletContext servletContext=this.getServletContext();
    		servletContext.setAttribute("wanqing", "wanqing77");//保存数据
    	}
    }
然后再创建一个Bservlet读数据
    
     /**
     * Servlet implementation class Bservlet
     * 用ServletContext读数据
     */
    @WebServlet("/Bservlet")
    public class Bservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		ServletContext context=this.getServletContext();
    		String value=(String) context.getAttribute("wanqing");
    		System.out.println(value);
    	}
    
    }
## 2.获取公共的初始化参数 ##
servlet也能获取初始化参数，但是它是局部的参数，与就是说，一个servlet只能获取自己的初始化参数，不能获取别人的。
可以配置公共的初始化参数，为所有servlet所用，需要使用ServletContext才能使用
还可以使用ServletContext来获取再web.xml文件中的配置应用初始化参数，应用初始化参数和servlet初始化参数不同，在web.xml中：
    
    <context-param>
       <param-name>wanqing</param-name>
       <param-value>wanqing77</param-value>
    </context-param>
然后创建一个Cservlet来获取这个初始化参数
    
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
## 3.用ServletContext获取资源路径 ##
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
## 4.获取类路径下的资源 ##
      /**
     * Servlet implementation class Aservlet
     * 获取类路径下的资源
     */
    @WebServlet("/Aservlet")
    public class Aservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		/*
    		 * 用classLoader
    		 * 1.得到class，在得到classLoader
    		 * 2.再调用getResourceAsStream(),得到inputStream
    		 */
    //		ClassLoader classLoader=this.getClass().getClassLoader();
    //		//classLoader相对于的是/classes
    //		InputStream inputStream= classLoader.getResourceAsStream("a.txt");
    //		String aString=IOUtils.toString(inputStream);
    //		//用commons-io-27.jar中的ioutils将流对象转换成string
    //		System.out.println(aString);
    		
    		/*
    		 * 用class
    		 * 
    		 */
    //		Class class1=this.getClass();
    //		//相对于当前的.class文件所在的目录
    //		//InputStream inputStream= class1.getResourceAsStream("b.txt");
    //		
    //		//加了/相对于classes下
    //		InputStream inputStream=class1.getResourceAsStream("/a.txt");
    //		String string=IOUtils.toString(inputStream);
    //		System.out.println(string);
    		
    		
    		//获取index.jsp的内容
    		Class class1=this.getClass();
    		InputStream inputStream= class1.getResourceAsStream("/../../index.jsp");
    		String string =IOUtils.toString(inputStream);
    		System.out.println(string);
    		
    		
    	}
    
    }
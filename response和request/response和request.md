# 1.服务器处理请求的流程： #
  服务器每次收到请求时，都会为这个请求开辟一个新的线程。
  服务器会把客户端的请求数据封装呆request对象中，request就是请求数据的载体。
  服务器还会创建response对象，这个对象与客户端连接在一起，它可以用来向客户端发送响应。
![](https://wx1.sinaimg.cn/mw1024/0077Gv1hgy1gha852jw0zj30ps0fh762.jpg)
 
# 2.response:#
  其类型为HttpServletResponse
  servletresponse:与协议无关的类型
  httpservletresponse:与http协议相关的类型
  发送这样的东西（响应的内容）：

    200 ok   状态码
    Content-Type:text/html;charset=utf-8;  响应头
    ...
  
  
    <html>
    .....
    响应体的内容
    它会在浏览器上显示
    ....
  
    </html>
## 1.状态码: ##
200 成功（2开头的表示成功）
302 重定向（3开头的表示中转）
404 客户端的错误，一般表示访问路径不存在或者访问路径错误（4开头的表示客户端的错误）
500 服务器端的错误 （5开头表示服务器端的错误）
sendError(int sc)-->发送错误的状态码，如404，500
sendError(int sc ,String msg)-->发送错误的状态码，还可以带一个错误信息
setstatus(int sc)-->发送成功的状态码，也可以用来发302

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
## 2.响应头: ##
响应头是一个键值对
可能存在（一个名字，一个值），也可能存在（一个名字，多个值）
**setHeader(string name,string value)适应于单值的响应头 **
例如:
  
    response.setHeader("name","wanqing77");
**addHeader(string name,string value)适用于多值的响应头**
例如: 

    response.addHeader("name","wanqing11");
    response.addHeader("name","wanqing22");
    response.addHeader("name","wanqing33");
**setIntHeader(string name,int value)适用于单值的int类型的响应头**

    response.setIntHeader("name",888);
**addIntHeader(string name,int value)适用于多值的int类型的响应头**
**setDateHeader(string name,long value)适用于单值的毫秒类型的响应头**

    response.setDateHeader("name",1000*60*60*24);过期时间为24小时
**addDateHeader(string name,long value)适用于多值的毫秒类型的响应头**
## 3.重定向  ##
### 1.重定向时序图  ###
![](https://wx2.sinaimg.cn/mw1024/0077Gv1hly1ghckph22mej30mp0e6q38.jpg)
### 2.重定向操作 ###
先创建一个Bservlet，当用户访问Bservlet时，Bservlet会响应302，并重发Loaction头
 
     /**
     * 演示重定向
     * 用户请求Bservlet，然后Bservlet响应302，并给出Location头
     * Servlet implementation class Bservlet
     */
    @WebServlet("/Bservlet")
    public class Bservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		/*
    		 * 重定向的两个步骤
    		 * 1.设置Location
    		 * 2.发送302状态码
    		 */
    		response.setHeader("Location", "/day10_stateID/Cservlet");
    		response.setStatus(302);
    		System.out.println("Bservlet");
    	}
    }
然后根据Location头定位到Cservlet
    
    /**浏览器重定向Cservlet
     * Servlet implementation class Cservlet
     */
    @WebServlet("/Cservlet")
    public class Cservlet extends HttpServlet {
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("Cservlet");
    	}
    }
### 3.快速重定向 ###
sendRedirect(string location)

     /**
     * 演示重定向
     * 用户请求Bservlet，然后Bservlet响应302，并给出Location头
     * Servlet implementation class Bservlet
     */
    @WebServlet("/Bservlet")
    public class Bservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		/*
    		 * 重定向的两个步骤
    		 * 1.设置Location
    		 * 2.发送302状态码
    		 */
    //		response.setHeader("Location", "/day10_stateID/Cservlet");
    //		response.setStatus(302);
    //		System.out.println("Bservlet");
    		
    		//使用sendredirect方法快速重定向
    		response.sendRedirect("http://www.baidu.com");
    	}
    }
## 4.定时刷新（定时重定向）  ##
先创建一个Dservlet，当用户访问Dservlet时，Dervlet响应头refresh会定时转页面

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
转到Eservlet界面
    
    /**
     * Servlet implementation class Eservlet
     */
    @WebServlet("/Eservlet")
    public class Eservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		response.getWriter().print("不知道是不乱码");
    	}
    }
## 5.response的两个流 ##
**ServletOutputStream 用来向客户端发送字节数据
PrintWriter  用来向客户端发送字符数据！需要设置编码**
**这两个流不能同时使用**

     /**
     * 演示响应字节数据
     * Servlet implementation class Fservlet
     */
    @WebServlet("/Fservlet")
    public class Fservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //		String string=new String("hello world");
    //		byte[] b=string.getBytes();
    //		response.getOutputStream().write(b);
    		
    		//将一个图片转化为字节数据
    		String path="E:/我的壁纸/7.jpg";
    //		通过打开与实际文件的连接来创建 FileInputStream ，该文件由文件系统中的路径名 name命名。 
    		FileInputStream inputStream=new FileInputStream(path);
    //		读取输入流内容的字节到字节数组中
    		byte[] bs= IOUtils.toByteArray(inputStream);
    		response.getOutputStream().write(bs);
    	}
    }
# 3.requset #
request封装了客户端所有的请求数据
## 1.获取常用信息 ##
获取客户端的IP：用request.getremoteaddr()方法可以获取
获取请求方式：用request.getmethod()方法
## 2.获取Http请求头 ##
string getHeader(string name);适用于单值头
string getIntHeader(string name);适用于单值int类型的请求头
Long getDateHeader(string name);适用于单值毫秒类型的请求头
使用User-Agent请求头可以查看客户端的信息，案例如下：
    
     /**
     * 演示获取客户端的IP地址，请求方式，获取User-Agent,得到客户端的信息（操作系统和浏览器）
     * Servlet implementation class Aservlet
     */
    @WebServlet("/Aservlet")
    public class Aservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		//获取客户端的IP地址
    		String IP=request.getRemoteAddr();
    		System.out.println("您的IP地址为:"+IP);
    		//获取请求方式
    		System.out.println("您请求的方式为:"+request.getMethod());
    		//获取客户端信息，用User-Agent请求头完成
    		String userAgent=request.getHeader("User-Agent");
    		System.out.println(userAgent);
    		
    	}
    }
## 3.referer请求头的应用 ##
     /**
     * 使用referer请求头，来设置防盗链
     * Servlet implementation class Cservlet
     */
    @WebServlet("/Cservlet")
    public class Cservlet extends HttpServlet {
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String referer=request.getHeader("referer");
    		System.out.println(referer);
    		if(referer==null||!referer.contains("localhost")) {
    			response.sendRedirect("http://www.Baidu.com");
    		}
    		else {
    			System.out.println("hello");
    		}
    	}
    }
如果是通过地址栏访问，会直接跳转到百度页面，如果是通过index.html访问，就可以跳转到/Cservlet
## 4.获取请求URl ##
URL:  http://localhost:8080/day10_request/Aservlet?username=xxxx&password=yyy
string getScheme():获取Http协议
string getServername():获取服务器名：localhost
string getServerPort():获取服务器端口：8080
string getContextPath():获取项目名：day10_request
string getServerPath():获取servlet路径：/Aservlet
string getQueryString():获取参数部分：username=xxxx&password=yyy
string getRequestURI():获取请求URI：等于项目名加servlet路径，day10_request/Aservlet
string getRequestURL():获取请求URL，http://localhost:8080/day10_request/Aservlet，不包含参数

     /**
     * 通过request获取URL得相关方法
     * Servlet implementation class Bservlet
     */
    @WebServlet("/Bservlet")
    public class Bservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		//360浏览器中要加这个响应头才能实现下面得<br/>换行，应该是360浏览器没有设置响应头
    		//而在IE浏览器中则就不用加这个直接换行，IE也没设置响应头，应该是浏览器得差异把。
    		//response.setHeader("Content-type", "text/html");
    		
    		
    		response.getWriter().print(request.getScheme()+"<br/>");//获取协议：http
    		response.getWriter().print(request.getServerName()+"<br/>");//获取服务器名称：localhost
    		response.getWriter().print(request.getServerPort()+"<br/>");//获取服务器
    		response.getWriter().print(request.getContextPath()+"<br/>");//获取项目名day10_request
    		response.getWriter().print(request.getServletPath()+"<br/>");//获取servlet路径/Aservlet
    		response.getWriter().print(request.getQueryString()+"<br/>");//获取参数
    		response.getWriter().print(request.getRequestURI()+"<br/>");//获取URI，项目名加servlet路径
    		response.getWriter().print(request.getRequestURL()+"<br/>");//获取URL
    	}
    
    }
运行结果：
![](https://wxt.sinaimg.cn/mw1024/0077Gv1hgy1ghew6i2x6ij30mz05mt8n.jpg?tags=%5B%5D)
## 5.获取请求参数 ##
请求参数是客户端发送给服务器的，有的在表单里面（post）,有的在URL后面（GET）
string getparameter(string name):获取指定名称的请求参数值，适用于单值请求参数
string[] getparameter(string name):获取指定名称的请求参数值，适用于多值请求参数
enumeration<string> getparameterNames()：获取所用请求参数的名称
Map<string,string[]>getparameterMap()：获取所有请求参数，以key为参数名，以value为参数值
演示测试案例，首先创建一个parameter.html文件：
     
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    </head>
    <body>
    <h1>演示请求参数</h1>
    <a href="/day10_parameter/Aservlet?xxx=XXX&yyy=YYY">GET请求</a>
    <form action="/day10_parameter/Aservlet" method="post">
    用户名:<input type="text" name="username"><br>
    密   码:<input type="password" name="password"><br>
    性   别:<input type="radio" name="sex" value="nan">男
    <input type="radio" name="sex" value="nv">女
       <br>
    <input type="submit" value="提交">
    </form>
    </body>
    </html>
然后在创建Aservlet用来访问：
    
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
![](https://wx1.sinaimg.cn/mw1024/0077Gv1hgy1gheylma3jlj30k203smxa.jpg)
## 6.请求转发和请求包含 ##
requestDispatcher rd=request.getrequestDispatcher("/MYservlet")
使用request获取requestDispatcher对象，方法的参数是被转发或被包含的servlet路径（"/servlet"）
请求转发：rd.forward(request,response)
请求包含：rd.include(request,response)
![](https://wx2.sinaimg.cn/mw1024/0077Gv1hgy1ghfvri9uf3j30og0f6wex.jpg)
有时一个请求需要多个servlet协作才能完成，所以需要从一个servlet跳转到另一个servlet
一个请求跨多个servlet，需要使用转发和包含。
请求转发：由下一个servlet完成响应体，当前servlet可以设置响应头（留头不留体）
请求包含：由两个servlet共同完成响应体（都留）
无论是请求转发和请求包含，都在一个请求范围内，使用同一个request和response
### 1.请求转发 ###
访问Aservlet，Aservlet完成不了这项工作，就交给Bservlet来完成，Aservlet就可以啥都不用干（意思是不用设置响应体）
Aservlet：  
    
     /**
     * 演示请求转发
     * Servlet implementation class Aservlet
     */
    @WebServlet("/Aservlet")
    public class Aservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("Aservlet");
    		//设置一个响应头(无用)
    		response.setHeader("aaa", "AAA");
    		//设置响应体
    		response.getWriter().print("hello this is Aservlet");
    		//请求转发
    		request.getRequestDispatcher("/Bservlet").forward(request, response);
    		
    	}
    } 
Bservlet：  
     
    /**
     * Servlet implementation class Bservlet
     */
    @WebServlet("/Bservlet")
    public class Bservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("/Bservlet");
    		response.getWriter().print("hello this is Bservlet");
    		
    	}
    }
![](https://wx2.sinaimg.cn/mw1024/0077Gv1hgy1ghg4tz3xzkj30ik03aaa8.jpg)
**我们可以看到地址栏访问的是Aservlet，但是输出的是Bservlet的响应内容，我们在Aservlet设置的响应体内容根本没有用这就是所说的留头不留体**
### 2.请求包含 ###
访问Cservlet，Cservlet根本一个人难搞定，就叫Dservlet一起搞
Cservlet：

     /**
     * 演示请求包含
     * Servlet implementation class Cservlet
     */
    @WebServlet("/Cservlet")
    public class Cservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("Cservlet");
    		//设置响应头
    		response.setHeader("aaa", "bbb");
    		//设置响应体
    		response.getWriter().print("hello this is Cservlet");
    		//请求包含
    		request.getRequestDispatcher("/Dservlet").include(request, response);
    		
    	}
    }
Dservlet：

     /**
     * Servlet implementation class Dservlet
     */
    @WebServlet("/Dservlet")
    public class Dservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("Dservlet");
    		response.getWriter().print("hello this is Dservlet");
    	}
    }
![](https://wx2.sinaimg.cn/mw1024/0077Gv1hgy1ghg4tz3ntrj30ik03374g.jpg)
**我们可以看到既有Cservlet响应体的内容，又有Dservlet响应体的内容（这是两个人一起完成的，所以都留）**
## 7.request域 ##
![](https://wx4.sinaimg.cn/mw1024/0077Gv1hgy1ghhdfoxymjj30x40ggq3q.jpg)

servlet中有三大域对象：request，session，application（所有域对象）都有这三个方法
1.void setAttribute(string name,object value);这个用来保存数据
2.object getAttribute(string name);用来读取数据
3.void removeAttribute(string name):删除数据
同一请求内（即请求转发或者请求包含）使用request.setAttribute(),request.getAttribute()来传递值。
前一个servlet用setAttribute()保存，后一个servlet用getAttribute()读取
## 8.请求转发和重定向的区别 ##
1.请求转发是一个请求一次响应，而重定向是两个请求两个响应
2.请求转发它的地址栏不会发生变化，而重定向会显示后一个请求的地址
3.请求转发只能转发到同项目的servlet中，而重定向不仅能重定向到本项目的servlet，还能定向到其他的项目，比如重定向到（百度）
4.请求转发是服务器端的行为，只需要给出转发的servlet路径，而重定向需要给出requestURI，即包含项目名
5.请求转发的效率比重定向的效率高，因为请求转发只有一个请求。如果需要地址栏发生变化，则必须用到重定向，如果要在下一个servlet获取request域中的数据，则必须用到请求转发
 Aservlet:
    
     /**
     * 演示请求转发
     * 演示request域对象
     * Servlet implementation class Aservlet
     */
    @WebServlet("/Aservlet")
    public class Aservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("Aservlet");
    		//设置一个响应头(无用)
    		response.setHeader("aaa", "AAA");
    		//设置响应体
    		response.getWriter().print("hello this is Aservlet");
    		//向request域中添加数据
    		request.setAttribute("username", "wanqing");
    		//请求转发
    		request.getRequestDispatcher("/Bservlet").forward(request, response);
    		
    	}
    }
Bservlet:
    
    /**
     * Servlet implementation class Bservlet
     */
    @WebServlet("/Bservlet")
    public class Bservlet extends HttpServlet {
    
    	/**
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		//读取域中的数据
    		System.out.println(request.getAttribute("username"));
    		System.out.println("/Bservlet");
    		response.getWriter().print("hello this is Bservlet");
    		
    	}
    }
# 4.编码 #
常见字符编码：iso-8859-1(不支持中文)，gbk（中国国际码）utf-8（万国码，支持全世界的编码，所以用它）
## 1.响应编码 ##
1.在使用response.getWriter()向客户端发送字符数据时，如果在之前没有设置编码，则默认使用iso,因为iso不支持中文，所以输出中文时一定乱码
2.在使用response.getWriter()之前，可以通过使用response.serCharaceterEncoding("utf-8")来设置响应编码，如果浏览器是使用utf-8编码，则不会乱码，否则也会乱码
3.在使用response.getWriter()之前可以使用response.setHeader("Content-type","text/html;charset=utf-8")来设置响应头，这个头给了浏览器，浏览器就知道用什么编码，所以这个就不会乱码
4.设置编码的快捷方法，response.setContentType("text/html;charset=utf-8")
![](https://wx1.sinaimg.cn/mw1024/0077Gv1hgy1ghi58zij68j30xs0i9adc.jpg)
## 2.请求编码 ##
1.post编码
![](https://wx1.sinaimg.cn/mw1024/0077Gv1hgy1ghibt4gpxgj310b0dxwg5.jpg)
2.get编码
![](https://wx2.sinaimg.cn/mw1024/0077Gv1hgy1ghibq6nn6zj30qy0f6gnp.jpg)
演示请求编码：
需要一个index.html
    
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    </head>
    <body>
    <form action="/day10_Encode/Aservlet" method="post">
    用户名：<input type="text" name="username"><br>
    <input type="submit" value="提交">
    <hr/>
    <a href="/day10_Encode/Aservlet?username=张三">GET</a>
    </form>
    </body>
    </html>
Aservlet

     /**
     * 演示request请求编码
     * Servlet implementation class Aservlet
     */
    @WebServlet("/Aservlet")
    public class Aservlet extends HttpServlet {
    
    	/**
    	 * get请求编码
    	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		/*
    		 * 由于tomcat8.0以上都是默认utf-8编码，所以注释内容不需要，要是tomcat为7则需要
    		 */
    		//1.先获取使用iso的错误字符串
    		//2.回退，用utf-8重编
    		String name=request.getParameter("username");
    //		byte[] bs=name.getBytes("iso-8859-1");
    //		name=new String(bs,"utf-8");
    		
    		System.out.println(name);
    		
    	}
    
    	/*post请求编码
    	 * 
    	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    	 */
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		//1.在获取参数之前，要调用request.setcharacterEncoding("utf-8")，将处理参数的编码编程utf-8
    		request.setCharacterEncoding("utf-8");
    		//2.使用getparameter()来获取参数
    		System.out.println(request.getParameter("username"));
    	}
    
    }

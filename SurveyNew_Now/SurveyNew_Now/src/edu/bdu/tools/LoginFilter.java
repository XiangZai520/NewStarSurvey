package edu.bdu.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 制作时间：2011年8月24日
 * @author xin lei hu
 *
 */
public class LoginFilter implements Filter{
	
	private FilterConfig fc;
    /**
     * 根据用户的请求，判断访问者是否是调查问卷的管理者
     */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
	throws IOException,ServletException{
	
		HttpServletRequest request = (HttpServletRequest)sRequest;//获得用户请求信息
		
		HttpServletResponse response = (HttpServletResponse)sResponse;//获得响应对象
		
		HttpSession session = request.getSession();
		
		Object user = session.getAttribute("user");//获得当前用户信息
		
		if(user == null && !(user instanceof edu.bdu.entity.User))
		{
			
	       response.sendRedirect("../login.jsp");//如果当前session不存在跳转到登陆页
         
		}
		else
		{
			chain.doFilter(sRequest, sResponse);//传递用户请求
		}
	}
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig fc) throws ServletException{
		this.fc = fc;
	}
	/**
	 * 销毁
	 */
	public void destroy(){
		this.fc = null;
	}
	
}

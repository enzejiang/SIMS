package com.xingtu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xingtu.bean.User;

/**
 * 项目名称：ssms
 * 类名称：VisitFilter
 * <br>
 * 包路径：com.xingtu.filter
 * <br>
 * 类描述：如果用户没有登录，返回登录界面
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月27日 下午9:40:59
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月27日 下午9:40:59
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class VisitFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		User user = (User) request.getSession().getAttribute("user");
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		
		if (user != null) {
			chain.doFilter(request, response);
		} else{
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/index.jsp");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

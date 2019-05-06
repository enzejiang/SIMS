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

/**
 * 项目名称：ssms
 * 类名称：EncodeFilter
 * <br>
 * 包路径：com.xingtu.filter
 * <br>
 * 类描述：设置请求和响应的编码格式，以及响应时的资源类型
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月27日 下午9:09:29
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月27日 下午9:09:29
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class EncodeFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		
		
		String os = System.getProperty("os.name"); 
		System.out.println("***************************************************\n当前操作系统："+ os);
		//设置响应时的编码格式和响应资源的类型（比如："text/html"或者"text/css"） 。
		if (os.toLowerCase().startsWith("win") && os.toLowerCase().contains("win")) {  
			// 资源响应设置：windows系统使用如下设置
			response.setContentType("text/html; charset=utf-8");
		} else {
			// 资源响应设置：其他操作系统使用如下设置
			String contentType = request.getHeader("Accept") == null ? "text/html" : request.getHeader("Accept");
			response.setContentType(contentType + "; charset=utf-8");
		}
		
		//放行资源
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

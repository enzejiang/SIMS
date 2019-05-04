package com.xingtu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xingtu.service.ICourseService;
import com.xingtu.service.impl.CourseServiceImpl;

import net.sf.json.JSONArray;



public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private ICourseService service = new CourseServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if("toCourseListView".equalsIgnoreCase(method)){ //转发到课程列表页
			request.getRequestDispatcher("/WEB-INF/view/other/courseList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if ("getGrid".equalsIgnoreCase(method)){ //获取所有课程
			getGrid(request, response);
		} else if("insert".equalsIgnoreCase(method)){ //添加课程
			insert(request, response);
		} else if("delete".equalsIgnoreCase(method)){ //删除课程
			delete(request, response);
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer courseId = Integer.parseInt(request.getParameter("courseId"));
		try {
			this.service.delete(courseId);
			response.getWriter().write("true");
		} catch (Exception e) {
			response.getWriter().write("false");
			e.printStackTrace();
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		this.service.insert(name);
		response.getWriter().write("true");
	}
	
	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Object> result = service.getCourses();
		//返回数据
		response.getWriter().write(JSONArray.fromObject(result).toString());
	}

}

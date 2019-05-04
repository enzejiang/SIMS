package com.xingtu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xingtu.service.IStudentService;
import com.xingtu.service.impl.StudentServiceImpl;

import net.sf.json.JSONArray;

public class StudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8620345580022453935L;
	
	private IStudentService service = new StudentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if ("toStudentListView".equalsIgnoreCase(method)) { //转发到学生列表页
			request.getRequestDispatcher("/WEB-INF/view/student/studentList.jsp").forward(request, response);
		}  
		if ("toCourseRegist".equalsIgnoreCase(method)) { //转发到学生列表页
			request.getRequestDispatcher("/WEB-INF/view/student/CourseRegist.jsp").forward(request, response);
		}  
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");

		if ("getGrid".equalsIgnoreCase(method)) { // 获取所有学生基本信息数据
			getGrid(request, response);
		} else if ("insert".equalsIgnoreCase(method)) { // 新增学生
			insert(request, response);
		} else if ("delete".equalsIgnoreCase(method)) { // 删除学生
			delete(request, response);
		} 
		else if ("modify".equalsIgnoreCase(method)) { // 删除学生
			modify(request, response);
		} 
	}
	
	
	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Object> result = service.getStudents();
		JSONArray jsonArr = new JSONArray();
		jsonArr = JSONArray.fromObject(result);
		
		//返回数据
        response.getWriter().write(jsonArr.toString());
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String parentPhone = request.getParameter("parentPhone");
		String parentWechat = request.getParameter("parentWechat");
		String photo = request.getParameter("photo/student.jpg");
		String birthday = request.getParameter("birthday");
		String paymentday = request.getParameter("paymentday");
		String graduationTime = request.getParameter("graduationTime");

		
		Integer id = this.service.insert(code, name, gender, parentPhone, parentWechat, photo, birthday, paymentday, graduationTime);
		if (id == null) {
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"已存在学号为【" + code + "】的学生，不能添加！\"}");
		} else {
			response.getWriter().write("{\"status\":\"true\", \"msg\":\"添加成功！\"}");
		}
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids[]");
		//String[] codes = request.getParameterValues("codes[]");
		//Integer teacherId = Integer.parseInt(request.getParameter("teacherId"));
		try {
			for (Integer i=0; i<ids.length; i++) {
				this.service.delete(Integer.parseInt(ids[i]));
			}
			response.getWriter().write("true");
		} catch (Exception e) {
			response.getWriter().write("false");
			e.printStackTrace();
		}
	}
	private void modify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String parentPhone = request.getParameter("parentPhone");
		String parentWechat = request.getParameter("parentWechat");
		String photo = request.getParameter("photo/student.jpg");
		String birthday = request.getParameter("birthday");
		String paymentday = request.getParameter("paymentday");
		String graduationTime = request.getParameter("graduationTime");
		int id = Integer.parseInt(request.getParameter("id"));
		

		
		Integer ret = this.service.update(id,code, name, gender, parentPhone, parentWechat, photo, birthday, paymentday, graduationTime);
		if (ret == null) {
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"已存在学号为【" + code + "】的学生，信息不能修改！\"}");
		} else {
			response.getWriter().write("{\"status\":\"true\", \"msg\":\"修改成功！\"}");
		}
	}

}

package com.xingtu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xingtu.service.ISchoolTimetableService;
import com.xingtu.service.IStudentService;
import com.xingtu.service.impl.SchoolTimetableServiceImpl;
import com.xingtu.service.impl.StudentServiceImpl;

import net.sf.json.JSONArray;

public class SchoolTimetableServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268464094769530123L;
	
	private ISchoolTimetableService service = new SchoolTimetableServiceImpl();
	
	private IStudentService studentService = new StudentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if ("toStudentCourseEnrollmentListView".equalsIgnoreCase(method)) { //转发到待报名学生列表页
			request.getRequestDispatcher("/WEB-INF/view/student/courseEnrollment.jsp").forward(request, response);
		} else if ("toCourseRenewals".equalsIgnoreCase(method)) {
			request.getRequestDispatcher("/WEB-INF/view/student/courseRenewals.jsp").forward(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");

		if ("getGrid".equalsIgnoreCase(method)) { // 获取所有学生基本信息数据
			getGrid(request, response);
		} else if ("insert".equalsIgnoreCase(method)) { // 新增学生课程报名信息
			insert(request, response);
		} else if ("delete".equalsIgnoreCase(method)) { // 删除学生课程报名信息
			delete(request, response);
		} else if ("getCourseRenewalsGrid".equalsIgnoreCase(method)) { // 获取学生课程续费数据
			getCourseRenewalsGrid(request, response);
		} else if ("updateSchoolTimetableEndDate".equalsIgnoreCase(method)) { // 更新课程结束日期、学生课程续费
			updateSchoolTimetableEndDate(request, response);
		}
	}


	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String classesId = request.getParameter("classesId");
		 List<Object> result = null;
		 if (StringUtils.isNotBlank(classesId)) {
			 result = this.studentService.getStudentsNotINClasses(Integer.parseInt(classesId)); 
		 } else {
			 result = this.studentService.getStudents();
		 }
		 JSONArray jsonArr = new JSONArray(); 
		 jsonArr = JSONArray.fromObject(result);
		  
		 //返回数据 
		 response.getWriter().write(jsonArr.toString());
		 
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String classesId = request.getParameter("classesId");
		String[] ids = request.getParameterValues("ids[]");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		try {
			List<Integer> studentIds = new ArrayList<Integer>();
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					studentIds.add(Integer.parseInt(id));
				}
			}
			this.service.insert(Integer.parseInt(classesId), studentIds, startDate, endDate);
			
			response.getWriter().write("{\"status\":\"true\", \"msg\":\"报名成功！\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"报名失败！\"}");
		}
		
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
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
		
		*/
	}
	
	
	private void getCourseRenewalsGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String classesIdStr = request.getParameter("classesId");
		 String courseName = request.getParameter("courseName");
		 String studentName = request.getParameter("studentName");
		 
		 Integer classesId = null;
		 if (classesIdStr != null && !"".equals(classesIdStr)) {
			 classesId = Integer.parseInt(classesIdStr);
		 }
		 
		 List<Object> result  = this.service.getCourseRenewalsByMultiConds(classesId, courseName, studentName);
		 
		 JSONArray jsonArr = new JSONArray(); 
		 jsonArr = JSONArray.fromObject(result);
		  
		 //返回数据 
		 response.getWriter().write(jsonArr.toString());
		 
	}
	
	
	private void updateSchoolTimetableEndDate(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		//String[] ids = request.getParameterValues("ids[]");
		String ids = request.getParameter("ids");
		String newEndDate = request.getParameter("endDate");
		try {
			this.service.updateEndDate(ids, newEndDate);
			response.getWriter().write("{\"status\":\"true\", \"msg\":\"续费成功！\"}");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"续费失败！\"}");
		}
		 
	}

}

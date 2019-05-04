package com.xingtu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xingtu.bean.Course;
import com.xingtu.bean.Grade;
import com.xingtu.bean.Teacher;
import com.xingtu.service.IClassesService;
import com.xingtu.service.ICourseService;
import com.xingtu.service.IGradeService;
import com.xingtu.service.ITeacherService;
import com.xingtu.service.impl.ClassesServiceImpl;
import com.xingtu.service.impl.CourseServiceImpl;
import com.xingtu.service.impl.GradeServiceImpl;
import com.xingtu.service.impl.TeacherServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private IClassesService service = new ClassesServiceImpl();
	
	private IGradeService gradeService = new GradeServiceImpl();
	
	private ICourseService courseService = new CourseServiceImpl();
	
	private ITeacherService teacherService = new TeacherServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if ("toClassesListView".equalsIgnoreCase(method)){ // 班级列表页
			request.getRequestDispatcher("/WEB-INF/view/other/classesList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if("getGrid".equalsIgnoreCase(method)){ // 获取所有班级
			getGrid(request, response);
		} else if("insert".equalsIgnoreCase(method)){ // 新增班级信息
			insert(request, response);
		} else if("delete".equalsIgnoreCase(method)){ // 删除班级信息
			delete(request, response);
		}else if("modify".equalsIgnoreCase(method)){ // 修改班级信息
			modify(request, response);
		}
		
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String gradeIdStr = request.getParameter("gradeId");
		String teacherIdStr = request.getParameter("teacherId");
		String courseIdStr = request.getParameter("courseId");
		
		Integer gradeId = StringUtils.isNotBlank(gradeIdStr) ? Integer.parseInt(gradeIdStr) : null;
		Integer teacherId = StringUtils.isNotBlank(teacherIdStr) ? Integer.parseInt(teacherIdStr) : null;
		Integer courseId = StringUtils.isNotBlank(courseIdStr) ? Integer.parseInt(courseIdStr) : null;
		
		this.service.insert(name, gradeId, teacherId, courseId);
		response.getWriter().write("true");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int classesId = Integer.parseInt(request.getParameter("classesId"));
		try {
			this.service.delete(classesId);
			response.getWriter().write("true");
		} catch (Exception e) {
			response.getWriter().write("false");
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int classesId = Integer.parseInt(request.getParameter("classesId"));
		try {
			this.service.delete(classesId);
			response.getWriter().write("true");
		} catch (Exception e) {
			response.getWriter().write("false");
			e.printStackTrace();
		}
	}

	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Object> result = service.getClasses();
		JSONArray jsonArr = new JSONArray();
		jsonArr = JSONArray.fromObject(result);
		
		if (jsonArr != null && jsonArr.size() > 0) {
			for (Integer i=0; i<jsonArr.size(); i++) {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				Integer gradeId = jsonObj.getInt("gradeId");
				Integer courseId = jsonObj.getInt("courseId");
				Integer teacherId = jsonObj.getInt("teacherId");
				
				if (gradeId != null) {
					Grade grade = this.gradeService.getById(gradeId);
					jsonObj.element("grade", grade);
				}
				
				if (courseId != null) {
					Course course = this.courseService.getById(courseId);
					jsonObj.element("course", course);
				}
				
				if (teacherId != null) {
					Teacher teacher = this.teacherService.getById(teacherId);
					jsonObj.element("teacher", teacher);
				}
			}
		}
		
		//返回数据
        response.getWriter().write(jsonArr.toString());
	}

	public void getPagingClasses(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * //获取参数 String gradeid = request.getParameter("gradeId"); //获取分页参数 int page =
		 * Integer.parseInt(request.getParameter("page")); int rows =
		 * Integer.parseInt(request.getParameter("rows"));
		 * 
		 * String result = service.getClasses(gradeid, new Page(page, rows)); //返回数据
		 * response.getWriter().write(result);
		 */
	}

}

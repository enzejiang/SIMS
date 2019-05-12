package com.xingtu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xingtu.bean.Course;
import com.xingtu.bean.Teacher;
import com.xingtu.bean.User;
import com.xingtu.service.ICourseService;
import com.xingtu.service.ITeacherService;
import com.xingtu.service.impl.CourseServiceImpl;
import com.xingtu.service.impl.TeacherServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TeacherServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874956943112911026L;
	
	private ITeacherService service = new TeacherServiceImpl();
	
	private ICourseService courseService = new CourseServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if ("toTeacherListView".equalsIgnoreCase(method)) { //转发到教师列表页
			request.getRequestDispatcher("/WEB-INF/view/teacher/teacherList.jsp").forward(request, response);
		} else if ("toTeacherCourseListView".equalsIgnoreCase(method)) { //转发到教师课程列表页
			request.getRequestDispatcher("/WEB-INF/view/teacher/teacherCourseList.jsp").forward(request, response);
		} else if ("toExamTeacherView".equalsIgnoreCase(method)) { //转发到教师列表页
			request.getRequestDispatcher("/WEB-INF/view/teacher/examTeacherList.jsp").forward(request, response);
		} else if ("toTeacherPersonalView".equalsIgnoreCase(method)) { //转发到教师列表页
			toPersonal(request, response);
		}  
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");

		if ("getGrid".equalsIgnoreCase(method)) { // 获取所有教师数据
			getGrid(request, response);
		} else if ("insert".equalsIgnoreCase(method)) { // 新增教师
			insert(request, response);
		} else if ("delete".equalsIgnoreCase(method)) { // 删除教师
			delete(request, response);
		} else if ("getTeacherCourseGrid".equalsIgnoreCase(method)) { // 获取教师课程
			getTeacherCourseGrid(request, response);
		}
	}
	
	
	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Object> result = service.getTeachers();
		JSONArray jsonArr = new JSONArray();
		jsonArr = JSONArray.fromObject(result);
		
		if (jsonArr != null && jsonArr.size() > 0) {
			for (Integer i=0; i<jsonArr.size(); i++) {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				Integer courseId = jsonObj.getInt("majorCourseId");
			
				if (courseId != null) {
					Course course = this.courseService.getById(courseId);
					jsonObj.element("course", course);
				}
			}
		}
		
		//返回数据
        response.getWriter().write(jsonArr.toString());
	}
	
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String wechat = request.getParameter("wechat");
		String photo = request.getParameter("photo");
		String birthday = request.getParameter("birthDay");
		String entryday = request.getParameter("entryDay");
		String qq = request.getParameter("qq");
		
		String basicSalaryStr = request.getParameter("basicSalary");
		String classfeesStr = request.getParameter("classfees");
		String majorCourseIdStr = request.getParameter("majorCourseId");
		
		Float basicSalary = StringUtils.isNotBlank(basicSalaryStr) ? Float.parseFloat(basicSalaryStr) : null;
		Float classfees = StringUtils.isNotBlank(classfeesStr) ? Float.parseFloat(classfeesStr) : null;
		Integer majorCourseId = StringUtils.isNotBlank(majorCourseIdStr) ? Integer.parseInt(majorCourseIdStr) : null;
		
		Integer id = this.service.insert(code, name, gender, phone, wechat, photo, birthday, entryday, basicSalary, classfees, majorCourseId, qq);
		if (id == null) {
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"已存在工号为【" + code + "】的教师，不能添加！\"}");
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
	
	
	private void getTeacherCourseGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String classesIdStr = request.getParameter("classesId");
		String courseName = request.getParameter("courseName");
		String teacherCode = request.getParameter("teacherCode");
		String teacherName = request.getParameter("teacherName");
		
		Integer classesId = StringUtils.isNotBlank(classesIdStr) ? Integer.parseInt(classesIdStr) : null;
		List<JSONObject> result = service.getTeacherCourseByMultiConds(classesId, courseName, teacherCode, teacherName);
		//返回数据
		response.getWriter().write(JSONArray.fromObject(result).toString());
	}
	
	
	/**
	 * 转到个人信息页，加载个人信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Teacher teacher = service.getByCode(user.getAccount());
		request.getSession().setAttribute("userDetail", teacher);
		request.getRequestDispatcher("/WEB-INF/view/teacher/teacherPersonal.jsp").forward(request, response);
	}
	

	public void teacherList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取分页参数
		/*
		 * int page = Integer.parseInt(request.getParameter("page")); int rows =
		 * Integer.parseInt(request.getParameter("rows"));
		 * 
		 * //获取数据 String result = service.getTeacherList(new Page(page, rows)); //返回数据
		 * response.getWriter().write(result);
		 */
	}
	
}

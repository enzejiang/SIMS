package com.xingtu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xingtu.service.IGradeService;
import com.xingtu.service.impl.GradeServiceImpl;

import net.sf.json.JSONArray;


/**
 * 项目名称：ssms
 * 类名称：GradeServlet
 * <br>
 * 包路径：com.xingtu.servlet
 * <br>
 * 类描述：年级Servlet
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月22日 下午11:57:44
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月22日 下午11:57:44
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private IGradeService service = new GradeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		if ("toGradeListView".equalsIgnoreCase(method)) { // 到教师列表页
			request.getRequestDispatcher("/WEB-INF/view/other/gradeList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的方法
		String method = request.getParameter("method");
		
		if ("getGrid".equalsIgnoreCase(method)) { // 获取所有年级
			getGrid(request, response);
		} else if ("insert".equalsIgnoreCase(method)) { // 添加年级
			insert(request, response);
		} else if ("delete".equalsIgnoreCase(method)) { // 删除年级
			delete(request, response);
		} else if ("update".equalsIgnoreCase(method)) { // 修改年级
			update(request, response);
		}
		
	}
	
	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Object> result = this.service.getGrades();
		//JSONArray.fromObject(result).toString();
		//返回数据
        response.getWriter().write(JSONArray.fromObject(result).toString());
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String classesId = request.getParameterValues("classesId");
		String name = request.getParameter("name");
		String createTime = request.getParameter("createTime").substring(0, 7);
		
		//Integer classesId = this.service.insert(name, createTime);
		this.service.insert(name, createTime);
        response.getWriter().write("true");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer gradeId = Integer.parseInt(request.getParameter("gradeId"));
		try {
			this.service.delete(gradeId);
			response.getWriter().write("true");
		} catch (Exception e) {
			response.getWriter().write("false");
			e.printStackTrace();
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer gradeId = Integer.parseInt(request.getParameter("gradeId"));
		String name = request.getParameter("name");
		String createTime = request.getParameter("createTime");
		
		this.service.update(gradeId, name, createTime);
        response.getWriter().write("true");
	}

}

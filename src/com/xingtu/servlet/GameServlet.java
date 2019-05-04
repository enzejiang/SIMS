package com.xingtu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.xingtu.service.impl.GameServiceImpl;
import com.xingtu.service.IGameService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GameServlet
 */

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IGameService service = new GameServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if ("toGameListView".equalsIgnoreCase(method)) { //转发到学生列表页
			request.getRequestDispatcher("/WEB-INF/view/other/gameList.jsp").forward(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		String sheng = request.getParameter("s1_add");
		String shi = request.getParameter("s2_add");
		String xian = request.getParameter("s3_add");
		String place = sheng +"_" + shi + "_"+xian;
		String date = request.getParameter("date");
		String result = request.getParameter("result");
		String content = request.getParameter("content");
		String addr = request.getParameter("addr");
		
		Integer id = this.service.insert(name, place, date, content, result, addr);
		if (id == null) {
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"比赛已经存在，不能添加！\"}");
		} else {
			response.getWriter().write("{\"status\":\"true\", \"msg\":\"添加成功！\"}");
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids[]");
		try {
			for (Integer i=0; i<ids.length; i++) {
				this.service.delete(Integer.parseInt(ids[i]));
				System.out.println("GameServerlet delete id " + ids[i]);
			}
			
			response.getWriter().write("true");
		} catch (Exception e) {
			response.getWriter().write("false");
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int gameId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String place = request.getParameter("place");
		String date = request.getParameter("date");
		String result = request.getParameter("result");
		String content = request.getParameter("content");
		String addr = request.getParameter("addr");
		try {
			 this.service.update(name, place, date, content, result, addr, gameId);
			 response.getWriter().write("{\"status\":\"true\", \"msg\":\"修改成功！\"}");
		} catch (Exception e) {
			response.getWriter().write("{\"status\":\"false\", \"msg\":\"修改失败！\"}");
			e.printStackTrace();
		}
	}

	private void getGrid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Object> result = service.getGames();
		JSONArray jsonArr = new JSONArray();
		jsonArr = JSONArray.fromObject(result);
		//System.out.println("GameServerlet GetGrid " + result.size());
		//System.out.println("GameServerlet Json " + jsonArr.toString());
		//返回数据
        response.getWriter().write(jsonArr.toString());
	}


}

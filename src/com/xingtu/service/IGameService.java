package com.xingtu.service;

import java.util.List;


import com.xingtu.bean.Game;

public interface IGameService {
	/**
	 * 名称：getGames
	 * <br>
	 * 描述：获取编辑列表
	 * <br>
	 * 创建人：Enze
	 * 创建时间：2019年5月03日 21:30:42
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @return
	 */
	 List<Object> getGames();
	
	/**
	 * 名称：insert
	 * <br>
	 * 描述：新增比赛信息
	 * <br>
	 * 创建人：Enze
	 * 创建时间：2019年5月03日 21:30:42
	 * <br>
	 * 返回值类型：Integer
	 * @throws TODO
	 * @param name
	 * @param place
	 * @param date
	 * @param content
	 * @param result
	 * @return
	 */
	Integer insert(String name, String place, String date, String content, String result, String addr);
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：删除比赛信息
	 * <br>
	 * 创建人：Enze
	 * 创建时间：2019年5月03日 21:30:42
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 名称：update
	 * <br>
	 * 描述：更新比赛信息
	 * <br>
	 * 创建人：Enze
	 * 创建时间：2019年5月03日 21:30:42
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param name
	 * @param gradeId
	 * @param teacherId
	 * @param courseId
	 * @param id
	 */
	
	void update(String name, String place, String date, String content, String result, String addr,Integer id);
}

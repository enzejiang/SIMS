package com.xingtu.service;

import java.util.List;

import com.xingtu.bean.Course;

/**
 * 项目名称：ssms
 * 类名称：ICourseService
 * <br>
 * 包路径：com.xingtu.service
 * <br>
 * 类描述：课程服务接口
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月24日 上午2:35:54
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月24日 上午2:35:54
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public interface ICourseService {
	/**
	 * 名称：getCourses
	 * <br>
	 * 描述：获取所有课程信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午2:37:00
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @return
	 */
	List<Object> getCourses();
	
	/**
	 * 名称：insert
	 * <br>
	 * 描述：新增课程信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午2:37:39
	 * <br>
	 * 返回值类型：Integer
	 * @throws TODO
	 * @param name
	 * @return
	 */
	Integer insert(String name);
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：删除课程信息，已开设班级的课程不能删除
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午2:50:41
	 * <br>
	 * 返回值类型：boolean
	 * @throws TODO
	 * @param id
	 * @return
	 */
	boolean delete(Integer id);
	
	/**
	 * 名称：updateName
	 * <br>
	 * 描述：更新课程名称
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午2:39:17
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param name
	 * @param id
	 */
	void updateName(String name, Integer id);
	
	/**
	 * 名称：getById
	 * <br>
	 * 描述：根据ID查找课程信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午2:40:13
	 * <br>
	 * 返回值类型：Course
	 * @throws TODO
	 * @param id
	 * @return
	 */
	Course getById(Integer id);

}

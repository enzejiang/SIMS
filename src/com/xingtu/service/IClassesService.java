package com.xingtu.service;

import java.util.List;

import com.xingtu.bean.Classes;

/**
 * 项目名称：ssms
 * 类名称：IClassesService
 * <br>
 * 包路径：com.xingtu.service
 * <br>
 * 类描述：班级服务
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月23日 上午12:51:37
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月23日 上午12:51:37
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public interface IClassesService {
	/**
	 * 名称：getClasses
	 * <br>
	 * 描述：获取编辑列表
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午1:01:42
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @return
	 */
	List<Object> getClasses();
	
	/**
	 * 名称：insert
	 * <br>
	 * 描述：新增班级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午1:01:58
	 * <br>
	 * 返回值类型：Integer
	 * @throws TODO
	 * @param name
	 * @param gradeId
	 * @param teacherId
	 * @param courseId
	 * @return
	 */
	Integer insert(String name, Integer gradeId, Integer teacherId, Integer courseId);
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：删除班级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午1:02:11
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 名称：updateName
	 * <br>
	 * 描述：更新班级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午1:02:25
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param name
	 * @param gradeId
	 * @param teacherId
	 * @param courseId
	 * @param id
	 */
	void updateName(String name, Integer gradeId, Integer teacherId, Integer courseId, Integer id);
	
	void update(String name, Integer gradeId, Integer teacherId, Integer courseId, String startTime, String endTime, Integer id);
	/**
	 * 名称：getByCourseId
	 * <br>
	 * 描述：根据课程ID查找班级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午2:54:11
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @param courseId
	 * @return
	 */
	List<Object> getByCourseId(Integer courseId);
	
	/**
	 * 名称：getByTeacherId
	 * <br>
	 * 描述：根据教师ID查找班级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 下午10:54:22
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @param teacherId
	 * @return
	 */
	List<Object> getByTeacherId(Integer teacherId);
	
	/**
	 * 名称：getById
	 * <br>
	 * 描述：根据ID获取班级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:16:19
	 * <br>
	 * 返回值类型：Classes
	 * @throws TODO
	 * @param id
	 * @return
	 */
	Classes getById(Integer id);

}

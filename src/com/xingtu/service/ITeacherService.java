package com.xingtu.service;

import java.util.List;

import com.xingtu.bean.Teacher;

import net.sf.json.JSONObject;

/**
 * 项目名称：ssms
 * 类名称：ITeacherService
 * <br>
 * 包路径：com.xingtu.service
 * <br>
 * 类描述：教师信息服务接口
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月24日 下午10:32:07
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月24日 下午10:32:07
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public interface ITeacherService {
	/**
	 * 名称：getTeachers
	 * <br>
	 * 描述：获取所有教师信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 下午10:32:54
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @return
	 */
	List<Object> getTeachers();
	
	/**
	 * 名称：insert
	 * <br>
	 * 描述：新增教师信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 下午10:43:41
	 * <br>
	 * 返回值类型：Integer
	 * @throws TODO
	 * @param code
	 * @param name
	 * @param gender
	 * @param phone
	 * @param wechat
	 * @param photo
	 * @param birthday
	 * @param entryday
	 * @param basicSalary
	 * @param classfees
	 * @param majorCourseId
	 * @param qq
	 * @return
	 */
	Integer insert(String code, String name, String gender, String phone, String wechat, String photo, 
			String birthday, String entryday, Float basicSalary, Float classfees, Integer majorCourseId, String qq);
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：根据ID删除教师信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 下午10:44:19
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param id
	 */
	boolean delete(Integer id);
	
	/**
	 * 名称：getById
	 * <br>
	 * 描述：根据ID查询教师信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 下午10:46:23
	 * <br>
	 * 返回值类型：Teacher
	 * @throws TODO
	 * @param id
	 * @return
	 */
	Teacher getById(Integer id);
	
	/**
	 * 名称：getByCode
	 * <br>
	 * 描述：根据code查询教师信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月25日 上午12:21:13
	 * <br>
	 * 返回值类型：Teacher
	 * @throws TODO
	 * @param code
	 * @return
	 */
	Teacher getByCode(String code);
	
	/**
	 * 名称：getTeacherCourseByMultiConds
	 * <br>
	 * 描述：按给定条件查询教师所教的课程信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年5月12日 下午11:29:43
	 * <br>
	 * 返回值类型：List<JSONObject>
	 * @throws TODO
	 * @param classesId
	 * @param courseName
	 * @param teacherCode
	 * @param teacherName
	 * @return
	 */
	List<JSONObject> getTeacherCourseByMultiConds(Integer classesId, String courseName, String teacherCode, String teacherName);

}

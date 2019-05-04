package com.xingtu.service;

import java.util.List;

import com.xingtu.bean.Student;

public interface IStudentService {
	/**
	 * 名称：insert
	 * <br>
	 * 描述：TODO
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月28日 下午11:22:56
	 * <br>
	 * 返回值类型：Integer
	 * @throws TODO
	 * @param code
	 * @param name
	 * @param gender
	 * @param parentPhone
	 * @param parentWechat
	 * @param photo
	 * @param birthday
	 * @param paymentday
	 * @param graduationTime
	 * @return
	 */
	Integer insert(String code, String name, String gender, String parentPhone, String parentWechat, 
			String photo, String birthday, String paymentday, String graduationTime);
	
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：根据学生ID删除学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月28日 下午11:23:54
	 * <br>
	 * 返回值类型：boolean
	 * @throws TODO
	 * @param id
	 * @return
	 */
	boolean delete(Integer id);
	
	/**
	 * 名称：update
	 * <br>
	 * 描述：根据学生ID修改学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月28日 下午11:26:44
	 * <br>
	 * 返回值类型：Integer
	 * @throws TODO
	 * @param id
	 * @param code
	 * @param name
	 * @param gender
	 * @param parentPhone
	 * @param parentWechat
	 * @param photo
	 * @param birthday
	 * @param paymentday
	 * @param graduationTime
	 * @return
	 */
	Integer update(Integer id, String code, String name, String gender, String parentPhone, String parentWechat, 
			String photo, String birthday, String paymentday, String graduationTime);
	
	
	/**
	 * 名称：getStudents
	 * <br>
	 * 描述：查询所有学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月28日 下午11:20:16
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @return
	 */
	List<Object> getStudents();
	
	/**
	 * 名称：getStudentsByClassesId
	 * <br>
	 * 描述：根据班级ID查询整个班级的学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月28日 下午11:28:14
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @param classesId
	 * @return
	 */
	List<Object> getStudentsByClassesId(Integer classesId);
	
	/**
	 * 名称：getStudentsByCourseId
	 * <br>
	 * 描述：根据课程ID查询该课程下所有学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月28日 下午11:29:29
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @param courseId
	 * @return
	 */
	List<Object> getStudentsByCourseId(Integer courseId);
	
	/**
	 * 名称：getById
	 * <br>
	 * 描述：根据ID查找学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:21:20
	 * <br>
	 * 返回值类型：Student
	 * @throws TODO
	 * @param id
	 * @return
	 */
	Student getById(Integer id);
	
	/**
	 * 名称：getByCode
	 * <br>
	 * 描述：根据学号Code查找学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年5月2日 上午12:59:21
	 * <br>
	 * 返回值类型：Student
	 * @throws TODO
	 * @param code
	 * @return
	 */
	Student getByCode(String code);
	
}

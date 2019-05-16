package com.xingtu.service;

import java.util.List;

import com.sun.xml.internal.bind.v2.TODO;

import net.sf.json.JSONObject;

/**
 * 项目名称：ssms
 * 类名称：ISchoolTimetableService
 * <br>
 * 包路径：com.xingtu.service
 * <br>
 * 类描述：课程表服务接口
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月29日 上午12:50:46
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月29日 上午12:50:46
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public interface ISchoolTimetableService {
	/**
	 * 名称：insert
	 * <br>
	 * 描述：根据班级ID和学生ID集合插入多条课程表信息（多个学生报名）
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:29:09
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param classesId
	 * @param studentIds
	 * @param startDate
	 * @param endDate
	 */
	void insert(Integer classesId, List<Integer> studentIds, String startDate, String endDate);
	
	/**
	 * 名称：insert
	 * <br>
	 * 描述：单个学生报名
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:29:21
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param classesId
	 * @param studentId
	 * @param startDate
	 * @param endDate
	 */
	void insert(Integer classesId, Integer studentId, String startDate, String endDate);
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：根据课程表ID删除课程表信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:05:20
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 名称：deleteAllByStudentId
	 * <br>
	 * 描述：删除一个学生的所有课程
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:04:25
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param studentId
	 */
	void deleteAllByStudentId(Integer studentId);
	
	/**
	 * 名称：updateEndDate
	 * <br>
	 * 描述：更新单个学生课程到期日期，学生给课程续费
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月29日 上午1:07:44
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param id
	 * @param newEndDate
	 */
	void updateEndDate(Integer id, String newEndDate);
	
	/**
	 * 名称：updateEndDate
	 * <br>
	 * 描述：更新多个学生课程到期日期，学生给课程续费
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年5月7日 上午2:05:41
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param ids
	 * @param newEndDate
	 */
	void updateEndDate(String ids, String newEndDate);
	
	/**
	 * 名称：getCourseRenewalsByMultiConds
	 * <br>
	 * 描述：按给定条件查询学生课程表信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年5月7日 上午1:00:40
	 * <br>
	 * 返回值类型：List<Object>
	 * @throws TODO
	 * @param classesId
	 * @param courseName
	 * @param studentName
	 * @return
	 */
	List<Object> getCourseRenewalsByMultiConds(Integer classesId, String courseName, String studentName);
	
	/**
	 * 名称：getClassesStudentsByMultiConds
	 * <br>
	 * 描述：按给定条件查询班级学生信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年5月16日 下午10:48:13
	 * <br>
	 * 返回值类型：List<JSONObject>
	 * @throws TODO
	 * @param classesId
	 * @param courseName
	 * @param teacherName
	 * @return
	 */
	List<JSONObject> getClassesStudentsByMultiConds(Integer classesId, String courseName, String teacherName);
}

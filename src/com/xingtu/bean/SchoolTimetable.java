package com.xingtu.bean;

import java.io.Serializable;

/**
 * 项目名称：ssms
 * 类名称：SchoolTimetable
 * <br>
 * 包路径：com.xingtu.bean
 * <br>
 * 类描述：课程表
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月29日 上午12:36:21
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月29日 上午12:36:21
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class SchoolTimetable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3786016115241678256L;
	
	private Integer id;
	
	private Integer classesId; // 班级ID
	
	private String classesName; //班级名称
	
	private Integer courseId; // 课程ID
	
	private String courseName; //课程名称
	
	private Integer studentId; //学生ID
	
	private String studentCode; //学生编号，学生学号
	
	private String studentName; //学生姓名
	
	private String studentGender; //学生性别
	
	private String startDate; //课程开始日期
	
	private String endDate; //课程结束日期，课程到期日期

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassesId() {
		return classesId;
	}

	public void setClassesId(Integer classesId) {
		this.classesId = classesId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}

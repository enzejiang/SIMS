package com.xingtu.bean;

import java.io.Serializable;

public class Classes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6111954729783271325L;
	
	private Integer id; // 主键ID
	
	private String name; // 班级名称
	
	private Integer gradeId; // 年级ID
	
	private Integer teacherId; // 教师ID，教工ID
	
	private Integer courseId; // 课程ID
	
	private String  startTime;//开课时间
	
	private String  endTime; //结课时间
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public void setStartTime(String stTime) {
		this.startTime = stTime;
	}
	
	public String getStartTime()
	{
		return this.startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getendTime()
	{
		return this.endTime;
	}
}

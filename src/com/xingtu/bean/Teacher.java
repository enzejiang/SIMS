package com.xingtu.bean;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4849980384694269465L;
	
	private Integer id;
	
	private String code; // 教师编号，教工号
	
	private String name; // 教师姓名
	
	private String gender; // 性别：男或女
	
	private String phone; // 联系方式，电话号码
	
	private String wechat; // 微信号
	
	private String photo; // 照片、照片地址
	
	private String birthDay; // 生日
	
	private String entryDay; // 入职时间
	
	private Float basicSalary; // 基本工资
	
	private Float classfees; // 课堂费用，课时费用
	
	private Integer majorCourseId; // 主教课程
	
	private String qq; // QQ号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEntryDay() {
		return entryDay;
	}

	public void setEntryDay(String entryDay) {
		this.entryDay = entryDay;
	}

	public Float getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Float basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Float getClassfees() {
		return classfees;
	}

	public void setClassfees(Float classfees) {
		this.classfees = classfees;
	}

	public Integer getMajorCourseId() {
		return majorCourseId;
	}

	public void setMajorCourseId(Integer majorCourseId) {
		this.majorCourseId = majorCourseId;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}

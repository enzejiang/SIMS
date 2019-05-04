package com.xingtu.bean;

import java.io.Serializable;

/**
 * 项目名称：ssms
 * 类名称：Student
 * <br>
 * 包路径：com.xingtu.bean
 * <br>
 * 类描述：学生基本信息
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月26日 上午1:26:30
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月26日 上午1:26:30
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7613070553944540230L;
	
	private Integer id; // 学生ID，主键ID
	
	private String code; // 学生编号，学号
	
	private String name; // 学生姓名
	
	private String gender; // 性别
	
	private String parentPhone; // 父母电话、监护人电话
	
	private String parentWechat; // 父母微信、监护人微信
	
	private String photo; // 照片
	
	private String birthday; // 生日
	
	private String rigsttime;//报名时间
	
	private String paymentday; // 付款时间，缴费时间
	
	private String graduationTime; // 毕业时间

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

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public String getParentWechat() {
		return parentWechat;
	}

	public void setParentWechat(String parentWechat) {
		this.parentWechat = parentWechat;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRigstTime()
	{
		return rigsttime;
	}
	
	public void setRigstTime(String rigstTime)
	{
		this.rigsttime = rigstTime;
	}
	
	public String getPaymentday() {
		return paymentday;
	}

	public void setPaymentday(String paymentday) {
		this.paymentday = paymentday;
	}

	public String getGraduationTime() {
		return graduationTime;
	}

	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}

}

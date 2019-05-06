package com.xingtu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.xingtu.bean.Student;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
	private IBaseDao dao = new BaseDaoImpl();

	@Override
	public Integer insert(String code, String name, String gender, String parentPhone, String parentWechat,
			String photo, String birthday, String paymentday, String graduationTime) {
		Student obj = this.getByCode(code);
		
		if (obj != null) {
			return null;
		}
		
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String rigTime = sdf.format(date);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");//自动生成学号
        String newCode=sdf2.format(date);
		StringBuffer sql = new StringBuffer("");
		sql.append("INSERT INTO S_STUDENT(");
		sql.append("CODE, NAME, GENDER, PARENTPHONE, PARENTWECHAT, PHOTO, BIRTHDAY, RIGSTTIME,PAYMENTDAY, GRADUATIONTIME");
		sql.append(") VALUE(");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?,?");
		sql.append(")");
		Integer id = this.dao.insertReturnKeys(sql.toString(), new Object[]{code, name, gender, parentPhone, parentWechat, photo, birthday, rigTime, paymentday, graduationTime});

		return id;
	}

	@Override
	public boolean delete(Integer id) {
		this.dao.delete("DELETE FROM S_STUDENT WHERE ID = ?", new Object[]{id});
		return true;
	}

	@Override
	public Integer update(Integer id, String code, String name, String gender, String parentPhone, String parentWechat,
			String photo, String birthday, String paymentday, String graduationTime) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("");
		sql.append("UPDATE  S_STUDENT SET ");
		sql.append("CODE=?, NAME=?, GENDER=?, PARENTPHONE=?, PARENTWECHAT=?, PHOTO=?,BIRTHDAY=?, PAYMENTDAY=?, GRADUATIONTIME=?");
		sql.append("WHERE ID=?");
		List<Object> params = new LinkedList<>();
		params.add(code);
		params.add(name);
		params.add(gender);
		params.add(parentPhone);
		params.add(parentWechat);
		params.add(photo);
		params.add(birthday);
		params.add(paymentday);
		params.add(graduationTime);
		params.add(id);
		this.dao.update(sql.toString(),params);
		return 0;
	}

	@Override
	public List<Object> getStudents() {
		List<Object> list = this.dao.getList(Student.class, "SELECT * FROM S_STUDENT ORDER BY NAME");
		
		return list;
	}

	@Override
	public List<Object> getStudentsByClassesId(Integer classesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getStudentsByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getById(Integer id) {
		return (Student)this.dao.getObject(Student.class, "SELECT * FROM S_STUDENT WHERE ID = ?", new Object[]{id});
	}

	@Override
	public Student getByCode(String code) {
		return (Student)this.dao.getObject(Student.class, "SELECT * FROM S_STUDENT WHERE CODE = ?", new Object[]{code});
	}

	@Override
	public List<Object> getStudentsNotINClasses(Integer classesId) {
		List<Object> list = this.dao.getList(Student.class, "SELECT * FROM S_STUDENT A WHERE A.ID NOT IN ("
				+ "SELECT B.STUDENTID FROM S_SCHOOL_TIMETABLE B WHERE B.CLASSESID = ?) ORDER BY A.`NAME`", new Object[]{classesId});
		
		return list;
	}

}

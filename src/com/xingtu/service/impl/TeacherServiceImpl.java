package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.Teacher;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IClassesService;
import com.xingtu.service.ITeacherService;

public class TeacherServiceImpl implements ITeacherService {
	private IBaseDao dao = new BaseDaoImpl();
	private IClassesService classesService = new ClassesServiceImpl();

	@Override
	public List<Object> getTeachers() {
		List<Object> list = this.dao.getList(Teacher.class, "SELECT * FROM T_TEACHER ORDER BY NAME");
		
		return list;
	}

	@Override
	public Integer insert(String code, String name, String gender, String phone, String wechat, String photo,
			String birthday, String entryday, Float basicSalary, Float classfees, Integer majorCourseId, String qq) {
		Teacher obj = this.getByCode(code);
		
		if (obj != null) {
			//throw new RuntimeException("已存在编号为：【" + code + "】的教师，无法保存！");
			return null;
		}
		
		Integer id = this.dao.insertReturnKeys("INSERT INTO T_TEACHER(CODE, NAME, GENDER, PHONE, WECHAT, PHOTO, BIRTHDAY, ENTRYDAY, BASICSALARY, CLASSFEES, MAJORCOURSEID, QQ) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				new Object[]{code, name, gender, phone, wechat, photo, birthday, entryday, basicSalary, classfees, majorCourseId, qq});
		return id;
	}

	@Override
	public boolean delete(Integer id) {
		List<Object> classes = this.classesService.getByTeacherId(id);
		if (classes != null && classes.size() > 0) {
			throw new RuntimeException("该教师已带班级，不能删除！");
		} else {
			this.dao.delete("DELETE FROM T_TEACHER WHERE ID = ?", new Object[]{id});
			return true;
		}

	}

	@Override
	public Teacher getById(Integer id) {
		return (Teacher)this.dao.getObject(Teacher.class, "SELECT * FROM T_TEACHER WHERE ID = ?", new Object[]{id});
	}

	@Override
	public Teacher getByCode(String code) {
		return (Teacher)this.dao.getObject(Teacher.class, "SELECT * FROM T_TEACHER WHERE CODE = ?", new Object[]{code});
	}

}

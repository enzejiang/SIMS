package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.Course;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IClassesService;
import com.xingtu.service.ICourseService;

public class CourseServiceImpl implements ICourseService {
	private IBaseDao dao = new BaseDaoImpl();
	private IClassesService classesService = new ClassesServiceImpl();

	@Override
	public List<Object> getCourses() {
		List<Object> list = this.dao.getList(Course.class, "SELECT * FROM C_COURSE");
		
		return list;
	}

	@Override
	public Integer insert(String name) {
		Integer id = this.dao.insertReturnKeys("INSERT INTO C_COURSE(NAME) VALUE(?)", new Object[]{name});
		
		return id;
	}

	@Override
	public boolean delete(Integer id) {
		List<Object> classes = this.classesService.getByCourseId(id);
		if (classes != null && classes.size() > 0) {
			return false;
		} else {
			this.dao.delete("DELETE FROM C_COURSE WHERE ID = ?", new Object[]{id});
			return true;
		}
	}

	@Override
	public void updateName(String name, Integer id) {
		this.dao.update("UPDATE C_COURSE SET NAME=? WHERE ID=?", new Object[]{name, id});
	}

	@Override
	public Course getById(Integer id) {
		return (Course)this.dao.getObject(Course.class, "SELECT * FROM C_COURSE WHERE ID = ?", new Object[]{id});
	}

}

package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.Classes;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IClassesService;

public class ClassesServiceImpl implements IClassesService {
	private IBaseDao dao = new BaseDaoImpl();

	@Override
	public List<Object> getClasses() {
		//获取数据
		List<Object> list = this.dao.getList(Classes.class, "SELECT * FROM C_CLASSES");
		
		return list;
	}

	@Override
	public Integer insert(String name, Integer gradeId, Integer teacherId, Integer courseId) {
		Integer id = this.dao.insertReturnKeys("INSERT INTO C_CLASSES(NAME, GRADEID, TEACHERID, COURSEID) VALUE(?, ?, ?, ?)", new Object[]{name, gradeId, teacherId, courseId});
		
		return id;
	}

	@Override
	public void delete(Integer id) {
		this.dao.delete("DELETE FROM C_CLASSES WHERE ID = ?", new Object[]{id});
	}

	@Override
	public void updateName(String name, Integer gradeId, Integer teacherId, Integer courseId, Integer id) {
		this.dao.update("UPDATE G_GRADE SET NAME=?, GRADEID=?, TEACHERID=?, COURSEID=? WHERE ID=?", new Object[]{name, gradeId, teacherId, courseId, id});
	}

	public void update(String name, Integer gradeId, Integer teacherId, Integer courseId, String startTime, String endTime, Integer id)
	{
		this.dao.update("UPDATE G_GRADE SET NAME=?, GRADEID=?, TEACHERID=?, COURSEID=? , STARTTIME=?, ENDTIME=? WHERE ID=?", new Object[]{name, gradeId, teacherId, courseId, startTime, endTime, id});
	}
	
	@Override
	public List<Object> getByCourseId(Integer courseId) {
		List<Object> list = this.dao.getList(Classes.class, "SELECT * FROM C_CLASSES WHERE COURSEID = ?", new Object[]{courseId});
		return list;
	}

	@Override
	public List<Object> getByTeacherId(Integer teacherId) {
		List<Object> list = this.dao.getList(Classes.class, "SELECT * FROM C_CLASSES WHERE TEACHERID = ?", new Object[]{teacherId});
		return list;
	}

	@Override
	public Classes getById(Integer id) {
		return (Classes)this.dao.getObject(Classes.class, "SELECT * FROM C_CLASSES WHERE ID = ?", new Object[]{id});
	}

}

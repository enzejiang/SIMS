package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.Grade;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IGradeService;

public class GradeServiceImpl implements IGradeService {
	private IBaseDao dao = new BaseDaoImpl();

	@Override
	public List<Object> getGrades() {
		//获取数据
		List<Object> list = this.dao.getList(Grade.class, "SELECT * FROM G_GRADE ORDER BY ID DESC");
		
		return list;
	}

	@Override
	public Integer insert(String name, String createTime) {
		Integer id = this.dao.insertReturnKeys("INSERT INTO G_GRADE(NAME, CREATETIME) VALUE(?, ?)", new Object[]{name, createTime});
		
		return id;
	}

	@Override
	public void delete(Integer id) {
		this.dao.delete("DELETE FROM G_GRADE WHERE ID = ?", new Object[]{id});
	}

	@Override
	public void update(Integer id, String name, String createTime) {
		this.dao.update("UPDATE G_GRADE SET NAME=?, CREATETIME=? WHERE ID=?", new Object[]{name, createTime, id});
	}

	@Override
	public Grade getById(Integer id) {
		return (Grade)this.dao.getObject(Grade.class, "SELECT * FROM G_GRADE WHERE ID = ?", new Object[]{id});
	}

}

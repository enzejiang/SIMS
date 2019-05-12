package com.xingtu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xingtu.bean.Teacher;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IClassesService;
import com.xingtu.service.ITeacherService;

import net.sf.json.JSONObject;

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

	@Override
	public List<JSONObject> getTeacherCourseByMultiConds(Integer classesId, String courseName, String teacherCode, String teacherName) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT");
		sql.append(" C.`NAME` C_NAME, C.STARTTIME, C.ENDTIME, G.`NAME` G_NAME, D.`NAME` D_NAME, T.* ");
		sql.append("FROM");
		sql.append(" C_CLASSES C ");
		sql.append("LEFT JOIN T_TEACHER T ON T.ID = C.TEACHERID ");
		sql.append("LEFT JOIN G_GRADE G ON G.ID = C.GRADEID ");
		sql.append("LEFT JOIN C_COURSE D ON D.ID = C.COURSEID ");
		sql.append("WHERE 1=1 ");
		
		List<Object> param = new ArrayList<Object>();
		
		if (classesId != null) {
			sql.append("AND c.id = ? ");
			param.add(classesId);
		}
		
		if (courseName != null && !"".equals(courseName)) {
			sql.append("AND D.`NAME` LIKE ? ");
			param.add("%" + courseName + "%");
		}
		
		if (teacherCode != null && !"".equals(teacherCode)) {
			sql.append("AND T.`CODE` LIKE ? ");
			param.add("%" + teacherCode + "%");
		}
		
		if (teacherName != null && !"".equals(teacherName)) {
			sql.append("AND T.`NAME` LIKE ? ");
			param.add("%" + teacherName + "%");
		}
		
		sql.append("ORDER BY ID, D_NAME");
		
		System.out.println("Method[getTeacherCourseByMultiConds]--SQL：" + sql.toString());
		return this.dao.getList(sql.toString(), param);
	}

}

package com.xingtu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xingtu.bean.Classes;
import com.xingtu.bean.Course;
import com.xingtu.bean.SchoolTimetable;
import com.xingtu.bean.Student;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IClassesService;
import com.xingtu.service.ICourseService;
import com.xingtu.service.ISchoolTimetableService;
import com.xingtu.service.IStudentService;

public class SchoolTimetableServiceImpl implements ISchoolTimetableService {
	private IBaseDao dao = new BaseDaoImpl();
	private IStudentService studentService = new StudentServiceImpl();
	private IClassesService classesService = new ClassesServiceImpl();
	private ICourseService courseService = new CourseServiceImpl();

	@Override
	public void insert(Integer classesId, List<Integer> studentIds, String startDate, String endDate) {
		if (classesId != null && studentIds != null && studentIds.size() > 0) {
			Classes classes = this.classesService.getById(classesId);
			if (classes != null) {
				Course course = this.courseService.getById(classes.getCourseId());
				for (Integer studentId : studentIds) {
					Student student = this.studentService.getById(studentId);
					
					save(classes, course, student, startDate, endDate);
				}
			}
		}
	}
	
	@Override
	public void insert(Integer classesId, Integer studentId, String startDate, String endDate) {
		if (classesId != null && studentId != null) {
			Classes classes = this.classesService.getById(classesId);
			Course course = this.courseService.getById(classes.getCourseId());
			Student student = this.studentService.getById(studentId);
			save(classes, course, student, startDate, endDate);
		}

	}

	@Override
	public void delete(Integer id) {
		this.dao.delete("DELETE FROM S_SCHOOL_TIMETABLE WHERE ID = ?", new Object[]{id});
	}

	@Override
	public void deleteAllByStudentId(Integer studentId) {
		this.dao.delete("DELETE FROM S_SCHOOL_TIMETABLE WHERE STUDENTID = ?", new Object[]{studentId});
	}

	@Override
	public void updateEndDate(Integer id, String newEndDate) {
		String sql = "UPDATE S_SCHOOL_TIMETABLE SET ENDDATE=? WHERE ID=?";
		this.dao.update(sql, new Object[]{newEndDate, id});
		System.out.println("Method[updateEndDate]--SQL：" + sql);
	}
	
	
	@Override
	public void updateEndDate(String ids, String newEndDate) {
		String sql = "UPDATE S_SCHOOL_TIMETABLE SET ENDDATE=? WHERE ID IN (" + ids + ")";
		this.dao.update(sql, new Object[]{newEndDate});
		System.out.println("Method[updateEndDate]--SQL：" + sql);
	}
	
	
	private void save(Classes classes, Course course, Student student, String startDate, String endDate) {
		if (classes != null && course != null && student != null) {
			StringBuffer sql = new StringBuffer("");
			sql.append("INSERT INTO S_SCHOOL_TIMETABLE(");
			sql.append("CLASSESID, CLASSESNAME, COURSEID, COURSENAME, STUDENTID, STUDENTCODE, STUDENTNAME, STUDENTGENDER, STARTDATE, ENDDATE");
			sql.append(") VALUE(");
			sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			sql.append(")");
			this.dao.insertReturnKeys(sql.toString(), new Object[]{classes.getId(), classes.getName(), course.getId(), course.getName(), 
					student.getId(), student.getCode(), student.getName(), student.getGender(), startDate, endDate});
			System.out.println("SQL：" + sql.toString());
		}
	}

	@Override
	public List<Object> getCourseRenewalsByMultiConds(Integer classesId, String courseName, String studentName) {
		StringBuffer sql = new StringBuffer("SELECT * FROM S_SCHOOL_TIMETABLE S WHERE 1=1 ");
		List<Object> param = new ArrayList<Object>();
		
		if (classesId != null) {
			sql.append("AND	S.CLASSESID = ? ");
			param.add(classesId);
		}
		
		if (courseName != null && !"".equals(courseName)) {
			sql.append("AND S.COURSENAME LIKE ? ");
			param.add("%" + courseName + "%");
		}
		
		if (studentName != null && !"".equals(studentName)) {
			sql.append("AND S.STUDENTNAME LIKE ? ");
			param.add("%" + studentName + "%");
		}
		
		System.out.println("Method[getCourseRenewalsByMultiConds]--SQL：" + sql.toString());
		return this.dao.getList(SchoolTimetable.class, sql.toString(), param);
	}

}

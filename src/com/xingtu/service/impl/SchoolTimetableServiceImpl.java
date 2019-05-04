package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.Classes;
import com.xingtu.bean.Course;
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
		this.dao.delete("DELETE FROM S_SCHOOLTIMETABLE WHERE ID = ?", new Object[]{id});
	}

	@Override
	public void deleteAllByStudentId(Integer studentId) {
		this.dao.delete("DELETE FROM S_SCHOOLTIMETABLE WHERE STUDENTID = ?", new Object[]{studentId});
	}

	@Override
	public void updateEndDate(Integer id, String newEndDate) {
		this.dao.update("UPDATE S_SCHOOLTIMETABLE SET ENDDATE=? WHERE ID=?", new Object[]{newEndDate, id});

	}
	
	
	private void save(Classes classes, Course course, Student student, String startDate, String endDate) {
		if (classes != null && course != null && student != null) {
			StringBuffer sql = new StringBuffer("");
			sql.append("INSERT INTO S_SCHOOLTIMETABLE(");
			sql.append("CLASSESID, CLASSESNAME, COURSEID, COURSENAME, STUDENTID, STUDENTCODE, STUDENTNAME, STUDENTGENDER, STARTDATE, ENDDATE");
			sql.append(") VALUE(");
			sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			sql.append(")");
			this.dao.insertReturnKeys(sql.toString(), new Object[]{classes.getId(), classes.getName(), course.getId(), course.getName(), 
					student.getId(), student.getCode(), student.getName(), student.getGender(), startDate, endDate});
		}
	}

}

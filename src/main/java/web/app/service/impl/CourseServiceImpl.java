package web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.app.entity.Course;
import web.app.repos.CourseRepo;
import web.app.service.ICourseService;

@Component
public class CourseServiceImpl implements ICourseService{

	@Autowired
	CourseRepo courseRepo;
	
	@Override
	public List<Course> getCourses(Integer student_id) {

		List<Course> courses = courseRepo.findAllByStudent_Id(student_id);
		return courses;
	}

	@Override
	public boolean delete(Integer course_id) {
		try {
			courseRepo.deleteById(course_id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cant Delete");
			return false;
		}
		return true;
	}

}

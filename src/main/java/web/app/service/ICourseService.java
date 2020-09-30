package web.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import web.app.dtos.CourseDTO;
import web.app.entity.Course;

@Repository
public interface ICourseService {
	public List<Course> getCourses(Integer student_id);
	public CourseDTO getCourse(Integer id);
	@Transactional
	public boolean delete(Integer course_id);
	public CourseDTO insert(CourseDTO dto);
	public CourseDTO update(CourseDTO dto);

}

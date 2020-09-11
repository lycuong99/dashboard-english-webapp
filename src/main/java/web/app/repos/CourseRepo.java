package web.app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.app.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>{
	public List<Course> findAllByStudent_Id(Integer student_id);
}

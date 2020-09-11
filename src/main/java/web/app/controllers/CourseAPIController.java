package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.app.entity.Course;
import web.app.repos.CourseRepo;
import web.app.service.ICourseService;

@RestController
@RequestMapping("/course-api")
public class CourseAPIController {

	@Autowired
	ICourseService courseSevice;
	
	public ResponseEntity<?> insert(String student_id, Course course)
	{
		return ResponseEntity.ok(null);
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Integer courseId)
	{
		if(courseSevice.delete(courseId))
		{
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}

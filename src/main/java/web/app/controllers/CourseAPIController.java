package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import web.app.dtos.CourseDTO;
import web.app.entity.Course;
import web.app.entity.Student;
import web.app.repos.CourseRepo;
import web.app.service.ICourseService;
import web.app.service.IStudentService;

@RestController
@RequestMapping("/course-api")
public class CourseAPIController {

	@Autowired
	ICourseService courseSevice;

	@Autowired
	IStudentService studentService;
	
	public ResponseEntity<?> insert(String student_id, Course course)
	{
		return ResponseEntity.ok(null);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")Integer courseId)
	{
		if(courseSevice.delete(courseId))
		{
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id")Integer courseId)
	{
		CourseDTO dto = courseSevice.getCourse(courseId);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	@PutMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody CourseDTO dto)
	{
	System.out.println(dto.toString());
		courseSevice.insert(dto);


	return new ResponseEntity<>(dto,HttpStatus.OK);
	}

	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody CourseDTO dto)
	{
		System.out.println(dto.toString());
		courseSevice.update(dto);


		return new ResponseEntity<>(dto,HttpStatus.OK);
	}

}

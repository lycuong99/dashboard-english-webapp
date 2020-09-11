package web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.app.dtos.StudentDTO;
import web.app.entity.Course;
import web.app.service.ICourseService;
import web.app.service.IStudentService;

@Controller
public class CourseController {
	
	@Autowired
	IStudentService studentService;
	
	@Autowired
	ICourseService courseService;
	
	@RequestMapping("/courses")
	public String getStudentInfo(@RequestParam(name = "id", required = true) Integer id, Model model)
	{
		StudentDTO dto = studentService.getStudent(id);
		List<Course> courses= courseService.getCourses(id);
		model.addAttribute("student",dto);
		model.addAttribute("courses",courses);
		return "student-info";
	}
}

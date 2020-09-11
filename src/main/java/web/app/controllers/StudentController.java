package web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.app.dtos.StudentDTO;
import web.app.entity.Course;
import web.app.entity.Student;
import web.app.service.IStudentService;

@Controller
public class StudentController {
	
	@Autowired
	IStudentService studentService;
	
	
	@RequestMapping("/")
	public String homePage(Model model, @RequestParam(defaultValue = "", required = false, name = "key") String key)
	{
		String campus = "1";
//		Page<Student> students = studentService.getStudents(PageRequest.of(0, 100), campus, key);
		List<Student> students = studentService.getStudents(campus);
		model.addAttribute("key", key);
		model.addAttribute("students", students);
		return "index";
	}
	
	
	
	@RequestMapping("/student/")
	public String getStudentDetail(@RequestParam String id, @ModelAttribute Model model)
	{
		
		return "student-detail";
	}
	
	@RequestMapping("/admin")
	public String admin()
	{
		return "index";
	}
	
	@RequestMapping("/courses/{id}")
	public String getCourses(Model model, @PathVariable("id") Integer id)
	{
		StudentDTO dto =  studentService.getStudent(id);
		List<Course> courses = dto.getCourses();
		return "courses";
	}
}

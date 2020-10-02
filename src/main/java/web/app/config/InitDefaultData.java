package web.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import web.app.entity.Campus;
import web.app.entity.Course;
import web.app.entity.Student;
import web.app.repos.CampusRepo;
import web.app.repos.StudentRepo;

@Component
public class InitDefaultData {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	CampusRepo campusRepo;
	
	@EventListener
	public void appReady(ApplicationReadyEvent event)
	{
		 initStudent();
		
	}
	private void initStudent()
	{
		Campus campus = new Campus(1);
		campusRepo.saveAndFlush(campus);
		campusRepo.saveAndFlush(new Campus(2));


//		studentRepo.deleteAll();
		
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < 5; i++) {
			Student s = new Student("090712312", 1, "Ly Van Cuong", "12/12/2012", new Campus(1), "12/1/2017", "");
			
			Course c1 = new Course("IELTS", 100.0, "1/1/2018", "12/12/2020", "4/4/2017", 40);
			c1.setStudent(s);
			List<Course> courses = new ArrayList();
			courses.add(c1);

			s.setCourses(courses);			
			students.add(s);
		}
		
		for (int i = 0; i < 5; i++) {
			Student s = new Student("01234567", 1, "Nguyen Thanh Duy", "12/12/2012", new Campus(2), "12/1/2017", "");
			
			Course c1 = new Course("IELTS", 100.0, "1/1/2018", "12/12/2020", "4/4/2017", 40);
			c1.setNote("NONE");
			ArrayList<Integer> arr = new ArrayList();
			arr.add(1);
			arr.add(2);

			c1.setSchedules(arr);
			c1.setStudent(s);
			List<Course> courses = new ArrayList();
			courses.add(c1);
			s.setCourses(courses);			
			students.add(s);
		}
		
		studentRepo.saveAll(students);
	}
}

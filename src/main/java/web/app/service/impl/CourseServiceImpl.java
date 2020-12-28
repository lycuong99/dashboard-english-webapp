package web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.app.dtos.CourseDTO;
import web.app.entity.Course;
import web.app.entity.Student;
import web.app.repos.CourseRepo;
import web.app.repos.StudentRepo;
import web.app.service.ICourseService;
import web.app.transfer.TransferUtilities;

@Component
public class CourseServiceImpl implements ICourseService{

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentRepo studentRepo;
	
	@Override
	public List<Course> getCourses(Integer student_id) {

		return courseRepo.findAllByStudent_Id(student_id);
	}

	@Override
	public CourseDTO getCourse(Integer id) {
		return TransferUtilities.transferToDTO(courseRepo.getOne(id));
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

	@Override
	public CourseDTO insert(CourseDTO dto) {
		Course entity = TransferUtilities.transferToEntity(dto);

		Student student =studentRepo.getOne(dto.getStudentId());
		entity.setStudent(student);

		//Update student class when insert Course
		if(dto.getClassCourse() >= 1){
			student.setClassName(dto.getClassCourse());
		}

		entity = courseRepo.saveAndFlush(entity);
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	public CourseDTO update(CourseDTO dto) {

		Course entity = courseRepo.getOne(dto.getId());

		entity.setName(dto.getName());
		entity.setFee(dto.getFee());
		entity.setSchedules(dto.getSchedules());
		entity.setClassCourse(dto.getClassCourse());
		entity.setDateEnd(dto.getDateEnd());
		entity.setDateStart(dto.getDateStart());
		entity.setDateRegis(dto.getDateRegis());
		entity.setTotalLesson(dto.getTotalLesson());
		entity.setNote(dto.getNote());

		entity = courseRepo.saveAndFlush(entity);

		return dto;
	}

}

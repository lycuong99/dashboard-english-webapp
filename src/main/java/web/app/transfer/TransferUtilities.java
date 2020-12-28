package web.app.transfer;

import web.app.dtos.CourseDTO;
import web.app.dtos.FullyCourseStudentDTO;
import web.app.dtos.StudentDTO;
import web.app.dtos.UserDTO;
import web.app.entity.*;

public class TransferUtilities {
	public static FullyCourseStudent transferToEntity(FullyCourseStudentDTO dto)
	{
		FullyCourseStudent entity = new FullyCourseStudent();

		entity.setCourse(dto.getCourse());
		entity.setbOd(dto.getbOd());
		entity.setCampusInt(dto.getCampus());
		entity.setName(dto.getName());
		entity.setNameParent(dto.getNameParent());
		entity.setNote(dto.getNote());
		entity.setClassName(dto.getClassName());
		entity.setPhone(dto.getPhone());
		entity.setFee(dto.getFee());
		entity.setDateRegis(dto.getDateRegis());
		entity.setDateStart(dto.getDateStart());
		entity.setDateEnd(dto.getDateEnd());

		return entity;
	}
	public static FullyCourseStudentDTO transferToDTO(FullyCourseStudent entity) {
		FullyCourseStudentDTO dto = new FullyCourseStudentDTO();

		dto.setId(entity.getId());
		dto.setCourse(entity.getCourse());
		dto.setbOd(entity.getbOd());
		dto.setCampusObj(entity.getCampus());
		dto.setName(entity.getName());
		dto.setNameParent(entity.getNameParent());
		dto.setNote(entity.getNote());
		dto.setClassName(entity.getClassName());
		dto.setPhone(entity.getPhone());
		dto.setFee(entity.getFee());
		dto.setDateRegis(entity.getDateRegis());
		dto.setDateStart(entity.getDateStart());
		dto.setDateEnd(entity.getDateEnd());

		return dto;
	}
	public static StudentDTO transferToDTO(Student entity) {
		StudentDTO dto = new StudentDTO();
		
		dto.setId(entity.getId());
		dto.setAdmissionDate(entity.getAdmissionDate());
		dto.setbOd(entity.getbOd());
		dto.setCampus(entity.getId());
		dto.setName(entity.getName());
		dto.setNameParent(entity.getNameParent());
		dto.setNote(entity.getNote());
		dto.setClassName(entity.getClassName());
		dto.setPhone(entity.getPhone());
		return dto;
	}

	public static Student transferToEntity(StudentDTO dto) {
		Student entity = new Student();

		entity.setAdmissionDate(dto.getAdmissionDate());
		entity.setbOd(dto.getbOd());
		entity.setCampusInt(dto.getCampus());
		entity.setName(dto.getName());
		entity.setNameParent(dto.getNameParent());
		entity.setNote(dto.getNote());
		entity.setClassName(dto.getClassName());
		entity.setPhone(dto.getPhone());
		return entity;
	}

	public static Course transferToEntity(CourseDTO dto) {
		Course entity = new Course();
		entity.setName(dto.getName());
		entity.setFee(dto.getFee());
		entity.setSchedules(dto.getSchedules());
		entity.setClassCourse(dto.getClassCourse());
		entity.setDateEnd(dto.getDateEnd());
		entity.setDateStart(dto.getDateStart());
		entity.setDateRegis(dto.getDateRegis());
		entity.setTotalLesson(dto.getTotalLesson());
		entity.setNote(dto.getNote());
		return entity;
	}

	public static CourseDTO transferToDTO(Course entity) {
		CourseDTO courseDTO = new CourseDTO();

		courseDTO.setId(entity.getId());
		courseDTO.setName(entity.getName());
		courseDTO.setFee(entity.getFee());
		courseDTO.setSchedules(entity.getSchedules());
		courseDTO.setClassCourse(entity.getClassCourse());
		courseDTO.setDateEnd(entity.getDateEnd());
		courseDTO.setDateStart(entity.getDateStart());
		courseDTO.setDateRegis(entity.getDateRegis());
		courseDTO.setTotalLesson(entity.getTotalLesson());
		courseDTO.setNote(entity.getNote());

		return courseDTO;
	}

	public static User transferToEntity(UserDTO dto)
	{
		return new User(dto.getUsername(), dto.getPassword(), dto.getRole());
	}
}

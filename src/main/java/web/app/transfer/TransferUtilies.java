package web.app.transfer;

import web.app.dtos.StudentDTO;
import web.app.entity.Student;

public class TransferUtilies {

	public static StudentDTO transferToDTO(Student entity) {
		StudentDTO dto = new StudentDTO();
		
		dto.setId(entity.getId());
		dto.setAdmissionDate(entity.getAdmissionDate());
		dto.setbOd(entity.getbOd());
		dto.setCampus(entity.getCampus());
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
		entity.setCampus(dto.getCampus());
		entity.setName(dto.getName());
		entity.setNameParent(dto.getNameParent());
		entity.setNote(dto.getNote());
		entity.setClassName(dto.getClassName());
		entity.setPhone(dto.getPhone());
		return entity;
	}
}

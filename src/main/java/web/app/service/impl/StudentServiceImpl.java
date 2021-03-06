package web.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.app.dtos.StudentDTO;
import web.app.entity.Student;
import web.app.repos.StudentRepo;
import web.app.service.IStudentService;
import web.app.transfer.TransferUtilities;


@Component
public class StudentServiceImpl implements IStudentService{

	@Autowired 
	StudentRepo studentRepo;
	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudents(int campus) {
		List<Student> results = studentRepo.findAllByCampusId(campus);
		return results;
	}


	@Override
	public void getCourse(int studentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public boolean deleteStudent(Integer id) {
		try {
			studentRepo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	}

	@Override
	public StudentDTO getStudent(Integer id) {
		// TODO Auto-generated method stub
		Student entity = studentRepo.getOne(id);
		
		return TransferUtilities.transferToDTO(entity);
	}

	@Override
	public Student insertStudent(StudentDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Student result = null;
		try {
			System.out.println("INSERT");
			result = studentRepo.saveAndFlush(TransferUtilities.transferToEntity(dto));
			System.out.println(result.getId());
		} catch (Exception e) {
			throw new Exception("Can't insert student");
		}
		return result;
		
	}

	@Override
	public StudentDTO updateStudent(StudentDTO dto) throws Exception {
		
		Student entity = null;
		try {
			System.out.println("UPDATE");
			entity = studentRepo.getOne(dto.getId());
			
			entity.setbOd(dto.getbOd());
			entity.setCampusInt(dto.getCampus());
			entity.setName(dto.getName());
			entity.setNameParent(dto.getNameParent());
			entity.setNote(dto.getNote());
			entity.setClassName(dto.getClassName());
			entity.setPhone(dto.getPhone());
			
			entity = studentRepo.saveAndFlush(entity);	
		} catch (Exception e) {
//			e.printStackTrace();
			throw new Exception("Can't update student");
		}
		return TransferUtilities.transferToDTO(entity);
	}
	
}

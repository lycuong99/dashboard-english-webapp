package web.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import web.app.dtos.StudentDTO;
import web.app.entity.Student;

@Service
public interface IStudentService {
	
	public List<Student> getAllStudents();
	public List<Student> getStudents(int campus);

	public StudentDTO getStudent(Integer id);
	
	public void getCourse(int studentId);
//	public boolean insertStudent(StudentDTO dto);
	public Student insertStudent(StudentDTO dto) throws Exception;
	public StudentDTO updateStudent(StudentDTO dto) throws Exception;
	@Transactional
	public boolean deleteStudent(Integer id);
	
}

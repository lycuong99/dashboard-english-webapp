package web.app.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.app.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	Page<Student> findAllByCampus(String campus, Pageable pageble);
	List<Student> findAllByCampus(String campus);
	Page<Student> findAllByCampusAndNameContainingOrPhoneContaining(String campus, Pageable pageble, String name, String phone);
}

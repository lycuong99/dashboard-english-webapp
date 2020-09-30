package web.app.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import web.app.dtos.ReportIncomeDTO;
import web.app.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findAllByCampusId(int campus);
	Page<Student> findAllByCampusAndNameContainingOrPhoneContaining(String campus, Pageable pageble, String name, String phone);

	@Query(value = "select s.id, s.name, s.class_name, s.name_parent,s.phone, c.name As nameCourse, c.id As idCourse , c.date_regis, c.fee from students As s INNER JOIN (Select * from courses \n" +
			"where TO_DATE(date_regis,'DD/MM/YYYY') BETWEEN  TO_DATE(?1,'DD/MM/YYYY') AND  TO_DATE(?2,'DD/MM/YYYY')) As c on s.id=c.student_id\n" +
			"where s.campus_id = ?3", nativeQuery = true)
	public List<List<String>> getReportIncome(String startDate, String endDate, Integer campus);

	@Query(value = "select s.id, s.name, s.class_name, s.name_parent,s.phone, c.name As nameCourse, c.id As idCourse , c.date_end from students As s INNER JOIN (Select * from courses \n" +
			"where TO_DATE(date_end,'DD/MM/YYYY') BETWEEN  TO_DATE(?1,'DD/MM/YYYY') AND  TO_DATE(?2,'DD/MM/YYYY')) As c on s.id=c.student_id\n" +
			"where s.campus_id = ?3", nativeQuery = true)
	public List<List<String>> getReportEndDate(String startDate, String endDate, Integer campus);
}

package web.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.app.entity.FullyCourseStudent;


import java.util.List;

@Repository
public interface FullyCourseStudentRepo extends JpaRepository<FullyCourseStudent, Integer> {
    List<FullyCourseStudent> findAllByCampusId(int campus);

    @Query(value = "SELECT * FROM FullyCourseStudents Where campus_id = ?1 AND TO_DATE(date_regis,'DD/MM/YYYY') BETWEEN  TO_DATE(?2,'DD/MM/YYYY') AND  TO_DATE(?3,'DD/MM/YYYY')", nativeQuery = true)
    List<FullyCourseStudent> findAllByCampusIdAndDateRegisBetween(int campus, String start, String end);
}

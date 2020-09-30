package web.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.entity.Campus;
import web.app.entity.Student;

@Repository
public interface CampusRepo extends JpaRepository<Campus, Integer> {

}

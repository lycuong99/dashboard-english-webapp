package web.app.service;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.app.dtos.FullyCourseStudentDTO;
import web.app.entity.FullyCourseStudent;
import javax.transaction.Transactional;
import java.util.List;

@Service
public interface IFullyCourseStudentService {

    public List<FullyCourseStudent> getStudents(int campus);
    public FullyCourseStudentDTO getStudent(Integer id);

    public FullyCourseStudent insertStudent(FullyCourseStudentDTO dto) throws Exception;
    public FullyCourseStudentDTO updateStudent(FullyCourseStudentDTO dto) throws Exception;
    public List<FullyCourseStudent> getStudentsDateRegisBetween(int campus, String start, String end);

    @Transactional
    public boolean deleteStudent(Integer id);
}

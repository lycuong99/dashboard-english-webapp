package web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.app.dtos.FullyCourseStudentDTO;
import web.app.entity.FullyCourseStudent;
import web.app.repos.FullyCourseStudentRepo;
import web.app.service.IFullyCourseStudentService;
import web.app.transfer.TransferUtilities;

import java.util.List;

@Component
public class FullyCourseStudentServiceImpl implements IFullyCourseStudentService {
    @Autowired
    FullyCourseStudentRepo repo;

    @Override
    public List<FullyCourseStudent> getStudents(int campus) {
        return repo.findAllByCampusId(campus);
    }

    @Override
    public FullyCourseStudentDTO getStudent(Integer id) {
        return TransferUtilities.transferToDTO(repo.getOne(id));
    }

    @Override
    public FullyCourseStudent insertStudent(FullyCourseStudentDTO dto) throws Exception {
        FullyCourseStudent result = null;
        try {

            result = repo.saveAndFlush(TransferUtilities.transferToEntity(dto));
            System.out.println(result.getId());
        } catch (Exception e) {
           e.printStackTrace();
            throw new Exception("Can't insert student");
        }
        return result;
    }

    @Override
    public FullyCourseStudentDTO updateStudent(FullyCourseStudentDTO dto) throws Exception {
        FullyCourseStudent entity = null;
        try {
            System.out.println("UPDATE");
            entity = repo.getOne(dto.getId());

            entity.setCampusInt(dto.getCampus());
            entity.setName(dto.getName());
            entity.setNameParent(dto.getNameParent());
            entity.setClassName(dto.getClassName());
            entity.setPhone(dto.getPhone());
            entity.setCourse(dto.getCourse());
            entity.setFee(dto.getFee());
            entity.setbOd(dto.getbOd());
            entity.setDateRegis(dto.getDateRegis());
            entity.setDateStart(dto.getDateStart());
            entity.setDateEnd(dto.getDateEnd());
            entity.setNote(dto.getNote());

            entity = repo.saveAndFlush(entity);
        } catch (Exception e) {
//			e.printStackTrace();
            throw new Exception("Can't update student");
        }
        return TransferUtilities.transferToDTO(entity);
    }

    @Override
    public List<FullyCourseStudent> getStudentsDateRegisBetween(int campus, String start, String end) {
        return repo.findAllByCampusIdAndDateRegisBetween(campus,start,end);
    }

    @Override
    public boolean deleteStudent(Integer id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

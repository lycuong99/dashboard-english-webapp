package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.dtos.FullyCourseStudentDTO;
import web.app.dtos.StudentDTO;
import web.app.entity.FullyCourseStudent;
import web.app.service.IFullyCourseStudentService;

@RestController
@RequestMapping("/full-courses-student-api")
public class FullyCourseStudentAPIController {
    @Autowired
    IFullyCourseStudentService service;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        System.out.println("Delete" + id);
        if (service.deleteStudent(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/insert")
    public  ResponseEntity<?> insert(@RequestBody FullyCourseStudentDTO request)
    {
        FullyCourseStudent result;
        System.out.println("INSERT " + request.toString());
        try {
            result = service.insertStudent(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FullyCourseStudentDTO requestData) {
        FullyCourseStudentDTO result = null;
        try {
            result = service.updateStudent(requestData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println(result.toString());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        FullyCourseStudentDTO student = service.getStudent(id);
        if(student==null) new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(student,HttpStatus.OK);
    }
}

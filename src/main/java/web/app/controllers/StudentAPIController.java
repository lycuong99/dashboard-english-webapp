package web.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.app.dtos.StudentDTO;
import web.app.entity.Student;
import web.app.service.IStudentService;
import web.app.transfer.TransferUtilies;

@RestController
@RequestMapping("/student-api")
public class StudentAPIController {
	@Autowired
	IStudentService studentService;

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		System.out.println("Delete" + id);
		if (studentService.deleteStudent(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody StudentDTO requestData) {
		System.out.println("Insert:"+ requestData.toString());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		requestData.setAdmissionDate(format.format(new Date()));
		Student result;
		try {
			 result = studentService.insertStudent(requestData);
		} catch (Exception e) {			
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody StudentDTO requestData) {
		StudentDTO result = null;
		try {
			 result = studentService.updateStudent(requestData);
		} catch (Exception e) {
//			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		System.out.println(result.toString());
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Integer id){
		StudentDTO student = studentService.getStudent(id);
		if(student==null) new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
}

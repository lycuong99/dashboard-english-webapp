package web.app.entity;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="courses")
public class Course {
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
	)
	@GenericGenerator(
			name = "native",
			strategy = "native"
	)
	private int id;

	private int classCourse = 0;
	private String name;
	private double fee;
	private String dateStart;
	private String dateEnd;
	private String dateRegis;
	private int totalLesson;
	private String note;

	@ElementCollection
	@CollectionTable(name = "schedules")
	private List<Integer> schedules = new ArrayList<>();
	
	@ManyToOne()
	@JoinColumn(name = "student_id")
	private Student student;
		
	
	public Course() {
		
	}
	
	public Course(String name, double fee, String dateStart, String dateEnd, String dateRegis, int totalLesson,
			Student student) {
		this.name = name;
		this.fee = fee;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.dateRegis = dateRegis;
		this.totalLesson = totalLesson;
		this.student = student;
	}
	
	public Course(String name, double fee, String dateStart, String dateEnd, String dateRegis, int totalLesson) {
		this.name = name;
		this.fee = fee;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.dateRegis = dateRegis;
		this.totalLesson = totalLesson;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Integer> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Integer> schedules) {
		this.schedules = schedules;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDateRegis() {
		return dateRegis;
	}
	public void setDateRegis(String dateRegis) {
		this.dateRegis = dateRegis;
	}
	public int getTotalLesson() {
		return totalLesson;
	}
	public void setTotalLesson(int totalLession) {
		this.totalLesson = totalLession;
	}

	public int getClassCourse() {
		return classCourse;
	}

	public void setClassCourse(int classCourse) {
		this.classCourse = classCourse;
	}
	
	
	
}

package web.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="courses")
public class Course {
	@Id
	@GeneratedValue
	private int id;
	

	private String name;
	private double fee;
	private String dateStart;
	private String dateEnd;
	private String dateRegis;
	private int totalLession;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
		
	
	public Course() {
		
	}
	
	public Course(String name, double fee, String dateStart, String dateEnd, String dateRegis, int totalLession,
			Student student) {
		this.name = name;
		this.fee = fee;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.dateRegis = dateRegis;
		this.totalLession = totalLession;
		this.student = student;
	}
	
	public Course(String name, double fee, String dateStart, String dateEnd, String dateRegis, int totalLession) {
		this.name = name;
		this.fee = fee;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.dateRegis = dateRegis;
		this.totalLession = totalLession;
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
	public int getTotalLession() {
		return totalLession;
	}
	public void setTotalLession(int totalLession) {
		this.totalLession = totalLession;
	}
	
	
	
}

package web.app.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name="students")
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String phone;
	private String className;
	private String name;
	private String bOd;
	private String campus;
	private String admissionDate;
	private String note;
	private String nameParent;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Course> courses;
	
	public Student() {
		
	}
	public Student(String phone, String className, String name, String bOd, String campus, String admissionDate,
			String note) {
		this.phone = phone;
		this.className = className;
		this.name = name;
		this.bOd = bOd;
		this.campus = campus;
		this.admissionDate = admissionDate;
		this.note = note;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNameParent() {
		return nameParent;
	}
	public void setNameParent(String nameParent) {
		this.nameParent = nameParent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getbOd() {
		return bOd;
	}
	public void setbOd(String bOd) {
		this.bOd = bOd;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Collection<Course> getCourses() {
		return courses;
	}
	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	
	
}

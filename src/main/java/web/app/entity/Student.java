package web.app.entity;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name="students")
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(
			strategy= GenerationType.TABLE
	)

	private Integer id;
	
	private String phone;
	private Integer className;
	private String name;
	private String bOd;
	private String admissionDate;
	private String note;
	private String nameParent;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "campus_id")
	private Campus campus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Course> courses;
	
	public Student() {
		
	}
	public Student(String phone, int className, String name, String bOd, Campus campus, String admissionDate,
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
	public Integer getClassName() {
		return className;
	}
	public void setClassName(Integer className) {
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

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public void setCampusInt(Integer campus) {
		this.campus = new Campus(campus);
	}
}

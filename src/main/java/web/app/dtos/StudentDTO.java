package web.app.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import web.app.entity.Campus;
import web.app.entity.Course;

import javax.xml.bind.annotation.XmlTransient;

public class StudentDTO {
	private Integer id;
	private String name;
	private String nameParent;
	private Integer className;
	private String bOd;
	private Integer campus;
	private String admissionDate;
	private String note;
	private String phone;
	private List<Course> courses;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameParent() {
		return nameParent;
	}
	public void setNameParent(String nameParent) {
		this.nameParent = nameParent;
	}
	public Integer getClassName() {
		return className;
	}
	public void setClassName(Integer className) {
		this.className = className;
	}
	public String getbOd() {
		return bOd;
	}
	public void setbOd(String bOd) {
		this.bOd = bOd;
	}
	public Integer getCampus() {
		return campus;
	}

	public void setCampus(Integer campus) {
		this.campus = campus;
	}

	public void setCampusObj(Campus campus) {
		this.campus = campus.getId();
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "StudentDTO [name=" + name + ", nameParent=" + nameParent + ", className=" + className + ", bOd=" + bOd
				+ ", campus=" + campus + ", admissionDate=" + admissionDate + ", note=" + note + ", phone=" + phone
				+ "]";
	}
	
	
	
}

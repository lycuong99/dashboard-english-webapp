package web.app.dtos;

import java.util.List;

import web.app.entity.Course;

public class StudentDTO {
	private Integer id;
	private String name;
	private String nameParent;
	private String className;
	private String bOd;
	private String campus;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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

package web.app.dtos;

public class ReportEndDateDTO {
    private String  studentId;
    private String studentName;
    private String  className;
    private String  nameParent;
    private String phone;
    private String nameCourse;
    private String courseId;
    private String  dateEnd;

    public ReportEndDateDTO(String studentId, String studentName, String className, String nameParent, String phone, String nameCourse, String courseId, String dateEnd) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.nameParent = nameParent;
        this.phone = phone;
        this.nameCourse = nameCourse;
        this.courseId = courseId;
        this.dateEnd = dateEnd;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}

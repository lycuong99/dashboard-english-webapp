package web.app.dtos;


import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private int id;
    private int studentId;
    private int classCourse = 0;
    private String name;
    private double fee;
    private String dateStart;
    private String dateEnd;
    private String dateRegis;
    private int totalLesson;
    private List<Integer> schedules = null;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassCourse() {
        return classCourse;
    }

    public void setClassCourse(int classCourse) {
        this.classCourse = classCourse;
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

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public List<Integer> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Integer> schedules) {
        this.schedules = schedules;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", classCourse=" + classCourse +
                ", name='" + name + '\'' +
                ", fee=" + fee +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", dateRegis='" + dateRegis + '\'' +
                ", totalLesson=" + totalLesson +
                ", schedules=" + schedules +
                '}';
    }
}

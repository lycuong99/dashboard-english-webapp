package web.app.dtos;

import web.app.entity.Campus;

public class FullyCourseStudentDTO {
    private Integer id;
    private String name;
    private String nameParent;
    private String phone;
    private Integer className;
    private String bOd;
    private String dateRegis;
    private String dateStart;
    private String dateEnd;
    private String note;
    private String course;
    private double fee;
    private Integer campus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getbOd() {
        return bOd;
    }

    public void setbOd(String bOd) {
        this.bOd = bOd;
    }

    public String getDateRegis() {
        return dateRegis;
    }

    public void setDateRegis(String dateRegis) {
        this.dateRegis = dateRegis;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
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

    @Override
    public String toString() {
        return "FullyCourseStudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameParent='" + nameParent + '\'' +
                ", phone='" + phone + '\'' +
                ", className=" + className +
                ", bOd='" + bOd + '\'' +
                ", dateRegis='" + dateRegis + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", note='" + note + '\'' +
                ", course='" + course + '\'' +
                ", fee=" + fee +
                ", campus=" + campus +
                '}';
    }
}

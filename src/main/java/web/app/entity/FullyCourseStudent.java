package web.app.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Data
@Table(name="fullycoursestudents")

public class FullyCourseStudent {
    @Id
    @GeneratedValue(
            strategy= GenerationType.TABLE
    )

    private Integer id;
    private String name;
    private String nameParent;
    private String phone;
    private Integer className;
    private String bOd;
    private String admissionDate;
    private String dateRegis;
    private String dateStart;
    private String dateEnd;
    private String note;
    private String course;

    private double fee=0;

    public FullyCourseStudent() {
    }

    public FullyCourseStudent(Integer id, String name, String nameParent, String phone, Integer className, String bOd, String admissionDate, String dateRegis, String dateStart, String dateEnd, String note, String course, double fee, Campus campus) {
        this.id = id;
        this.name = name;
        this.nameParent = nameParent;
        this.phone = phone;
        this.className = className;
        this.bOd = bOd;
        this.admissionDate = admissionDate;
        this.dateRegis = dateRegis;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.note = note;
        this.course = course;
        this.fee = fee;
        this.campus = campus;
    }

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "campus_id")
    private Campus campus;

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

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
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

package web.app.entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Schedule {
    @Id
    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    private boolean t2=false;
    private boolean t3=false;
    private boolean t4=false;
    private boolean t5=false;
    private boolean t6=false;
    private boolean t7=false;
    private boolean cn=false;

}

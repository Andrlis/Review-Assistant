package Data.Mark;

import Data.Student;
import Data.Test.Test;

import javax.persistence.*;

/**
 * Created by kesso on 18.08.17.
 */
@Entity
@Table(name = "tests_result")
public class TestMark {
    @Id
    @Column(name = "id_test_result")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_test")
    private Test test;
    @Column(name = "mark")
    private Integer mark;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student")
    private Student student;

    public TestMark() {
    }

    public TestMark(Integer id, Test test, Integer mark, Student student) {
        this.id = id;
        this.test = test;
        this.mark = mark;
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

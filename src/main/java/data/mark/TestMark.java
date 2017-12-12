package data.mark;

import data.Student;
import data.test.Test;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Comparator;

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
    @ManyToOne()
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_test")
    private Test test;
    @Column(name = "mark")
    private Integer mark;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_student")
    private Student student;

    public TestMark() {
        mark = -1;
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

    public static final Comparator<TestMark> COMPARATOR_BY_NUMBER_OF_TEST = new Comparator<TestMark>() {
        @Override
        public int compare(TestMark testMark, TestMark t1) {
            return testMark.getTest().getTestNumber() - t1.getTest().getTestNumber();
        }
    };
}

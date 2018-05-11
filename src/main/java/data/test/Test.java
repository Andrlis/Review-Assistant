package data.test;

import data.Student;
import data.mark.TestMark;
import org.apache.log4j.Logger;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kesso on 19.08.17.
 */
@Entity
@Table(name = "tests")
public class Test {
    private static final Logger logger = Logger.getLogger(Test.class);
    @Id
    @Column(name = "id_test")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "test_number")
    private Integer testNumber;
    @Column(name = "test_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testDate;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "tests_result",
            joinColumns = @JoinColumn(name = "id_test"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Student>   studentControlList;


    public Test() {
        this.studentControlList = new ArrayList<>();
    }

    public Test(Integer id, Integer testNumber, Date testDate, List<Student> students) {
        this.id = id;
        this.testNumber = testNumber;
        this.testDate = testDate;
        this.studentControlList = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public void addStudent(Student student) {
        this.studentControlList.add(student);
    }

    public List<Student> getStudentControlList() {
        return studentControlList;
    }

    public void setStudentControlList(List<Student> studentControlList) {
        this.studentControlList = studentControlList;
    }
}

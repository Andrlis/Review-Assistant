package data.сomment;

import data.Student;
import data.UniversityClass;

import javax.persistence.*;

@Entity
@Table(name = "class_comments")
public class Comment {
    @Id
    @Column(name = "id_class_comments")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @OneToOne
    @JoinColumn(name = "id_class")
    private UniversityClass universityClass;

    @Column(name = "comment", length = 280)
    private String comment;

    public Comment(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

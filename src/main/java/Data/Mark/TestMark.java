package Data.Mark;

import Data.Test.Test;

import javax.persistence.*;

/**
 * Created by kesso on 18.08.17.
 */
@Entity
@Table(name = "tests_result")
public class TestMark extends Mark {
    @Id
    @Column(name ="id_test")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_test")
    private Test test;

    public TestMark(){
        super();
    }

    public TestMark(Integer id, Integer mark, Test test) {
        super(mark);
        this.id = id;
        this.test = test;
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
}

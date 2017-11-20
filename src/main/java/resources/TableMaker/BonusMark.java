package resources.TableMaker;

import data.Student;

public class BonusMark {

    private Integer id;
    private Integer mark;

    public BonusMark(Student student){
        id = student.getId();
        mark = student.getBonusMark();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}

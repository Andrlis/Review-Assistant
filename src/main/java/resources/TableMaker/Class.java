package resources.TableMaker;

import data.UniversityClass;

import java.util.Date;


public class Class {
    private Integer id;
    private Date date;
    private boolean visitable;

    public Class(UniversityClass universityClass, boolean visitable) {
        this.id = universityClass.getId();
        this.date = universityClass.getDate();
        this.visitable = visitable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public boolean isVisitable() {
        return visitable;
    }

    public void setVisitable(boolean visitable) {
        this.visitable = visitable;
    }
}

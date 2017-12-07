package resources.TableMaker.Data;

import data.UniversityClass;

import java.util.Date;


public class Class {
    private Integer id;
    private boolean visitable;

    public Class(UniversityClass universityClass, boolean visitable) {
        this.id = universityClass.getId();
        this.visitable = visitable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isVisitable() {
        return visitable;
    }

    public void setVisitable(boolean visitable) {
        this.visitable = visitable;
    }
}

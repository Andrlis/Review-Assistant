package resources.TableMaker.Data;


import data.Student;

public class ItogMark {

    private float mark;
    private Student student;

    public ItogMark(Student stud){
        student = stud;
    }

    public float getMark(){
        mark = 0;
        for(int i = 0; i < student.getLabMarkList().size();i++ ){
            mark += student.getLabMarkList().get(i).getMark();
        }
        for(int i = 0; i < student.getTestMarkList().size(); i++){
            mark+= student.getTestMarkList().get(i).getMark();
        }
        mark+= student.getBonusMark();
        return mark/10;
    }
}

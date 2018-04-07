package jsonMaker.jsonData;


import data.Student;

public class ItogMark {

    private float totalMark;
    private Student student;

    public ItogMark(Student stud){
        student = stud;
    }

    public float getMark(){
        totalMark = 0;

        int amountOfMarks = student.getLabMarkList().size();
        for(int i = 0; i < student.getLabMarkList().size();i++ ){
            totalMark += student.getLabMarkList().get(i).getRealMark();
        }

        amountOfMarks += student.getTestMarkList().size();
        for(int i = 0; i < student.getTestMarkList().size(); i++){
            totalMark+= student.getTestMarkList().get(i).getRealMark();
        }

        amountOfMarks++;
        if (student.getBonusMark() > 0)
            totalMark += student.getBonusMark();

        return totalMark/amountOfMarks;
    }
}

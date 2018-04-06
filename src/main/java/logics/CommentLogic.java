package logics;

import data.Student;
import data.UniversityClass;
import data.сomment.Comment;
import dao.DataBaseCore;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import dao.DataBaseCoreInterface;

public class CommentLogic {

    private DataBaseCoreInterface dataBaseCore;

    public CommentLogic(){
        dataBaseCore = DataBaseCore.getInstance();
    }

    public CommentLogic(DataBaseCoreInterface core) {
        dataBaseCore = core;
    }

    public Comment get(Integer studentId, Integer classId) throws DataBaseQueryException, DataBaseCriteriaCountException {
        Comment answer;
        try {
            answer = (Comment) dataBaseCore.getByCriteria(Comment.class,
                    "student.id", studentId, "universityClass.id", classId);
        } catch (DataBaseQueryException e){

            Student student = (Student) dataBaseCore.getById(Student.class, studentId);
            UniversityClass universityClass = (UniversityClass) dataBaseCore.getById(UniversityClass.class, classId);

            answer = new Comment();
            answer.setStudent(student);
            answer.setUniversityClass(universityClass);

            dataBaseCore.create(answer);
        }

        return answer;
    }
}

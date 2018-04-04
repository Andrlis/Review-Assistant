package resources.Controllers;

import data.Student;
import data.UniversityClass;
import data.сomment.Comment;
import resources.Hibernate.Controller.DataBaseCore;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class CommentController {

    private DataBaseCoreInterface dataBaseCore;

    public CommentController(){
        dataBaseCore = DataBaseCore.getInstance();
    }

    public CommentController(DataBaseCoreInterface core) {
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

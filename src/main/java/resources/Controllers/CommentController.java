package resources.Controllers;

import data.Student;
import data.UniversityClass;
import data.сomment.Comment;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class CommentController extends DefaultController{
    public CommentController() {
        super();
    }

    public CommentController(DataBaseCoreInterface core) {
        super(core);
    }

    public Comment get(Integer studentId, Integer classId) throws DataBaseQueryException, DataBaseCriteriaCountException {
        Comment answer;
        try {
            answer = (Comment) dataBaseCore.getByCriteria(Comment.class,
                    "student.id", studentId, "universityClass.id", classId);
        } catch (DataBaseQueryException e){

            Student student = (Student) getById(Student.class, studentId);
            UniversityClass universityClass = (UniversityClass) getById(UniversityClass.class, classId);

            answer = new Comment();
            answer.setStudent(student);
            answer.setUniversityClass(universityClass);

            saveToDataBase(answer);
        }

        return answer;
    }
}

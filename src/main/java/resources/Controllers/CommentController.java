package resources.Controllers;

import data.сomment.Comment;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class CommentController extends DefaultController<Comment>{
    public CommentController() {
        super(Comment.class);
    }

    public CommentController(DataBaseCoreInterface core) {
        super(Comment.class, core);
    }

    public Comment get(Integer studentId, Integer classId) throws DataBaseQueryException, DataBaseCriteriaCountException {
        return (Comment) dataBaseCore.getByCriteria(Comment.class,
                "student.id", studentId, "universityClass.id", classId);
    }
}

package resources.Hibernate;

import data.Student;
import data.UniversityClass;
import data.сomment.Comment;

public class CommentsHibernateShell {
    private HibernateCore hibernateCore;

    public CommentsHibernateShell(){
        hibernateCore = HibernateCore.getInstance();
    }

    public void saveComment(Student student, UniversityClass universityClass, String comment){
        Comment newComment = new Comment();
        newComment.setStudent(student);
        newComment.setUniversityClass(universityClass);
        newComment.setComment(comment);

        hibernateCore.save(comment);
    }

    public void saveComment(Integer idStudent, Integer idUniversityClass, String comment) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("INSERT INTO class_comments(id_class, id_student, comment) " +
                "VALUES(" + idStudent + ", " + idUniversityClass + ", '" + comment + "');");

    }
}

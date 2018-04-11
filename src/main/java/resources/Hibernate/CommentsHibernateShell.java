package resources.Hibernate;

import data.Student;
import data.UniversityClass;
import data.сomment.Comment;

public class CommentsHibernateShell {
    private HibernateCore hibernateCore;

    public CommentsHibernateShell(){
        hibernateCore = HibernateCore.getInstance();
    }

    public void updateComment(Comment comment){
        hibernateCore.save(comment);
    }

    public void updateComment(Student student, UniversityClass universityClass, String comment){
        Comment newComment = new Comment();
        newComment.setStudent(student);
        newComment.setUniversityClass(universityClass);
        newComment.setComment(comment);

        hibernateCore.save(comment);
    }

    public void updateComment(Integer idStudent, Integer idUniversityClass, String comment) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("INSERT INTO class_comments(id_class, id_student, comment) " +
                "VALUES(" + idStudent + ", " + idUniversityClass + ", '" + comment + "');");
    }

    public void createComment(String idStudent, String idUniversityClass, String comment) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("INSERT INTO class_comments(id_class, id_student, comment) " +
                "VALUES(" + idStudent + ", " + idUniversityClass + ", '" + comment + "');");
    }


    public void deleteComment(Comment comment) {
        hibernateCore.delete(comment);
    }

    public void deleteComment(Integer id) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("DELETE FROM class_comments WHERE id_class_comments = " + id);
    }

    public String getComment(String id) throws HibernateShellQueryException {
        Comment comment = hibernateCore.getCommentById(Integer.parseInt(id));

        if(comment != null)
            return comment.getComment();

        return "";
    }

    public String getComment(String studentId, String classId) throws HibernateShellQueryException {
        Comment comment = hibernateCore.getComment(Integer.parseInt(studentId), Integer.parseInt(classId));
        String commentText = "";

        if(comment == null) {
            this.createComment(studentId, classId, "");
        } else {
            commentText = comment.getComment();
        }

        return commentText;
    }

    public String getComment(Integer studentId, Integer classId) {
        Comment comment = hibernateCore.getComment(studentId, classId);

        if(comment != null)
            return comment.getComment();

        return "";
    }

    public Comment getCommentById(String id) {
        Comment comment;

        try {
            comment = hibernateCore.getCommentById(Integer.parseInt(id));
        } catch (HibernateShellQueryException e) {
            return null;
        }

        return comment;
    }
}

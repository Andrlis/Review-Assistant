package resources.Hibernate;

import data.Student;
import data.UniversityClass;
import data.сomment.Comment;
import org.junit.Test;

import java.util.Date;

public class CommentsHibernateShellTest {

    @Test
    public void testComment() {
        Student student = new Student();
        student.setFulName("test");
        student.seteMail("");
        student.setGitRepoName("");
        student.setGitURL("");

        UniversityClass universityClass = new UniversityClass();
        universityClass.setDate(new Date());

        HibernateCore.getInstance().save(student);
        HibernateCore.getInstance().save(universityClass);

        Comment comment = new Comment();
        comment.setUniversityClass(universityClass);
        comment.setStudent(student);
        comment.setComment("test");

        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();

        commentsHibernateShell.updateComment(comment);

        commentsHibernateShell.deleteComment(comment);


        HibernateCore.getInstance().delete(student);
        HibernateCore.getInstance().delete(universityClass);
    }
}
package resources.Hibernate;

import data.Student;

public class StudentHibernateShell {

    static public void deleteStudent(String id){
        Student student = HibernateShell.getStudentById(id);
        HibernateShell.delete(student);
    }

    static public void  NoteStudentPresence(String studentId, String classId){
        HibernateShell.createQuery("DELETE FROM absentees WHERE id_class = " + classId + " and id_student = " + studentId);
    }
}

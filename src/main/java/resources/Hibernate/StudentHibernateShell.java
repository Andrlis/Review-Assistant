package resources.Hibernate;

import data.Student;

public class StudentHibernateShell {

    static public void deleteStudent(String id){
        Student student = HibernateShell.getStudentById(id);
        HibernateShell.delete(student);
    }
}

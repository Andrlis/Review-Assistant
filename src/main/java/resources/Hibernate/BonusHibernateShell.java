package resources.Hibernate;

import data.Student;

public class BonusHibernateShell {
    private HibernateCore hibernateCore;

    public BonusHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public void updateBonusMark(String id, Integer mark) throws HibernateShellQueryException {
        Student student = hibernateCore.getStudentById(id);

        if (student != null) {
            student.setBonusMark(mark);
            hibernateCore.update(student);
        }
    }
}

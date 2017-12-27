package resources.Hibernate;

import data.Student;

public class BonusHibernateShell {
    private HibernateCore hibernateCore;

    public BonusHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public void updateBonusMark(Integer id, Integer mark) throws HibernateShellQueryException {
        Student student = hibernateCore.getStudentById(id.toString());

        if (student != null) {
            student.setBonusMark(mark);
            hibernateCore.update(student);
        }
    }
}

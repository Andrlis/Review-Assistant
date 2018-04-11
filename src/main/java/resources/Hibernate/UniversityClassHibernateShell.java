package resources.Hibernate;

import data.UniversityClass;

public class UniversityClassHibernateShell {

    private HibernateCore hibernateCore;

    public UniversityClassHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public UniversityClass getUniversityClassById(String id) throws HibernateShellQueryException {
        return hibernateCore.getUniversityClassById(Integer.parseInt(id));
    }
}

package resources.Hibernate;

import java.util.List;

public class TestHibernateShell {

    public static void addNextTest(String group, String comment) {
        List<Integer> ids = HibernateShell.getStudentsId();

        HibernateShell.addTest();
        Long testNumber = HibernateShell.getNumberOfTests();
        for(Integer id : ids) {
            HibernateShell.addTestMark(id,testNumber.intValue());
        }

    }
}

package resources.Hibernate;

import data.mark.TestMark;
import data.test.Test;

import java.util.Date;
import java.util.List;


public class TestHibernateShell {

    private HibernateCore hibernateCore;

    public TestHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public void addNextTest() throws HibernateShellQueryException {
        List<Integer> ids = hibernateCore.getStudentsId();

        addTest();
        Long testNumber = hibernateCore.getNumberOfTests();
        for (Integer id : ids) {
            addTestMark(id, testNumber.intValue());
        }
    }

    public Integer getNumberOfNextTest() throws HibernateShellQueryException {
        return (int) (hibernateCore.getNumberOfTests() + 1);
    }

    public void updateTestMark(Integer id, Integer mark) throws HibernateShellQueryException {
        TestMark testMark = hibernateCore.getTestMarkById(id);

        if (testMark != null) {
            testMark.setMark(mark);
            hibernateCore.update(testMark);
        }
    }

    public void addTest() throws HibernateShellQueryException {
        Test test = new Test();
        test.setTestDate(new Date());
        test.setTestNumber(getNumberOfNextTest());

        hibernateCore.save(test);
    }

    private void addTestMark(Integer studentId, Integer testNumber) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("INSERT INTO tests_result(id_student, id_test, mark)" +
                " VALUES (" + studentId + ", " + testNumber + ", -1);");
    }

    public TestMark getTestMarkById(String id){
        TestMark testMark;

        try {
            testMark = hibernateCore.getTestMarkById(Integer.getInteger(id));
        } catch (HibernateShellQueryException e) {
            return null;
        }

        return testMark;
    }
}

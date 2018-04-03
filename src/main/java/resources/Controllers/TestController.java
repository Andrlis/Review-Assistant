package resources.Controllers;


import data.Student;
import data.mark.TestMark;
import data.test.Test;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

import java.util.Date;
import java.util.List;

public class TestController extends DefaultController {
    public TestController() {
        super();
    }

    public TestController(DataBaseCoreInterface core) {
        super(core);
    }

    public Integer getNextNumber() throws DataBaseQueryException {
        return dataBaseCore.getCount(Test.class) + 1;
    }

    public Test getByNumber(String number) throws DataBaseQueryException, DataBaseCriteriaCountException {
        return (Test) dataBaseCore.getByCriteria(Test.class, "testNumber", number);
    }

    public Test addNewTest() throws DataBaseQueryException {
        Test test = new Test();
        test.setTestDate(new Date());
        test.setTestNumber(this.getNextNumber());

        this.saveToDataBase(test);

        return test;
    }

    public void issue() throws DataBaseQueryException {
        StudentController studentController = new StudentController(dataBaseCore);
        DefaultController defaultController = new DefaultController(dataBaseCore);

        Test test = this.addNewTest();

        List<Object> students = studentController.getAll(Student.class);
        for(Object object : students) {
            Student student = (Student) object;
            TestMark testMark = new TestMark();
            testMark.setStudent(student);
            testMark.setTest(test);

            defaultController.saveToDataBase(testMark);
        }
    }
}

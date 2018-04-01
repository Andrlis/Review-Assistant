package resources.Controllers;


import data.Student;
import data.UniversityClass;
import data.group.SubGroup;
import data.mark.TestMark;
import data.test.Test;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

import java.util.Date;
import java.util.List;

public class TestController extends DefaultController<Test> {
    public TestController() {
        super(Test.class);
    }

    public TestController(DataBaseCoreInterface core) {
        super(Test.class, core);
    }

    public Integer getNextNumber() {
        return dataBaseCore.getCount(Test.class) + 1;
    }

    public Test getByNumber(String number) {
        return (Test) dataBaseCore.getByCriteria(Test.class, "testNumber", number);
    }

    public Test addNewTest() {
        Test test = new Test();
        test.setTestDate(new Date());
        test.setTestNumber(this.getNextNumber());

        this.saveToDataBase(test);

        return test;
    }

    public void issue() {
        StudentController studentController = new StudentController(dataBaseCore);
        DefaultController<TestMark> testMarkDefaultController = new DefaultController<>(TestMark.class, dataBaseCore);

        Test test = this.addNewTest();

        List<Student> students = studentController.getAll();
        for(Student student : students) {
            TestMark testMark = new TestMark();
            testMark.setStudent(student);
            testMark.setTest(test);

            testMarkDefaultController.saveToDataBase(testMark);
        }
    }
}

package resources.Controllers;


import data.UniversityClass;
import data.group.SubGroup;
import data.test.Test;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

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

    public void issue() {
        //TODO
    }
}

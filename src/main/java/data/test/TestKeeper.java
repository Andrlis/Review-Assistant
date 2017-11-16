package data.test;

import java.util.List;

/**
 * Created by kesso on 24.08.17.
 */
public class TestKeeper {
    private List<Test> testList;

    public TestKeeper() {}

    public TestKeeper(List<Test> testList) {
        this.testList = testList;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public void addTest(Test test) {
        if(!this.testList.contains(test)) {
            this.testList.add(test);
        }
    }
}

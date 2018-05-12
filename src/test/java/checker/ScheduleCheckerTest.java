package checker;

import bsuirAPI.BsuirRequestInterface;
import dao.DataBaseCoreInterface;
import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScheduleCheckerTest {

    private ScheduleChecker scheduleChecker;
    private ArrayList<Group> groupsForTest;
    private ArrayList<IssuedLab> issuedLabsForTest;
    private ArrayList<UniversityClass> universityClassesForTest;

    private class DataBaseCoreTest implements DataBaseCoreInterface {
        public Object getById(Class c, Integer id) throws DataBaseQueryException {
            return new Object();
        }

        public Object create(Object object) throws DataBaseQueryException {
            universityClassesForTest.add((UniversityClass)object);

            return object;
        }

        public Object update(Object object) throws DataBaseQueryException {


            return new Object();
        }

        public void delete(Object object) throws DataBaseQueryException {
        }

        public Integer getCount(Class c) throws DataBaseQueryException {
            return Integer.valueOf("0");
        }

        public Object getByCriteria(Class c, Object... criteria) throws DataBaseQueryException, DataBaseCriteriaCountException {
            return new Object();
        }

        public Integer getNumberCriteria(Class c, Object... criteria) throws DataBaseQueryException, DataBaseCriteriaCountException {
            return Integer.valueOf("0");
        }

        public List<Object> getAll(Class c) throws DataBaseQueryException {
            return new ArrayList<>();
        }
    }

    private class BsuirRequestTest implements BsuirRequestInterface {
        public String getTimetable(String groupName) throws IOException {
            if (groupName.equals("550502"))
                return TestConstants.XML_TIMETABLE;
            else return "";
        }

        public String getCurrentWeek() throws IOException {
            return "3";
        }

        public String getGroups() throws IOException {
            return "";
        }
    }

    @Before
    public void setUp() throws Exception {
        scheduleChecker = new ScheduleChecker(new DataBaseCoreTest(), new BsuirRequestTest());
        groupsForTest = new ArrayList<>();
        issuedLabsForTest = new ArrayList<>();
        universityClassesForTest = new ArrayList<>();

        SubGroup subGroup1 = new SubGroup();
        SubGroup subGroup2 = new SubGroup();
        subGroup1.setSubGroupNumber("1");
        subGroup2.setSubGroupNumber("2");

        Group group = new Group();
        group.setNumberOfGroup("550502");
        group.addSubGroup(subGroup1);
        group.addSubGroup(subGroup2);

        groupsForTest.add(group);
    }

    @Test
    public void groupScheduleCheck() throws Exception {
        scheduleChecker.groupScheduleCheck(groupsForTest);
    }

}
package bsuirAPI;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Andrlis on 21.08.2017.
 */
public class BsuirRequestsTest extends TestCase {

    private String response;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        response = null;
    }

    @Test
    public void testGetGroups() throws Exception {
        System.out.println("test BsuirRequests.getGroups()");
        response = BsuirRequests.getGroups();
        assertNotNull(response);
        assertTrue(response.contains("studentGroupXmlModels"));
        System.out.println("test success.");
    }

    @Test
    public void testGetTimetable() throws Exception {
        System.out.println("test BsuirRequests.getTimetable()");
        response = BsuirRequests.getTimetable("21366");
        assertNotNull(response);
        assertTrue(response.contains("scheduleXmlModels"));
        System.out.println("test success.");
    }

    @Test
    public void testGetCurrentWeek() throws Exception {
        System.out.println("test BsuirRequests.getCurrentWeek()");
        response = BsuirRequests.getCurrentWeek();
        assertNotNull(response);
        assertTrue(Integer.parseInt(response)<5);
        System.out.println("test success.");
    }
}
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
        System.out.println("Test BSUIRRequests.getGroups()");
        response = BsuirRequests.getGroups();
        assertNotNull(response);
        assertEquals(true, response.contains("studentGroupXmlModels"));
    }
}
package gitAPI;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class GitRequestsTest extends TestCase {

    private String response;
    private GitRequests requests;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        requests = new GitRequests();
        response = null;
    }

    @Test
    public void testGetRepoInfo() throws Exception {
        System.out.println("Test GitRequests.getRepoInfo()");
        response = requests.getRepoInfo("Andrlis", "TRTPOControlSystem");
        assertNotNull(response);
        assertFalse(response.contains("Not Found"));
        assertTrue(response.contains("Andrlis"));
        assertTrue(response.contains("TRTPOControlSystem"));
        System.out.println("Test success.");
    }

    @Test
    public void testGetCommitList() throws Exception {
        System.out.println("Test GitRequests.getCommitList()");
        response = requests.getCommitList("Andrlis", "TRTPOControlSystem");
        assertNotNull(response);
        assertFalse(response.contains("Not Found"));
        assertTrue(response.contains("Andrlis"));
        assertTrue(response.contains("TRTPOControlSystem"));
        assertTrue(response.contains("sha"));
        assertTrue(response.contains("commit"));
        System.out.println("Test success.");
    }

}
package bsuirAPI;

import java.io.IOException;

public interface BsuirRequestInterface {
    String getTimetable(String groupName) throws IOException;
    String getCurrentWeek() throws IOException;
    String getGroups() throws IOException;
}

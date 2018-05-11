package bsuirAPI;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BsuirRequestCore {

    private static final Logger logger = Logger.getLogger(BsuirRequestCore.class);

    public String getGroups() throws IOException {
        URL url = new URL(String.format("https://students.bsuir.by/api/v1/groups"));
        URLConnection connection = url.openConnection();

        logger.info("Send request about group(\"https://www.bsuir.by/schedule/rest/studentGroup\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }

    public String getCurrentWeek() throws IOException {
        URL url = new URL("https://students.bsuir.by/api/v1/portal/schedule/week");
        URLConnection connection = url.openConnection();

        logger.info("Send request for receipt of current week(\"https://students.bsuir.by/api/v1/portal/schedule/week\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }
}

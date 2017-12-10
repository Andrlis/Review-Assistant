package bsuirAPI;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

/**
 * Created by Andrey on 13.07.2017.
 */
public class BsuirRequests {
    private static final Logger logger = Logger.getLogger(BsuirRequests.class);

    /**
     * Return xml-list of groups.
     *
     * @return String
     */
    public static String getGroups() throws IOException {
        String response = null;

        URL url = new URL(String.format("https://students.bsuir.by/api/v1/groups"));
        URLConnection connection = url.openConnection();

        logger.info("Send request about group(\"https://www.bsuir.by/schedule/rest/studentGroup\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        response = in.readLine();
        return response;
    }

    /**
     * Return group`s timetable for all period.
     *
     * @param groupName
     * @return
     * @throws IOException
     */
    public static String getTimetable(String groupName) throws IOException {

        String response;

        URL url = new URL(String.format("https://students.bsuir.by/api/v1/studentGroup/schedule?studentGroup=%s", groupName));

        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Accept", "application/xml;charset=UTF-8");

        logger.info("Send request about timetable(\"https://students.bsuir.by/api/v1/studentGroup/schedule?studentGroup=" + groupName + "\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        response = in.readLine();

        return response;
    }


    /**
     * Return current week.
     *
     * @return
     * @throws IOException
     */
    public static String getCurrentWeek() throws IOException {
        URL url = new URL("https://students.bsuir.by/api/v1/portal/schedule/week");
        URLConnection connection = url.openConnection();

        logger.info("Send request for receipt of current week(\"https://students.bsuir.by/api/v1/portal/schedule/week\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }
}

package bsuirAPI;

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

    /**
     * Return xml-list of groups.
     *
     * @return String
     */
    public String getGroups() throws IOException {
        URL url = new URL("https://www.bsuir.by/schedule/rest/studentGroup");
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }

    /**
     * Return group`s timetable for all period.
     *
     * @param groupId
     * @return
     * @throws IOException
     */
    public String getTimetable(String groupId) throws IOException {
        URL url = new URL(String.format("https://www.bsuir.by/schedule/rest/schedule/%s", groupId));
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }

    /**
     * !!!Can`t check this method. I think it should return xml-file with group`s timetable.
     *
     * @param groupName
     * @return
     * @throws IOException
     */
    public String getGroupTimetable(String groupName) throws IOException {
        URL url = new URL(String.format("https://www.bsuir.by/schedule/rest/schedule.xhtml?id=%s", groupName));
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }

    /**
     * Return current week.
     *
     * @return
     * @throws IOException
     */
    public String getCurrentWeek() throws IOException {
        URL url = new URL(String.format("https://www.bsuir.by/schedule/rest/currentWeek/date/%s",
                new SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())));     //something strange. It shows previous day. Date change at 1.00
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return in.readLine();
    }
}

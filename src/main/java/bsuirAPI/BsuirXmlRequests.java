package bsuirAPI;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Andrey on 13.07.2017.
 */
public class BsuirXmlRequests extends BsuirRequestCore implements BsuirRequestInterface {
    private static final Logger logger = Logger.getLogger(BsuirXmlRequests.class);

    /**
     * Return group`s timetable for all period.
     *
     * @param groupName
     * @return
     * @throws IOException
     */
    public String getTimetable(String groupName) throws IOException {

        String response;

        URL url = new URL(String.format("https://students.bsuir.by/api/v1/studentGroup/schedule?studentGroup=%s", groupName));

        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Accept", "application/xml");

        logger.info("Send request about timetable(\"https://students.bsuir.by/api/v1/studentGroup/schedule?studentGroup=" + groupName + "\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        response = in.readLine();

        return response;
    }


}

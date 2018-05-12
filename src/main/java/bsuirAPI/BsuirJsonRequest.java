package bsuirAPI;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BsuirJsonRequest extends BsuirRequestCore implements BsuirRequestInterface {

    private static final Logger logger = Logger.getLogger(BsuirJsonRequest.class);

    public String getTimetable(String groupName) throws IOException {

        StringBuffer response = new StringBuffer();
        String inputLine;

        URL url = new URL(String.format("https://students.bsuir.by/api/v1/studentGroup/schedule?studentGroup=%s", groupName));

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestProperty("Content-Type","application/json; charset=utf-8");

        logger.info("Send request about timetable(\"https://students.bsuir.by/api/v1/studentGroup/schedule?studentGroup=" + groupName + "\").");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }
}

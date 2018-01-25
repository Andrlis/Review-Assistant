package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kesso on 22.08.17.
 */
public class TimeLogic {
    public static Date getTodayDatePlusTime(String time) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String today = formatDate.format(date);
        SimpleDateFormat formatDateAndTime = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        Date answer = null;
        time.replaceAll("-.*", "");
        try {
            answer = formatDateAndTime.parse(today + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return answer;
    }
}

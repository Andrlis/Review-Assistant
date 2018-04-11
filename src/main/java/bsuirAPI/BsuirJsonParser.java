package bsuirAPI;

import bsuirAPI.jsonDeserislizers.LessonDeserializer;
import bsuirAPI.jsonDeserislizers.ScheduleDeserializer;
import bsuirAPI.jsonDeserislizers.TimetableDeserializer;
import bsuirAPI.timetable.Lesson;
import bsuirAPI.timetable.Schedule;
import bsuirAPI.timetable.Timetable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BsuirJsonParser implements BsuirParserInterface {

    public Timetable parseTimetable(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Schedule.class, new ScheduleDeserializer())
                .registerTypeAdapter(Lesson.class, new LessonDeserializer())
                .registerTypeAdapter(Timetable.class, new TimetableDeserializer())
                .create();

        return gson.fromJson(json, Timetable.class);
    }
}
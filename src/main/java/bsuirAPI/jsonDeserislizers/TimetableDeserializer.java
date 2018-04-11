package bsuirAPI.jsonDeserislizers;

import bsuirAPI.timetable.Schedule;
import bsuirAPI.timetable.Timetable;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TimetableDeserializer  implements JsonDeserializer<Timetable> {

    @Override
    public Timetable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
        JsonObject jsonObject = json.getAsJsonObject();
        Timetable timetable = new Timetable();

        JsonArray schedules = jsonObject.getAsJsonArray("schedules");
        for(JsonElement schedule : schedules) {
            timetable.getSchedules().add((Schedule) context.deserialize(schedule, Schedule.class));
        }

        return timetable;
    }
}
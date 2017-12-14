package bsuirAPI.bsuirScheduleJson;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ScheduleDeserializer  implements JsonDeserializer<Schedule> {

    @Override
    public Schedule deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        Schedule schedule = new Schedule();

        schedule.setWeekDay(jsonObject.get("weekDay").getAsString());

        JsonArray lessons = jsonObject.getAsJsonArray("schedule");
        for(JsonElement lesson : lessons) {
            schedule.getLessons().add((Lesson)context.deserialize(lesson, Lesson.class));
        }

        return schedule;
    }
}

package bsuirAPI.bsuirScheduleJson;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ScheduleKeeperDeserializer  implements JsonDeserializer<ScheduleKeeper> {

    @Override
    public ScheduleKeeper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        ScheduleKeeper scheduleKeeper = new ScheduleKeeper();

        JsonArray schedules = jsonObject.getAsJsonArray("schedules");
        for(JsonElement schedule : schedules) {
            scheduleKeeper.getSchedules().add((Schedule) context.deserialize(schedule, Schedule.class));
        }

        return scheduleKeeper;
    }
}

package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.lecturer.Lecturer;

import java.lang.reflect.Type;

public class LecturerConverter implements JsonSerializer<Lecturer>{
    @Override
    public JsonElement serialize(Lecturer lecturer, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("value", lecturer.getFullName());
        object.addProperty("id", lecturer.getId());

        return object;
    }
}

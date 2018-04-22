package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.group.SubGroup;

import java.lang.reflect.Type;

public class SubGroupConverter implements JsonSerializer<SubGroup> {
    @Override
    public JsonElement serialize(SubGroup subGroup, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("value", subGroup.getSubGroupNumber());
        object.addProperty("id", subGroup.getId());


        return object;
    }
}

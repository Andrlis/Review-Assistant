package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.group.Group;

import java.lang.reflect.Type;

public class GroupConverter implements JsonSerializer<Group> {

    @Override
    public JsonElement serialize(Group group, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("value", group.getNumberOfGroup());
        object.addProperty("id", group.getId().toString());
        return object;
    }
}

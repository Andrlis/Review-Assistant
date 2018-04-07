package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import jsonMaker.jsonData.ItogMark;

import java.lang.reflect.Type;

public class ItogMarkConverter implements JsonSerializer<ItogMark> {

    @Override
    public JsonElement serialize(ItogMark src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("value", Float.toString(src.getMark()));

        return object;
    }
}
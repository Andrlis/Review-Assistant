package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.lab.Lab;

import java.lang.reflect.Type;

public class LabConverter implements JsonSerializer<Lab> {
    @Override
    public JsonElement serialize(Lab src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("number-of-lab", src.getNumberOfLab());
        object.addProperty("key-word", src.getKeyWord());

        return object;
    }
}
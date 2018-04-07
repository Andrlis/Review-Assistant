package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.lab.IssuedLab;

import java.lang.reflect.Type;

public class IssuedLabConverter implements JsonSerializer<IssuedLab> {
    @Override
    public JsonElement serialize(IssuedLab src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("date-of-issue", src.getUniversityClassOfIssue().getDataTime());
        object.addProperty("coefficient", src.getCoefficientOfCurrentDeadline());

        return object;
    }
}
package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import jsonMaker.jsonData.ItogMark;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class ItogMarkConverter implements JsonSerializer<ItogMark> {

    @Override
    public JsonElement serialize(ItogMark src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        float mark = src.getMark();
        String pattern = "##0.00";
        String markStr = new DecimalFormat(pattern).format(mark);
        String markClass = "";
        if(mark >= 4) {
            markClass = "student-is-admissible";
        } else {
            markClass = "student-is-not-admissible";
        }

        object.addProperty("value", markStr);
        object.addProperty(
                "cell-class",
                markClass);

        return object;
    }
}
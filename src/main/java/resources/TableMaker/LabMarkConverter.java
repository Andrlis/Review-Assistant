package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.mark.LabMark;

import java.lang.reflect.Type;

public class LabMarkConverter implements JsonSerializer<LabMark> {
    @Override
    public JsonElement serialize(LabMark src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject labObject = new JsonObject();

        labObject.addProperty("cell-class", getCoeff(src.getCoefficient()));
        labObject.addProperty("value", (src.getMark() != null) ? src.getMark().toString() : "");
        labObject.addProperty("type", "lab");
        labObject.addProperty("id", src.getId().toString());

        return labObject;
    }

    static private String getCoeff(Double coeff) {
        if(coeff == null) {
            return "nocoeff";
        }

        if(coeff == 1) {
            return "coeff10";
        }

        return "coeff0" + (int)(coeff*10);
    }
}

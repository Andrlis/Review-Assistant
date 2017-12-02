package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.mark.LabMark;

import java.lang.reflect.Type;

public class LabMarkConverter implements JsonSerializer<LabMark> {

	private boolean editable;

	public LabMarkConverter(boolean editable) {
		this.editable = editable;
	}

    @Override
    public JsonElement serialize(LabMark src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject labObject = new JsonObject();

        labObject.addProperty("cell-class", "cell-ui" + ( editable ? " editable " : " " ) + getCoeff(src.getCoefficient()));
        labObject.addProperty("value", (src.getMark() != null) ? (src.getMark() != -1) ? src.getMark().toString() : "" : "");
        labObject.addProperty("type", "lab");
        labObject.addProperty("id", src.getId().toString());

        return labObject;
    }

    static public String getCoeff(Double coeff) {
        if(coeff == null) {
            return "nocoeff";
        }

        if(coeff == 0) {
            return "coeff-00";
        }
        if(coeff == 1) {
            return "coeff-10";
        }

        return "coeff-0" + (int)(coeff*10);
    }
}

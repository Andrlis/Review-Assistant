package jsonMaker.convetrters;

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

        labObject.addProperty(
                "cell-class",
                "cell-ui mark-cell lab-mark-cell" + ( editable ? " editable " : " " ) +
                        getCoeff(src.getCoefficient())
        );

        Integer mark = src.getMark();
        String m = "";
        if(mark != null && mark != -1){
            m = mark.toString();
        }

        labObject.addProperty(
                "value",
                 m
        );
        labObject.addProperty("type", "lab");
        labObject.addProperty("id", src.getId().toString());

        return labObject;
    }

    static public String getCoeff(Double coeff) {
	    if(coeff == -2){
	        return "cheat";
        }
        if(coeff == -1 || coeff == null) {
            return "";
        }
        if(coeff == 0) {
            return "coef-00";
        }
        if(coeff == 1) {
            return "coef-10";
        }
        return "coef-0" + Double.toString(coeff).substring(2, 3);
    }
}

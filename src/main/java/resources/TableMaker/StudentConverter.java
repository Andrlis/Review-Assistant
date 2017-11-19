package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Student;
import data.mark.LabMark;

import java.lang.reflect.Type;

public class StudentConverter implements JsonSerializer<Student> {
    @Override
    public JsonElement serialize(Student src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("cell-class", "student-name-call");
        object.addProperty("value", src.getFulName());

        for(LabMark labMark : src.getLabMarkList()) {
            JsonObject labObject = new JsonObject();


            labObject.addProperty("cell-class", StudentConverter.getCoeff(labMark.getCoefficient()));
            labObject.addProperty("value", labMark.getMark().toString());

            String labNum = new String("lab" + labMark.getIssuedLab().getLabDescription().getNumberOfLab());

            object.add(labNum, labObject);
        }
        return object;
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

package resources.TableMaker.Convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Student;
import data.mark.LabMark;

import java.lang.reflect.Type;

public class StudentConverter implements JsonSerializer<Student> {

    private boolean info_cell_editable;

    public StudentConverter() {
        info_cell_editable = false;
    }

    public StudentConverter(boolean info_cell_editable) {
        this.info_cell_editable = info_cell_editable;
    }

    @Override
    public JsonElement serialize(Student src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("cell-class", "cell-ui" + (info_cell_editable ? " info-cell-editable" : ""));
        object.addProperty("value", src.getFulName());
        object.addProperty("type", "student");
        object.addProperty("id", src.getId().toString());
        return object;
    }

}

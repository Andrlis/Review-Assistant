package jsonMaker.convetrters;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Student;

import java.lang.reflect.Type;

public class StudentConverter implements JsonSerializer<Student> {

    private boolean info_cell_editable;
    private boolean addLink;

    public StudentConverter( boolean addLink, boolean info_cell_editable) {
        this.addLink = addLink;
        this.info_cell_editable = info_cell_editable;
    }

    @Override
    public JsonElement serialize(Student src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("cell-class", "cell-ui" + (info_cell_editable ? " info-cell-editable" : ""));
        object.addProperty("value", src.getFulName());
        object.addProperty("type", "student");
        object.addProperty("id", src.getId().toString());
        if (addLink)
            object.addProperty("link", src.getGitURL());
        return object;
    }

}

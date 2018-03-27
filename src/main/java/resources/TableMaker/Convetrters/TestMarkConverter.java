package resources.TableMaker.Convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.mark.TestMark;

import java.lang.reflect.Type;

public class TestMarkConverter implements JsonSerializer<TestMark> {
    private boolean editable;

    public TestMarkConverter(boolean editable) {
        this.editable = editable;
    }

    @Override
    public JsonElement serialize(TestMark src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject testObject = new JsonObject();

        testObject.addProperty("cell-class", " test-mark-cell cell-ui mark-cell" + ( editable ? " editable" : ""));
        testObject.addProperty("value", (src.getMark() != null) ? (src.getMark() != -1) ? src.getMark().toString() : "" : "");
        testObject.addProperty("type", "test");
        testObject.addProperty("id", src.getId().toString());

        return testObject;
    }
}

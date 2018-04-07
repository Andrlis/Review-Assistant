package jsonMaker.convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import jsonMaker.jsonData.Template;

import java.lang.reflect.Type;

public class TemplateConverter implements JsonSerializer<Template> {

    @Override
    public JsonElement serialize(Template template, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("cell-class", template.getCellClass() );
        object.addProperty("value", template.getValue());

        return object;
    }
}

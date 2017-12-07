package resources.TableMaker.Convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import resources.TableMaker.Data.Class;

import java.lang.reflect.Type;

public class UniversityClassConverter implements JsonSerializer<Class>{

    private boolean editable;

	public UniversityClassConverter(boolean editable) {
	    this.editable = editable;
    }
	@Override
	public JsonElement serialize(Class src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();

		object.addProperty("cell-class", "cell-ui presence-cell" + ( editable ? " editable" : "" ) + ( src.isVisitable() ? " visited" : " missed" ));
		object.addProperty("value", src.isVisitable() ? "" : "н");

        object.addProperty("type", "class");
        object.addProperty("id", src.getId().toString());
		
		return object;
	}
}

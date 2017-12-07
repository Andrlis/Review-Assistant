package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.UniversityClass;

import java.lang.reflect.Type;

public class UniversityClassConverter implements JsonSerializer<UniversityClass>{
	@Override
	public JsonElement serialize(UniversityClass src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();

		object.addProperty("cell-class", "class-class");
		object.addProperty("value", src.getDate().toString());
		
		return object;
	}
}

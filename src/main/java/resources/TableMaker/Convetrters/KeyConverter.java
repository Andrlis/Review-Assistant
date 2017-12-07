package resources.TableMaker.Convetrters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import resources.TableMaker.Data.Key;

import java.lang.reflect.Type;

public class KeyConverter implements JsonSerializer<Key>{
	@Override
	public JsonElement serialize(Key src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();

		object.addProperty("cell-class", "cell-ui");
		object.addProperty("value", src.getKey());

		return object;
	}
}

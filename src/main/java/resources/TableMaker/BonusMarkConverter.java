package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Student;

import java.lang.reflect.Type;

public class BonusMarkConverter implements JsonSerializer<BonusMark> {
	private boolean editable;

	public BonusMarkConverter(boolean editable) {
		this.editable = editable;
	}

    @Override
    public JsonElement serialize(BonusMark src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("cell-class", "cell-ui" + ( editable ? " editable" : "" ));

         object.addProperty("value", (src.getMark() != null) ? (src.getMark() != -1) ? src.getMark().toString() : "" : "");

        object.addProperty("type", "bonus");
        object.addProperty("id", src.getId().toString());


        return object;
    }
}

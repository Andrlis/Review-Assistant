package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Student;

import java.lang.reflect.Type;

public class StudentRedactConverter implements JsonSerializer<Student> {
	@Override
	public JsonElement serialize(Student src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject object = new JsonObject();

		JsonObject nameObject = new JsonObject();
		nameObject.addProperty("call-class", "name");
		nameObject.addProperty("value", src.getFulName());

		JsonObject eMailObject = new JsonObject();
		eMailObject.addProperty("call-class", "eMail");
		eMailObject.addProperty("value", src.geteMail());

		JsonObject gitUserNameObject = new JsonObject();
		gitUserNameObject.addProperty("call-class", "gitUserName");
		gitUserNameObject.addProperty("value", src.getGitUserName());

		JsonObject gitRepoNameObject = new JsonObject();
		gitRepoNameObject.addProperty("call-class", "gitRepoName");
		gitRepoNameObject.addProperty("value", src.getGitRepoName());


		object.addProperty("cell-class", "student");
		object.addProperty("id", src.getId().toString());
		object.add("name", nameObject);
		object.add("eMail", eMailObject);
		object.add("gitUserName", gitRepoNameObject);
		object.add("gitRepoName", gitRepoNameObject);

		return object;
	}
}

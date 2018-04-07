package jsonMaker.jsonData;

import java.util.ArrayList;
import java.util.Set;

public class Key {
	private String key;

	public Key() {}

	public Key(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static ArrayList<Key> getKeyList(Set<String> list) {
		ArrayList<Key> keys = new ArrayList<Key>();
		for(String string : list) {
			keys.add(new Key(string));
		}

		return keys;
	}
}
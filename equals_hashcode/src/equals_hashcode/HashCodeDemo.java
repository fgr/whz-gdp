package equals_hashcode;

import java.util.HashMap;
import java.util.Map;

public class HashCodeDemo {
	public static void main(String[] args) {
		Map<Person, String> m = new HashMap<>();

		m.put(new Person("John", "Doe"), "0375-3154");

		// ...
		// spaeter, anderswo
		// ...

		String johnsTelNum = m.get(new Person("John", "Doe"));

		System.out.println(johnsTelNum);
	}
}

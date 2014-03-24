package equals_hashcode;

import java.util.List;

public class EqualsDemo {
	public boolean isRegestered(List<Person> registered, String firstName, String lastName) {
		Person p = new Person(firstName, lastName);
		return registered.contains(p);
	}
}

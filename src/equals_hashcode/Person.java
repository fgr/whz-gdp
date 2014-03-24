package equals_hashcode;

public class Person {
	private String fname;
	private String lname;

	private Address address;

	public Person(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if ((obj instanceof Person) == false)
			return false;

		Person otherP = (Person) obj;
		if (lname.equals(otherP.lname) //
				&& fname.equals(otherP.fname) //
				&& address.equals(otherP.address))
			return true;
		else
			return false;

	}

	@Override
	public int hashCode() {
		int result = 17;

		{
			int c = fname.hashCode();
			result = 31 * result + c;
		}

		{
			result = 31 * result + lname.hashCode();
		}
		
		{
			int c = address.hashCode();
			result = 31 * result + c;
		}

		return result;
	}

}

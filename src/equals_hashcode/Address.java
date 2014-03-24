package equals_hashcode;

public class Address {
	public static class Street {
		public final String street;
		public final float number;

		public Street(String street, short number) {
			this.street = street;
			this.number = number;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (obj == this)
				return true;
			if (!(obj instanceof Street))
				return false;

			Street o = (Street) obj;
			return street.equals(o.street) && number == o.number;
		}

		private volatile int hashCode = 0;

		@Override
		public int hashCode() {
			int result = hashCode;
			if (result == 0) {
				result = 17;

				{
					int c = 0;
					if (street != null)
						c = street.hashCode();
					result = 31 * result + c;
				}

				result = 31 * result + Float.floatToIntBits(number);

				hashCode = result;
			}
			return hashCode;
		}
	}

	public final Street street;
	public final String city;
	public final boolean withinEU;

	public Address(String street, short number, String city) {
		this(new Street(street, number), city);
	}

	public Address(Street street, String city) {
		this(street, city, true);
	}

	public Address(Street street, String city, boolean withinEU) {
		if (street == null)
			throw new IllegalArgumentException("No street provided.");
		if (city == null)
			throw new IllegalArgumentException("No city provided.");
		this.street = street;
		this.city = city;
		this.withinEU = withinEU;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Address))
			return false;

		Address o = (Address) obj;
		return street.equals(o.street) //
				&& city.equals(o.city) //
				&& withinEU == o.withinEU;
	}

	@Override
	public int hashCode() {
		int result = 17;

		{
			int c = street.hashCode();
			result = 31 * result + c;
		}

		{
			int c = city.hashCode();
			result = 31 * result + c;
		}

		{
			int c = withinEU ? 1 : 0;
//			int c;
//			if(withinEU)
//				c = 1;
//			else
//				c = 0;
			result = 31 * result + c;
		}

		return result;
	}
}

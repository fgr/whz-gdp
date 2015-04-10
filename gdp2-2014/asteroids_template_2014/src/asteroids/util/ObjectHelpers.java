package asteroids.util;

public final class ObjectHelpers {
	public static void assertNotNull(Object o) {
		if (o == null)
			throw new NullPointerException("Must not be null!");
	}
}

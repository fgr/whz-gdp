package equals_hashcode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetDemo {
	public static void main(String[] args) {
		final int numOfObjects = 10_000_000;
		final int rounds = 5_000_000;
		final int key = 42;

		for (int i = 0; i < 3; ++i) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Durchlauf " + (i + 1) + ":");
			{
				Set<MyInt1> myint1s = createMyInt1s(numOfObjects);
				MyInt1 keyMyInt1 = new MyInt1(key);

				long timeStart = System.currentTimeMillis();

				for (int r = 0; r < rounds; ++r)
					myint1s.contains(keyMyInt1);

				long timeSpent = System.currentTimeMillis() - timeStart;
				System.out.println("	MyInt1-Dauer: " + timeSpent + " Millisekunden");
			}

			{
				Set<MyInt2> myint2s = createMyInt2s(numOfObjects);
				MyInt2 keyMyInt2 = new MyInt2(key);

				long timeStart = System.currentTimeMillis();

				for (int r = 0; r < rounds; ++r)
					myint2s.contains(keyMyInt2);

				long timeSpent = System.currentTimeMillis() - timeStart;
				System.out.println("	MyInt2-Dauer: " + timeSpent + " Millisekunden");
			}
		}
	}

	private final static class MyInt1 {
		public final int i;

		public MyInt1(int i) {
			this.i = i;
		}
	}

	private static Set<MyInt1> createMyInt1s(int howMany) {
		Random r = new Random();

		Set<MyInt1> myint1s = new HashSet<>();

		while (howMany-- > 0) {
			int i = r.nextInt();
			MyInt1 m = new MyInt1(i);
			if (myint1s.contains(m))
				continue;
			myint1s.add(m);
		}

		return myint1s;
	}

	private static Set<MyInt2> createMyInt2s(int howMany) {
		Random r = new Random();

		Set<MyInt2> myint2s = new HashSet<>();

		while (howMany-- > 0) {
			int i = r.nextInt();
			MyInt2 m = new MyInt2(i);
			if (myint2s.contains(m))
				continue;
			myint2s.add(m);
		}

		return myint2s;
	}

	private final static class MyInt2 {
		public final int i;

		public MyInt2(int i) {
			this.i = i;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (this == obj)
				return true;
			if (!(obj instanceof MyInt2))
				return false;
			MyInt2 o = (MyInt2) obj;
			return this.i == o.i;
		}

		@Override
		public int hashCode() {
			return i;
		}
	}
}

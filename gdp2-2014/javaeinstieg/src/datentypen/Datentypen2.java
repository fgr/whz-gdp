package datentypen;

/**
 * Arrays und Schleifen
 * <p>
 * Die Größe eines Arrays ist statisch, nicht dynamisch änderbar.
 */
class Datentypen2 {
	public static void main(String[] args) {
		String hello[] = new String[] { "Hallo", "Welt", "!" };

		// Variante 1
		//
		for (int i = 0; i < hello.length; ++i) {
			String elem = hello[i];
			System.out.println(elem);
		}

		// Variante 2
		//
		// for (int i = 0; i < hello.length; ++i) {
		// System.out.println(hello[i]);
		// }

		// Variante 3
		//
		// int i = 0;
		// while (i < hello.length) {
		// System.out.println(hello[i]);
		// ++i;
		// }

		// Variante 4
		//
		// for (String elem : hello) {
		// System.out.println(elem);
		// }
	}
}

package datentypen;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * HashMap
 */
public class Datentypen5 {
	public static void main(String[] args) {
		HashMap<String, Integer> woerter = new HashMap<>();

		// Eingabe
		//
		System.out.print(//
				"Bitte Wörter eingeben und mit Enter gefolgt von Ctrl-d abschließen: ");
		Scanner scanner = new Scanner(System.in); // Ressource, die nach
													// Benutzung freigegeben
													// werden muss!
		while (scanner.hasNext()) {
			String wort = scanner.next();
			woerter.put(wort, 1);

			// Richtig 1:
			// if (woerter.containsKey(wort)) {
			// int anzahl = woerter.get(wort); // auto boxing Integer zu int
			// ++anzahl;
			// woerter.put(wort, anzahl);
			// } else {
			// woerter.put(wort, 1);
			// }

			// // Richtig 2:
			// Integer anzahl = woerter.get(wort);
			// if (anzahl == null) {
			// anzahl = 1;
			// } else {
			// ++anzahl;
			// }
			// woerter.put(wort, anzahl);
		}
		scanner.close(); // Resource Leak: Hierher kommen wir u.U. nie!

		// Richtig 1 (try mit finally)
		//
		// Scanner scanner = new Scanner(System.in);
		// try {
		// while (scanner.hasNext()) {
		// String wort = scanner.next();
		// Integer anzahl = woerter.get(wort);
		// if (anzahl == null) {
		// anzahl = 1;
		// } else {
		// ++anzahl;
		// }
		// woerter.put(wort, anzahl);
		// }
		// }
		// } finally {
		// scanner.close();
		// }

		// Richtig 2 (try with resource)
		//
		// try (Scanner scanner = new Scanner(System.in)) {
		// String wort = scanner.next();
		// Integer anzahl = woerter.get(wort);
		// if (anzahl == null) {
		// anzahl = 1;
		// } else {
		// ++anzahl;
		// }
		// woerter.put(wort, anzahl);
		// }

		// Ausgabe
		for (Entry<String, Integer> e : woerter.entrySet()) {
			String wort = e.getKey();
			int anzahl = e.getValue();
			System.out.println(//
					String.format("Wort '%s' kommt %d mal vor.", wort, anzahl));
		}
	}
}

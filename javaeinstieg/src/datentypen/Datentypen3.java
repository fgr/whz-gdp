package datentypen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * ArrayList
 * <p>
 * Die Größe einer {@link ArrayList} ist dynamisch; sie wächst oder schrumpft.
 */
public class Datentypen3 {
	public static void main(String[] args) {
		ArrayList<String> zeilen = new ArrayList<String>();

		// Eingabe
		//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out
					.print("Bitte Zeile eingeben und mit Enter abschließen: ");
			String zeile;
			try {
				zeile = br.readLine();
			} catch (IOException e) {
				System.err.println("Fehler beim Einlesen!");
				return;
			}
			if ("ende".equals(zeile)) {
				break;
			}
			zeilen.add(zeile);
		}

		// Ausgabe (Varinte 1a)
		//
		for (int i = 0; i < zeilen.size(); ++i) {
			String z = zeilen.get(i);
			System.out.println(i + "te Zeile: " + z);
		}

		// // Ausgabe (Varinte 1b)
		// //
		// for (int i = 0; i < zeilen.size(); ++i) {
		// System.out.println(i + "te Zeile: " + zeilen.get(i));
		// }
		//
		// // Ausgabe (Varinte 2)
		// //
		// for (String z : zeilen) {
		// System.out.println("Zeile: " + z); // kein Index verfügbar
		// }
		//
		// Ausgabe (Varinte 3)
		//
		// for (Iterator<String> it = zeilen.iterator(); it.hasNext();) {
		//
		// Iterator<String> it = zeilen.iterator();
		// while (it.hasNext()) {
		// String z = it.next();
		// System.out.println("Zeile: " + z); // kein Index verfügbar
		// }
	}
}

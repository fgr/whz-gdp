package datentypen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * HashSet
 */
public class Datentypen4 {
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

		HashSet<String> zeilenOhneDuplikate = new HashSet<>();
		// Duplikate aussorieren
		//
		for (String z : zeilen) {
			if (zeilenOhneDuplikate.contains(z) == false) {
				zeilenOhneDuplikate.add(z);
			}

			// Variante 2
			// if (!zeilenOhneDuplikate.contains(z)) {
			zeilenOhneDuplikate.add(z);
			// }

			// Variante 3
			// if (zeilenOhneDuplikate.contains(z)) {
			// continue;
			// }
		}

		// Ausgabe 
		//
		for (int i = 0; i < zeilenOhneDuplikate.size(); ++i) {
			// String z = zeilenOhneDuplikate.get(i); // Kein Index-basierter Zugriff aus HashSet möglich!
			// System.out.println(i + "te Zeile: " + z);	
		}

		for(String z : zeilenOhneDuplikate) {
			System.out.println("Ohne Duplikate: " + z);
		}
	}
}

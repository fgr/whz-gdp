package datentypen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Arrays und Schleifen
 * <p>
 * Die Größe eines Arrays ist statisch, nicht dynamisch änderbar.
 */
class Datentypen2a {
	public static void main(String[] args) throws IOException {
		final int maxZeilen = 10;
		String zeilen[] = new String[maxZeilen];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < maxZeilen; ++i) {
			System.out
					.print("Bitte Zeile eingeben und mit Enter abschließen: ");
			String zeile = br.readLine();

			if ("ende".equals(zeile)) {
				break;
			} else {
				zeilen[i] = zeile;
			}
		}

		for (String z : zeilen) {
			System.out.println("Zeile: " + z);
		}
	}
}

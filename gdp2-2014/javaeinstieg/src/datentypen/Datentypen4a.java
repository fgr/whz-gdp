package datentypen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * HashSet
 */
public class Datentypen4a {
	public static void main(String[] args) {
		HashSet<String> zeilenOhneDuplikate = new HashSet<String>();

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
			if (zeilenOhneDuplikate.add(zeile)) {
				System.out.println("Duplikat!");
			}
		}

		for (String z : zeilenOhneDuplikate) {
			System.out.println("Zeile: " + z);
		}
	}
}

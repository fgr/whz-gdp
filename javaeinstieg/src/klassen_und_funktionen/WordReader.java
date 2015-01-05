package klassen_und_funktionen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WordReader {
	public List<String> readWords() {
		ArrayList<String> words = new ArrayList<>();

		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				String w = readWord(scanner);
				if (w == null)
					break;
				words.add(w);
			}

			// String w;
			// while ((w = readWord(scanner)) != null) {
			// words.add(w);
			// }
		}

		return words;
	}

	private String readWord(Scanner s) {
		if (!s.hasNext())
			return null;
		return s.next();
	}
}

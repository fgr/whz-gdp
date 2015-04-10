package klassen_und_funktionen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Main {
	public static void main(String[] args) {

		// Eingabe
		//
		System.out.print(//
				"Bitte Wörter eingeben und mit Enter gefolgt von Ctrl-d abschließen: ");
		WordReader wr = new WordReader();
		List<String> words = wr.readWords();

		WordCounter wc = new WordCounter();
		Map<String, Integer> wordCount = wc.countWords(words);

		// Ausgabe
		for (Entry<String, Integer> e : wordCount.entrySet()) {
			String wort = e.getKey();
			int anzahl = e.getValue();
			System.out.println(//
					String.format("Wort '%s' kommt %d mal vor.", wort, anzahl));
		}
	}
}

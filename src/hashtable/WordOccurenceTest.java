package hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordOccurenceTest {

	private static String fileName = "Alice.txt";

	/**
	 * Takes text file as input and generate HashStructure to store word as key and
	 * count of occurrences as value
	 * 
	 * @param inputStream
	 */
	public static void buildCountTable() {
		Scanner scanner = null;
		List<String> wordsList = new ArrayList<String>();
		try {
			File file = new File(WordOccurenceTest.class.getClassLoader().getResource(fileName).getFile());
			scanner = new Scanner(file);
			String regex = "[^a-zA-Z0-9/-]";

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				line = line.replaceAll("\t", "");
				line = line.replaceAll("\n", "");
				line = line.replaceAll(regex, " ").toLowerCase();
				String[] words = line.split(" +");
				for (String word : words) {
					String w = word.trim();
					if (!w.equals("")) {
						wordsList.add(w);
					}
				}

			}

			HashStructure hashTable = new HashStructure();

			for (String word : wordsList) {
				if (hashTable.find(word) == null) {
					hashTable.insert(word, 1);
				} else {
					hashTable.increase(word);
				}
			}

			StringList keysList = hashTable.list();

			HashNode banknode = hashTable.find("bank");
			System.out.println("Count for bank: " + banknode.value);
			hashTable.increase("bank");
			System.out.println("Count for bank after increase: " + banknode.value);

//			hashTable.delete("bank");
//			hashTable.increase("bank");

			PrintWriter writer = new PrintWriter("Output.txt", "UTF-8");
			while (keysList != null) {
				HashNode node = hashTable.find(keysList.data);
				writer.println(node.key + ": " + node.value);
				// System.out.println(node.key + ": " + node.value);
				keysList = keysList.next;
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		buildCountTable();
	}

}

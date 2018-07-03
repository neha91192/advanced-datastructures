package binomialheap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinHeapTest {

	private static String fileName = "input.txt";

	public static void main(String[] args) {

		Scanner scanner = null;
		try {
			File file = new File(BinomialHeapTest.class.getClassLoader().getResource(fileName).getFile());
			scanner = new Scanner(file);
			BinomialHeap binHeap = new BinomialHeap();
			binHeap = binHeap.create();
			BinomialNode Nil = binHeap.Nil;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] inputkeys = line.split(" ");
				for (String key : inputkeys) {
					BinomialNode binNode = new BinomialNode(Nil, Nil, Nil, Integer.parseInt(key));
					binHeap.insert(binHeap, binNode);
				}

			}
			BinomialNode node = binHeap.findMinimum(binHeap);
			System.out.println(node.key);
			binHeap.extractMin(binHeap);
			binHeap.printUtil(binHeap.head);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

package redblacktrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RBTTest {

	private static String fileName = "RBTinput.txt";

	public static void main(String[] args) {

		Scanner scanner = null;
		try {
			File file = new File(RBTTest.class.getClassLoader().getResource(fileName).getFile());
			scanner = new Scanner(file);
			RedBlackTree rbt = new RedBlackTree();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] inputkeys = line.split(" ");
				for (String key : inputkeys) {
					Node node = new Node(Integer.parseInt(key));
					rbt.insert(node);
				}

			}
			displayUtil(rbt.root, 0);
			// rbt.sort(rbt.root);
			// Node node = rbt.search(rbt.root, 12);
			// if (node.key != 0) {
			// System.out.println("Node value: " + node.key);
			// } else {
			// System.out.println("Node value not found!");
			// }
			// Node treeMin = rbt.treeMin(rbt.root);
			// System.out.println("Min value in tree: " + treeMin.key);
			//
			// Node newnode = new Node(20);
			// rbt.insert(newnode);
			//
			// Node treeMax = rbt.treeMax(rbt.root);
			// System.out.println("Max value in tree: " + treeMax.key);
			//
			// Node successor = rbt.successor(rbt.root.left);
			// System.out.println("Successor in tree:" + successor.key);
			//
			// Node predecessor = rbt.predecessor(rbt.root);
			// System.out.println("Predecessor in tree:" + predecessor.key);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void display(Node n) {
		displayUtil(n, 0);
	}

	private static void displayUtil(Node n, int space) {
		if (n.key == 0) {
			return;
		}
		space += 10;
		displayUtil(n.right, space);
		System.out.println();
		for (int i = 10; i < space; i++) {
			System.out.print(" ");
		}
		System.out.println(n.key + "(" + n.color + ")");
		// System.out.println(n.color);
		displayUtil(n.left, space);
	}

}

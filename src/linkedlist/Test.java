package linkedlist;

public class Test {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {

		// test for integer
		ILinkedList<Integer> linkedList = new MyLinkedList<>();
		boolean result1 = linkedList.addLast(2);
		boolean result2 = linkedList.addLast(3);
		boolean result3 = linkedList.addLast(4);
		linkedList.addLast(5);

		printReverse(linkedList.getHead());

		// detect loop
		boolean loopExist = linkedList.detectLoop();
		if (loopExist) {
			System.out.println("Loop Exist in linked list");
		} else {
			System.out.println("No loop in linked list");
		}

		// linkedList.reverse();
		linkedList.addAfterN(3, 2);
		linkedList.addAfterN(3, 4);
		if (result1 & result2 & result3) {
			System.out.println("Success for Integers");
		} else {
			System.out.println("Error in implementing LinkedList for integers");
		}

		// test for search
		int value = 3;
		Node resultNode = linkedList.search(value);

		// test for string
		ILinkedList<String> linkedListS = new MyLinkedList<>();
		boolean res1 = linkedListS.addLast("Neha");
		boolean res2 = linkedListS.addLast("Harmit");
		boolean res3 = linkedListS.addFirst("kitto");
		if (res1 & res2 & res3) {
			System.out.println("Success for String");
		} else {
			System.out.println("Error in implementing LinkedList for String");
		}

		// test for remove
		linkedListS.remove("kitto");

		// calling for string case only
		printLinkedList(linkedListS);

	}

	/**
	 * Prints the linked list in given order
	 * 
	 * @param linkedList
	 */
	@SuppressWarnings("rawtypes")
	public static void printLinkedList(ILinkedList linkedList) {
		Node head = linkedList.getHead();
		Node tmp = head;
		while (tmp != null) {
			System.out.print(tmp.getData() + " ");
			tmp = tmp.getNext();
		}
	}

	/**
	 * Prints the linked list in reverse order
	 * 
	 * @param linkedList
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void printReverse(Node head) {
		Node tmp = head;
		if (tmp.getNext() != null) {
			printReverse(tmp.getNext());
		}
		System.out.print(tmp.getData() + " ");
	}
}
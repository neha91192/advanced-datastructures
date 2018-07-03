package linkedlist;

import java.util.LinkedList;

/**
 * 
 * @author nehshukl
 *
 * @param <T>
 */
public interface ILinkedList<T> {

	/**
	 * Appends an element of generic type in the end of the linked list. Maintains a
	 * header to trace the entire linked list.
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> boolean addLast(T element);

	/**
	 * Appends an element of generic type at the start of the linked list. The new
	 * element gets added before the first element and the head reference is
	 * assigned to the new node.
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> boolean addFirst(T element);

	/**
	 * Appends an element immediately after the given Node the given node is
	 * encountered.
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> boolean addAfterN(T element, T N);

	/**
	 * Finds and returns the element data sent in the request parameter. If the
	 * element is successfully found, it returns the requested node. If no element
	 * matching to this is found in the list, it returns null/ element not found
	 * exception.
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "hiding" })
	public <T> Node search(T element);

	/**
	 * Deletes a particular element from the linked list.
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> boolean remove(T element);

	/**
	 * Fetches first element i.e head of the list
	 * 
	 * @return head
	 */
	public Node<T> getHead();

	/**
	 * Reverses the entire list element. If the list looks like this: 1 --> 2 --> 3
	 * --> 4 --> NULL, after calling this method, the list will become like this: 4
	 * --> 3 --> 2 --> 1 --> NULL
	 */
	public void reverse();

	/**
	 * Finds cycle in the list. There are two generic ways of solving this problem -
	 * Standard tracking of the elements already visited either by creating and
	 * storing hashes of the visited node or storing the unique addresses of each
	 * node.
	 * 
	 * @return
	 */
	public boolean detectLoop();

	/**
	 * Adds node at the end of the list. Ideally this method is not required and
	 * advised not to use for any other purpose except for creating and testing
	 * detectLoop method
	 * 
	 * @param n
	 */
	@SuppressWarnings("rawtypes")
	public void appendNode(Node n);

	/**
	 * Adds node directly at the start of the linked list.
	 * 
	 * @param n
	 */
	@SuppressWarnings("rawtypes")
	public void appendNodeAtFirst(Node n);

	/**
	 * Sorts the linked list using merge sort
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public LinkedList<T> sort(Node headRef);
}

package linkedlist;

import java.util.LinkedList;

/**
 * 
 * @author nehshukl
 *
 * @param <T>
 */
public class MyLinkedList<T> implements ILinkedList<T> {
	/**
	 * Stores the reference to first node
	 */
	@SuppressWarnings("rawtypes")
	private Node head;

	/**
	 * Fetches the instance of this class
	 * 
	 * @return current list
	 */
	public MyLinkedList<T> getMyLinkedList() {
		return this;
	}

	/**
	 * Fetches first element i.e head of the list
	 * 
	 * @return head
	 */
	@SuppressWarnings("unchecked")
	public Node<T> getHead() {
		return head;
	}

	/**
	 * Returns last element i.e tail of the list
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Node<T> getTail() {
		Node tmp = head;
		while (tmp.getNext() != null) {
			tmp = tmp.getNext();
		}
		return tmp;
	}

	/**
	 * Adds a new node in the list. Fetches last node of the list (tail) and appends
	 * the new node instance with the required values. Finding tail takes O(n)
	 * complexity and therefore in case complexity needs to be improved, tail
	 * reference can also be maintained. But in ideal case, a Linked List has to be
	 * traversed starting from the head node.
	 * 
	 * Method: 1. Find if head is null 2. If head is null, create a new node, assign
	 * head reference to it, set next reference of that new node to null 3. Else,
	 * find the last node of the list (tail), set new node to tail's next reference,
	 * keep next of new node to null
	 * 
	 */
	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	public final <T> boolean addLast(T element) {
		if (element != null) {
			Node<T> newNode = new Node<T>(element, null);
			if (head == null) {
				head = newNode;
			} else {
				Node tail = getTail();
				tail.setNext(newNode);
			}

		}

		return true;

	}

	/**
	 * Adds a new node immediately after node with value N is encountered. There can
	 * be a modification to this method where a new node is added after all the
	 * possible N's have been encountered. This provisioning helps in maintaining
	 * the sorted list. For example if the list contains elements like this: 2 --> 3
	 * --> 3 --> 5 --> NULL, adding element 4 after second 3 makes sense. This
	 * functionality can be added by maintaining an additional reference node which
	 * keeps a track of last encountered node after which new node has to be added.
	 * 
	 */
	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> boolean addAfterN(T element, T N) {
		if (element != null) {
			if (head != null) {
				Node<T> newNode = new Node<T>(element, null);
				Node tmp = head;
				while (tmp.getNext() != null) {
					if (tmp.getData().equals(N)) {
						newNode.setNext(tmp.getNext());
						tmp.setNext(newNode);
						return true;
					}
					tmp = tmp.getNext();
				}

			}
		}
		return false;
	}

	/**
	 * Adds new node as the first element of the list. The complexity of the list
	 * method is O(1) since the head reference is maintained throughout the program.
	 * Further logic is simple. Create a new node and swap it with the head node
	 * using tmp reference node.
	 */
	@SuppressWarnings({ "hiding", "rawtypes", "unchecked" })
	@Override
	public final <T> boolean addFirst(T element) {
		Node<T> newNode = new Node<T>(element, null);
		if (element != null) {
			if (head == null) {
				head = newNode;
			} else {
				Node tmp = head;
				head = newNode;
				newNode.setNext(tmp);
			}
		}
		return true;
	}

	/**
	 * Performs linear search to find matching element in the list.Traversing of the
	 * list remains the same and this method immediately returns the node containing
	 * required element. The complexity of this method is again O(n)
	 */
	@SuppressWarnings({ "rawtypes", "hiding" })
	@Override
	public <T> Node search(T element) {
		if (element != null) {
			if (head != null) {
				Node tmp = head;
				while (tmp.getNext() != null) {
					if (tmp.getData().equals(element)) {
						return tmp;
					}
					tmp = tmp.getNext();
				}
			}
		}
		return null;
	}

	/**
	 * Performs simple deletion of the element to be removed. It requires search
	 * operation where tmp or traversing node needs to be a place before the element
	 * needs to be deleted so that the references of the previous node can be
	 * updated to the next node of the element to be deleted.
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> boolean remove(T element) {
		if (element != null) {
			if (head != null) {
				Node<T> tmp = new Node<T>(null, head);
				while (tmp.getNext() != null) {
					if (tmp.getNext().getData().equals(element)) {
						tmp.setNext(tmp.getNext().getNext());
						return true;
					}
					tmp = tmp.getNext();
				}
			}
		}
		return false;
	}

	/**
	 * Method: tail keeps a track of the sublist that is formed. itr helps to keep a
	 * reference of the immediate node to be transfered to the new list. head
	 * variable maintains the original list. For example, if the list is like this :
	 * 2 --> 3 --> 4 --> 5 In the first pass (before while loop starts), tail will
	 * point to 2, head will point to 3, itr will also point to 3 and the link is
	 * broken by setting tail's next to NULL. Now the list would look like this
	 * (broken into 2 list): new List (NULL <-- 2 where tail variable remains with
	 * 2) and original list ( 3 --> 4 --> 5 --> NULL where itr and head remains with
	 * 3) While loop starts now. Head is shifted by 1 position. itr makes connection
	 * with the new list using tail variable ( 3 is added to new list), tail is set
	 * to the external node of the new list, i.e 3, and itr goes back to the
	 * original position with head. List look like this now (NULL <-- 2 <--3 where
	 * tail is pointing to 3) and ( 4 --> 5 --> NULL where itr and head point to 4)
	 * Same thing gets repeated in the next iteration and the list after this pass
	 * will be ( NULL <-- 2 <-- 3 <-- 4 ) and (5 --> NULL) now itr and head point to
	 * 5 and tail points to 4. while loop gets completed when itr reaches last node.
	 * After end of the while loop, set next node of the itr to the tail element of
	 * the new list. That's how the new reversed list is formed with the head
	 * maintained at the start of the new list.
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reverse() {
		Node tail = head;

		head = head.getNext();
		Node itr = head;
		tail.setNext(null);

		while (itr.getNext() != null) {
			head = head.getNext();
			itr.setNext(tail);
			tail = itr;
			itr = head;

		}
		itr.setNext(tail);

	}

	/**
	 * Implementing Floyd's algorithm over here. It says maintain two pointers of
	 * the list. First one is a slow pointer which traverses on every node and
	 * second is a fast pointer which traverses only alternate nodes. When the loop
	 * starts, the condition of the loop breaks when both the pointers collide at
	 * one position. Loop doesn't exist if one the pointer reaches NULL position
	 * before that.
	 */
	@SuppressWarnings("rawtypes")
	public boolean detectLoop() {
		Node slow = head;
		Node fast = slow.getNext();

		while (slow != fast) {

			if (slow.getNext() != null && fast.getNext() != null) {
				slow = slow.getNext();
				fast = fast.getNext().getNext();
			} else {
				return false;
			}

		}
		return true;
	}

	/**
	 * Adds node at the end of the list. Ideally this method is not required and
	 * advised not to use for any other purpose except for creating and testing
	 * detectLoop method
	 * 
	 * @param n
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void appendNode(Node n) {
		Node tail = getTail();
		if (tail != null) {
			tail.setNext(n);
		}
	}

	/**
	 * Adds node directly at the start of the linked list.
	 * 
	 * @param n
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void appendNodeAtFirst(Node n) {
		if (n != null && n.getData() != null) {
			if (head == null) {
				head = n;
			} else {
				Node tmp = head;
				head = n;
				n.setNext(tmp);
			}
		}
	}

	/**
	 * Uses merge sort to sort the linked list. To start with, it requires head
	 * reference of the list. The algorithm then divides the given linked list into
	 * two sub-linked list until the head contains only single element or the head
	 * is NULL. Later on, the list is recursively merged using mergeSorted() method.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public LinkedList<T> sort(Node headRef) {

		if (headRef == null || (headRef != null && headRef.getNext() != null)) {

		} else {

		}
		return null;
	}
	


}

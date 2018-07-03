package linkedlist;

public class Node<T>{
	
	public Node (T value, Node<T> next){
		this.data = value;
		this.next = next;
	}
	/**
	 * Stores next element of the linked list
	 */
	private Node<T> next;
	/**
	 * Contains the value stored in the node.
	 */
	private T data;
	
	/**
	 * Returns the next node in the list
	 * @return next
	 */
	public Node<T> getNext() {
		return next;
	}
	/**
	 * Sets the next node in the list
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * Returns value of the node
	 * @return value
	 */
	public Object getData() {
		return data;
	}
	/**
	 * Sets the value of the node
	 * @param value
	 */
	public void setData(T value) {
		this.data = value;
	}
	
	
}
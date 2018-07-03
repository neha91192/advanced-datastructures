package linkedlist.intersection;
/**
 * @author nehashukla
 *
 */
public class MyLinkedList {
	
	/**
	 * Inner class to create Node instance
	 * @author nehashukla
	 *
	 */
	public class Node {
		
		public String data;
		public Node next;
		
		public Node(String data, Node next){
			this.data= data;
			this.next = next;
		}
		
	}
	/**
	 * It is given that the two lists with headA and headB intersects. This method returns common intersection point between the linked list. 
	 * @param headA is the pointer used for Linked List A
	 * @param headB is the pointer used for Linked List B
	 * @return commonNode which denotes the intersection point. Returns null if no intersection is found
	 */
	public Node findIntersection(Node headA, Node headB) {
		int countA = findLength(headA);
		int countB = findLength(headB);
		
		Node offset = null; 
		
		Node commonNode = null;
	
		if(countA > countB) {
			//creates offset pointer by shifting a-b element starting from headA
			int count = countA-countB;
			offset= adjustOffset(headA, count);
			commonNode = traverse(offset, headB);
		}	
		else if(countB >= countA){
			//creates offset pointer by shifting a-b element starting from headB
			int count = countB-countA;
			offset = adjustOffset(headB, count);
			commonNode = traverse(offset, headA);
		}
		else {
		commonNode = traverse(headA, headB); //case where both the linked list are of equal length 
		}
		
		return commonNode;
		
	}
	/**
	 * Responsible for calculating number of nodes in the linked list.
	 * @param node denotes head pointer of the list
	 * @return the integer count of no of nodes in the list
	 */
	private int findLength(Node node) {
		int count =0;
		Node tmp = node;
		while(tmp!=null) {
			count++;
			tmp=tmp.next;
		}
		return count;
	}
	/**
	 * Returns position of the list after incrementing the pointer by the required offset count.
	 * @param head denotes starting point of the list
	 * @param count denotes integer difference between lengths of the two lists
	 * @return tmp node containing the offset 
	 */
	private Node adjustOffset(Node head, int count) {
		Node tmp = head;
		for(int i=0; i<count; i++) {
			tmp = tmp.next;
		}
		return tmp;
	}
	/**
	 * Traverses the two list and returns the first common occurrence of both the list.
	 * @param node contains the starting point of the list 1 required for the traversal 
	 * @param offset contains the starting point of the list 2 required for the traversal
	 * @return first encountered intersection node. Also returns null if no intersection is found 
	 */
	private Node traverse(Node node, Node offset) {
		Node head1 = node;
		Node head2 = offset;
		
		while(head1!=null || head2!=null) {
			if(head1.equals(head2)) {
				return head1;
			}else {
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		return null;
		
	}
	
	
}

package linkedlist.intersection;

import linkedlist.intersection.MyLinkedList;
import linkedlist.intersection.MyLinkedList.Node;

public class MyTest {
	public static void main(String[] args) {
		MyLinkedList l = new MyLinkedList();
		Node a=l.new Node("A", null);
		Node b=l.new Node("B", null);
		Node c=l.new Node("C", null);
		Node d=l.new Node("D", null);
		Node e=l.new Node("E", null);
		
		Node n1=l.new Node("1", null);
		Node n2=l.new Node("2", null);
		Node n3=l.new Node("3", null);
		
		Node list1=null;
		Node list2=null;
		Node intersectionPoint=null;
		
//		System.out.println("************Test for intersection at first***************");
		list1=l.new Node("T",a);	
		a.next=b;
		b.next=c;
		c.next=d;
		d.next=e;
//		System.out.print("List 1:");
//		printList(list1);
//		System.out.print("List 2:");
//		printList(list1);
//		intersectionPoint = l.findIntersection(list1, list1); // same list to test first intersection
//		System.out.println("Intersection point:"+intersectionPoint.data);
		
		System.out.println("************Test for intersection at Last***************");
		list2=l.new Node("1",null);
		list2.next=e;
		//n2.next=n3;
		//n3.next=e; //create intersection at last point
		System.out.print("List 1:");
		printList(list1);
		System.out.print("List 2:");
		printList(list2);
		intersectionPoint = l.findIntersection(list1, list2); // same list to test first intersection
		System.out.println("Intersection point:"+intersectionPoint.data);
		
		System.out.println("************Test for lists with different sizes***************");
		list2=l.new Node("0",n3);	
		n3.next=d; //create intersection
		System.out.print("List 1:");
		printList(list1);
		System.out.print("List 2:");
		printList(list2);
		intersectionPoint = l.findIntersection(list1, list2); // same list to test first intersection
		System.out.println("Intersection point:"+intersectionPoint.data);

		System.out.println("************Test for lists with same sizes***************");
		list2=l.new Node("0",n1);	
		n1.next=n2;
		n2.next=n3;
		n3.next=d; //create intersection
		
		System.out.print("List 1:");
		printList(list1);
		System.out.print("List 2:");
		printList(list2);
		intersectionPoint = l.findIntersection(list1, list2); // same list to test first intersection
		System.out.println("Intersection point:"+intersectionPoint.data);
		
		
		System.out.println("************Test for lists with no intersection***************");
		list2=l.new Node("0",n1);	
		n1.next=n2;
		n2.next=n3;
		n3.next=null; // no intersection 
		
		System.out.print("List 1:");
		printList(list1);
		System.out.print("List 2:");
		printList(list2);
		intersectionPoint = l.findIntersection(list1, list2); // same list to test first intersection
		if(intersectionPoint==null){
			System.out.println("No intersection found!");
		}
		
		
		
	}
	
	public static void printList(Node node) {
		while(node!=null ) {
			System.out.print(" "+node.data+" ");
			node=node.next;
		}
		System.out.println();
	}

}


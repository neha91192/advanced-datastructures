package redblacktrees;

/**
 * Inner class to create Node instance
 * 
 * @author nehashukla
 *
 */
public class Node {

	public int key;
	public Node left;
	public Node right;
	public Node p;
	public String color;

	public Node() {
		left = null;
		right = null;
		p = null;
		color = "BLACK";
	}

	public Node(int key) {
		this.key = key;
	}

}

package binomialheap;

public class BinomialNode {

	public BinomialNode parent;
	public BinomialNode child;
	public int degree;
	public int key;
	public BinomialNode sibling;

	public BinomialNode() {

	}

	public BinomialNode(BinomialNode parent, BinomialNode child, BinomialNode sibling, int key) {
		this.parent = parent;
		this.child = child;
		this.sibling = sibling;
		// this.degree = degree;
		this.key = key;
	}

}

package redblacktrees;

public class RedBlackTree {

	private final String BLACK = "BLACK";
	private final String RED = "RED";

	public Node nil = new Node();
	public Node root = nil;

	public RedBlackTree() {
		root.left = nil;
		root.right = nil;
		root.p = nil;
	}

	/**
	 * Performs inorder traversal
	 * 
	 * @param root
	 */
	public void sort(Node root) {
		if (root != nil) {
			sort(root.left);
			System.out.println(root.key);
			sort(root.right);
		}
	}

	/**
	 * Searches node for the given key value
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public Node search(Node node, int key) {
		if (node.equals(nil) || key == node.key) {
			return node;
		}
		if (key < node.key) {
			return search(node.left, key);
		} else {
			return search(node.right, key);
		}
	}

	/**
	 * Finds the minimum key value in the RBT
	 * 
	 * @param node
	 * @return
	 */
	public Node treeMin(Node node) {
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}

	/**
	 * Finds the maximum key value in the RBT
	 * 
	 * @param node
	 * @return
	 */
	public Node treeMax(Node node) {
		while (node.right != nil) {
			node = node.right;
		}
		return node;
	}

	/**
	 * Finds the next higher element in the tree for a given node
	 * 
	 * @param node
	 * @return
	 */
	public Node successor(Node node) {
		if (node.right != nil) {
			return (treeMin(node.right));
		}
		Node parent = node.p;
		while (parent != nil && node.equals(parent.right)) {
			node = parent;
			parent = parent.p;
		}
		return parent;
	}

	/**
	 * Finds the next lower element in the tree for a given node
	 * 
	 * @param node
	 * @return
	 */
	public Node predecessor(Node node) {
		if (node.left != nil) {
			return (treeMax(node.left));
		}
		Node parent = node.p;
		while (parent != nil && node.equals(parent.left)) {
			node = parent;
			parent = parent.p;
		}
		return parent;
	}

	/**
	 * Performs left rotation on the node. node2 becomes the new root node, node1
	 * becomes node2's left child and node2's left child as node1's right child.
	 * 
	 * @param node1
	 */
	public void leftRotate(Node node1) {
		Node node2 = node1.right;
		node1.right = node2.left;
		if (!node2.left.equals(nil)) {
			node2.left.p = node1;
		}
		node2.p = node1.p;
		if (node1.p.equals(nil)) {
			root = node2; // Sets node2 as root
		} else if (node1.equals(node1.p.left)) {
			node1.p.left = node2;
		} else {
			node1.p.right = node2;
		}
		node2.left = node1;
		node1.p = node2;

	}

	/**
	 * Performs right rotation on the node.
	 * 
	 * @param node1
	 */
	public void rightRotate(Node node1) {
		Node node2 = node1.left;
		node1.left = node2.right;
		if (!node2.right.equals(nil)) {
			node2.right.p = node1;
		}
		node2.p = node1.p;
		if (node1.p.equals(nil)) {
			root = node2; //
		} else if (node1.equals(node1.p.right)) {
			node1.p.right = node2;
		} else {
			node1.p.left = node2;
		}
		node2.right = node1;
		node1.p = node2;

	}

	/**
	 * y is the auxillary node which keeps a track of new node's desired parent. If
	 * y is nil, it means the new node to be inserted is a root node. When y is
	 * found, it compares with the new node key value and place it accordingly as
	 * either left or right child. The operation till here is similar to BST
	 * insertion. After this, it calls fixRB(node) method to update RBT properties
	 * on the existing tree.
	 * 
	 * @param node
	 */
	public void insert(Node node) {
		Node y = nil;
		Node temp = root;
		while (temp != nil) {
			y = temp;
			if (node.key < temp.key) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		node.p = y;
		if (y.equals(nil)) {
			root = node;
		} else if (node.key < y.key) {
			y.left = node;
		} else {
			y.right = node;
		}
		node.left = nil;
		node.right = nil;
		node.color = RED;
		fixRB(node);
		System.out.println("*******");
		System.out.println("Height of RBT after insertion: " + height(root));
		System.out.println("*******");
	}

	/**
	 * Performs fixing of RBT after insert operation. The following are the cases:
	 * 1. If the node to be inserted is a root node. Then color it BLACK. 2. If the
	 * parent is already BLACK, then the new node to be inserted will be RED. 3. If
	 * the node to be inserted has any non-root parent. Check the color of its
	 * Uncle: a. If the color of Uncle is RED. Change the color of node's parent and
	 * uncle to black and further change the color of node's grandparent to Red.
	 * Iteratively perform changes on the grandparent until RBT property is fixed.
	 * b. If the color of Uncle is BLACK or is Nil: If the node to be inserted is
	 * the right child of its parent and the parent is also the right child, perform
	 * left rotate. Similarly, if there is left-left relation between parent and
	 * child, perform right rotation and change the color. If parent is the left
	 * child and new node is right child of its parent then perform right rotation
	 * followed by left rotation.Follow the same symmetric operation on the
	 * right-left relation between parent and child. Change the color of the nodes
	 * in both the cases.
	 * 
	 * 
	 * @param node
	 */
	public void fixRB(Node node) {
		Node y = nil;
		while (node.p.color.equals(RED)) {
			if (node.p.equals(node.p.p.left)) { // if node's parent is a left child
				y = node.p.p.right; // node's uncle
				if (y.color.equals(RED)) {
					node.p.color = BLACK;
					y.color = BLACK;
					node.p.p.color = RED;
					node = node.p.p;
				} else {
					if (node.equals(node.p.right)) {
						node = node.p;
						leftRotate(node);
					}
					node.p.color = BLACK;
					node.p.p.color = RED;
					rightRotate(node.p.p);
				}
			} else if (node.p.equals(node.p.p.right)) {
				y = node.p.p.left;
				if (y.color.equals(RED)) {
					node.p.color = BLACK;
					y.color = BLACK;
					node.p.p.color = RED;
					node = node.p.p;
				} else {
					if (node.equals(node.p.left)) {
						node = node.p;
						rightRotate(node);
					}
					node.p.color = BLACK;
					node.p.p.color = RED;
					leftRotate(node.p.p);
				}
			}
		}
		root.color = BLACK;
	}

	/**
	 * Recursively calls for the height of the left node and right node. The maximum
	 * of the two will give the height of the entire RBT
	 * 
	 * @param node
	 * @return
	 */
	public int height(Node node) {
		int height = -1;
		if (node == nil)
			return height;
		else {
			height = 1 + Math.max(height(node.left), height(node.right));
			return height;
		}
	}

}

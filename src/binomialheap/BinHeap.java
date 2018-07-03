package binomialheap;

public class BinHeap {

	BinomialNode head;

	public BinHeap() {
		head = null;
	}

	/**
	 * This simply returns an instance of a new heap containing an empty head node
	 * with Nil object.
	 * 
	 * @return
	 */
	public BinHeap create() {
		BinHeap newHeap = new BinHeap();
		return newHeap;
	}

	/**
	 * Takes two parameters as input. One is the existing heap and other is the new
	 * node which needs to be added. The node is appended as the head of a new heap,
	 * and then the union operation is performed on the new heap and the existing
	 * heap.
	 * 
	 * @param heap
	 * @param node
	 */
	public void insert(BinHeap heap, BinomialNode node) {
		BinHeap newHeap = create();
		newHeap.head = node;

		heap = union(heap, newHeap);
		head = heap.head;
	}

	/**
	 * Performs the union operation. It includes - merging of two heaps, then
	 * finding and performing binomial link operation on the eligible candidates and
	 * finally it maintains the Binomial-heap properties of a Binomial heap
	 * 
	 * @param heap1
	 * @param heap2
	 * @return
	 */
	public BinHeap union(BinHeap heap1, BinHeap heap2) {
		BinHeap heap = create();
		// returns combined heap(heap1 and heap2), sorted in the order of the degrees
		// of each Binomial trees
		heap.head = merge(heap1, heap2);
		heap1 = null;
		heap2 = null;

		// if the head after union is still Nil
		if (heap.head.equals(null)) {
			return heap;
		}
		BinomialNode prevX = null;
		BinomialNode x = heap.head;
		BinomialNode nextX = x.sibling;
		while (nextX != null) {
			if (x.degree != nextX.degree || (nextX.sibling != null && (nextX.sibling.degree == x.degree))) {
				// ideal case, move the pointers ahead
				prevX = x;
				x = nextX;
			} else {
				// degree of x and nextX is same
				if (x.key <= nextX.key) {
					x.sibling = nextX.sibling;
					link(nextX, x);
				} else {
					if (prevX == null) {
						heap.head = nextX;
					} else {
						prevX.sibling = nextX;
					}
					link(x, nextX);
					x = nextX;
				}

			}
			nextX = x.sibling;
		}

		return heap;
	}

	/**
	 * Merges the two heaps such that the degrees of each trees are placed in the
	 * increasing order. Trees with the same degrees are placed next to each other.
	 * 
	 * @param heap1
	 * @param heap2
	 * @return
	 */
	public BinomialNode merge(BinHeap heap1, BinHeap heap2) {
		BinomialNode head = new BinomialNode();
		if (heap1.head == null || heap2.head == null) {
			head = (heap2.head != null) ? heap2.head : heap1.head;
		} else {
			BinomialNode x = heap1.head;
			BinomialNode y = heap2.head;
			BinomialNode prevY = null;

			while (x != null && y != null) {
				if (x.degree >= y.degree) {
					prevY = y;
					y = y.sibling;
					prevY.sibling = x;
				}
				if (x.sibling == null) {
					prevY = x;
				}
				x = x.sibling;
			}
			if (x == null) {
				prevY.sibling = y;
			}

			head = (heap1.head.degree >= heap2.head.degree) ? heap2.head : heap1.head;
		}
		return head;
	}

	/**
	 * Links two binomial trees of same degree while maintaining the min-heap
	 * property.
	 * 
	 * @param y
	 * @param z
	 */
	public void link(BinomialNode y, BinomialNode z) {
		y.parent = z;
		y.sibling = z.child;
		z.child = y;
		z.degree = z.degree + 1;
	}

	/**
	 * Finds the minimum value node in the head-list. Since the Binomial heap is a
	 * collection of binomial trees with min-heap property, minimum element can be
	 * found in the root nodes only.
	 * 
	 * @param heap
	 * @return
	 */
	public BinomialNode findMinimum(BinHeap heap) {
		BinomialNode minPointer = null;
		BinomialNode x = heap.head;
		int min = Integer.MAX_VALUE;
		while (x != null) {
			if (x.key < min) {
				min = x.key;
				minPointer = x;
			}
			x = x.sibling;
		}
		return minPointer;
	}

	/**
	 * Returns the minimum node in the heap
	 * 
	 * @param heap
	 */
	public void extractMin(BinHeap heap) {
		BinomialNode minPointer = null;
		BinomialNode prevToMin = null;
		BinomialNode x = heap.head;
		int min = Integer.MAX_VALUE;
		while (x != null) {
			if (x.key < min) {
				min = x.key;
				prevToMin = minPointer;
				minPointer = x;
			}
			x = x.sibling;
		}
		// extract tree with min root value
		if (prevToMin != null) {
			prevToMin.sibling = minPointer.sibling;
		} else {
			heap.head = heap.head.sibling;
		}

		// reverses min pointer elements and add in a new heap
		BinomialNode reversedHead = reverse(minPointer.child);

		BinomialNode itr = reversedHead;
		while (itr != null) {
			itr.parent = null;
			itr = itr.sibling;
		}
		BinHeap newHeap = heap.create();
		newHeap.insert(newHeap, reversedHead);
		// call union
		heap = union(heap, newHeap);
		head = heap.head;

	}

	public void print(BinomialNode n) {
		if (n != null) {
			print(n.child);
			System.out.println("Key:" + n.key + " Degree:" + n.degree);
			print(n.sibling);
		}
	}

	public void printUtil(BinomialNode node) {

	}

	/**
	 * Reverses the linked list
	 * 
	 * @param head
	 * @return
	 */
	public BinomialNode reverse(BinomialNode head) {
		BinomialNode tail = head;
		if (head != null) {
			head = head.sibling;
			BinomialNode itr = head;
			tail.sibling = null;

			while (itr.sibling != null) {
				head = head.sibling;
				itr.sibling = tail;
				tail = itr;
				itr = head;

			}
			itr.sibling = tail;
		}
		return head;
	}

	/**
	 * Decrements key of the node to a new value k (strictly lesser than current
	 * key). After the operation, it iteratively checks if the min-heap condition is
	 * violated and if it is, it exchanges places of child parent until the key in
	 * parent is lesser than the child.
	 * 
	 * @param heap
	 * @param node
	 * @param k
	 */
	public void decrease(BinHeap heap, BinomialNode node, int k) {
		if (k > node.key) {

		}
		node.key = k;
		BinomialNode y = node;
		BinomialNode z = y.parent;
		while (z != null && y.key < z.key) {
			int temp = z.key;
			z.key = y.key;
			y.key = temp;
			y = z;
			z = y.parent;
		}

	}

	/**
	 * Deletes a node from the heap. It first decreases the key of the node to be
	 * deleted to the most minimum value of an integer and then calls for the
	 * extract min operation which removes the minimum node (node to be deleted)
	 * from the heap.
	 * 
	 * @param heap
	 * @param x
	 */
	public void delete(BinHeap heap, BinomialNode x) {
		int minValue = Integer.MIN_VALUE;
		decrease(heap, x, minValue);
		extractMin(heap);
	}
}

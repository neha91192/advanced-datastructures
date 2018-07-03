package binomialheap;

public class BinomialHeap {

	BinomialNode Nil = new BinomialNode();
	BinomialNode head = Nil;

	public BinomialHeap() {

	}

	/**
	 * This simply returns an instance of a new heap containing an empty head node
	 * with Nil object.
	 * 
	 * @return
	 */
	public BinomialHeap create() {
		BinomialHeap newHeap = new BinomialHeap();
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
	public void insert(BinomialHeap heap, BinomialNode node) {
		BinomialHeap newHeap = create();
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
	public BinomialHeap union(BinomialHeap heap1, BinomialHeap heap2) {
		BinomialHeap heap = create();
		// returns combined heap(heap1 and heap2), sorted in the order of the degrees
		// of each Binomial trees
		heap.head = merge(heap1, heap2);
		heap1 = null;
		heap2 = null;

		// if the head after union is still Nil
		if (heap.head.equals(Nil)) {
			return heap;
		}
		BinomialNode prevX = Nil;
		BinomialNode x = heap.head;
		BinomialNode nextX = x.sibling;
		while (nextX != Nil) {
			if (x.degree != nextX.degree || (nextX.sibling != Nil && (nextX.sibling.degree == x.degree))) {
				// ideal case, move the pointers ahead
				prevX = x;
				x = nextX;
			} else {
				// degree of x and nextX is same
				if (x.key <= nextX.key) {
					x.sibling = nextX.sibling;
					link(nextX, x);
				} else {
					if (prevX == Nil) {
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
	public BinomialNode merge(BinomialHeap heap1, BinomialHeap heap2) {
		BinomialNode head = new BinomialNode();
		if (heap1.head.equals(Nil) || heap2.head.equals(Nil)) {
			head = (!heap2.head.equals(Nil)) ? heap2.head : heap1.head;
		} else {

			BinomialNode x = heap1.head;
			BinomialNode y = heap2.head;

			BinomialNode prevX = Nil;
			BinomialNode prevY = Nil;

			while (x != Nil && y != Nil) {
				if (x.degree >= y.degree) {
					prevY = y;
					y = y.sibling;
					prevY.sibling = x;
					prevX.sibling = prevY;

				}
				prevX = x;
				x = x.sibling;
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
	public BinomialNode findMinimum(BinomialHeap heap) {
		BinomialNode minPointer = Nil;
		BinomialNode x = heap.head;
		int min = Integer.MAX_VALUE;
		while (x != Nil) {
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
	public void extractMin(BinomialHeap heap) {
		BinomialNode minPointer = Nil;
		BinomialNode prevToMin = Nil;
		BinomialNode x = heap.head;
		int min = Integer.MAX_VALUE;
		while (x != Nil) {
			if (x.key < min) {
				min = x.key;
				prevToMin = minPointer;
				minPointer = x;

			}
			x = x.sibling;
		}
		// extract tree with min root value
		prevToMin.sibling = minPointer.sibling;

		// reverse min pointer elements and add in a new heap
		BinomialNode reversedHead = reverse(minPointer.child);

		BinomialNode itr = reversedHead;
		while (itr != Nil) {
			itr.parent = Nil;
		}
		BinomialHeap newHeap = heap.create();
		newHeap.insert(newHeap, reversedHead);
		// call union
		union(heap, newHeap);

	}

	public void printUtil(BinomialNode node) {
		int space = 10;
		int difference = 10;
		BinomialNode rootListItr = node;
		while (!rootListItr.equals(Nil)) {
			int constantSpace = rootListItr.degree * 5 + space;
			for (int i = 0; i < constantSpace; i++) {
				System.out.print(" ");
			}
			System.out.println(rootListItr.key);
			print(rootListItr.child, constantSpace - difference);
			rootListItr = rootListItr.sibling;
		}

	}

	public void print(BinomialNode node, int space) {
		if (node.equals(Nil)) {
			return;
		} else {
			BinomialNode itr = node;
			for (int i = 0; i < space; i++) {
				System.out.print(" ");
			}

			System.out.println(itr.key);
			itr = itr.sibling;
			while (!itr.equals(Nil) && (itr.degree == itr.sibling.degree)) {
				for (int i = 0; i < space; i++) {
					System.out.print(" ");
				}
				System.out.print(itr.key);
				itr = itr.sibling;
			}
			print(node.child, 10);

		}
	}

	// public void print(BinomialNode head) {
	// BinomialNode itr = head;
	// while (itr != Nil) {
	// int space = itr.degree * 10;
	// for (int i = 0; i < space + 10; i++) {
	// System.out.print(" ");
	// }
	// System.out.print(itr.key);
	// printTree(itr.child, space + 10);
	// itr = itr.sibling;
	// }
	// }
	//
	// public void printTree(BinomialNode node, int space) {
	// if (node.equals(Nil)) {
	// return;
	// } else {
	// int difference = 10;
	// BinomialNode itr = node;
	// while (itr != Nil) {
	// System.out.println();
	// System.out.println();
	// for (int i = difference; i < space; i++) {
	// System.out.print(" ");
	// }
	// System.out.println(itr.key);
	// for (int i = difference; i < space; i++) {
	// System.out.print(" ");
	// }
	// itr = itr.sibling;
	// }
	// printTree(node.child, space);
	// }
	// }

	/**
	 * Reverses the linked list
	 * 
	 * @param head
	 * @return
	 */
	public BinomialNode reverse(BinomialNode head) {
		BinomialNode tail = head;
		head = head.sibling;
		BinomialNode itr = head;
		tail.sibling = Nil;

		while (!itr.sibling.equals(Nil)) {
			head = head.sibling;
			itr.sibling = tail;
			tail = itr;
			itr = head;

		}
		itr.sibling = tail;

		return tail;
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
	public void decrease(BinomialHeap heap, BinomialNode node, int k) {
		if (k > node.key) {

		}
		node.key = k;
		BinomialNode y = node;
		BinomialNode z = y.parent;
		while (z != Nil && y.key < z.key) {
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
	public void delete(BinomialHeap heap, BinomialNode x) {
		int minValue = Integer.MIN_VALUE;
		decrease(heap, x, minValue);
		extractMin(heap);
	}
}

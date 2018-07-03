package hashtable;

import java.util.NoSuchElementException;

public class HashStructure {

	HashNode[] hashTable;
	HashFunction hashClass = null;

	private static int MAX_SIZE = 1024;

	public HashStructure() {
		this.hashTable = new HashNode[MAX_SIZE];
		this.hashClass = new HashFunction(MAX_SIZE);
	}

	/**
	 * Finds the HashNode entry with the given key value. If it is already present,
	 * it overrides with the value v. Otherwise, create a new HashNode object and
	 * stores in the position determined by the HashFunction.
	 * 
	 * @param k
	 *            key of the HashStructure entry
	 * @param v
	 *            value of the HashStructure entry
	 */
	public void insert(String k, int v) {
		HashNode node = find(k);
		if (node != null) {
			node.value = v;
		} else {
			int hash = hashClass.hash(k);
			HashNode entryNode = hashTable[hash];
			HashNode newEntry = new HashNode(k, v, null);
			if (entryNode != null) {
				// appends new entry in the front
				newEntry.next = entryNode;
			}
			hashTable[hash] = newEntry;
		}
	}

	/**
	 * Deletes node with the value k
	 * 
	 * @param k
	 */
	public void delete(String k) {
		int hash = hashClass.hash(k);
		HashNode entryNode = hashTable[hash];
		if (entryNode != null) {
			if (entryNode.key.equals(k)) {
				// delete
				hashTable[hash] = entryNode.next;
				return;
			} else {
				// traverse the list and delete node with the same key
				HashNode prevNode = null;
				while (entryNode.next != null) {
					prevNode = entryNode;
					entryNode = entryNode.next;
					if (entryNode.key.equals(k)) {
						prevNode.next = entryNode.next;
						return;
					}
				}
			}
		}
	}

	/**
	 * Increments the integer value of the given key by 1.
	 * 
	 * @throws {@link
	 *             NoSuchElementException} when the key provided in the argument is
	 *             not found
	 * @param k
	 */
	public void increase(String k) {
		HashNode node = find(k);
		if (node != null) {
			node.value++;
		} else {
			NoSuchElementException e = new NoSuchElementException();
			e.printStackTrace();
		}
	}

	/**
	 * Takes key as input and returns HashNode entry as output. HashNode entry can
	 * be present at the first node itself or anywhere in the list.
	 * 
	 * @param k
	 *            of type {@link String} contains key of the HashStructure
	 * @return entry of type {@link HashNode} if found, otherwise a null object
	 */
	public HashNode find(String k) {

		int hash = hashClass.hash(k);
		HashNode entry = hashTable[hash];

		if (entry != null) {
			while (entry != null) {
				if (k.equals(entry.key)) {
					return entry;
				} else {
					entry = entry.next;
				}
			}
		}

		return entry;

	}

	/**
	 * Uses StringList Data Structure (Linked List) to store values of keys of
	 * HashStructure.
	 * 
	 * @return list of keys of HashStructure of type {@link StringList}
	 */
	public StringList list() {
		StringList head = new StringList(null, null);
		StringList keys = head;
		for (HashNode node : hashTable) {
			while (node != null) {
				StringList key = new StringList(node.key, null);
				keys.next = key;
				node = node.next;
				keys = keys.next;
			}
		}
		return head.next;
	}

}

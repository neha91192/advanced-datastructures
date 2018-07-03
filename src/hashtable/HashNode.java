package hashtable;

// for storing linked list in hash table
public class HashNode {

	public String key;

	public int value;

	public HashNode next;

	public HashNode(String data, int value, HashNode next) {
		this.key = data;
		this.value = value;
		this.next = next;
	}

}

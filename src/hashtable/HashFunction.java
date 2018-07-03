package hashtable;

public class HashFunction {

	private int tableSize;
	private final float A = 0.6180339887F;
	private final int w = 32;

	public HashFunction(int tableSize) {
		this.tableSize = tableSize;
	}

	/**
	 * find k, A = (root(5)-1)/2 = 0.6180339887, w = word size of JVM = 4 bytes (32
	 * bits) for 32 bits system or 8 bytes (64 bits) for 64 bits system, m = size of
	 * the hashtable, p = power of 2 of table size, s = A * 2^w, r = k * s. perform
	 * arithmetic right shift on r to find p most significant bits (r >> (w-p)) &
	 * (m-1). This value is the final hash of key
	 * 
	 * @param key
	 * @return
	 */
	public int hash(String key) {

		// k stores ascii value of string
		int k = 0;
		for (int i = 0; i < key.length() - 1; i++) {
			k = k + key.charAt(0);
		}
		// determine s
		double s = A * Math.pow(2, w);
		long S = (long) s;
		// determine p
		int m = tableSize;
		int p = 0;
		while (m > 1) {
			m = m / 2;
			p = p + 1;
		}

		// determine r
		long r = k * S;

		// find p most significant bits of r
		int hash = (int) ((r >> (w - p)) & (tableSize - 1));

		return hash;
	}

}

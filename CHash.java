//CHash.java
//Hash table used to hold all of the customers in the database catalogged by CCN
//implemeted using double hashing

//Creates Hash class
public class CHash implements java.io.Serializable{

	//instance variables
	private int n;
	private Cnode[] hashTable;

	//Constructor 
	public CHash() {
		hashTable = new Cnode[257];
		n = 0;
	}

	//Method that looks up and returns the index position of a node (by its key, k)
	public Cnode lookUp(int k) {
		int c = 0;
		while (c != 257) {
			if (hashTable[c] == null) {
				c++;
			}
			else {
				if (k == hashTable[c].getCCN()) {
					return hashTable[c];
				}
				else {
					c++;
				}
			}
		}
		return hashTable[c];
	}

	//Helper methods for insert method that "hashes" the key (double hashing) to create an index position
	//If that index position is taken, another algoritm is continuously run until an empty index position is found
	public int hash(int k) {
		int hashNum = k % 257;
		int index = hashNum;
		while (hashTable[index] != null) {
			if (hashTable[hashNum] == null) {
				index = hashNum;
			}
			else {
				index = (index + hashNum%25)%257;
			}
		}
		return index;
	}

	//Method that uses the hash-generated index position to insert node x
	public void insert(Cnode x) {
		int index = hash(x.getCCN());
		hashTable[index] = x;
		n++;
	}

	//Method that deletes (by setting index to null) a node with a given key
	public void delete(int k) {
		int c = 0;
		while (c != 257) {
			if (hashTable[c] == null) {
				c++;
			}
			else {
				if (k == hashTable[c].getCCN()) {
					hashTable[c] = null;
				}
				else {
					c++;
				}
			}
		}
		n--;
	}

	//Method that checks if the hash table is empty
	public boolean isEmptySet() {
		return n==0;
	}

	//Method that prints out the nodes in the hashTable, prints null if index is empty
	public void printHash() {
		int c = 0;
		while (c != 257) {
			System.out.println(c);
			if (hashTable[c] == null) {
				System.out.println("Null");
			}
			else {
				System.out.println(hashTable[c].getCCN());
			}
			c++;
		}
	}
}
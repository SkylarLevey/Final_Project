//RTHeap.java
//Priority Queue/Heap to keep track of the lowest rated Rotten Tomatoes score
//implemented with an Array 

public class RTHeap implements java.io.Serializable{

	//variable declaration
	private Mnode[] q;
	private int n;

	//constructor
	public RTHeap(){
		q = new Mnode[257];
		n = 0;
	}	

	//retruns the boolean of whether or not the hash table is empty
	public boolean isEmpty(){
		return n==0;
	}

	//returns the worst rated movie
	public Mnode findMin(){
		return q[0];
	}

	//adds the MNode to the nearest availible slot, then swops up in priority when necessary
	public void insert(Mnode x){
		q[n] = x;
		n++;
		int index = n-1;
		while(q[((index-1)/2)].getRTScore() > q[index].getRTScore()){
			swop(((index-1)/2), index);
			index = (index-1)/2;
		}

	}

	//removes the min MNode by switching it to the back then working the back to a new place with swops
	public void deleteMin(){
		swop(0, n-1);
		q[n-1]=null;
		n--;
		int index = 0;
		while (((q[index*2+1] != null) && (q[index].getRTScore() > q[index*2+1].getRTScore())) || ((q[index*2+2] != null) && (q[index].getRTScore() > q[index*2+2].getRTScore()))){
			if (q[index*2+1] == null){
				swop((index*2+2), index);}
			else if (q[index*2+2] == null){
				swop((index*2+1), index);}
			else if (q[index*2+1].getRTScore() < q[index*2+2].getRTScore()){
				swop((index*2+1), index);
				index = index*2+1;}
			else{
				swop((index*2+2), index);
				index = index*2+2;}
		}
	}

	//swops the nodes of the two given indecies 
	private void swop(int x, int y){
		Mnode temp = q[x];
		q[x] = q[y];
		q[y] = temp;
	}
}
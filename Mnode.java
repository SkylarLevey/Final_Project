//Mnode.java
//movie node class
//used to keep track of the movies in the database and their information

//instance variables
public class Mnode implements java.io.Serializable{
	private String title;
	private int releaseD;
	private int id;
	private int rtScore;
	private boolean hasMovie;
	private Mnode nextLeft;
	private Mnode nextRight;

	//constructor
	public Mnode(String title0, int releaseD0, int id0, int rtScore0){
		title = title0;
		releaseD = releaseD0;
		id = id0;
		rtScore = rtScore0;
		hasMovie = true;
		nextLeft = null;
		nextRight = null;
	}

	//returns the availibility boolean of a movie
	public boolean getHasMovie(){
		return hasMovie;
	}
	
	//changes the avialibility to a given boolean
	public void hasMovieChange(boolean have){
		hasMovie = have;
	}

	//returns the name of the movie
	public String getName(){
		return title;
	}

	//returns the release date of a movie
	public int getReleaseD(){
		return releaseD;
	}

	//returns the ID of a movie
	public int getID(){
		return id;
	}

	//returns the Rotten Tomatoes Score of a movie
	public int getRTScore(){
		return rtScore;
	}

	//return left movie node
	public Mnode getLeft(){
		return nextLeft;
	}
	
	//return right movie node
	public Mnode getRight(){
		return nextRight;
	}

	//sets the left movie node to the given movie
	public void setLeft(Mnode node0){
		nextLeft = node0;
	}
	
	//sets the right movie node to the given movie
	public void setRight(Mnode node0){
		nextRight = node0;
	}

}


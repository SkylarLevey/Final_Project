//Cnode.java
//Customer node class
//used to keep track of the users of the database and their information

import java.util.Scanner;
import java.util.InputMismatchException;

//variable declaration
public class Cnode implements java.io.Serializable{
	private String name;
	private int ccn;
	private String email;
	private int numMP;
	private Mnode[] moviePref;

	//contructor
	public Cnode(String name0, int ccn0, String email0){
		name = name0;
		ccn = ccn0;
		email = email0;
		numMP = 0;
		moviePref = new Mnode[20];
	}

	//returns the name of a customer
	public String getName(){
		return name;
	}
	
	//changes the name of a customer
	public void setName(String name0){
		name = name0;
	}
	
	//returns the ccn of a customer
	public int getCCN(){
		return ccn;
	}
	
	//changes the ccn of a customer
	public void setCCN(int ccn0){
		ccn = ccn0;
	}

	//returns the email of a customer
	public String getEmail(){
		return email;
	}

	//changes the email of a customer
	public void setEmail(String email0){
		email = email0;
	}
	
	//returns the private variable representing number of items in the wish list
	public int getNumMP(){
		return numMP;
	}

	//returns the name of a movie in the wish list given its rank
	public String getMovieNameWithRank(int rank0){
		return moviePref[rank0-1].getName();
	}

	//returns the boolean availibility of a movie in the wish list given its rank
	public boolean getMovieAvailibilityWithRank(int rank0){
		return moviePref[rank0-1].getHasMovie();
	}
	
	//adds a movie to the wish list
	public void addWLMovies(BSTFP bst, MHashID hashID){
		//gets the movie to be added from the user
		Scanner input = new Scanner(System.in);
		System.out.println("What rank in your wishlist would you like the movie to occupy?");
		int movieRank = 100;
		while(movieRank == 100){
			try{
				movieRank = input.nextInt();
			}
			catch (InputMismatchException f) {
				System.out.println("That is not a valid movie rank, please enter another: ");
				input.nextLine();
			}
		}
		String choice = "cowz";

		while ((choice.equals("a") == false) && (choice.equals("A") == false) && (choice.equals("b") == false) && (choice.equals("B") == false)){
			System.out.print("Would you like to search by: (A) release date or (B) movie ID ");
			choice = input.next();
			if ((choice.equals("a") == false) && (choice.equals("A") == false) && (choice.equals("b") == false) && (choice.equals("B") == false)){
				System.out.println("That was not an option, please try again:  ");
			}
		}

		//adds a movie based on its release date
		if (choice.equals("A") || choice.equals("a")){
			Mnode movie = null;
			while(movie == null){
				System.out.println("please enter the release date of your movie");
				try{
					int movieRD = input.nextInt();
					movie = bst.search(movieRD);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("A movie with that release date was not in the database, please enter another");
				}
				catch (InputMismatchException f) {
					System.out.println("A movie with that release date was not in the database, please enter another");
					input.nextLine();
				}
				catch (NullPointerException d){
					System.out.println("A movie with that release date was not in the database, please enter another");

				}

			}
			movieOrder(movie, movieRank);
		}

		//adds a movie based on its ID
		else{
			Mnode movie = null;
			int movieID = 0;
			while(movie == null){
				System.out.println("please enter ID of your movie");
				try{
					movieID = input.nextInt();
					movie = hashID.lookUp(movieID);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("A movie with that ID was not in the database, please enter another");
				}
				catch (InputMismatchException f) {
					System.out.println("A movie with that ID was not in the database, please enter another");
					input.nextLine();
				}
				catch (NullPointerException d){
					System.out.println("A movie with that ID was not in the database, please enter another");
				}
			}
			movieOrder(movie, movieRank);
		}
	}

	//actually adds a movie to the array
	private void movieOrder(Mnode newMovie0, int movieRank0){
		int counter = 19;
		if (movieRank0 > numMP)
			moviePref[numMP] = newMovie0;
		else{
			while (counter > (movieRank0-1)){
				moviePref[counter] = moviePref[counter-1];
				counter--;
			}
			moviePref[(movieRank0-1)] = newMovie0;
		}
		System.out.println(newMovie0.getName() + " was added to wishlist");
		numMP++;
	}

	//removes a movie from the wish list, and shifts all other entries to fill its place
	public void deleteWLMovie(){
		Scanner input = new Scanner(System.in);
		printMovieWL();
		int movieRank = 21;
		System.out.println("What number/rank on your wish list do you want to delete");
		while ((movieRank > numMP) || (movieRank < 1)){
			try{
				movieRank = input.nextInt();
			}
			catch (InputMismatchException f) {
				System.out.println("That is not a valid movie rank, please enter another");
				input.nextLine();
			}
			if ((movieRank > numMP) || (movieRank < 1)){
				System.out.println("That rank is not in your list, please enter another");
			}
		}
		while ((movieRank) <= numMP){
			moviePref[movieRank-1] = moviePref[movieRank];
			movieRank++;
		}
		numMP--;
		System.out.println("The movie has been successfully deleted");
	}

	//simply deletes a given movie rank from the wish list
	public void simpleDeleteWLMovie(int movieRank){
		while ((movieRank) <= numMP){
			moviePref[movieRank-1] = moviePref[movieRank];
			movieRank++;
		}
		numMP--;
		System.out.println("The movie has been successfully deleted");
	}

	//displays all of the movies in order of priority in the wish list along with their availibility
	public void printMovieWL(){
		for (int i = 0; i < numMP; i++){
			System.out.println("Rank " +(i+1)+ ":  " + moviePref[i].getName() + ",  Availible: " + moviePref[i].getHasMovie());	
		} 
	} 
}
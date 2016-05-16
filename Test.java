//Test.java
//main funtion for the database includes the instances of the different data stuctures
//also includes the user menu and all the calls to the other files

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
public class Test implements java.io.Serializable {

	public static void main(String[] args) {

		//inicial instructions as well as the option decerialize the last database or start with a new one
		System.out.println();
		System.out.println("To select an option enter the letter of the option you would like, then press enter");
		System.out.print("Would you like to (A) start from your last database, (B) with an empty one, or (C) with a preloaded 25-movie database: ");
		Scanner input = new Scanner(System.in);
		String firstChoice = input.next();

		//instances of the data structures
		CHash customerHash = new CHash();
		MHashID movieIDHash = new MHashID();
		BSTFP releaseDBST = new BSTFP();
		RTHeap rottenTomatoesHeap = new RTHeap();

		//starting with a new database
		if (firstChoice.equals("B") || firstChoice.equals("b")){
			System.out.println("Starting with a new database!");
		}

		//starting with a database with 25 precoded movies
		else if(firstChoice.equals("C") || firstChoice.equals("c")){

			Mnode[] list;
			list = new Mnode[25];

			list[0] = new Mnode("Interstellar", 20141107, 10001, 71);
			list[1] = new Mnode("Her", 20131218, 10002, 94);
			list[2] = new Mnode("Tangled", 20101124, 10003, 90);
			list[3] = new Mnode("The Lion King", 19940615, 10004, 91);
			list[4] = new Mnode("Up", 20090529, 10005, 98);
			list[5] = new Mnode("The Conjuring", 20130719, 10006, 86);
			list[6] = new Mnode("The Producers", 20151216, 10007, 51);
			list[7] = new Mnode("The Untouchables", 19870603, 10008, 80);
			list[8] = new Mnode("The Fellowship of the Ring", 20011219, 10009, 97);
			list[9] = new Mnode("The Departed", 20061006, 10010, 91);
			list[10] = new Mnode("The Shawshank Redemption", 19940923, 10011, 91);
			list[11] = new Mnode("The Dark Knight", 20080718, 10012, 94);
			list[12] = new Mnode("Ten Things I Hate About You", 19990331, 10013, 61);
			list[13] = new Mnode("A Kights Tale", 20010511, 10014, 58);
			list[14] = new Mnode("The Godfather", 19720324, 10015, 99);
			list[15] = new Mnode("Pulp Fiction", 19941014, 10016, 94);
			list[16] = new Mnode("Django Unchained", 20121225, 10017, 88);
			list[17] = new Mnode("Fight Club", 19990921, 10018, 79);
			list[18] = new Mnode("Forrest Gump", 19940706, 10019, 72);
			list[19] = new Mnode("The Matrix", 19990331, 10020, 87);
			list[20] = new Mnode("Back to the Future", 19850703, 10021, 96);
			list[21] = new Mnode("Monty Python and the Holy Grail", 19750101, 10022, 97);
			list[22] = new Mnode("Scarface", 19831209, 10023, 82);
			list[23] = new Mnode("The Wolf of Wall Street", 20131225, 10024, 77);
			list[24] = new Mnode("The Princess Bride", 19870925, 10025, 97);

			for (int i = 0; i < 25; i++){
				movieIDHash.insert(list[i]);
				releaseDBST.insert(list[i]);
				rottenTomatoesHeap.insert(list[i]);
			}
			System.out.println("There are 25 movies in your database!");
		}

		//if they write anything other than B and C they are automatically given the last database
		else{
			//deserealization
			try {
		         FileInputStream fileIn = new FileInputStream("output.txt");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         customerHash = (CHash) in.readObject();
		         movieIDHash = (MHashID) in.readObject();
		         releaseDBST = (BSTFP) in.readObject();
		         rottenTomatoesHeap = (RTHeap) in.readObject();
		         in.close();
		         fileIn.close();
		    } catch(IOException i) {
		         i.printStackTrace();
		    } catch(ClassNotFoundException j) {
		         j.printStackTrace();
		    }
		    System.out.println("Starting with your last database!");
		}

		//main menu while loop
		String choice = "cowz";
		while((choice.equals("Q") == false) && (choice.equals("q") == false)){

			System.out.println();
			System.out.println("What would you like to do today?");
			System.out.println("(A) Add a new customer to the database");
			System.out.println("(B) Change a customer's information");
			System.out.println("(C) Add a movie to the database");
			System.out.println("(D) View/Delete the worst rated movie");
			System.out.println("(E) Watch movie: search by movie release date");
			System.out.println("(F) Watch movie: search by movie ID number");
			System.out.println("(G) Display entire Database in order of release Date");
			System.out.println("(Q) Quit menu");
			System.out.print(" Option-> ");

			choice = input.next();

			//different options and their various funtion calls
			if(choice.equals("A") || choice.equals("a")){
				addCustomer(customerHash);}
			else if(choice.equals("B") || choice.equals("b")){
				customerMenu(customerHash, releaseDBST, movieIDHash);}
			else if(choice.equals("C") || choice.equals("c")){
				addMovie(movieIDHash, releaseDBST, rottenTomatoesHeap);}
			else if(choice.equals("D") || choice.equals("d")){
				deleteMovie(movieIDHash, releaseDBST, rottenTomatoesHeap);}
			else if(choice.equals("E") || choice.equals("e")){
				searchRD(releaseDBST);}
			else if(choice.equals("F") || choice.equals("f")){
				searchID(movieIDHash);}	
			else if(choice.equals("G") || choice.equals("g")){
				printAll(releaseDBST);}
			else if(choice.equals("Q") || choice.equals("q")){
				
				//if the user selects q serealize the database
				try {
					FileOutputStream fileOut = new FileOutputStream("output.txt");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(customerHash);
					out.writeObject(movieIDHash);
					out.writeObject(releaseDBST);
					out.writeObject(rottenTomatoesHeap);
					out.close();
					fileOut.close();
					System.out.println("Serialized object successfully in output.txt");
				} catch(IOException i) {
					i.printStackTrace();
				}
			}

			//check to make sure they entered a valid option
			if ((choice.equals("Q") == false) && (choice.equals("q") == false) && (choice.equals("A") == false) && (choice.equals("a") == false) &&
				(choice.equals("B") == false) && (choice.equals("b") == false) && (choice.equals("C") == false) && (choice.equals("c") == false) &&
				(choice.equals("D") == false) && (choice.equals("d") == false) && (choice.equals("E") == false) && (choice.equals("e") == false) &&
				(choice.equals("F") == false) && (choice.equals("f") == false) && (choice.equals("G") == false) && (choice.equals("g") == false)) {
				System.out.println("That was not one of the options");
			}

			//after each action from the main menu finishes the user is given the option to quit
			else{
				if ((choice.equals("Q") == false) && (choice.equals("q") == false)){
					System.out.print("Would you like to do something else with the database (Y) yes, (Q) quit-> ");
					choice = input.next();

					//if the user selects to quit serealize the database
					if(choice.equals("Q") || choice.equals("q"))
					{
						try {
							FileOutputStream fileOut = new FileOutputStream("output.txt");
							ObjectOutputStream out = new ObjectOutputStream(fileOut);
							out.writeObject(customerHash);
							out.writeObject(movieIDHash);
							out.writeObject(releaseDBST);
							out.writeObject(rottenTomatoesHeap);
							out.close();
							fileOut.close();
							System.out.println("Serialized object successfully in output.txt");
						} catch(IOException i) {
							i.printStackTrace();
						}
					}
				}
			}
		}
	}

	//B: Change a customer's information, this has its own menu as there are so many different things that can be changed with the customers
	public static void customerMenu(CHash customers, BSTFP releaseD, MHashID hashID){
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		
		//sudo login to get which customer we are changing the data for
		System.out.println("Please enter the last four digits of the customer's Credit Card");
		int ccN = 0;
		Cnode friend = null;
		while ((ccN == 0) || (friend == null)){
			try {
				ccN = input2.nextInt();
				friend = customers.lookUp(ccN);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("A customer with that CCN was not in the database, please enter another: ");
			} catch (InputMismatchException f) {
				System.out.println("A customer with that CCN was not in the database, please enter another: ");
				input.nextLine();
			}
		}

		//customer menu loop
		String choice = "cowz";
		while((choice.equals("Q") == false) && (choice.equals("q") == false)){

			System.out.println();
			System.out.println("  You are logged in as " + friend.getName());
			System.out.println("  Would you like to:");
			System.out.println("  (A) Change Name");
			System.out.println("  (B) Update Email");
			System.out.println("  (C) Update Credit card number");
			System.out.println("  (D) View wish list");
			System.out.println("  (E) Add a movie to your wish list");
			System.out.println("  (F) Delete a movie from your wish list");
			System.out.println("  (G) Watch a movie on your wishlist");
			System.out.println("  (Q) Log out/Go back to main menu");
			System.out.print("    Option-> ");

			choice = input.next();

			//respective options and funtion calls
			if(choice.equals("A") || choice.equals("a")){
				System.out.println("What would you like to change the name to?");
				String name = input.next();
				friend.setName(name);
			}
			else if(choice.equals("B") || choice.equals("b")){
				System.out.println("What would you like to change the Email to");
				String email = input.next();
				friend.setEmail(email);	
			}
			else if(choice.equals("C") || choice.equals("c")){
				System.out.println("What would you like to change the last four digits to?");
				int cCN = 0;
				while (cCN == 0){
					try {
						cCN = input2.nextInt();
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("That is not a valid Credit card number, please enter another: ");
					} catch (InputMismatchException f) {
						System.out.println("That is not a valid Credit card number, please enter another: ");
						input.nextLine();
					}
				}
				friend.setCCN(cCN);
			}
			else if(choice.equals("D") || choice.equals("d")){
				System.out.println();
				System.out.println(friend.getName() + "'s Wish List");
				friend.printMovieWL();
			}
			else if(choice.equals("E") || choice.equals("e")){
				friend.addWLMovies(releaseD, hashID);
			}
			else if(choice.equals("F") || choice.equals("f")){
				friend.deleteWLMovie();
			}	

			//allows users to "watch" a movie on their wishlist, then they are given the option to remove it
			else if(choice.equals("G") || choice.equals("g")){
				friend.printMovieWL();
				System.out.print("Enter the rank of the movie you would like to watch: ");
				int rank = 0;
				while ((rank < 1) || (rank > friend.getNumMP())){
					try {
						rank = input2.nextInt();
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("That is not a valid rank, please enter another: ");
					} catch (InputMismatchException f) {
						System.out.println("That is not a valid rank, please enter another: ");
						input.nextLine();
					}
					if ((rank < 1) || (rank > friend.getNumMP())){
						System.out.println("That is not a valid rank, please enter another: ");
					}
					if(friend.getMovieAvailibilityWithRank(rank) == false){
						System.out.println("That movie is no longer availible, please enter another rank: ");
						rank = 0;
					}
				}
				System.out.println("You are watching " + friend.getMovieNameWithRank(rank));
   				System.out.println();
   				System.out.println();
   				System.out.println("Would you like to remove " + friend.getMovieNameWithRank(rank) + " from your wish list?");
   				System.out.print("(Y) yes (N) no ->  ");
   				String choise2 = input.next();
				if ((choise2.equals("Y") == true) || (choise2.equals("y") == true)){
					friend.simpleDeleteWLMovie(rank);}
				else{ 
				System.out.println(friend.getMovieNameWithRank(rank) + " is still on your wish list");}
			}

			//checks to make sure they entered a valid option from the menu
			if ((choice.equals("Q") == false) && (choice.equals("q") == false) && (choice.equals("A") == false) && (choice.equals("a") == false) &&
				(choice.equals("B") == false) && (choice.equals("b") == false) && (choice.equals("C") == false) && (choice.equals("c") == false) &&
				(choice.equals("D") == false) && (choice.equals("d") == false) && (choice.equals("E") == false) && (choice.equals("e") == false) &&
				(choice.equals("F") == false) && (choice.equals("f") == false) && (choice.equals("G") == false) && (choice.equals("g") == false)) {
				System.out.println("That was not one of the options");}
		}
	}


	//A: Add a customer to the database
	public static void addCustomer(CHash customers){
		
		//takes in user info then makes a new customer instance with said info
		Scanner input = new Scanner(System.in);
		System.out.println("please enter the Name of the new customer");
		String name = input.nextLine();
		System.out.println("please enter the Email of the new customer");
		String email = input.nextLine();
		System.out.println("please enter the last four digits of the customer's Credit Card");
		int ccN = 0;
		while (ccN == 0){
			try {
				ccN = input.nextInt();
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter a valid CCN");
			} catch (InputMismatchException f) {
				System.out.println("Please enter a valid CCN");
				input.nextLine();
			}
		}
		
		Cnode customer = new Cnode(name, ccN, email);
		customers.insert(customer);
		System.out.println("This customer has been added to the database");
	}



	//C: Add a movie to the database
	public static void addMovie(MHashID mIDs, BSTFP releaseD, RTHeap lLowRT) {
		
		//takes in movie info then makes a new movie instance with said info
		System.out.println("Please enter the name of the movie");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		
		int date = 0;
		System.out.println("Please enter the release date of the movie (yyyymmdd)");
		while (date < 19500000 || date > 20500000){
			try{
				date = input.nextInt();
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("that is not a valid date, please enter another");
			}
			catch (InputMismatchException f) {
				System.out.println("that is not a valid date, please enter another");
				input.nextLine();
			}
			if (date < 19500000 || date > 20500000){
				System.out.println("that is not a valid date, please enter another");
			}
		}

		int id = 0;
		System.out.println("Please enter the ID of the movie");
		while (id == 0){
			try{
				id = input.nextInt();
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("that is not a valid ID, please enter another");
			}
			catch (InputMismatchException f) {
				System.out.println("that is not a valid ID, please enter another");
				input.nextLine();
			}
		}
		
		int rtScore = 0;
		System.out.println("Please enter the Rotten Tomatoes Score of the movie");
		while (rtScore < 1 || rtScore > 100){
			try{
				rtScore = input.nextInt();
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("that is not a valid Rotten Tomatoes score, please enter another");
			}
			catch (InputMismatchException f) {
				System.out.println("that is not a valid Rotten Tomatoes score, please enter another");
				input.nextLine();
			}
			if (rtScore < 1 || rtScore > 100){
				System.out.println("that is not a valid Rotten Tomatoes score, please enter another");
			}
		}
		
		//adds new movie to data structures
		Mnode movie = new Mnode(name, date, id, rtScore);
		releaseD.insert(movie);
		mIDs.insert(movie);
		lLowRT.insert(movie);
	}


	//D: Delete a movie from the database
    public static void deleteMovie(MHashID mIDs, BSTFP releaseD0, RTHeap rtHeap){
    	Mnode worst = rtHeap.findMin();
   		String name = worst.getName();
   		int rtScore = worst.getRTScore();
   		System.out.println("The movie with the lowest rating is " + name + " with a Rotten Tomatoes score of " + rtScore);
   		System.out.print("Would you like to delete this movie from the database? (Y) yes, (N) no-> ");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		if (choice.equals("Y") || choice.equals("y")){
			rtHeap.deleteMin();
			//movie remains in customer wish lists, it is just unvailible
   			worst.hasMovieChange(false);
   			mIDs.delete(worst.getID());
   			releaseD0.delete(worst);
   			System.out.println("The lowest rated movie has been deleted");
		}
		else{
			System.out.println("The lowest rated movie has not been deleted");
		}

       	
    }

    //E: Search database by movie release date
    public static void searchRD(BSTFP releaseD0){
   		Scanner input = new Scanner(System.in);
   		Scanner input2 = new Scanner(System.in);
   		int releaseD = 0;
   		Mnode movie = null;
   		System.out.println("Please enter the release date of the movie you would like to find");
   		while (movie == null){
   			try{
   				releaseD = input2.nextInt();
   				movie = releaseD0.search(releaseD);
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
   		System.out.println("Title: " + movie.getName());
   		System.out.println("Release date (yyyymmdd): " + movie.getReleaseD());
   		System.out.println("Rotten Tomatoes score: " + movie.getRTScore());

   		//gives the user the option to "watch" the found movie
   		System.out.print("Would you like to watch this movie? y/n -> ");
   		String choice = input.next();
   		if (choice.equals("Y") || choice.equals("y")) {
   			System.out.println("You are watching " + movie.getName());
   			System.out.println();
   			System.out.println();
   		}
   	}

   	//F: Search database by movie ID number
   	 public static void searchID(MHashID mIDs){
   		Scanner input = new Scanner(System.in);
   		Scanner input2 = new Scanner(System.in);
   		int id = 0;
   		Mnode movie = null;
   		System.out.println("Please enter the ID number of the movie you would like to find");
   		while (movie == null){
   			try{
   				id = input2.nextInt();
   				movie = mIDs.lookUp(id);
   			}
   			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("A movie with that ID was not in the database, please enter another");
			}
			catch (InputMismatchException f) {
				System.out.println("A movie with that ID was not in the database, please enter another");
				input.nextLine();
			}
			catch (NullPointerException d){
				System.out.println("A movie with that ID was not in the database, please enter another: ");
			}
   		}
   		System.out.println("Title: " + movie.getName());
   		System.out.println("Release date (yyyymmdd): " + movie.getReleaseD());
   		System.out.println("Rotten Tomatoes score: " + movie.getRTScore());

   		//gives the user the option to "watch" the found movie
   		System.out.print("Would you like to watch this movie? y/n -> ");
   		String choice = input.next();
   		if (choice.equals("Y") || choice.equals("y")){
   			System.out.println("You are watching " + movie.getName());
   			System.out.println();
   			System.out.println();
   		}

   	}

   	//G: Display entire database in order of release date
   	public static void printAll(BSTFP releaseD0){
   		releaseD0.traverse();
   	}
}

/*********************************************
 * Robust menu-driven program that creates a small database system 
 * of Students, displays it, creates index (hash table) using binary search tree
 * to retrieve, modify, add, and delete student records taken from 
 * an input text file.
 * User types EXIT to exit program.
 * 
 * Methods used:
 * main(String[]) - implements program
 * menu() - displays menu for user choices
 * initFile() - validates input file
 * build() - builds random-access file of Students
 * display() - displays random-access file contents (5 at a time)
 * displayPageMenu() - gives display options
 * buildIndex() - builds hashtable (BST) index of Pairs
 * displayIndex() - displays index
 * retrieve() - retrieves Student from random-access file using index
 * modify() - modifies Student using index
 * add() - adds student to end of database
 * delete() - deletes student from database
 *
 * Farha Zindah
 * COSC 311, Fall 2021 (Project 5)
 * 12/6/2021
 *
 * Project5.java
 *
 *********************************************/
import java.io.*;
import java.util.*;

/**
 * Uses Pair, Student, and BST classes 
 * to read records from a file and write them to a random-access
 * file. File is then accessed using index (binary search tree)
 * @author azind
 *
 */
public class Project5 {

	// Initialize scanners and random-access file
	private static Scanner input;
	private static Scanner fin;
	private static RandomAccessFile raf;

	// Variables used to display run menu efficiently
	private static boolean stopPrint = false;
	private static boolean displayAll = false;
	private static boolean run = true;
	private static boolean indexBuilt = false;
	
	// Used to check if random-access file changed
	private static String rafName = "";

	// Initialize the index (Hash Table)
	private static Hashing<Integer> index = new Hashing<>();

	/** Main method runs program menu until user enters option 9 - exit
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner to read user input
		input = new Scanner(System.in);
		
		System.out.println("___________________________________________________\n"
				+ "\tWelcome to the Student Records\n______________________________" + "_____________________");

		// Run menu options until user types "EXIT"
		while (run) {

			try {
				menu();
				if (!run) {
					// close random-access file when user exits
					raf.close();
				}
			}

			catch (FileNotFoundException ex) {
				System.out.println("Error: File not found - return to menu");
			}

			catch (IOException ex) {
				System.out.println("Invalid request: please build index first");
			}

			catch (NullPointerException e) {
				System.out.println("Invalid request: please build file first");
			}
			catch (Exception e) {}
		}
	}

	/**
	 * Menu method that displays program options. Allows users to make and display
	 * random-access files and the index, and retrieve, modify, add, or delete a
	 * record.
	 */
	public static void menu() throws IOException {
		// Gets user's choice of action
		String menuChoice = "";

		System.out.println("\n\t\t\tMenu\n" + "---------------------------------------------------");
		System.out.println("Would you like to:\n\n1) Make a random-access file\t\t6) Modify a Record\n2) "
				+ "Display random-access file\t\t7) Add New Record\n3) Build Index"
				+ "\t\t\t\t8) Delete a Record\n4) Display Index\t\t\t9) Exit\n5) Retrieve a Record\n");

		menuChoice = input.next().toUpperCase();

		// Implement method based on user choice
		switch (menuChoice) {
		case "1":
			indexBuilt = false;
			initFile();
			build();
			break;
		case "2":
			display();
			break;
		case "3":
			buildIndex();
			break;
		case "4":
			displayIndex();
			break;
		case "5":
			retrieve();
			break;
		case "6":
			modify();
			break;
		case "7":
			add();
			break;
		case "8":
			delete();
			break;
		case "9":
			System.out.println("Program Terminated. Display Index:"
					+ "\n________________________________________");
			index.print(0,36,true);
			run = false;
			break;
		}
	}

	/**
	 * Method to read user input and initialize random access file
	 * @throws IOException 
	 */
	public static void initFile() throws IOException {

		boolean validFile = false;

		// get user input file and validate
		while (!validFile)
			try {
					// Get the input (txt file) name
					System.out.print("Enter an input text file name: ");
					String inputFile = input.next();
					
					// Initialize file and scanner
					File file = new File(inputFile);
					fin = new Scanner(file);

					// Get random-access file name
					System.out.print("Enter random-access file name: ");
					rafName = input.next();
					
					// Check if file already exists
					File f = new File(rafName);
					if (f.exists()) {
						f.delete();
					}
					
					// Create random-access file
					raf = new RandomAccessFile(new File(rafName), "rw");

				System.out.println("You have created a random access file named " + rafName + "!");
				validFile = true;
			} 
			catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
	}

	/**
	 * Method to build random access file. It reads from text file and writes
	 * students' records to random access file
	 * 
	 * @throws IOException
	 */
	public static void build() throws IOException {
		// create student object to read from file
		Student student = new Student();
		
		try {
			while (fin.hasNext()) {
				// read student from input file, write to raf
				student.readFromTextFile(fin);
				student.writeToFile(raf);
			}
		} 
		catch (InputMismatchException e) {}
		// close input file scanner
		fin.close();
	}

	/**
	 * Method to display random access file
	 * 
	 * @throws NullPointerException, IOException
	 */
	public static void display() throws NullPointerException {
		// create student object to read from file
		Student student = new Student();

		// get file name from user
		System.out.println("Type file name to display: ");

		String pickF = input.next().toLowerCase();
		
		try {
			// If file is new file, change random-access files
			if (!pickF.equals(rafName)) {

				// if random-access file doesn't exist, display message
				File f = new File(pickF);
				if (f.length() == 0)
					System.out.println(pickF + " does not exist!");
				
				// otherwise, open new random-access file
				else {
					rafName = pickF;
					raf = new RandomAccessFile(rafName, "rw");
					
					// index is set to not built for new file
					indexBuilt = false;
				}
			}
			
			// Check number of students printed in a page
			int numPrinted = 0;
			raf.seek(0);
			
			// Display students until end of random-access file
			while (raf.getFilePointer() < raf.length() && !stopPrint) {
				// read student from file
				student.readFromFile(raf);
				
				// If student is not deleted, display student
				if (!student.getFirst().trim().equals("DELETED")) {
					System.out.println(student);
					numPrinted++;
					// Stop after every 5 students printed, and display page menu
					if (numPrinted % 5 == 0 && !displayAll)
						displayPageMenu();
				}
			}
		} 
		catch (IOException e) {
			System.out.println();
		}
		
		// Reset variables
		displayAll = false;
		stopPrint = false;
	}

	/**
	 * Method to display next student records - Allows user to display next page,
	 * display all, or go to menu
	 */
	public static void displayPageMenu() {

		// Allow user to display more records or leave to menu
		System.out.println("\nReturn to Main Menu (M), Next Screen (N), Display All (A)");

		String option = input.next().toLowerCase();

		switch (option) {
		case "n":
			// continue display method for every 5 students
			break;
		case "a":
			// display all records
			displayAll = true;
			System.out.println("All Records:");
			break;
		default:
			// return to main menu
			stopPrint = true;
			return;
		}
	}

	/**
	 * Method to build Hash Table (BST) index, each node contains a key (student id)
	 * and address (where a record is located in the database)
	 * 
	 * @throws FileNotFoundException
	 */
	public static void buildIndex() throws FileNotFoundException {
		// create student object to read from file
		Student student = new Student();
		
		// Ask user to enter database name
		System.out.println("Enter file name to build index: ");
		String fName = input.next().toLowerCase();

		// If user picks new file, change random-access file being used
		if (!fName.equals(rafName)) {
			
			// if random-access file doesn't exist, display message
			File f = new File(fName);
			if (f.length() == 0) {
				System.out.println(fName + " is empty or does not exist!");
				return;
			}
			else {
				rafName = fName;
				raf = new RandomAccessFile(rafName, "rw");
			}
		}
		
		try {
			raf.seek(0);
			// While file has remaining students, read file and create index
			while (true) {
				student.readFromFile(raf);

				// if student hasn't been deleted, add them to the index
				if (!student.getFirst().trim().equals("DELETED")) {
					// add each record (Pair) to binary search tree index
					index.put(new Pair<Integer>(student.getID(), (int) (raf.getFilePointer() / 92) - 1)); 
				}
			}
		} 
		catch (IOException e) {
			// when end of method has been reached, set index to built
			indexBuilt = true;
			System.out.println("Index built!");
		}
	}

	/**
	 * Method to display index
	 * 
	 * @throws IOException
	 */
	public static void displayIndex() throws IOException, InputMismatchException {

		// If index hasn't been built, random-access file doesn't exist or can't be
		// accessed, throw exception
		if (!indexBuilt)
			throw new IOException();

		// Check where in index to start displaying
		System.out.println("\na) Display All\nb) Display Part");
		String displayChoice = input.next().toLowerCase();
		
		// Print out whole index level by level (using breadth-first traversal)
		if (displayChoice.equals("a"))
			index.print(); 
		// print out part of index
		else {

			// Prompt user for starting and ending index to display
			System.out.println("Enter starting index value: ");
			int start = input.nextInt();
			System.out.println("Enter ending index value: ");
			int end = input.nextInt();
			
			// If values are valid, print part of index
			if (start < end && end < 37 && start >= 0) 
				index.print(start, end, false);
			else {
				// Otherwise print entire index
				System.out.println("Index values not valid: printing full index");
				index.print();
			}
		}
	}

	/**
	 * Method retrieves a record from the randomAccessFile, seeks the correct record
	 * using index (binary search tree) and displays it on the screen
	 * 
	 * @throws IOException
	 */
	public static Pair<Integer> retrieve() throws IOException {
		// create student object to read from file
		Student student = new Student();
		
		// If index hasn't been built, random-access file doesn't exist or can't be accessed
		// throw exception
		if (!indexBuilt)
			throw new IOException();

		// Ask for student id
		System.out.print("Please enter a student id: ");
		int id = input.nextInt();

		try {
			// Use hash function to transform student ID into the hash table index
			// Search binary tree and return address value
			int address = index.get(id);
		
			// if pair (with student id) exists, retrieve from random-access file
			raf.seek(address * 92);
			student.readFromFile(raf);
			System.out.println(student);
							
			// set filepointer back to original record
			raf.seek(address * 92);
			
			return new Pair<Integer>(id, address);
		}
		catch (Exception e) {
			// if student record doesn't exist, print error
			System.out.println("Invalid record");
			return null;
		}
	}

	/**
	 * Method to modify student records
	 * 
	 * @throws IOException
	 */
	public static void modify() throws IOException {
		// create student object to read from file
		Student student = new Student();
		// variables to modify student
		String newFirst;
		String newLast;
		double newGPA;

		// If index hasn't been built, random-access file doesn't exist or can't be accessed
		// throw exception
		if (!indexBuilt)
			throw new IOException();
		
		try {
			// Check if student record exists
			// retrieve using index (Hashing) and display
			Pair<Integer> p = retrieve();

			student.readFromFile(raf);
			
			String mod = "";

			// While user doesn't choose 'main menu' and record is valid, modify record
			while (!mod.equals("m") && !p.equals(null)) {
				
				System.out.println("\nModify: \n(f) First Name\n(l) Last Name"
						+ "\n(g) GPA" + "\nType (m) to return to menu\n");

				mod = input.next().toLowerCase();

				switch (mod) {
				// Change first name
				case "f":
					System.out.print("New First Name: ");
					newFirst = input.next();
					student.setFirst(newFirst);
					break;
				// Change last name
				case "l":
					System.out.print("New Last Name: ");
					newLast = input.next();
					student.setLast(newLast);
					break;
				// Change gpa
				case "g":
					System.out.print("New gpa: ");
					newGPA = input.nextDouble();
					student.setGPA(newGPA);
					break;
				// Write student to file and return to menu
				default:
					raf.seek(raf.getFilePointer() - 92);
					student.writeToFile(raf);
					break;
				}
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Method to add a new record. User enters data for new record, data is appended
	 * to the end of the random-access file
	 * 
	 * @throws IOException
	 */
	public static void add() throws IOException {
		// create student object to read from file
		Student student = new Student();

		// If index hasn't been built, random-access file doesn't exist or can't be accessed
		// throw exception
		if (!indexBuilt)
			throw new IOException();

		// Prompt for new student data
		System.out.println("Please enter data in this format: fName lName id gpa");
		student.setData(input.next(), input.next(), input.nextInt(), input.nextDouble());

		// write to file
		raf.seek(raf.length());
		student.writeToFile(raf);

		// add student key and address to index (binary search tree)
		index.put(new Pair<Integer>(student.getID(), (int) (raf.length() / 92) - 1));
	}

	/**
	 * Method to delete a record: deletes specified record from index and the random
	 * access file using a lazy deletion technique.
	 * 
	 * @throws IOException
	 */
	public static void delete() throws IOException {
		
		// If index hasn't been built, random-access file doesn't exist or can't be accessed
		// throw exception
		if (!indexBuilt)
			throw new IOException();

		try {
			// retrieve student location using index
			Pair<Integer> p = retrieve();

			raf.seek(p.getSecond() * 92);

			// lazy delete student using first name
			raf.writeChars(String.format("%-20.20s", "DELETED"));
			System.out.println("Record deleted");

			// remove student from index
			index.remove(p);
		}

		catch (IOException e) {
			System.out.println("Invalid Record");
		}
		catch (NullPointerException e) {}
	}
}

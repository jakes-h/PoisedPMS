import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

/**
 * This is the main class of the Poised Engineering Project Management System.
 * <p>
 * This program stores various information about the projects that Poised work on,
 * including information about the project, architect, contractor and the customer.
 * <p>
 * New projects can be loaded and existing projects can be updated and finalized.
 * <p>
 * Details about incomplete projects and overdue projects can also be viewed.
 * <p> 
 * In version 1 of this program project data was saved in external text files.
 * Version 2 of the program is modified so that it now uses a database instead
 * of text files to store data needed for the program.
 * <p> 
 * @author Jaco Hartman
 * @version 2.0
 * */

public class Main
{
	/** 
	 * Initialize constant variables. 
	 */
	public static final Scanner userInput = new Scanner(System.in);
	static Project newProject = null;
	static String menu = null;

	/**
	 * Initialize ArrayList to store projects in. 
	 */
	static ArrayList<Project> projectList = new ArrayList<>();

	public static void main (String [] args) {
		
		/** 
		 * When the program starts, call the readTablesIncompleteProjects() 
		 * method to load all incomplete tasks from the database. 
		 */
		DatabaseManager.readTablesIncompleteProjects();	
		
		/**
		 * Show welcome message.
		 */
		System.out.println("Welcome to the Poised project management system!");

		/**
		 * Use while loop for the main menu options. 
		 */
		while (true) {
			System.out.print("""

					Main menu:
					1 - Add new project
					2 - View all incomplete projects
					3 - View all overdue projects
					4 - Update existing project details
					5 - Exit
					""");
			/**
			 * User to enter their menu option. 
			 */
			menu = userInput.nextLine();

			/**
			 * If there are no projects loaded, don't allow user to select options 2, 3 or 4. 
			 * User forced to first load a new project.
			 */
			if ((projectList.isEmpty()) && ((menu.equals("2")) || (menu.equals("3")) 
					|| (menu.equals("4")))) {
				System.out.println("No projects loaded. Please add a new project before selecting "
						+ "menu items 2, 3 or 4.");
			} else if (menu.equals("1")) {
				/**
				 * Call method to create a new project object.
				 */
				ProjectAdd.projectAdd();
				// Add the new project to the projectList.
				projectList.add(newProject);
				System.out.println("\nNew project loaded successfully.");
			} else if (menu.equals("2")) {
				/**
				 * Call method to view all incomplete projects.
				 */
				MenuOptions.viewIncompleteProjects();
			} else if (menu.equals("3")) {
				/**
				 * Call method to view all overdue projects.
				 */
				MenuOptions.viewOverdueProjects();
			} else if (menu.equals("4")) {
				/**
				 * Call method to allow user to update projects.
				 */
				MenuOptions.updateProject();
			} else if (menu.equals("5")) {
				/**
				 * Before exiting, call method to write the projects in the 
				 * projectList to the database.
				 */
				DatabaseManager.writeTablesIncompleteProjects();

				/**
				 * Print goodbye message and exit the program.
				 */
				System.out.println("Goodbye!");
				System.exit(0);
			} else {
				/**
				 * Show error message if user enters an incorrect input.
				 */
				System.out.println("Invalid entry. Please enter a valid option.");
			}
		}
	}
}

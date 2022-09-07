import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Class that contains various methods to update the details of existing projects.
 */

public class MenuOptions extends Main {

	/**
	 * Initialize variable to store the index of the project to be updated.
	 */
	static int projectUpdateIndex = 0;

	/**
	 * Method to view all incomplete projects.
	 */
	static void viewIncompleteProjects() {
		/**
		 * Initiate iterator and use while loop to traverse all elements in the projectList.
		 */
		Iterator<Project> projectListIterator = projectList.iterator();
		while (projectListIterator.hasNext()) {
			/**
			 * Print the details of the project.
			 */
			System.out.print(projectListIterator.next());
		}
		System.out.print("\n\n");
	}

	/**
	 * Method to view all overdue projects.
	 */
	static void viewOverdueProjects() {
		System.out.print("\n********************* OVERDUE PROJECTS ********************");
		/**
		 * Create SimpleDateFormat class object.
		 */
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		/**
		 * Initialize counter for overdue projects.
		 */
		int overdueCount = 0;
		/**
		 * Find today's date
		 */
		Date dateToday = new Date();
		/**
		 * Initialize Date variable to compare project deadline with today's date.
		 */
		Date deadLineDate = null;
		/**
		 * Use for loop to loop through projectList.
		 */
		for (int i = 0; i < projectList.size(); i++) {
			/**
			 * Get the deadline of the project object which is saved as a string.
			 */
			String deadlineString = projectList.get(i).getDeadline();
			try {
				/**
				 * Parse to Date format and use try catch for any parse errors.
				 */
				deadLineDate = date.parse(deadlineString);
			} catch (ParseException e) {
				System.out.print("Error parsing date string to date format.");
			}
			/**
			 * If the project is overdue, show the details of the project.
			 */
			if (dateToday.after(deadLineDate)) {
				System.out.print(projectList.get(i) + "\n");
				/**
				 * Increase the counter if the project is overdue.
				 */
				overdueCount++;						
			}
		}
		/**
		 * Show message if there are no overdue projects.
		 */
		if (overdueCount == 0) {
			System.out.println("\n\nNo overdue projects!\n");
		}
	}

	/**
	 * Method to update an existing project.
	 */
	static void updateProject() {
		/**
		 * Initialize variables to be used.
		 */
		String projectNumSearch = null;
		String projectNameSearch = null;
		boolean projectFound = false;
		/**
		 * User given the option to search for a project by project number or project name.
		 */
		System.out.println("\nWould you like to search for the project by project number or "
				+ "project name?");
		while (true) {
			System.out.print("""
					Please enter 1 or 2:
					1 - Search by project number
					2 - Search by project name
					""");
			/**
			 * User to enter 1 or 2.
			 */
			menu = userInput.nextLine().trim();
			if (menu.equals("1")) {
				System.out.println("\nWhat is the project number of the project you would like "
						+ "to update? (input is case sensitive)");
				/**
				 * User to enter the project number of the project to update.
				 */
				projectNumSearch = userInput.nextLine().trim();
				/**
				 * Loop through the projectList.
				 */
				for (int i = 0; i < projectList.size(); i++) {
					/**
					 * Get the projectNo of the current project in the for loop.
					 */
					String projectNo = projectList.get(i).getProjectNo();
					/**
					 * Check if the user's entered projectNo matches the projectNo 
					 * of the current project in the for loop.
					 */
					if (projectNo.equals(projectNumSearch)) {
						/**
						 * If it matches, assign the index, set projectFound to true 
						 * and break the loop.
						 */
						projectUpdateIndex = i;
						projectFound = true;
						System.out.println("Project found!");
						break;
					}
				}
				/**
				 * If a match was found, call the updateProjectMenu method to bring up the update options.
				 */
				if (projectFound == true) {
					updateProjectMenu();
					break;
				} else {
					/**
					 * If no match found, show an error message and return to the main menu.
					 */
					System.out.println("Sorry, no project with that project number found.");
					break;
				}
			} else if (menu.equals("2")) {
				System.out.println("\nWhat is the project name of the project you would like to "
						+ "update? (input is case sensitive)");
				/**
				 * User to enter the project name of the project to update.
				 */
				projectNameSearch = userInput.nextLine().trim();
				/**
				 * Loop through the projectList.
				 */
				for (int i = 0; i < projectList.size(); i++) {
					/**
					 * Get the projectName of the current project in the for loop.
					 */
					String projectName = projectList.get(i).getProjectName();
					/**
					 * Check if the user's entered projectName matches the projectName 
					 * of the current project in the for loop.
					 */
					if (projectName.equals(projectNameSearch)) {
						/**
						 * If it matches, assign the index, set projectFound to true 
						 * and break the loop.
						 */
						projectUpdateIndex = i;
						projectFound = true;
						System.out.println("Project found!");
						break;
					}
				}
				/**
				 * If a match was found, call the updateProjectMenu method to bring up the update options.
				 */
				if (projectFound == true) {
					updateProjectMenu();
					break;
				} else {
					/**
					 * If no match found, show an error message and return to the main menu.
					 */
					System.out.println("Sorry, no project with that project name found.");
					break;
				}
			} else {
				/**
				 * Error message if user enters an incorrect input.
				 */
				System.out.println("Invalid entry.");
			}	
		}
	}

	/**
	 * Method for the options to update an existing project.
	 */
	static void updateProjectMenu() {
		/**
		 * Print the details of the project to be updated.
		 */
		System.out.print(projectList.get(projectUpdateIndex) + "\n\n");
		/**
		 * Use while loop for the edit menu options.
		 */
		while (true) {
			System.out.print("""
					Menu:
					1 - Change the due date of the project
					2 - Change the total amount of the fee paid to date
					3 - Update the contractor's telephone number
					4 - Update the contractor's email address
					5 - Finalize the project
					6 - Back to main menu
					""");
			/**
			 * User to enter their menu option.
			 */
			menu = userInput.nextLine();

			if (menu.equals("1")) {
				System.out.println("\nWhat is the new due date of the project? "
						+ "Enter in format DD-MM-YYYY.");
				/**
				 * Use the setDeadline setter to change the deadline.
				 */
				projectList.get(projectUpdateIndex).setDeadline();
				System.out.println("Due date updated successfully.");									
			} else if (menu.equals("2")) {
				System.out.println("\nWhat is the new total amount of the fee paid "
						+ "to date?");
				/**
				 * Use the setFeePaid setter to change the fee paid to date..
				 */
				projectList.get(projectUpdateIndex).setFeePaid();
				System.out.println("Fee paid to date updated successfully.");
			} else if (menu.equals("3")) {
				System.out.println("\nWhat is the contractor's new telephone number?");
				/**
				 * Use the setTelNo setter to change the contractor's telephone number.
				 */
				projectList.get(projectUpdateIndex).contractor.setTelNo();
				System.out.println("Telephone number updated successfully.");
			} else if (menu.equals("4")) {
				System.out.println("\nWhat is the contractor's new email address?");
				String newEmail = userInput.nextLine();
				/**
				 * Use the setEmail setter to change the contractor's email.
				 */
				projectList.get(projectUpdateIndex).contractor.setEmail(newEmail);
				System.out.println("Email address updated successfully.");
			} else if (menu.equals("5")) {
				System.out.println("What is the completion date of this project? "
						+ "Enter in format DD-MM-YYYY.");
				/**
				 * User to enter the completion date using the enterDate method.
				 */
				String completionDate = ProjectAdd.enterDate();
				/**
				 * Use the setCompletionDate setter to set the completion date. 
				 */
				projectList.get(projectUpdateIndex).setCompletionDate(completionDate);
				/**
				 * Check if the customer already paid the full fee.
				 */
				if (projectList.get(projectUpdateIndex).feePaid 
						== projectList.get(projectUpdateIndex).feeTotal) {
					/**
					 * Use the setCompleted setter to mark the project as complete.
					 */
					projectList.get(projectUpdateIndex).setCompleted();
					/**
					 * No invoice to be generated if full fee already paid.
					 */
					System.out.print("""
							Project finalized.
							No invoice generated, project is fully paid.
							""");
					/**
					 * Call method to write the completed project details to the 
					 * completed projects tables in the database.
					 */
					DatabaseManager.writeTablesCompletedProject(projectUpdateIndex);
					/**
					 * Remove the completed project from the projectList.
					 */
					projectList.remove(projectUpdateIndex);
					break;
				} 
				/**
				 * If customer hasn't paid the full fee yet.
				 */
				else { 
					/**
					 *  Use the setCompleted setter to mark the project as complete.
					 */
					projectList.get(projectUpdateIndex).setCompleted();
					System.out.println("Project finalized.");
					/**
					 * Calculate the outstanding fee.
					 */
					double feeOutstanding = projectList.get(projectUpdateIndex).feeTotal 
							- projectList.get(projectUpdateIndex).feePaid;
					/**
					 * Print the customer invoice.
					 */
					System.out.print("\n*******CUSTOMER INVOICE*******"
							+ "\n\nCustomer details:\n" 
							+ projectList.get(projectUpdateIndex).customer
							+ "\n\nTotal amount outstanding: R" + feeOutstanding
							+ "\n");
					/**
					 * Call method to write the completed project details to the 
					 * completed projects tables in the database.
					 */
					DatabaseManager.writeTablesCompletedProject(projectUpdateIndex);
					/**
					 * Remove the completed project from the projectList.
					 */
					projectList.remove(projectUpdateIndex);
					break;
				} 
			} else if (menu.equals("6")) {
				/**
				 * Break the loop to return to the main menu.
				 */
				break;
			} else {
				/**
				 * Show error message if user enters an incorrect input.
				 */
				System.out.println("Invalid entry. Please enter a valid option.");
			}
		}
	}
}

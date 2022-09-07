import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * Class to capture the details of a new project.
 */

public class ProjectAdd extends Main {

	/**
	 * Method to add a new project.
	 */
	static void projectAdd() {
		/**
		 * User to enter the project number.
		 */
		System.out.println("\nWhat is the project number?");
		String projectNo = userInput.nextLine();

		/**
		 * User to enter the project name.
		 */
		System.out.println("What is the project name? (leave blank to auto generate)");
		String projectName = userInput.nextLine();

		/**
		 * User to enter the building type.
		 */
		System.out.println("What type of building is being designed i.e. "
				+ "House, Apartment block or Store?");
		String buildingType;
		while (true) {
			buildingType = userInput.nextLine().trim();
			/**
			 * Check if user entered correct input.
			 */
			if (("House".equals(buildingType)) 
					|| ("Apartment block".equals(buildingType)) 
					|| ("Store".equals(buildingType))) {
				/**
				 * Break the while loop if user entered correct input.
				 */
				break;
			} else {
				System.out.print("Invalid input. Please enter 'House', "
						+ "'Apartment block' or 'Store'. Input is case sensitive.\n");
			}
		}

		/**
		 * User to enter the project's physical address.
		 */
		System.out.println("What is the physical address for the project?");
		String projectAddress = userInput.nextLine();

		/**
		 * User to enter the ERF number.
		 */
		System.out.println("What is the ERF number?");
		String erfNo = userInput.nextLine();

		/**
		 * User to enter the project total fee.
		 */
		System.out.println("What is the total fee being charged for the project?");
		/**
		 * Use try catch block to ensure user enters valid numerical value.
		 */
		double feePaid;
		double feeTotal;
		/**
		 * While loop to run until user enters valid numerical value.
		 */
		while (true) {
			try {
				feeTotal = userInput.nextDouble();
				/**
				 * Break while loop if double successfully entered.
				 */
				break;
			}
			catch (InputMismatchException exception) {
				/**
				 * Error message if user didn't enter double correctly.
				 */
				System.out.println("Invalid entry. Please enter a numerical value.");
				userInput.next();
			}
		}

		/**
		 * User to enter the total amount paid to date.
		 */
		System.out.println("What is the total amount paid to date?");
		/**
		 * Use try catch block to ensure user enters valid numerical value.
		 */
		while (true) {
			try {
				feePaid = userInput.nextDouble();
				/**
				 * Check that user doesn't enter a fee higher than the total fee.
				 */
				if (feePaid > feeTotal) {
					System.out.println("Total amount paid to date cannot be more than"
							+ " the total fee. Please enter an amount less than or equal"
							+ " to the total fee.");
				} else {
					/**
					 * Break while loop if double successfully entered.
					 */
					break;
				}
			}
			catch (InputMismatchException exception) {
				/**
				 * Error message if user didn't enter double correctly.
				 */
				System.out.println("Invalid entry. Please enter a numerical value.");
				userInput.next();
			}
		}

		/**
		 * Skip the new line character in order to input the next variable.
		 */
		userInput.nextLine(); 

		/**
		 * User to enter the project deadline.
		 */
		System.out.println("What is the deadline for the project? Enter in format DD-MM-YYYY.");
		String deadline = enterDate();

		/**
		 * User to enter the Architect's first name.
		 */
		System.out.println("What is the first name of the Architect?");
		String firstName = userInput.nextLine();

		/**
		 * User to enter the Architect's surname.
		 */
		System.out.println("What is the surname of the Architect?");
		String surname = userInput.nextLine();

		/**
		 * Combine the architect's first name and surname.
		 */
		String name = firstName + " " + surname;

		/**
		 * User to enter the Architect's telephone number.
		 */
		System.out.println("What is the telephone number of the Architect?");
		/**
		 * Call the enterTelNo() method to make sure user enters telNo in integers only.
		 */
		String telNo = enterTelNo();

		/**
		 * User to enter the Architect's email.
		 */
		System.out.println("What is the email address of the Architect?");
		String email = userInput.nextLine();

		/**
		 * User to enter the Architect's physical address.
		 */
		System.out.println("What is the physical address of the Architect?");
		String address = userInput.nextLine();

		/**
		 * Create the architect Person object
		 */
		Person architect = new Person(name, telNo, email, address);

		/**
		 * User to enter the Contractor's first name.
		 */
		System.out.println("What is the first name of the Contractor?");
		firstName = userInput.nextLine();

		/**
		 * User to enter the Contractor's surname.
		 */
		System.out.println("What is the surname of the Contractor?");
		surname = userInput.nextLine();

		/**
		 * Combine the contractor's first name and surname.
		 */
		name = firstName + " " + surname;

		/**
		 * User to enter the contractor's telephone number.
		 */
		System.out.println("What is the telephone number of the Contractor?");
		telNo = enterTelNo();

		/**
		 * User to enter the contractor's email.
		 */
		System.out.println("What is the email address of the Contractor?");
		email = userInput.nextLine();

		/**
		 * User to enter the contractor's physical address.
		 */
		System.out.println("What is the physical address of the Contractor?");
		address = userInput.nextLine();

		/**
		 * Create the contractor Person object
		 */
		Person contractor = new Person(name, telNo, email, address);

		/**
		 * User to enter the customer's first name.
		 */
		System.out.println("What is the first name of the Customer?");
		firstName = userInput.nextLine();

		/**
		 * User to enter the customer's surname.
		 */
		System.out.println("What is the surname of the Customer?");
		surname = userInput.nextLine();

		/**
		 * Combine the customer's first name and surname.
		 */
		name = firstName + " " + surname;

		/**
		 * User to enter the customer's telephone number.
		 */
		System.out.println("What is the telephone number of the Customer?");
		telNo = enterTelNo();

		/**
		 * User to enter the customer's email.
		 */
		System.out.println("What is the email address of the Customer?");
		email = userInput.nextLine();

		/**
		 * User to enter the customer's physical address.
		 */
		System.out.println("What is the physical address of the Customer?");
		address = userInput.nextLine();

		/**
		 * Create the customer Person object.
		 */
		Person customer = new Person(name, telNo, email, address);

		/**
		 * Create the Project Name if user did not enter a Project Name.
		 */
		if ("".equals(projectName)) {
			/**
			 * Split the buildingType in two parts i.e. to separate Apartment block in two.
			 */
			String[] projectNameArray = buildingType.split(" ", 2);
			/**
			 * Use the first part of the buildingType and surname of the customer.
			 */
			projectName = projectNameArray[0] + " " + surname;
		}

		/**
		 * Create the Project object.
		 */
		newProject = new Project(projectNo, projectName, buildingType, projectAddress, 
				erfNo, feeTotal, feePaid, deadline, architect, contractor, customer);
	}

	/**
	 * Method to make sure user enters a date in the correct format.
	 * @return the date in String format.
	 */
	static String enterDate() {
		/**
		 * Create SimpleDateFormat class object.
		 */
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		String dateEntered;
		/**
		 * Use try catch to check if user enters date in correct format.
		 */
		while (true) {
			try {
				/**
				 * Parse user date input to date format.
				 */
				Date sdfDeadline = date.parse(userInput.nextLine());
				/**
				 * If successfully parsed, format back to String.
				 */
				dateEntered = date.format(sdfDeadline);
				break;
			} catch (ParseException e) {
				/**
				 * Show error if date incorrectly entered and asks user to enter again.
				 */
				System.out.println("Incorrect date format. Please enter in format DD-MM-YYYY.");
			}
		}
		return dateEntered;
	}
	
	/**
	 * Method to make sure user enters the telephone number in the correct format.
	 * @return the telephone number in String format.
	 */
	static String enterTelNo() {
		String telNo = null;
		/**
		 * Use while loop until user enters in correct format.
		 */
		while (true) {
			/**
			 * User to enter the telephone number and store as String.
			 */
			telNo = userInput.nextLine();
			/**
			 * Use try catch blocks to catch the parse exception.
			 */
			try {
				/**
				 * Parse the user's string entry to int.
				 */
				int telNum = Integer.parseInt(telNo);
				/**
				 * Break loop if correctly entered.
				 */
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid entry. Please enter integers only.");
			}
		}
		return telNo;
	}
}

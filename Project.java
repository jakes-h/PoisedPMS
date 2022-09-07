import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Project class to construct and create project objects.
 */
public class Project {

	/**
	 * Scanner to take in user input.
	 */
	Scanner userInput = new Scanner(System.in);

	/**
	 * Attributes of the project object.
	 */
	String projectNo;
	String projectName;
	String buildingType;
	String projectAddress;
	String erfNo;
	double feeTotal;
	double feePaid;
	String deadline;
	Person architect;
	Person contractor;
	Person customer;
	String completed = "No";
	String completionDate;

	/**
	 * Constructor of the project object.
	 * @param projectNo
	 * @param projectName
	 * @param buildingType
	 * @param projectAddress
	 * @param erfNo
	 * @param feeTotal
	 * @param feePaid
	 * @param deadline
	 * @param architect
	 * @param contractor
	 * @param customer
	 */
	public Project (String projectNo, String projectName, String buildingType,
			String projectAddress, String erfNo, double feeTotal, double feePaid,
			String deadline, Person architect, Person contractor, Person customer) {
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.projectAddress = projectAddress;
		this.erfNo = erfNo;
		this.feeTotal = feeTotal;
		this.feePaid = feePaid;
		this.deadline = deadline;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
	}


	/**
	 * Setter to change the deadline.
	 */
	public void setDeadline() {
		deadline = ProjectAdd.enterDate();
	} 

	/**
	 * Setter to change the fee paid to date.
	 */
	public void setFeePaid() {
		/**
		 * Use try catch block to ensure user enters valid numerical value.
		 */
		while (true) {
			try {
				feePaid = userInput.nextDouble();
				/**
				 * Check that user doesn't enter a fee higher than the total fee.
				 */
				if (feePaid > this.feeTotal) {
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
				 * Show error message if user didn't enter double correctly.
				 */
				System.out.println("Invalid entry. Please enter a numerical value.");
				userInput.next();
			}
		}
	}

	/**
	 * Setter to change the task to complete.
	 */
	public void setCompleted() {
		completed = "Yes";
	}
	
	/**
	 * Setter to set the task completion date.
	 * @param updateCompletionDate
	 */
	public void setCompletionDate(String updateCompletionDate) {
		completionDate = updateCompletionDate;
	}

	/**
	 * Getters
	 */
	public String getProjectNo() {
		return projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public String getErfNo() {
		return erfNo;
	}

	public double getFeeTotal() {
		return feeTotal;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public Person getArchitect() {
		return architect;
	}

	public Person getContractor() {
		return contractor;
	}

	public Person getCustomer() {
		return customer;
	}

	public String getCompleted() {
		return completed;
	}

	public String getDeadline() {
		return deadline;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	/**
	 * toString method to print out the project object.
	 */
	public String toString() {
		String output = "\n\n--------------------- PROJECT DETAILS ---------------------\n";
		output += "\nProject no: " + projectNo;
		output += "\nProject name: " + projectName;
		output += "\nBuilding Type: " + buildingType;
		output += "\nProject address: " + projectAddress;
		output += "\nErf no: " + erfNo;
		output += "\nTotal fee: " + (String.format("%.2f", feeTotal));
		output += "\nFee paid: " + (String.format("%.2f", feePaid));
		output += "\nDeadline: " + deadline;
		output += "\n\nArchitect:\n" + architect;
		output += "\n\nContractor:\n" + contractor;
		output += "\n\nCustomer:\n" + customer;
		output += "\n\nCompleted: " + completed;

		return output;
	}
}

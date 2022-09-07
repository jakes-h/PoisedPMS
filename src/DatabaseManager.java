import java.sql.*;
/**
 * Class that contains methods to read details of existing projects from the poise_pms database
 * and write details of existing projects and completed projects to the database.
 * <p>
 * This class replaced the class in version 1 of the program that interacted
 * with text files.
 */
public class DatabaseManager extends Main {

	/**
	 * Method to connect to the poise_pms database, via the jdbc:mysql: channel on localhost
	 * Use username "otheruser", password "swordfish".
	 * @return connection
	 * @throws SQLException
	 */
	static Connection getDbConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/poise_pms?useSSL=false",
				"otheruser",
				"swordfish"
				);
		return connection;
	}

	/**
	 * Method to load all incomplete tasks from the database.
	 */
	static void readTablesIncompleteProjects() {
		/**
		 * Use try catch blocks to catch SQLExceptions.
		 */
		try {
			/**
			 * Call method to connect to the poise_pms database and create
			 * two direct lines to the database for running queries
			 */
			Connection connection = getDbConnection();
			Statement statement1 = connection.createStatement();
			ResultSet resultsProjects = null;
			Statement statement2 = connection.createStatement();
			ResultSet resultsPersons = null;
			/** 
			 * Initialize person objects to use.
			 */
			Person architect = null;
			Person contractor = null;
			Person customer = null;


			/**
			 * Run executeQuery to select all rows in the incomplete_projects table.
			 */
			resultsProjects = statement1.executeQuery("SELECT * FROM incomplete_projects");
			/**
			 * Loop over the results, get all the information stored in the various columns 
			 * and save in respective variables.
			 */
			while (resultsProjects.next()) {
				String projectNo = resultsProjects.getString("Project_no");
				String projectName = resultsProjects.getString("Project_name");
				String buildingType = resultsProjects.getString("Building_type");
				String projectAddress = resultsProjects.getString("Project_address");
				String erfNo = resultsProjects.getString("Erf_no");
				double feeTotal = resultsProjects.getDouble("Total_fee");
				double feePaid = resultsProjects.getDouble("Fee_paid");
				String deadline = resultsProjects.getString("Deadline");

				/**
				 * Run executeQuery to select the row in the incomplete_projects_persons table 
				 * containing information for the Architect where the project number matches 
				 * the project in the incomplete_projects table.
				 */
				resultsPersons = statement2.executeQuery(
						"SELECT * FROM incomplete_projects_persons "
								+ "WHERE Project_no ='"+projectNo+"' "
								+ "AND Person_type='Architect'"
						);
				/**
				 * Get all the information stored in the various columns for the Architect
				 * and save in respective variables.
				 */
				while (resultsPersons.next()) {
					String architectName = resultsPersons.getString("Name");
					String architectTelNo = resultsPersons.getString("Tel_no");
					String architectEmail = resultsPersons.getString("Email");
					String architectAddress = resultsPersons.getString("Address");
					/**
					 * Create the architect Person object.
					 */
					architect = new Person(architectName, 
							architectTelNo, 
							architectEmail, 
							architectAddress);
				}

				/**
				 * Run executeQuery to select the row in the incomplete_projects_persons table 
				 * containing information for the Contractor where the project number matches 
				 * the project in the incomplete_projects table.
				 */
				resultsPersons = statement2.executeQuery(
						"SELECT * FROM incomplete_projects_persons "
								+ "WHERE Project_no ='"+projectNo+"' "
								+ "AND Person_type='Contractor'"
						);
				/**
				 * Get all the information stored in the various columns for the Contractor
				 * and save in respective variables.
				 */
				while (resultsPersons.next()) {
					String contractorName = resultsPersons.getString("Name");
					String contractorTelNo = resultsPersons.getString("Tel_no");
					String contractorEmail = resultsPersons.getString("Email");
					String contractorAddress = resultsPersons.getString("Address");
					/**
					 * Create the contractor Person object.
					 */
					contractor = new Person(contractorName, contractorTelNo, 
							contractorEmail, contractorAddress);
				}

				/**
				 * Run executeQuery to select the row in the incomplete_projects_persons table 
				 * containing information for the Customer where the project number matches 
				 * the project in the incomplete_projects table.
				 */
				resultsPersons = statement2.executeQuery(
						"SELECT * FROM incomplete_projects_persons "
								+ "WHERE Project_no ='"+projectNo+"' "
								+ "AND Person_type='Customer'"
						);
				/**
				 * Get all the information stored in the various columns for the Customer
				 * and save in respective variables.
				 */
				while (resultsPersons.next()) {
					String customerName = resultsPersons.getString("Name");
					String customerTelNo = resultsPersons.getString("Tel_no");
					String customerEmail = resultsPersons.getString("Email");
					String customerAddress = resultsPersons.getString("Address");
					/**
					 * Create the customer Person object.
					 */
					customer = new Person(customerName, customerTelNo, 
							customerEmail, customerAddress);
				}
				/**
				 * Create a temporary project object from the extracted data.
				 */
				Project tempProject = new Project(projectNo, projectName, buildingType, 
						projectAddress, erfNo, feeTotal, feePaid, deadline, 
						architect, contractor, customer);
				/**
				 * Add the project to the projectList.
				 */
				projectList.add(tempProject);  
			}

			/**
			 * Close up the connections.
			 */
			resultsProjects.close();
			resultsPersons.close();
			statement1.close();
			statement2.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error, couldn't read from database.");
			e.printStackTrace();
		}
	}

	/**
	 * Method to write incomplete projects to the database.
	 */
	static void writeTablesIncompleteProjects() {
		/**
		 * Use try catch blocks to catch SQLExceptions.
		 */
		try {
			/**
			 * Call method to connect to the poise_pms database and create
			 * direct line to the database for running queries
			 */
			Connection connection = getDbConnection();
			Statement statement = connection.createStatement();

			/**
			 * Run executeQueries to delete the data inside the incomplete_projects
			 * and incomplete_projects_persons tables.
			 */
			statement.executeUpdate(
					"TRUNCATE TABLE incomplete_projects"
					);

			statement.executeUpdate(
					"TRUNCATE TABLE incomplete_projects_persons"
					);

			/**
			 * Loop through every project on the projectList, extract the project and person
			 * details and save in variables.
			 */
			for (int i = 0; i < projectList.size(); i++) {
				String projectNo = projectList.get(i).getProjectNo();
				String projectName = projectList.get(i).getProjectName();
				String buildingType = projectList.get(i).getBuildingType();
				String projectAddress = projectList.get(i).getProjectAddress();
				String erfNo = projectList.get(i).getErfNo();
				double feeTotal = projectList.get(i).getFeeTotal();
				double feePaid = projectList.get(i).getFeePaid();
				String deadline = projectList.get(i).getDeadline();
				String complete = projectList.get(i).getCompleted();

				String architectName = projectList.get(i).architect.getName();
				String architectTelNo = projectList.get(i).architect.getTelNo();
				String architectEmail = projectList.get(i).architect.getEmail();
				String architectAddress = projectList.get(i).architect.getAddress();

				String contractorName = projectList.get(i).contractor.getName();
				String contractorTelNo = projectList.get(i).contractor.getTelNo();
				String contractorEmail = projectList.get(i).contractor.getEmail();
				String contractorAddress = projectList.get(i).contractor.getAddress();

				String customerName = projectList.get(i).customer.getName(); 
				String customerTelNo = projectList.get(i).customer.getTelNo();
				String customerEmail = projectList.get(i).customer.getEmail();
				String customerAddress = projectList.get(i).customer.getAddress();

				/** 
				 * Insert the project details into the incomplete_projects table.
				 */
				statement.executeUpdate(
						"INSERT INTO incomplete_projects VALUES ('"+projectNo+"', "
								+ "'"+projectName+"','"+buildingType+"','"+projectAddress+"',"
								+ "'"+erfNo+"',"+feeTotal+", "+feePaid+",'"+deadline+"',"
								+ "'"+complete+"')"
						);

				/** 
				 * Insert the Architect details into the incomplete_projects_persons table.
				 */
				statement.executeUpdate(
						"INSERT INTO incomplete_projects_persons VALUES ('"+projectNo+"', "
								+ "'Architect', '"+architectName+"', '"+architectTelNo+"', "
								+ "'"+architectEmail+"', '"+architectAddress+"')"
						);

				/** 
				 * Insert the Contractor details into the incomplete_projects_persons table.
				 */
				statement.executeUpdate(
						"INSERT INTO incomplete_projects_persons VALUES ('"+projectNo+"', "
								+ "'Contractor', '"+contractorName+"', '"+contractorTelNo+"', "
								+ "'"+contractorEmail+"', '"+contractorAddress+"')"
						);

				/** 
				 * Insert the Customer details into the incomplete_projects_persons table.
				 */
				statement.executeUpdate(
						"INSERT INTO incomplete_projects_persons VALUES ('"+projectNo+"', "
								+ "'Customer', '"+customerName+"', '"+customerTelNo+"', "
								+ "'"+customerEmail+"', '"+customerAddress+"')"
						);
			}

			/**
			 * Close up the connections.
			 */
			statement.close();
			connection.close();

		} catch (SQLException ex) {
			System.out.println("Error, couldn't read from database.");
			ex.printStackTrace();
		}
	}

	/**
	 * Method to add the completed project details to the completed_projects and 
	 * completed_projects_persons tables.
	 */
	static void writeTablesCompletedProject(int i) {
		/**
		 * Use try catch blocks to catch SQLExceptions.
		 */
		try {
			/**
			 * Call method to connect to the poise_pms database and create
			 * direct line to the database for running queries
			 */
			Connection connection = getDbConnection();
			Statement statement = connection.createStatement();

			/**
			 * Extract the completed project and person details and save in variables.
			 */
			String projectNo = projectList.get(i).getProjectNo();
			String projectName = projectList.get(i).getProjectName();
			String buildingType = projectList.get(i).getBuildingType();
			String projectAddress = projectList.get(i).getProjectAddress();
			String erfNo = projectList.get(i).getErfNo();
			double feeTotal = projectList.get(i).getFeeTotal();
			double feePaid = projectList.get(i).getFeePaid();
			String deadline = projectList.get(i).getDeadline();
			String complete = projectList.get(i).getCompleted();
			String completionDate = projectList.get(i).getCompletionDate();

			String architectName = projectList.get(i).architect.getName();
			String architectTelNo = projectList.get(i).architect.getTelNo();
			String architectEmail = projectList.get(i).architect.getEmail();
			String architectAddress = projectList.get(i).architect.getAddress();

			String contractorName = projectList.get(i).contractor.getName();
			String contractorTelNo = projectList.get(i).contractor.getTelNo();
			String contractorEmail = projectList.get(i).contractor.getEmail();
			String contractorAddress = projectList.get(i).contractor.getAddress();

			String customerName = projectList.get(i).customer.getName(); 
			String customerTelNo = projectList.get(i).customer.getTelNo();
			String customerEmail = projectList.get(i).customer.getEmail();
			String customerAddress = projectList.get(i).customer.getAddress();


			/** 
			 * Insert the project details into the completed_projects table.
			 */
			statement.executeUpdate(
					"INSERT INTO completed_projects VALUES ('"+projectNo+"', '"+projectName+"',"
							+ "'"+buildingType+"','"+projectAddress+"','"+erfNo+"',"+feeTotal+", "
							+ ""+feePaid+",'"+deadline+"','"+complete+"', '"+completionDate+"')"
					);

			/** 
			 * Insert the Architect details into the completed_projects_persons table.
			 */
			statement.executeUpdate(
					"INSERT INTO completed_projects_persons VALUES ('"+projectNo+"', 'Architect', "
							+ "'"+architectName+"', '"+architectTelNo+"', "
							+ "'"+architectEmail+"', '"+architectAddress+"')"
					);

			/** 
			 * Insert the Contractor details into the completed_projects_persons table.
			 */
			statement.executeUpdate(
					"INSERT INTO completed_projects_persons VALUES ('"+projectNo+"', 'Contractor', "
							+ "'"+contractorName+"', '"+contractorTelNo+"', "
							+ "'"+contractorEmail+"', '"+contractorAddress+"')"
					);

			/** 
			 * Insert the Customer details into the completed_projects_persons table.
			 */
			statement.executeUpdate(
					"INSERT INTO completed_projects_persons VALUES ('"+projectNo+"', 'Customer', "
							+ "'"+customerName+"', '"+customerTelNo+"', "
							+ "'"+customerEmail+"', '"+customerAddress+"')"
					);

			/**
			 * Close up the connections.
			 */
			statement.close();
			connection.close();

		} catch (SQLException ex) {
			System.out.println("Error, couldn't write to database.");
			ex.printStackTrace();
		}
	}
}

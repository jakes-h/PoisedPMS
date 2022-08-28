# Poised Project Management System

## Description
This program is a project management system for a small structural engineering firm called Poised. 

It stores various information about the projects that Poised work on, including information about the project, architect, contractor and the customer. 

New projects can be loaded and existing projects can be updated and finalized. Details about incomplete projects and overdue projects can also be viewed.

In version 1 of this program project data was saved in external text files.  This version (version 2) of the program is modified so that it uses a database instead of text files to store data needed for the program.

## Functionality
The user is presented with the following options and functions within the program:
1. Add new project
2. View all incomplete projects
3. View all overdue projects
4. Update existing project details
5. Exit

Within option 4 (update existing project details), the user is presented with the following options after selecting to search for a project by project number or project name:
1. Change the due date of the project
2. Change the total amount of the fee paid to date
3. Update the contractor's telephone number
4. Update the contractor's email address
5. Finalize the project
6. Back to main menu

## Classes
The program contains 6 .java class files in order to improve the readability of the code and break it up into different sections.

The program contains the following classes:

* #### **Main.java**

Main class of the program providing the user with the menu options that interacts with all other classes.

* #### **DatabaseManager.java**

Class that contains methods to read details of existing projects from the poise_pms database and write details of existing projects and completed projects to the database.

This class replaced the FilesInputOutput.java class in version 1 of the program that interacted with text files.

* #### **MenuOptions.java**

Provides functionality to view incomplete projects, view overdue projects and update existing projects.

* #### **Person.java**

Class to construct and create person objects.

* #### **Project.java**

Class to construct and create project objects.

* #### **ProjectAdd.java**

Class to capture the details of a new project.

## Database
Data is managed via the mySQL poisePMS database. The database contains the following tables in which data is stored and retrieved from:
* incomplete_projects
* incomplete_projects_persons
* completed_projects
* completed_projects_persons

## Contributors
Jaco Hartman

jacohartman@gmail.com

/**
 * Person class to construct and create person objects.
 */
public class Person {

	/**
	 * Attributes of the person object.
	 */
	String name;
	String telNo;
	String email;
	String address;

	/**
	 * Constructor of the person object.
	 * @param name
	 * @param telNo
	 * @param email
	 * @param address
	 */
	public Person (String name, String telNo, String email, String address) {
		this.name = name;
		this.telNo = telNo;
		this.email = email;
		this.address = address;
	}

	/**
	 * Getters and Setters
	 */
	public void setTelNo() {
		telNo = ProjectAdd.enterTelNo();
	}

	public void setEmail(String newEmail) {
		email = newEmail;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTelNo() {
		return telNo;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * toString method to print out the person object.
	 */
	public String toString() {
		String output = "Name: " + name;
		output += "\nTelephone number: " + telNo;
		output += "\nEmail address: " + email;
		output += "\nPhysical address: " + address;

		return output;
	}
}
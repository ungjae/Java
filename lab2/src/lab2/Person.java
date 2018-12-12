package lab2;

class Person {
	protected String name, id, firstName, lastName;
	public Person(String n, String i) {
		firstName = n.split(" ")[0];
		lastName = n.split(" ")[1];
		name = firstName + " " + lastName;
		id = i;
	}
	public String toString() {
		String str = "******************************";
		str += "\nName: " + name + "\nID: " + id;
		str += "\nEmail Address: " + email();
		return str;
	}
	public String email() {
		return name.charAt(0) + lastName + "@qc.cuny.edu"; //first letter and last name; UYun
	}
	
	public String getName() {
		return name;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	public void setFirstName(String newFirst) {
		firstName = newFirst;
	}
	public void setLastName(String newLast) {
		lastName = newLast;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String newId) {
		id = newId;
	}
}
	

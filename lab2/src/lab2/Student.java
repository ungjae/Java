package lab2;

class Student extends Person {

	protected double gpa;

	public Student(String n, String i, double g){ 
		super(n, i);
		gpa = g;
	}
	
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double newGpa) {
		gpa = newGpa;
	}


	public String compareTo(Object o) {
		// Compare students' gpa's
		// 
		String rank;
		
		if (o instanceof Student) {
			rank = Double.toString(this.gpa - ((Student) o).getGpa());
		} else {
			rank = "Both of them need to be students in order to compare";
		}
		
		return rank;
	}
}

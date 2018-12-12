package lab2;

class Faculty extends Employee {

	private double salary = 1500;

	public Faculty(String n, String i, String o, double s) {
		super(n, i, o);
		salary = s;
	}

	public double pay() {
		return salary;
	}

	public int compareTo(Object o) {
		// initialize return int value to show difference between this and object o
		int comp = 0;
		
		// know which kind of pay to access, cast the correct object type and then compare
		if (o instanceof Faculty) {
			comp = (int) (this.pay() - ((Faculty) o).pay());
		} else if (o instanceof Staff) {
			comp = (int) (this.pay() - ((Staff) o).pay());
		}
		return comp;
	}
}

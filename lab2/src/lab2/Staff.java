package lab2;

class Staff extends Employee {
	
	private double rate, hours;
	
	public Staff(String n, String i, String o, double r, double h) {
		super(n, i, o);
		rate = r;
		hours = h;
	}
	
	public double pay() {
		return hours * rate;
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

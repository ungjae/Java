package lab2;

abstract class Employee extends Person implements Comparable {
	
	protected String office;
	
	public Employee(String n, String i, String o){ 
		super(n,i);
		office = o;
	}
	
	public abstract double pay();
	
	public String toString(){
		String str = super.toString();
		str += "\nWeekly Salary: " + pay();
		return str;
	}
}


public class Account {
	String name;
	double balance;
	
	public Account(String name) {
		this.name = name;
		this.balance = Math.floor(100 * Math.random());				// Because the bank is nice and fun.
	}
	
	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	public void display() {											// Simple account display.
		System.out.println(this.name + ": € " + this.balance);
	}
	
	public void displayNn() {										// Same, but without a newline.
		System.out.print(this.name + ": € " + this.balance);
	}
	
	public int compareTo(String name) {								// Compares lexicographically <this.name> to <name> and returns an int indicating
		return this.name.compareTo(name);							// how greater - positively or negatively - is the difference.
	}
}

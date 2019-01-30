
public class Main {

	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.lookupOrCreate(new String[]{"Tyrion", "Jaime", "Tywin", "Cersei", "Kevan", "Willem", "Alton", "Lancel", "Martyn"});
		bank.display();
	}

}


public class Bank {
	private BST accounts;
	
	public Bank() {
		this.accounts = new BST(null);
	}
	
	
	public Account lookupOrCreate(String name) {			// If present, returns the account associated to <name>, otherwise creates it in the <accounts>.
		return accounts.lookupOrCreate(name);
	}
	
	public void lookupOrCreate(String[] namesArray) {		// Equivalent version for multiple entries.
		for (int i = 0; i < namesArray.length; i++) {
			this.lookupOrCreate(namesArray[i]);
		}
	}
	
	public void display() {
		this.accounts.display();							// To display the bank accounts in a tree-like manner.
	}
}

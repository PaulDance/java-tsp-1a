
public class BST {
	Account key;
	BST left, right;
	
	public BST(String keyName) {
		this.key = new Account(keyName);
		this.left = null;
		this.right = null;
	}
	
	public BST(Account key, BST left, BST right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	
	public Account lookupOrCreate(String name) {				// If present, returns the account associated to <name>, otherwise creates it at the correct place in the BST.
		if (this.key.name == null) {							// In case the bank is completely empty,
			this.key = new Account(name);						// it is initialized.
			return this.key;
		}
		else {
			if (this.key.name == name) {						// If the account is found,
				return this.key;								// then return it;
			}
			else if (this.key.compareTo(name) > 0) {			// otherwise, compare lexicographically the <name> to <key>'s to decide where to look after:
				if (this.left != null) {						// smaller to the left,
					return this.left.lookupOrCreate(name);
				}
				else {
					this.left = new BST(name);
					return this.left.key;
				}
			}
			else if (this.right != null) {						// greater to the right.
				return this.right.lookupOrCreate(name);
			}
			else {
				this.right = new BST(name);						// Leaves are created if the account is not present after all.
				return this.right.key;
			}
		}
	}
	
	private void displayRec(int depth) {						// Used in following method <display> for recursion.
		if (this.right != null) {
			this.right.displayRec(depth + 1);
		}
		
		System.out.print(new String(new char[depth]).replace("\0", "\t"));
		this.key.display();
		
		if (this.left != null) {
			this.left.displayRec(depth + 1);
		}
	}
	
	public void display() {										// To pretty-print the tree.
		this.displayRec(0);
	}
}

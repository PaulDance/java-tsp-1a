
public class Bank {
    private final BST accounts;

    public Bank() {
        this.accounts = new BST(null);
    }

    /**
     * If present, returns the account associated to `name`, otherwise creates
     * it in the accounts.
     */
    public Account lookupOrCreate(final String name) {
        return this.accounts.lookupOrCreate(name);
    }

    /**
     * Equivalent version for multiple entries.
     */
    public void lookupOrCreate(final String[] namesArray) {
        for (final String element: namesArray) {
            this.lookupOrCreate(element);
        }
    }

    /**
     * To display the bank accounts in a tree-like manner.
     */
    public void display() {
        this.accounts.display();
    }
}

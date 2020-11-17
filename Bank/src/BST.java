
public class BST {
    Account key;
    BST left, right;

    public BST(final String keyName) {
        this.key = new Account(keyName);
        this.left = null;
        this.right = null;
    }

    public BST(final Account key, final BST left, final BST right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    /**
     * If present, returns the account associated to `name`, otherwise creates
     * it at the correct place in the BST.
     */
    public Account lookupOrCreate(final String name) {
        if (this.key.name == null) {        // In case the bank is completely empty,
            this.key = new Account(name);   // it is initialized.
            return this.key;
        } else {
            if (this.key.name == name) {    // If the account is found,
                return this.key;            // then return it;
            } else if (this.key.compareTo(name) > 0) {  // decide where to look:
                if (this.left != null) {        // smaller to the left,
                    return this.left.lookupOrCreate(name);
                } else {
                    this.left = new BST(name);
                    return this.left.key;
                }
            } else if (this.right != null) {    // greater to the right.
                return this.right.lookupOrCreate(name);
            } else {
                this.right = new BST(name);     // Leaves are created if the account
                return this.right.key;          // is not present after all.
            }
        }
    }

    /**
     * Used in following method for recursion.
     */
    private void displayRec(final int depth) {
        if (this.right != null) {
            this.right.displayRec(depth + 1);
        }

        System.out.print(new String(new char[depth]).replace("\0", "\t"));
        this.key.display();

        if (this.left != null) {
            this.left.displayRec(depth + 1);
        }
    }

    /**
     * Pretty-print the tree.
     */
    public void display() {
        this.displayRec(0);
    }
}

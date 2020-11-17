
public class Account {
    String name;
    double balance;

    public Account(final String name) {
        this.name = name;
        this.balance = Math.floor(100 * Math.random()); // Some fun.
    }

    public Account(final String name, final double balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Simple account display.
     */
    public void display() {
        System.out.println(this.name + ": € " + this.balance);
    }

    /**
     * Same, but without a newline.
     */
    public void displayNn() {
        System.out.print(this.name + ": € " + this.balance);
    }

    /**
     * Compares lexicographically `this.name` to `name` and returns an `int`
     * indicating how greater - positively or negatively - is the difference.
     */
    public int compareTo(final String name) {
        return this.name.compareTo(name);
    }
}

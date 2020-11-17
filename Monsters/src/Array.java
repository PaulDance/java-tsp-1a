public class Array {
    Monster head;
    Array tail;

    public Array(final Monster head, final Array tail) {
        this.head = head;
        this.tail = tail;
    }

    public boolean isNil() {
        return this.head == null && this.tail == null;
    }

    public void display() {
        if (this.head != null) {
            this.head.display();
        }

        if (this.tail != null) {
            this.tail.display();
        }
    }

    public void debugDisplay() {
        if (this.head != null) {
            System.out.print("+--");
            this.head.display();
            System.out.println('|');

            if (this.tail != null) {
                this.tail.debugDisplay();
            }
        } else {
            System.out.println("Nil");
        }
    }

    public void add(final Monster monster) {
        this.tail = new Array(this.head, this.tail);
        this.head = monster;
    }

    public Array del(final String name) {
        if (this.head != null) {
            if (this.head.name.equals(name)) {
                return this.tail;
            } else if (this.tail != null) {
                return new Array(this.head, this.tail.del(name));
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public Array del(final Monster monster) {
        if (this.head != null) {
            if (this.head == monster) {
                return this.tail;
            } else if (this.tail != null) {
                return new Array(this.head, this.tail.del(monster));
            } else {
                return this;
            }
        } else {
            return this;
        }
    }
}

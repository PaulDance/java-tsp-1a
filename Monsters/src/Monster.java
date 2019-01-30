
public class Monster {
	String name = "";
	int health = 0;
	
	public Monster(String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	public void display() {
		System.out.println("Monster<" + this.name + ">");
	}
}
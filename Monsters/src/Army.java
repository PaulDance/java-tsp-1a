// Version with extensible arrays :

//public class Army {
//	Monster[] monsters;
//	int top = 0;
//	
//	public Army() {
//		this.monsters = new Monster[4];
//		this.top = 0;
//	}
//	
//	public void display() {
//		for (int i = 0; i < this.top; i++) {
//			this.monsters[i].display();
//		}
//	}
//	
//	public void addMonster(Monster monster) {
//		if (this.top >= this.monsters.length) {
//			Monster[] tmp = new Monster[2 * this.monsters.length];
//			
//			for (int i = 0; i < this.monsters.length; i++) {
//				tmp[i] = this.monsters[i];
//			}
//			
//			this.monsters = tmp;
//		}
//		
//		this.monsters[this.top] = monster;
//		this.top += 1;
//	}
//}

// Version with chained arrays.


public class Army {
	Array monstersArray;
	
	public Army() {
		this.monstersArray = new Array(null, null);
	}
	
	public void display() {
		this.monstersArray.display();
	}
	
	public void debugDisplay() {
		this.monstersArray.debugDisplay();
	}
	
	public void addMonster(Monster monster) {
		this.monstersArray.add(monster);
	}
	
	public void delMonster(String name) {
		this.monstersArray = this.monstersArray.del(name);
	}
	
	public void delMonster(Monster monster) {
		this.monstersArray = this.monstersArray.del(monster);
	}
}


public class Food {
	
	public String name;
	public int plusHp;
	public String race;
	public int level;
	
	public Food(String name, int plusHp, String race,int level) {
		this.name = name;
		this.plusHp = plusHp;
		this.race = race;
		this.level = level;
	}
	
	public String toString() {
		return this.name;
	}

}

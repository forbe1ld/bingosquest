
public class Food {
	
	public String name;
	public int plusHp;
	public String race;
	public int level;
	public int coins;
	
	public Food(String name, int plusHp, String race,int level, int coins) {
		this.name = name;
		this.plusHp = plusHp;
		this.race = race;
		this.level = level;
		this.coins = coins;
	}
	
	public String toString() {
		return this.name;
	}

}

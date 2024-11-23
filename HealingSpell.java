
public class HealingSpell {
	
	public String name;
	public int hpHealing;
	public int coins;
	int level;
	
	public HealingSpell(String name, int level, int hpHealing, int coins) {
		this.name = name;
		this.hpHealing = hpHealing;
		this.coins = coins;
		this.level = level;
	}
	
	public String toString() {
		return "Level " + this.level + " " + this.name  +  " (" + this.hpHealing + " HP Healing)";
	}

}

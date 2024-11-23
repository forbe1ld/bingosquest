
public class AttackSpell {
	
	public String name;
	public int hpDamage;
	public int coins;
	public int level;
	
	public AttackSpell(String name, int level, int hpDamage, int coins) {
		this.name = name;
		this.hpDamage = hpDamage;
		this.coins = coins;
		this.level = level;
	}
	
	public String toString() {
		return "Level " + this.level + " " + this.name  +  " (" + this.hpDamage + " HP Damage)";
	}

}

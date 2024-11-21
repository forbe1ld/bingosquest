import java.util.ArrayList;

public class Weapon {

	public double attackEfficiency;
	public String name;
	public String race;
	public int level;
	
	public Weapon(String name, double attackEfficiency, String race, int level) {
		this.name = name;
		this.attackEfficiency = attackEfficiency;
		this.race = race;
		this.level = level;
	}
	
	public String toString() {
		return this.name;
	}
	
}



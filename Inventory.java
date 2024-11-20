//Michael Leiby
import java.util.ArrayList;

public class Inventory {
	
	//Attributes of the inventory class

	public String race;
	public int level;
	public Weapon weapon;
	public Shield shield;
	public Food food;
	
	
	
	public String toString() {
		return "Weapon: " + this.weapon + " Shield: " + this.shield + " Food: " + this.food;
	}
	
	public Inventory(Player p, ArrayList<Weapon> weapons, ArrayList<Shield> shields, ArrayList<Food> foods) {
		String race = p.race;
		this.race = race;
		int level = p.level;
		this.level = level;
		
		for(Weapon w: weapons) {
			if(w.race.equalsIgnoreCase(race) && w.level == level) {
				this.weapon = w;
				break;
			}
		}
		
		for(Shield s: shields) {
			if(s.level == level) {
				this.shield = s;
				break;
			}
		}
		
		for(Food f: foods) {
			if(f.race.equalsIgnoreCase(race) && f.level == level) {
				this.food = f;
				break;
			}
		}
	}
	
	public void getInventory() {
		
		System.out.println( "------------------------------------ \n CURRENT INVENTORY\n " + "WEAPON: " + this.weapon + " SHIELD: " + this.shield + " FOOD: " + this.food + "\n------------------------------------");
		
	}
	
	
}

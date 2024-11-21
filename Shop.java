import java.util.ArrayList;

public class Shop {
	
	public Food food;
	public AttackSpell attackSpell;
	public HealingSpell healingSpell;
	public String race;
	public int level;
	
	
	public Shop(String race, int level, ArrayList<Food> foods, ArrayList<AttackSpell> aSpells, ArrayList<HealingSpell> hSpells) {
		this.race = race;
		this.level = level;
		
		for(Food f: foods) {
			if(f.race.equalsIgnoreCase(race) && f.level == level) {
				this.food = f;
				break;
			}
		}
		
		for(AttackSpell a: aSpells) {
			if(a.level == level) {
				this.attackSpell = a;
				break;
			}
		}
		
		for(HealingSpell h: hSpells) {
			if(h.level == level) {
				this.healingSpell = h;
				break;
			}
		}
		
	}

}

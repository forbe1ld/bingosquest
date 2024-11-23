import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Shop {
	
	public Food food;
	public AttackSpell attackSpell;
	public HealingSpell healingSpell;
	public String race;
	public int level;
	public int foodCoins;
	public IntegerProperty aSpellCoins;
	public IntegerProperty hSpellCoins;
	
	
	public Shop(String race, int level, ArrayList<Food> foods, ArrayList<AttackSpell> aSpells, ArrayList<HealingSpell> hSpells) {
		this.race = race;
		this.level = level;
		
		this.aSpellCoins = new SimpleIntegerProperty();
        this.hSpellCoins = new SimpleIntegerProperty();
		
		for(Food f: foods) {
			if(f.race.equalsIgnoreCase(race) && f.level == level) {
				this.food = f;
				this.foodCoins = f.coins;
				break;
			}
		}
		
		for(AttackSpell a: aSpells) {
			if(a.level == level) {
				this.attackSpell = a;
				this.aSpellCoins.set(a.coins);
				break;
			}
		}
		
		for(HealingSpell h: hSpells) {
			if(h.level == level) {
				this.healingSpell = h;
				this.hSpellCoins.set(h.coins);
				break;
			}
		}
		
	}

	 public IntegerProperty aSpellCoinsProperty() {
	        return aSpellCoins;
	    }

	    public IntegerProperty hSpellCoinsProperty() {
	        return hSpellCoins;
	    }
	    
	public Food getFood() {
		return food;
	}


	public void setFood(Food food) {
		this.food = food;
	}


	public AttackSpell getAttackSpell() {
		return attackSpell;
	}


	public void setAttackSpell(AttackSpell attackSpell) {
		this.attackSpell = attackSpell;
	}


	public HealingSpell getHealingSpell() {
		return healingSpell;
	}


	public void setHealingSpell(HealingSpell healingSpell) {
		this.healingSpell = healingSpell;
	}


	public String getRace() {
		return race;
	}


	public void setRace(String race) {
		this.race = race;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getFoodCoins() {
		return foodCoins;
	}


	public void setFoodCoins(int foodCoins) {
		this.foodCoins = foodCoins;
	}





	
	

}

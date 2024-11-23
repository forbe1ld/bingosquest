// Michael Leiby
//Player class which houses all player related attributes and methods
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;

public class Player {

	//attributes of a player
	
	//default name is Marxon. Alternative name can be set by user at start of new game
	public String name;
	//Initialized to 100 at the start of a new game
	public int hp;
	//Initialized to 1 at the start of a new game
	public int level;
	//Possible races include human (default), Dwarf, Elf, and Wizard
	public String race;
	//Boolean variable which tracks whether the player is alive or not
	public boolean isAlive;
	
	public int coins;
	
	//paramaterized constructor. PlayerBuilder class is used for actual player creation
	public Player(String name, int hp, int level, String race, boolean isAlive, int coins) {
		this.name = name;
		this.hp = hp;
		this.level = level;
		this.race = race;
		this.isAlive = true;
		this.coins = coins;
	}
	
	//getStats method, which allows a player to see their current stats on the console. Will initially be called after the player is created.
	public String getStats() {
		
		return "-----------------------------------------------------------------\n" + "CURRENT STATS\n" + "Name: " + this.name + ", Race: " + this.race + ", Level: " + this.level + ", HP: " + this.hp + ", COINS: " + this.coins + "\n-----------------------------------------------------------------";
		
		///System.out.println("-------------------------------------------------");
	//	System.out.println("CURRENT STATS");
		//System.out.printf("Name: %s \nRace: %s \nLevel: %d \nHP: %d \n", this.name, this.race, this.level, this.hp);
	//	System.out.println("-------------------------------------------------");
	}
	
	//Method which can be used to level the player up after defeating enemies/bosses etc. Need to match it with the code which updates the bottom information
	public void levelUp() {
		this.level += 1;	
	}
	
	//Method which can be used to change the player's hp. Need to match it with the code which updates the bottom information
	public void hpChange(int hpChange) {
		this.hp += hpChange;
	}
	
	
	
	
	
}

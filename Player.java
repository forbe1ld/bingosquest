// Michael Leiby
//Player class which houses all player related attributes and methods
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	//attributes of a player
	
	//default name is Marxon. Alternative name can be set by user at start of new game
	private String name;
	//Initialized to 100 at the start of a new game
	private int hp;
	//Initialized to 1 at the start of a new game
	public int level;
	//Possible races include human (default), Dwarf, Elf, and Wizard
	public String race;
	//Boolean variable which tracks whether the player is alive or not
	private boolean isAlive;
	
	//paramaterized constructor. PlayerBuilder class is used for actual player creation
	public Player(String name, int hp, int level, String race, boolean isAlive) {
		this.name = name;
		this.hp = hp;
		this.level = level;
		this.race = race;
		this.isAlive = true;
	}
	
	//getStats method, which allows a player to see their current stats on the console. Will initially be called after the player is created.
	public String getStats() {
		
		return "-------------------------------------------------\n" + "CURRENT STATS\n" + "Name: " + this.name + ", Race: " + this.race + ", Level: " + this.level + ", HP: " + this.hp + "\n-------------------------------------------------";
		
		///System.out.println("-------------------------------------------------");
	//	System.out.println("CURRENT STATS");
		//System.out.printf("Name: %s \nRace: %s \nLevel: %d \nHP: %d \n", this.name, this.race, this.level, this.hp);
	//	System.out.println("-------------------------------------------------");
	}
	
	public void levelUp() {
		this.level += 1;
		this.getStats();
	}
	
	
	
	
	
}

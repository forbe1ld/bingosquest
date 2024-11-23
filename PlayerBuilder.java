//Michael Leiby
//PlayerBuilder class which utilizes builder method to create a new player
public class PlayerBuilder {

	
	String name;
	int hp;
	int level;
	String race;
	boolean isAlive;
	int coins;
	
	//default selections given for player creation
	public PlayerBuilder() {
		
		this.name = "Marxon";
		this.hp = 100;
		this.level = 1;
		this.race = "Human";
		this.isAlive = true;
		this.coins = 10;
	}
	
	//set methods are provided for name and race. A new player cannot change the default of 100 hp and level 1 to start
	public PlayerBuilder setname(String name) {
		this.name = name;
		return this;
	}
	
	public PlayerBuilder setRace(String race) {
		this.race = race;
		return this;
	}
	
	public Player build() {
		return new Player(name, hp, level, race, isAlive, coins);
	}
}

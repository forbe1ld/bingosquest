// Michael Leiby
//Player class which houses all player related attributes and methods
public class Player {
	
	//attributes of a player
	
	//name of player is always "Bingo"
	private String name = "Bingo";
	
	//Initialized to be 10 at start of new game and scales with vitality stat
	int hp;
	private int hpMax;
	
	//Initialized to be 10 at start of new game and scales with source stat
	private int mp;
	private int mpMax;
	
	//level for weapon -> scales attack power
	//Initialized to be 1 at the start of a new game
	private int weapon;
	
	//levels for spells -> scales spell power respectively
	//Initialized to be 1 at the start of a new game
	private int atkSpell;
	private int healSpell;
	
	//number of potions player has
	//Initialized to be 0 at the start of a new game
	private int healthPotion;
	private int greatHealthPotion;
	private int manaPotion;
	private int greatManaPotion;
	
	//Possible archetypes include: all-rounder(default), Warrior, and Wizard
	private String arch;
	
	//Initialized to be 1 at start of new game
	int lvl;
	
	//Initialized to be 0 at start of new game
	private int xp;
	int gold;
	
	//stats
	private int str; //strength -> scales physical damage
	private int mnd; //mind -> scales spell efficacy
	private int vit; //vitality -> scales hp
	private int src; //source -> scales mp
	
	//Initialized to be 5 at the start of a new game
	private int xPos;
	private int yPos;
	
	//set character without save data yet
	Player(int num){
		if(num == 1) {
			this.setHpMax(10);
			this.setHp(10);
			this.setMp(10);
			this.setMpMax(10);
			this.setAtkSpell(5);
			this.setHealSpell(5);
			this.setWeapon(1);
			this.setHealthPotion(1);
			this.setGreatHealthPotion(1);
			this.setManaPotion(1);
			this.setGreatManaPotion(1);
			this.setArch("Wizard");
			this.setLvl(1);
			this.setStr(1);
			this.setMnd(3);
			this.setVit(2);
			this.setSrc(3);
			this.setxPos(5);
			this.setyPos(5);
			
		}
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMpMax() {
		return mpMax;
	}

	public void setMpMax(int mpMax) {
		this.mpMax = mpMax;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}

	public int getAtkSpell() {
		return atkSpell;
	}

	public void setAtkSpell(int atkSpell) {
		this.atkSpell = atkSpell;
	}

	public int getHealSpell() {
		return healSpell;
	}

	public void setHealSpell(int healSpell) {
		this.healSpell = healSpell;
	}

	public int getHealthPotion() {
		return healthPotion;
	}

	public void setHealthPotion(int healthPotion) {
		this.healthPotion = healthPotion;
	}

	public int getGreatHealthPotion() {
		return greatHealthPotion;
	}

	public void setGreatHealthPotion(int greatHealthPotion) {
		this.greatHealthPotion = greatHealthPotion;
	}

	public int getManaPotion() {
		return manaPotion;
	}

	public void setManaPotion(int manaPotion) {
		this.manaPotion = manaPotion;
	}

	public int getGreatManaPotion() {
		return greatManaPotion;
	}

	public void setGreatManaPotion(int greatManaPotion) {
		this.greatManaPotion = greatManaPotion;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getMnd() {
		return mnd;
	}

	public void setMnd(int mnd) {
		this.mnd = mnd;
	}

	public int getVit() {
		return vit;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
}

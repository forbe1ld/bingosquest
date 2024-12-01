// Michael Leiby
//Player class which houses all player related attributes and methods

import java.io.Serializable;

public class Player implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//attributes of a player
	
	//name of player is always "Bingo"
	private String name = "Bingo";
	
	//Initialized to be 10 at start of new game and scales with vitality stat
	private int hp;
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
	private int lvl;
	
	//Initialized to be 0 at start of new game
	private int xp;
	private int gold;
	
	//stats
	private int str; //strength -> scales physical damage
	private int mnd; //mind -> scales spell efficacy
	private int vit; //vitality -> scales hp
	private int src; //source -> scales mp
	
	//Initialized to be 5 at the start of a new game
	private int xPos;
	private int yPos;
	
	//boss kill counter
	private int bossKill;
	
	//set character without save data yet
	Player(int num){
		if(num == 1) {
			this.setHpMax(100);
			this.setHp(100);
			this.setMp(100);
			this.setMpMax(100);
			this.setAtkSpell(3);
			this.setHealSpell(3);
			this.setWeapon(3);
			this.setHealthPotion(8);
			this.setGreatHealthPotion(8);
			this.setManaPotion(8);
			this.setGreatManaPotion(8);
			this.setArch("Warrior");
			this.setLvl(1);
			this.setXp(0);
			this.setGold(1000);
			this.setStr(10);
			this.setMnd(10);
			this.setVit(10);
			this.setSrc(10);
			this.setxPos(4);
			this.setyPos(4);
			this.setBossKill(3);
		}
	}
	
	

	public Player(int hp, int hpMax, int mp, int mpMax, int weapon, int atkSpell, int healSpell,
			int healthPotion, int greatHealthPotion, int manaPotion, int greatManaPotion, String arch, int lvl, int xp,
			int gold, int str, int mnd, int vit, int src, int xPos, int yPos, int bossKill) {
		super();
		this.hp = hp;
		this.hpMax = hpMax;
		this.mp = mp;
		this.mpMax = mpMax;
		this.weapon = weapon;
		this.atkSpell = atkSpell;
		this.healSpell = healSpell;
		this.healthPotion = healthPotion;
		this.greatHealthPotion = greatHealthPotion;
		this.manaPotion = manaPotion;
		this.greatManaPotion = greatManaPotion;
		this.arch = arch;
		this.lvl = lvl;
		this.xp = xp;
		this.gold = gold;
		this.str = str;
		this.mnd = mnd;
		this.vit = vit;
		this.src = src;
		this.xPos = xPos;
		this.yPos = yPos;
		this.bossKill = bossKill;
	}



	public void levelUp() {
		this.lvl++;
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

	public int getBossKill() {
		return bossKill;
	}

	public void setBossKill(int bossKill) {
		this.bossKill = bossKill;
	}
	
}

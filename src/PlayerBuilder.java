
public class PlayerBuilder {
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
		
		PlayerBuilder(){
			this.setHp(15);
			this.setHpMax(15);
			this.setMp(15);
			this.setMpMax(15);
			this.setWeapon(1);
			this.setAtkSpell(1);
			this.setHealSpell(1);
			this.setHealthPotion(0);
			this.setGreatHealthPotion(0);
			this.setManaPotion(0);
			this.setGreatManaPotion(0);
			this.setArch("All-Rounder");
			this.setLvl(1);
			this.setXp(0);
			this.setGold(0);
			this.setStr(2);
			this.setMnd(2);
			this.setVit(2);
			this.setSrc(2);
			this.setxPos(5);
			this.setyPos(5);
			this.setBossKill(0);
		}
		
		public Player build() {
			return new Player(hp, hpMax, mp, mpMax, weapon, atkSpell, healSpell, healthPotion, greatHealthPotion, manaPotion, greatManaPotion, arch, lvl, xp, gold, str, mnd, vit, src, xPos, yPos, bossKill);
		}

		public PlayerBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public PlayerBuilder setHp(int hp) {
			this.hp = hp;
			return this;
		}
		public PlayerBuilder setHpMax(int hpMax) {
			this.hpMax = hpMax;
			return this;
		}
		public PlayerBuilder setMp(int mp) {
			this.mp = mp;
			return this;
		}
		public PlayerBuilder setMpMax(int mpMax) {
			this.mpMax = mpMax;
			return this;
		}
		public PlayerBuilder setWeapon(int weapon) {
			this.weapon = weapon;
			return this;
		}
		public PlayerBuilder setAtkSpell(int atkSpell) {
			this.atkSpell = atkSpell;
			return this;
		}
		public PlayerBuilder setHealSpell(int healSpell) {
			this.healSpell = healSpell;
			return this;
		}
		public PlayerBuilder setHealthPotion(int healthPotion) {
			this.healthPotion = healthPotion;
			return this;
		}
		public PlayerBuilder setGreatHealthPotion(int greatHealthPotion) {
			this.greatHealthPotion = greatHealthPotion;
			return this;
		}
		public PlayerBuilder setManaPotion(int manaPotion) {
			this.manaPotion = manaPotion;
			return this;
		}
		public PlayerBuilder setGreatManaPotion(int greatManaPotion) {
			this.greatManaPotion = greatManaPotion;
			return this;
		}
		public PlayerBuilder setArch(String arch) {
			this.arch = arch;
			return this;
		}
		public PlayerBuilder setLvl(int lvl) {
			this.lvl = lvl;
			return this;
		}
		public PlayerBuilder setXp(int xp) {
			this.xp = xp;
			return this;
		}
		public PlayerBuilder setGold(int gold) {
			this.gold = gold;
			return this;
		}
		public PlayerBuilder setStr(int str) {
			this.str = str;
			return this;
		}
		public PlayerBuilder setMnd(int mnd) {
			this.mnd = mnd;
			return this;
		}
		public PlayerBuilder setVit(int vit) {
			this.vit = vit;
			return this;
		}
		public PlayerBuilder setSrc(int src) {
			this.src = src;
			return this;
		}
		public PlayerBuilder setxPos(int xPos) {
			this.xPos = xPos;
			return this;
		}
		public PlayerBuilder setyPos(int yPos) {
			this.yPos = yPos;
			return this;
		}
		public PlayerBuilder setBossKill(int bossKill) {
			this.bossKill = bossKill;
			return this;
		}
		
}

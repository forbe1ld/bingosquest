
public class Enemy {
	private String name;
	
	private int hp;
	private int hpMax;
	private int dmg;
	
	private String[] weaknesses;
	private String[] resistences;
	private Effect[] effects;
	
	public String getName() {
		return name;
	}
	
	public Enemy setName(String name) {
		this.name = name;
		return this;
	}
	
	public int getHp() {
		return hp;
	}
	
	public Enemy setHp(int hp) {
		this.hp = hp;
		return this;
	}
	
	public int getHpMax() {
		return hpMax;
	}
	
	public Enemy setHpMax(int hpMax) {
		this.hpMax = hpMax;
		this.hp = hpMax;
		return this;
	}
	
	public int getDmg() {
		return dmg;
	}
	
	public Enemy setDmg(int dmg) {
		this.dmg = dmg;
		return this;
	}	
}

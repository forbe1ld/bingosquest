
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Enemy {
	private String name;
	
	private int hp;
	private int hpMax;
	private int dmg;
	
	private int xp;
	private int gold;
	
	private Image i1;
	private Image i2;

	public void logic(Enemy en, Player pl, Stage stage) {
		pl.setHp(pl.getHp() - en.getDmg());
	}
	
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

	public int getXp() {
		return xp;
	}

	public Enemy setXp(int xp) {
		this.xp = xp;
		return this;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Image getI1() {
		return i1;
	}

	public void setI1(Image i1) {
		this.i1 = i1;
	}

	public Image getI2() {
		return i2;
	}

	public void setI2(Image i2) {
		this.i2 = i2;
	}	
	
	
}

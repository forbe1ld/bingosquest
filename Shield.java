
public class Shield {
	
	public double enemyEfficiency;
	public String name;
	public int level;
	
	public Shield(String name, double enemyEfficiency, int level) {
		this.name = name;
		this.enemyEfficiency = enemyEfficiency;
		this.level = level;
	}
	
	public String toString() {
		return this.name;
	}

}
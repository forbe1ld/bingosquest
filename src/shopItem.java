import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class shopItem {
	private String name;
	private int quantity;
	private int level;
	private int cost;
	private Button button;
	
	shopItem(String name, int quantity, int level, int cost){
		this.name = name;
		this.quantity = quantity;
		this.level = level;
		this.cost = cost;
		this.button = new Button("Purchace");
		button.setAlignment(Pos.CENTER);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
	
}

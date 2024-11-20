import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Michael Leiby


public class MYGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// stuff for fill-in GUI for main menu page
		
		
		ArrayList<Player> players = new ArrayList<>();
		
		Pane pane = new Pane();
		
		Scene scene = new Scene(pane);
		
		Button btnNewGame = new Button("New Game");
		
		HBox statDisplay = new HBox();
		
		pane.getChildren().add(btnNewGame);
		
		
		primaryStage.setScene(scene);
		primaryStage.setHeight(768);
		primaryStage.setWidth(1024);
		primaryStage.show();
		
		btnNewGame.setOnAction(e -> {
			Stage playerCreationStage = new Stage();
			
			BorderPane playerCreation = new BorderPane();
			
			Scene playerCreationScene = new Scene(playerCreation);
			
			HBox hbox = new HBox();
			hbox.getChildren().add(new Text("Player Creation"));
			hbox.setAlignment(Pos.CENTER);
			
			playerCreation.setTop(hbox);
			
			HBox hbox2 = new HBox();
			
			GridPane info = new GridPane();
			
			TextField name = new TextField();
			TextField race = new TextField();
			
			Button btnEnter = new Button("Enter");
			
			info.add(new Text("NAME: "), 0, 0);
			info.add(new Text("RACE: "), 0, 1);
			info.add(name, 1, 0);
			info.add(race, 1, 1);
			info.add(btnEnter, 1, 2);
			
			Text message = new Text("Leave both blank and press Enter for default selections. Races could be Human, Elf, Dwarf, or Wizard. Player stats are printed to the console upon successful player creation.");
			
			HBox hbox3 = new HBox();
			
			hbox3.getChildren().add(message);
			hbox3.setAlignment(Pos.CENTER);
			
			playerCreation.setBottom(hbox3);
			
			
			
			hbox2.getChildren().add(info);
			hbox2.setAlignment(Pos.CENTER);
		
			
			playerCreation.setCenter(hbox2);
			
			Weapon woodenSword = new Weapon("Wooden Sword", 1, "Human", 1);
			Weapon stoneSword = new Weapon("Stone Sword", 1.1, "Human", 2);
			Weapon metalSword = new Weapon("Metal Sword", 1.2, "Human", 3);
			Weapon diamondSword = new Weapon("Diamond Sword", 1.3, "Human", 4);
			
			Weapon woodenBow = new Weapon("Wooden Bow", 1, "Elf", 1);
			Weapon stoneBow = new Weapon("Stone Bow", 1.1, "Elf", 2);
			Weapon metalBow = new Weapon("Metal Bow", 1.2, "Elf", 3);
			Weapon diamondBow = new Weapon("Diamond Bow", 1.3, "Elf", 4);
			
			Weapon woodenAxe = new Weapon("Wooden Axe", 1, "Dwarf", 1);
			Weapon stoneAxe = new Weapon("Stone Axe", 1.1, "Dwarf", 2);
			Weapon metalAxe = new Weapon("Metal Axe", 1.2, "Human", 3);
			Weapon diamondAxe = new Weapon("Diamond Axe", 1.3, "Dwarf", 4);
			
			Weapon woodenWand = new Weapon("Wooden Wand", 1, "Wizard", 1);
			Weapon stoneWand = new Weapon("Stone Wand", 1.1, "Wizard", 2);
			Weapon metalWand = new Weapon("Metal Wand", 1.2, "Wizard", 3);
			Weapon diamondWand = new Weapon("Diamond Wand", 1.3, "Wizard", 4);
			
			ArrayList<Weapon> weapons = new ArrayList<>();
			
			weapons.add(woodenSword);
			weapons.add(stoneSword);
			weapons.add(metalSword);
			weapons.add(diamondSword);
			
			weapons.add(woodenBow);
			weapons.add(stoneBow);
			weapons.add(metalBow);
			weapons.add(diamondBow);
			
			weapons.add(woodenAxe);
			weapons.add(stoneAxe);
			weapons.add(metalAxe);
			weapons.add(diamondAxe);
			
			weapons.add(woodenWand);
			weapons.add(stoneWand);
			weapons.add(metalWand);
			weapons.add(diamondWand);
			
			Shield woodenShield = new Shield("Wooden Shield", 1, 1);
			Shield stoneShield = new Shield("Stone Shield", 0.9, 2);
			Shield metalShield = new Shield("Metal Shield", 0.8, 3);
			Shield diamondShield = new Shield("Diamond Shield", 0.7, 4);
			
			ArrayList<Shield> shields = new ArrayList<>();
			
			shields.add(woodenShield);
			shields.add(stoneShield);
			shields.add(metalShield);
			shields.add(diamondShield);
			
			Food apple = new Food("Apple", 10, "Human", 1);
			Food cookie = new Food("Cookie", 20, "Human", 2);
			Food pie = new Food("Pie", 30, "Human", 3);
			Food steak = new Food("Steak", 40, "Human", 4);
			
			Food berries = new Food("Berries", 10, "Elf", 1);
			Food bread = new Food("Bread", 20, "Elf", 2);
			Food cake = new Food("Cake", 30, "Elf", 3);
			Food veal = new Food("Veal", 40, "Elf", 4);
			
			Food melon = new Food("Melon", 10, "Dwarf", 1);
			Food brownie = new Food("Brownie", 20, "Dwarf", 2);
			Food cheesecake = new Food("Cheesecake", 30, "Dwarf", 3);
			Food porkChop = new Food("Pork Chop", 40, "Dwarf", 4);
			
			Food banana = new Food("Banana", 10, "Wizard", 1);
			Food croissant = new Food("Croissant", 20, "Wizard", 2);
			Food raspberryTart = new Food("Raspberry Tart", 30, "Wizard", 3);
			Food filetMignon = new Food("Filet Mignon", 40, "Wizard", 4);
			
			ArrayList<Food> foods = new ArrayList<>();
			
			foods.add(apple);
			foods.add(cookie);
			foods.add(pie);
			foods.add(steak);
			
			foods.add(berries);
			foods.add(bread);
			foods.add(cake);
			foods.add(veal);
			
			foods.add(melon);
			foods.add(brownie);
			foods.add(cheesecake);
			foods.add(porkChop);
			
			foods.add(banana);
			foods.add(croissant);
			foods.add(raspberryTart);
			foods.add(filetMignon);
			
			
           btnEnter.setOnAction(infoEntered -> {
				
        	   if(name.getText().equals("") && race.getText().equals("")) {
              		Player player = new PlayerBuilder().build();
              		Inventory i = new Inventory(player, weapons, shields, foods);
              		players.add(player);
              		player.getStats();
              		i.getInventory();          		
              		playerCreationStage.close();
             	}
        	   else if(race.getText().equalsIgnoreCase("Human") || race.getText().equalsIgnoreCase("Dwarf") || race.getText().equalsIgnoreCase("Elf") || race.getText().equalsIgnoreCase("Wizard")){
        		   
        		   if(name.getText().equals("") && race.getText().equals("")) {
               		Player player = new PlayerBuilder().build();
               		Inventory i = new Inventory(player, weapons, shields, foods);
               		players.add(player);
               		System.out.println(player.getStats());
               		i.getInventory();
              		playerCreationStage.close();
              	}else if(name.getText().equals("")) {
             		Player player = new PlayerBuilder().setRace(race.getText()).build();
             		Inventory i = new Inventory(player, weapons, shields, foods);
             		players.add(player);
             		System.out.println(player.getStats());
             		i.getInventory();
               		playerCreationStage.close();
              	}else if(race.getText().equals("")) {
               		Player player = new PlayerBuilder().setname(name.getText()).build();
               		Inventory i = new Inventory(player, weapons, shields, foods);
               		players.add(player);
               		System.out.println(player.getStats());
               		i.getInventory();
               		playerCreationStage.close();
               	}
               	else {
               		Player player = new PlayerBuilder().setname(name.getText()).setRace(race.getText()).build();
               		Inventory i = new Inventory(player, weapons, shields, foods);
               		players.add(player);
               		System.out.println(player.getStats());
               		i.getInventory();
               		playerCreationStage.close();
               	}
        	   } else {
        		   System.out.println("Please try creating your player again with a valid race");
        		   playerCreationStage.close();
        	   }
        	   
        	   
				
			});
           
           
           
           
			
		playerCreationStage.setScene(playerCreationScene);
		playerCreationStage.setHeight(150);
		playerCreationStage.setWidth(1000);
		playerCreationStage.show();
		});
		
	}
	
public static void main(String[] args) {
		
	
	
	
	launch(args);
	}

}

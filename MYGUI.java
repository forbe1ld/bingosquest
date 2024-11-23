import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
		
		//Creating the arraylists which will hold player and array information
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<Inventory> inventories = new ArrayList<>();
		
		//Creating the opening pane/scene
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		
		//Creating the new game button and putting it in the middle of the pane, and then showing the primary stage
		Button btnNewGame = new Button("New Game");
		pane.setCenter(btnNewGame);
		primaryStage.setScene(scene);
		primaryStage.setHeight(800);
		primaryStage.setWidth(1500);
		primaryStage.show();
		
		//Action event which is triggered when the new game button is pressed
		btnNewGame.setOnAction(e -> {
			
			//Creating the player-creation pop-up stage/scene, which includes a gridpane and hboxes
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
			
			//Creating the enter button, which will trigger player creation when pressed
			Button btnEnter = new Button("Enter");
			
			//Adding the info to the gridpane
			info.add(new Text("NAME: "), 0, 0);
			info.add(new Text("RACE: "), 0, 1);
			info.add(name, 1, 0);
			info.add(race, 1, 1);
			info.add(btnEnter, 1, 2);
			
			//Message which will display while creating a player
			Text message = new Text("Leave both blank and press Enter for default selections. Races could be Human, Elf, Dwarf, or Wizard.");
			
			//Setting those hboxes into the player creation pop-up stage/pane
			HBox hbox3 = new HBox();
			hbox3.getChildren().add(message);
			hbox3.setAlignment(Pos.CENTER);
			playerCreation.setBottom(hbox3);
			hbox2.getChildren().add(info);
			hbox2.setAlignment(Pos.CENTER);
			playerCreation.setCenter(hbox2);
			
			//Initializing the weapon data
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
			
			//Creating the weapons array list and adding the weapons to it
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
			
			//Initializing the shield data
			Shield woodenShield = new Shield("Wooden Shield", 1, 1);
			Shield stoneShield = new Shield("Stone Shield", 0.9, 2);
			Shield metalShield = new Shield("Metal Shield", 0.8, 3);
			Shield diamondShield = new Shield("Diamond Shield", 0.7, 4);
			
			//Creating the shields array list and adding the shields to it
			ArrayList<Shield> shields = new ArrayList<>();
			
			shields.add(woodenShield);
			shields.add(stoneShield);
			shields.add(metalShield);
			shields.add(diamondShield);
			
			//Initializing the food data
			Food apple = new Food("Apple", 10, "Human", 1, 5);
			Food cookie = new Food("Cookie", 20, "Human", 2, 10);
			Food pie = new Food("Pie", 30, "Human", 3, 15);
			Food steak = new Food("Steak", 40, "Human", 4, 20);
			
			Food berries = new Food("Berries", 10, "Elf", 1, 5);
			Food bread = new Food("Bread", 20, "Elf", 2, 10);
			Food cake = new Food("Cake", 30, "Elf", 3, 15);
			Food veal = new Food("Veal", 40, "Elf", 4, 20);
			
			Food melon = new Food("Melon", 10, "Dwarf", 1, 5);
			Food brownie = new Food("Brownie", 20, "Dwarf", 2, 10);
			Food cheesecake = new Food("Cheesecake", 30, "Dwarf", 3, 15);
			Food porkChop = new Food("Pork Chop", 40, "Dwarf", 4, 20);
			
			Food banana = new Food("Banana", 10, "Wizard", 1, 5);
			Food croissant = new Food("Croissant", 20, "Wizard", 2, 10);
			Food raspberryTart = new Food("Raspberry Tart", 30, "Wizard", 3, 15);
			Food filetMignon = new Food("Filet Mignon", 40, "Wizard", 4, 20);
			
			//Creating the foods array list and adding the foods to it
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
			
			//Initializing the attack spell data
			AttackSpell lv1 = new AttackSpell("Attack Spell", 1, 20, 10);
			AttackSpell lv2 = new AttackSpell("Attack Spell", 2, 30, 15);
			AttackSpell lv3 = new AttackSpell("Attack Spell", 3, 40, 20);
			AttackSpell lv4 = new AttackSpell("Attack Spell", 4, 50, 25);
			
			//Creating the attack spell array list and adding the spells to it
			ArrayList<AttackSpell> aSpells = new ArrayList<>();
			
			aSpells.add(lv1);
			aSpells.add(lv2);
			aSpells.add(lv3);
			aSpells.add(lv4);
			
			//Initializing the healing spell data
			HealingSpell lvl1 = new HealingSpell("Healing Spell", 1, 25, 15);
			HealingSpell lvl2 = new HealingSpell("Healing Spell", 2, 50, 20);
			HealingSpell lvl3 = new HealingSpell("Healing Spell", 3, 75, 25);
			HealingSpell lvl4 = new HealingSpell("Healing Spell", 4, 95, 30);
			
			//Creating the healing spell array list and adding the spells to it
			ArrayList<HealingSpell> hSpells = new ArrayList<>();
			
			hSpells.add(lvl1);
			hSpells.add(lvl2);
			hSpells.add(lvl3);
			hSpells.add(lvl4);
			
	
			
			//Action event for the Enter button
           btnEnter.setOnAction(infoEntered -> {
				
        	   //Default case - The player doesn't enter any information in the player creation pane and is assigned the default name/race and corresponsing inventory
        	   if(name.getText().equals("") && race.getText().equals("")) {
              		Player player = new PlayerBuilder().build();
              		Inventory i = new Inventory(player, weapons, shields, foods);
              		
              		//Adding the player/inventory to the respective arraylists and closing the pop-up stage
              		players.add(player);
              		inventories.add(i);        		
              		playerCreationStage.close();
             	}
        	   //Next Case - Player has entered data into the text fields in the Player Creation pop-up
        	   
        	   //First, I check to make sure that they have entered a valid race
        	   else if(race.getText().equalsIgnoreCase("Human") || race.getText().equalsIgnoreCase("Dwarf") || race.getText().equalsIgnoreCase("Elf") || race.getText().equalsIgnoreCase("Wizard")){
        		  
        		 //Case where the player enters a valid race but no name  
        		   if(name.getText().equals("")) {
        			   //Assigning the player with their selected race and the default name and the corresponsing inventory
             		Player player = new PlayerBuilder().setRace(race.getText()).build();
             		Inventory i = new Inventory(player, weapons, shields, foods);
             		
             		//Adding the player/inventory to the respective arraylists and closing the pop-up stage
             		players.add(player);
             		inventories.add(i);
               		playerCreationStage.close();
               		
              	}
        		   //Case where the player enters a name but no race
        		   else if(race.getText().equals("")) {
        			   //Player is assigned with their chosen name and the default race and the corresponding inventory
               		Player player = new PlayerBuilder().setname(name.getText()).build();
               		Inventory i = new Inventory(player, weapons, shields, foods);
               		
               		//Adding the player/inventory to the respective array lists and closing the pop-up window
               		players.add(player);
               		inventories.add(i);
               		playerCreationStage.close();
               	}
        		   
        		   //Case where the player enters both name and race information
               	else {
               		//Assigning the player with their desired name and race 
               		Player player = new PlayerBuilder().setname(name.getText()).setRace(race.getText()).build();
               		Inventory i = new Inventory(player, weapons, shields, foods);
               		
               		//Adding the player/inventory to the respective array lists and closing the pop-up window
               		players.add(player);
               		inventories.add(i);
               		playerCreationStage.close();
               	}
        	   } 
        	   //Case where the player does not enter a valid race. An alert is thrown and they are prompted to try creating their player again
        	   else {
        		   
        		   Alert alert = new Alert(AlertType.INFORMATION);
        		   alert.setTitle("INVALID CHARACTER RACE");
        		   alert.setHeaderText("POSSIBLE RACES ARE HUMAN, ELF, DWARF, AND WIZARD");
        		   alert.setContentText("PLEASE TRY CREATING YOUR PLAYER AGAIN WITH A VALID RACE");
        		   alert.showAndWait();
        		   playerCreationStage.close();
        	   }
        	   
        	   //This code will execute if the player creation occurred successfully (aka valid race inputted)
        	   if(players.size() > 0) {
        		   //Creating the begin game button and placing it in the pane
        		   Button btnBeginGame = new Button("BEGIN GAME");
        		   pane.setCenter(btnBeginGame);
        		   
        		   //Action event which will be triggered when the player presses begin game button
        		   btnBeginGame.setOnAction(begin -> {
        			   
        			   //Removing the begin game button and creating an hbox to hold the stat/inventory/shop/spell information
        			   pane.getChildren().remove(btnBeginGame);
        			   HBox hboxStatsInventory = new HBox();
            		   Text t1 = new Text(players.get(0).getStats());
            		   Text t2 = new Text(inventories.get(0).getInventory());
            		   hboxStatsInventory.getChildren().addAll(t1,t2);
            		   hboxStatsInventory.setAlignment(Pos.CENTER);
            		   
            		  //Creating the buttons which allow you to eat your food, enter the shop, and use your spells
            		   Button btnEatFood = new Button("EAT FOOD");
            		   Button btnShop = new Button("SHOP");
            		   Button btnUseASpell = new Button("USE ATTACK SPELL");
            		   Button btnUseHSpell = new Button("USE HEALING SPELL");
            		   
            		   //Adding the 4 buttons to a grid pane
            		   GridPane buttons = new GridPane();
            		   buttons.add(btnEatFood, 0, 0);
            		   buttons.add(btnShop, 0, 1);
            		   buttons.add(btnUseASpell, 1, 0);
            		   buttons.add(btnUseHSpell, 1, 1);
            		   
            		   //Putting all of the different things into one hbox at the bottom of the border pane
            		   HBox bottomInfo = new HBox();
            		   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);
            		   bottomInfo.setAlignment(Pos.CENTER);
            		   pane.setBottom(bottomInfo);
            		   
            		   //Action event for the eat food button
            		   btnEatFood.setOnAction(eatFood ->{
            			   
            			   //First, it checks if the player actually has food
            			   if(inventories.get(0).food != null) {
            				   //If they do, then the hp points from the food are added to the player's hp 
            				   int calories = inventories.get(0).food.plusHp;
            				   players.get(0).hp += calories;
            				   
            				   //food is removed from the inventory, as it has been eaten
            				   inventories.get(0).food = null;
            				   
            				   //USE THIS CODE WHENEVER YOU NEED TO REFRESH THE STATS/INVENTORY WHCIH APPEARS ON THE BOTTOM
            				   bottomInfo.getChildren().clear();
            				   hboxStatsInventory.getChildren().clear();
            				   hboxStatsInventory.getChildren().addAll(new Text(players.get(0).getStats()), new Text(inventories.get(0).getInventory()));
            				   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);
            				   
            			   }
            			   //If they don't have any food in their inventory, an alert is thrown which tells them they don't have food and can get more from the shop
            			   else {
            				   Alert noFood = new Alert(AlertType.INFORMATION);
            				   noFood.setTitle("YOU DON'T HAVE ANY FOOD");
                    		   noFood.setHeaderText("YOU DONT HAVE ANY FOOD TO EAT");
                    		   noFood.setContentText("YOU CAN BUY ADDITIONAL FOOD FROM THE SHOP IF YOU ARE OUT OF FOOD");
                    		   noFood.showAndWait();
            				   
            			   }
            		   });

            		   //Action event for the use attack spell button
            		   btnUseASpell.setOnAction(useAttackSpell -> {
            			   //It first checks to see whether the player has an attack spell in their inventory. If not an alert is thrown
            			   if(inventories.get(0).aSpell == null) {
            				   Alert noASpell = new Alert(AlertType.INFORMATION);
            				   noASpell.setTitle("YOU DON'T HAVE AN ATTACK SPELL");
            				   noASpell.setHeaderText("YOU DON'T HAVE AN ATTACK SPELL");
            				   noASpell.setContentText("VISIT THE SHOP TO BUY AN ATTACK SPELL");
            				   noASpell.showAndWait();
            			   }
            			   //If they do, then they get to use it and it is removed from their inventory
            			   else {
            				   //INSERT WHAT THE ATTACK SPELL DOES TO THE ENEMY HERE 
            				   
            				   //Removing the used attack spell from the inventory
            				   inventories.get(0).aSpell = null;
            				   
            				 //USE THIS CODE WHENEVER YOU NEED TO REFRESH THE STATS/INVENTORY WHCIH APPEARS ON THE BOTTOM
            				   bottomInfo.getChildren().clear();
            				   hboxStatsInventory.getChildren().clear();
            				   hboxStatsInventory.getChildren().addAll(new Text(players.get(0).getStats()), new Text(inventories.get(0).getInventory()));
            				   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);   
            			   }
            		   });
            		   
            		   btnUseHSpell.setOnAction(useHealingSpell -> {
            			   
            			   //It first checks to see whether the player has a healing spell in their inventory. If not then an alert is thrown
            			   if(inventories.get(0).hSpell == null) {
            				   Alert noHSpell = new Alert(AlertType.INFORMATION);
            				   noHSpell.setTitle("YOU DON'T HAVE A HEALING SPELL");
            				   noHSpell.setHeaderText("YOU DON'T HAVE A HEALING SPELL");
            				   noHSpell.setContentText("VISIT THE SHOP TO BUY A HEALING SPELL");
            				   noHSpell.showAndWait();
            			   }
            			   //If they do, then they get to use it and it is removed from their inventory
            			   else {
            				   //Adding the appropriate amount to the player's HP
            				   players.get(0).hp += inventories.get(0).hSpell.hpHealing;
            				   
            				   //Removing the used healing spell from the inventory
            				   inventories.get(0).hSpell = null;
            				   
            				 //USE THIS CODE WHENEVER YOU NEED TO REFRESH THE STATS/INVENTORY WHCIH APPEARS ON THE BOTTOM
            				   bottomInfo.getChildren().clear();
            				   hboxStatsInventory.getChildren().clear();
            				   hboxStatsInventory.getChildren().addAll(new Text(players.get(0).getStats()), new Text(inventories.get(0).getInventory()));
            				   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);   
            			   }
            			   
            		   });
            		   
            		   //Action event which will be triggered when the shop button is pressed
            		  btnShop.setOnAction(shop -> {
            			  
            			//Initializing the shop data
            				Shop human1Shop = new Shop("Human", 1, foods, aSpells, hSpells);
            				Shop human2Shop = new Shop("Human", 2, foods, aSpells, hSpells);
            				Shop human3Shop = new Shop("Human", 3, foods, aSpells, hSpells);
            				Shop human4Shop = new Shop("Human", 4, foods, aSpells, hSpells);
            				
            				Shop elf1Shop = new Shop("Elf", 1, foods, aSpells, hSpells);
            				Shop elf2Shop = new Shop("Elf", 2, foods, aSpells, hSpells);
            				Shop elf3Shop = new Shop("Elf", 3, foods, aSpells, hSpells);
            				Shop elf4Shop = new Shop("Elf", 4, foods, aSpells, hSpells);
            				
            				Shop dwarf1Shop = new Shop("Dwarf", 1, foods, aSpells, hSpells);
            				Shop dwarf2Shop = new Shop("Dwarf", 2, foods, aSpells, hSpells);
            				Shop dwarf3Shop = new Shop("Dwarf", 3, foods, aSpells, hSpells);
            				Shop dwarf4Shop = new Shop("Dwarf", 4, foods, aSpells, hSpells);
            				
            				Shop wizard1Shop = new Shop("Wizard", 1, foods, aSpells, hSpells);
            				Shop wizard2Shop = new Shop("Wizard", 2, foods, aSpells, hSpells);
            				Shop wizard3Shop = new Shop("Wizard", 3, foods, aSpells, hSpells);
            				Shop wizard4Shop = new Shop("Wizard", 4, foods, aSpells, hSpells);
            				
            				//Creating the shop array list and adding the shops to it
            				ArrayList<Shop> shops = new ArrayList<>();
            				
            				shops.add(human1Shop);
            				shops.add(human2Shop);
            				shops.add(human3Shop);
            				shops.add(human4Shop);
            				
            				shops.add(elf1Shop);
            				shops.add(elf2Shop);
            				shops.add(elf3Shop);
            				shops.add(elf4Shop);
            				
            				shops.add(dwarf1Shop);
            				shops.add(dwarf2Shop);
            				shops.add(dwarf3Shop);
            				shops.add(dwarf4Shop);
            				
            				shops.add(wizard1Shop);
            				shops.add(wizard2Shop);
            				shops.add(wizard3Shop);
            				shops.add(wizard4Shop);
            			  
            			  //Creating the shop pane which will display the shop
            			  BorderPane shopPane = new BorderPane();
            			  
            			  //Creating the tableview which will hold the items available for purchase
            			  TableView<Shop> shopTable = new TableView<>();
            			  
            			  //Creating the columns of the shop tableview and assigning them the appropriate fields from the Shop class
            			  TableColumn<Shop, Food> colFood = new TableColumn<>("Food");
            			  colFood.setCellValueFactory(new PropertyValueFactory<>("food"));
            			  
            			  TableColumn<Shop, Integer> colFoodCoins = new TableColumn<>("Coins");
            			  colFoodCoins.setCellValueFactory(new PropertyValueFactory<>("foodCoins"));
            			  
            			  TableColumn<Shop, AttackSpell> colASpell = new TableColumn<>("Attack Spell");
            			  colASpell.setCellValueFactory(new PropertyValueFactory<>("attackSpell"));
            			  
            			  TableColumn<Shop, Integer> colASpellCoins = new TableColumn<>("Coins");
            			  colASpellCoins.setCellValueFactory(new PropertyValueFactory<>("aSpellCoins"));
            			  
            			  TableColumn<Shop, HealingSpell> colHSpell = new TableColumn<>("Healing Spell");
            			  colHSpell.setCellValueFactory(new PropertyValueFactory<>("healingSpell"));
            			  
            			  TableColumn<Shop, Integer> colHSpellCoins = new TableColumn<>("Coins");
            			  colHSpellCoins.setCellValueFactory( new PropertyValueFactory<>("hSpellCoins"));
            			  
            			  //Adding the created columns to the tableview
            			  shopTable.getColumns().addAll(colFood, colFoodCoins, colASpell, colASpellCoins, colHSpell, colHSpellCoins);
            			  
            			  //This is the shop which will display. I am initializing it with the base values
            			  Shop chosenShop = new Shop("Human", 1, foods, aSpells, hSpells);

            			  //Iterating through the possible shop options to select the correct one based on race/level
            			  for(Shop s: shops) {
            				  if(s.level == players.get(0).level && s.race.equalsIgnoreCase(players.get(0).race)) {
            					  chosenShop = s;
            					  break;
            				  }
            			  }
            			  
            			  //Assigning the data from the correct shop to be the data which is displayed
            			  ObservableList<Shop> store = FXCollections.observableArrayList(chosenShop);
            			  shopTable.setItems(store);
            			  
            			  //setting the shop table onto the shop pane
            			  shopPane.setCenter(shopTable);  
     
            			  //Creating the shop stage
            			  Stage shopStage = new Stage();
            			  
            			  //Creating an hbox to hold the shop buttons
            			  HBox shopButtons = new HBox();
            			  
            			  //Creating the buttons which will be featured in the shop
            			  Button btnCloseShop = new Button("CLOSE SHOP");
            			  Button btnBuyFood = new Button("BUY FOOD");
            			  Button btnBuyASpell = new Button("BUY ATTACK SPELL");
            			  Button btnBuyHSpell = new Button("BUY HEALING SPELL");
            			  
            			  //Adding the shop buttons to the hbox which will hold them
            			  shopButtons.getChildren().addAll( btnBuyFood, btnBuyASpell, btnBuyHSpell, btnCloseShop);
            			  shopButtons.setAlignment(Pos.CENTER);
            			  
            			  //Formatting the shop scene and putting it on the shop stage
            			  shopPane.setBottom(shopButtons);
            			  Scene shopScene = new Scene(shopPane, 675, 75);
            			  shopStage.setScene(shopScene);
            			  shopStage.setTitle("LEVEL " + players.get(0).level + " " + players.get(0).race + " SHOP");
            			  shopStage.show();
            			  
            			  //Action event for the close shop button. It closes the shop stage
            			  btnCloseShop.setOnAction(closeShop ->{
            				  shopStage.close();
            				  
            			  });
            			  
            			  //Action event for buy food button
            			  btnBuyFood.setOnAction(buyFood -> {
            				  //First, we check if the player already has food. If so, they have to eat that first before they can buy more
            				  //An information alert is thrown which tells them this
            				  if(inventories.get(0).food != null) {
            					  Alert foodAlert = new Alert(AlertType.INFORMATION);
            					  foodAlert.setTitle("YOU ALREADY HAVE FOOD");
            					  foodAlert.setHeaderText("YOU ALREADY HAVE FOOD IN YOUR INVENTORY");
            					  foodAlert.setContentText("YOU NEED TO EAT THE FOOD IN YOUR INVENTORY BEFORE BUYING MORE");
            					  foodAlert.showAndWait();
            				  }
            				  //The following check if the player has enough money for the purchase. If not, an alert is thrown telling them so
            				  else if (players.get(0).level == 1 && players.get(0).coins < 5){
            					  Alert noMoney = new Alert(AlertType.INFORMATION);
            					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
            					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
            					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
            					  noMoney.showAndWait();
            				  }else if (players.get(0).level == 2 && players.get(0).coins < 10){
            					  Alert noMoney = new Alert(AlertType.INFORMATION);
            					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
            					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
            					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
            					  noMoney.showAndWait();
            				  }
            			  else if (players.get(0).level == 3 && players.get(0).coins < 15){
        					  Alert noMoney = new Alert(AlertType.INFORMATION);
        					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
        					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
        					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
        					  noMoney.showAndWait();
        				  }
            			  else if (players.get(0).level == 4 && players.get(0).coins < 20){
        					  Alert noMoney = new Alert(AlertType.INFORMATION);
        					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
        					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
        					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
        					  noMoney.showAndWait();
        				  }
            				  //In this case the player doesn't currently have food in their inventory and has enough coins for the purchase
            				  //We update the player's inventory to have the food and subtract the price of the food from the player's coins
            				  else {
            					  for(Shop s: shops) {
            						  if(players.get(0).race.equals(s.race) && players.get(0).level == s.level ) {
            							  inventories.get(0).food = s.food;
            							  players.get(0).coins -= s.foodCoins;
            							  
            							  //USED TO UPDATE THE BOTTOM INFORMATION FOLLOWING THE PURCHASE
            							  bottomInfo.getChildren().clear();
                       				   hboxStatsInventory.getChildren().clear();
                       				   hboxStatsInventory.getChildren().addAll(new Text(players.get(0).getStats()), new Text(inventories.get(0).getInventory()));
                       				   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);
            						  }
            					  }
            				  }
            			  });
            			  
            			  //Action event for the buy Attack Spell button
            			  btnBuyASpell.setOnAction( buyASpell -> {
            				  //As with food, the player can only buy an attack spell if they currently don't have one in their inventory
            				  //Alerts are thrown similarly as with the food
            				  //Alerts are also thrown if the player doesn't have enough coins as well - same as food
            				 if(inventories.get(0).aSpell != null) {
            					 Alert aSpellAlert = new Alert(AlertType.INFORMATION);
           					  aSpellAlert.setTitle("YOU ALREADY HAVE AN ATTACK SPELL");
           					  aSpellAlert.setHeaderText("YOU ALREADY HAVE AN ATTACK SPELL IN YOUR INVENTORY");
           					  aSpellAlert.setContentText("YOU NEED TO USE THE ATTACK SPELL IN YOUR INVENTORY BEFORE BUYING ANOTHER ONE");
           					  aSpellAlert.showAndWait();
            				 }else if (players.get(0).level == 1 && players.get(0).coins < 10){
           					  Alert noMoney = new Alert(AlertType.INFORMATION);
           					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
           					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
           					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
           					  noMoney.showAndWait();
           				  }else if (players.get(0).level == 2 && players.get(0).coins < 15){
           					  Alert noMoney = new Alert(AlertType.INFORMATION);
           					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
           					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
           					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
           					  noMoney.showAndWait();
           				  }
           			  else if (players.get(0).level == 3 && players.get(0).coins < 20){
       					  Alert noMoney = new Alert(AlertType.INFORMATION);
       					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
       					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
       					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
       					  noMoney.showAndWait();
       				  }
           			  else if (players.get(0).level == 4 && players.get(0).coins < 25){
       					  Alert noMoney = new Alert(AlertType.INFORMATION);
       					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
       					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
       					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
       					  noMoney.showAndWait();
       				  }
            				 //In this case the player doesn't currently have the spell in their inventory and has enough coins
            				 //We add the attack spell to the player's current inventory and subtract the spell's price away from the player's coins
           			  else {
           				for(Shop s: shops) {
  						  if(players.get(0).level == s.level && players.get(0).race.equalsIgnoreCase(s.race)) {
  							  inventories.get(0).aSpell = s.attackSpell;
  							  players.get(0).coins -=  (s.aSpellCoins.getValue());
  							  
  							  //USED TO UPDATE THE BOTTOM INFORMATION
  							  bottomInfo.getChildren().clear();
             				   hboxStatsInventory.getChildren().clear();
             				   hboxStatsInventory.getChildren().addAll(new Text(players.get(0).getStats()), new Text(inventories.get(0).getInventory()));
             				   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);
  						  }
  					  }
           			  }
            			  });
            			  //Action event for the buy healing spell button
            			  //Same checks/alerts are given as with food & attack spell -- can't buy a healing spell if there's already one in your inventory
            			  btnBuyHSpell.setOnAction( buyHSpell -> {
            				  if(inventories.get(0).hSpell != null) {
             					 Alert aSpellAlert = new Alert(AlertType.INFORMATION);
            					  aSpellAlert.setTitle("YOU ALREADY HAVE A HEALING SPELL");
            					  aSpellAlert.setHeaderText("YOU ALREADY HAVE A HEALING SPELL IN YOUR INVENTORY");
            					  aSpellAlert.setContentText("YOU NEED TO USE THE HEALING SPELL IN YOUR INVENTORY BEFORE BUYING ANOTHER ONE");
            					  aSpellAlert.showAndWait();
             				 }else if (players.get(0).level == 1 && players.get(0).coins < 10){
            					  Alert noMoney = new Alert(AlertType.INFORMATION);
            					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
            					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
            					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
            					  noMoney.showAndWait();
            				  }else if (players.get(0).level == 2 && players.get(0).coins < 15){
            					  Alert noMoney = new Alert(AlertType.INFORMATION);
            					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
            					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
            					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
            					  noMoney.showAndWait();
            				  }
            			  else if (players.get(0).level == 3 && players.get(0).coins < 20){
        					  Alert noMoney = new Alert(AlertType.INFORMATION);
        					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
        					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
        					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
        					  noMoney.showAndWait();
        				  }
            			  else if (players.get(0).level == 4 && players.get(0).coins < 25){
        					  Alert noMoney = new Alert(AlertType.INFORMATION);
        					  noMoney.setTitle("YOU DON'T HAVE ENOUGH MONEY FOR THAT PURCHASE");
        					  noMoney.setHeaderText("YOU NEED MORE COINS TO MAKE THAT PURCHASE");
        					  noMoney.setContentText("NOT ENOUGH COINS FOR THE PURCHASE");
        					  noMoney.showAndWait();
        				  }
            				  //In this case, the player is allowed to buy the healing spell, so we add it to their inventory and subtract the price from their coins
            			  else {
            				for(Shop s: shops) {
   						  if(players.get(0).level == s.level && players.get(0).race.equalsIgnoreCase(s.race)) {
   							  inventories.get(0).hSpell = s.healingSpell;
   							  players.get(0).coins -=  (s.hSpellCoins.getValue());
   							  
   							  //USED TO UPDATE THE BOTTOM INFORMATION
   							  bottomInfo.getChildren().clear();
              				   hboxStatsInventory.getChildren().clear();
              				   hboxStatsInventory.getChildren().addAll(new Text(players.get(0).getStats()), new Text(inventories.get(0).getInventory()));
              				   bottomInfo.getChildren().addAll(hboxStatsInventory, buttons);
   						  }
   					  }
            			  } 
            				  
            			  });
            			  
            		  }) ;
            		   
            		   
            		   
        		   });
        		   
        		  
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

import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneHandler {
	
	private Map map;
	
	// Custom buttons for "Yes" and "No"
    private ButtonType yesBtn = new ButtonType("Yes");
    private ButtonType noBtn = new ButtonType("No");
    
    private Random random = new Random();
    
	public static void loadMapScene(Stage stage, Player player) throws Exception{
		BorderPane pane = new BorderPane();
		Button combatSceneBtn = new Button("Combat scene");
		combatSceneBtn.setOnAction(event->{
			try {
				SceneHandler.loadCombatScene(stage, player);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Button shopSceneBtn = new Button("Shop scene");
		shopSceneBtn.setOnAction(event->{
			try {
				SceneHandler.loadShopScene(stage, player);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Button endCombatSceneBtn = new Button("End CombatScene");
		endCombatSceneBtn.setOnAction(event->{
			try {
				SceneHandler.loadEndCombatScene(stage, player, null, 3);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		});
		
		Button statusStageBtn = new Button("Player Status");
		statusStageBtn.setOnAction(event->{
			try {
				SceneHandler.loadStatusStage(player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Button saveGameBtn = new Button("save Game");
		saveGameBtn.setOnAction(event -> {
			try {
				Inventory inventory = new Inventory(player, null, null, null);
				SaveLoadManager.saveGame(player,  inventory);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		
		Button quitSceneBtn = new Button("Quit Application");
		quitSceneBtn.setOnAction(event->{
			stage.close();
		});
		
		VBox buttons = new VBox();
		
		buttons.getChildren().addAll(combatSceneBtn, shopSceneBtn, endCombatSceneBtn, statusStageBtn, saveGameBtn, quitSceneBtn);
		buttons.setAlignment(Pos.CENTER);
		
		
		pane.setTop(new StackPane(new Label("Map Scene")));
		pane.setCenter(buttons);
		
		Scene mainScene = new Scene(pane, 800, 600);
		
		stage.setScene(mainScene);
		stage.show();
	}
	
	public static void loadCombatScene(Stage stage, Player player) throws Exception{
		MutableBoolean isBattling = new MutableBoolean(true);
		Enemy enemy = EnemyCatalog.enemyFromCatalog(4);
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		
		//Button layout
		VBox buttons = new VBox();
		Button attackBtn = new Button("Attack");
		Button spellBtn = new Button("Spell");
		Button itemBtn = new Button("Item");
		Button fleeBtn = new Button("Flee");
		
		buttons.getChildren().addAll(attackBtn, spellBtn, itemBtn, fleeBtn);
		
		//enemy image
		VBox combatDisplay = new VBox();
		ImageView enemyView = new ImageView();
		enemyView.setImage(enemy.getI1());
		
		//enemy stats
		HBox enemyHealth = new HBox();
		Label enemyHp = new Label(String.valueOf(enemy.getHp()));
		enemyHealth.getChildren().addAll(new Label(enemy.getName()+ ": HP: "), enemyHp, new Label(" / "), new Label(String.valueOf(enemy.getHpMax())));
		
		//player stats
		HBox playerHealth = new HBox();
		Label playerHp = new Label(String.valueOf(player.getHp()));
		Label playerMp = new Label(String.valueOf(player.getMp()));
		playerHealth.getChildren().addAll(new Label(player.getName()+ ": HP: "), playerHp, new Label(" / "), new Label(String.valueOf(player.getHpMax())), new Label(" MP: "), playerMp, new Label(" / "), new Label(String.valueOf(player.getMpMax())));
		
		combatDisplay.getChildren().addAll(enemyView, enemyHealth, playerHealth);
		
		//information text
		Label info1 = new Label("Started battle with " + enemy.getName());
		Label info2 = new Label("");
		VBox infoBox = new VBox();
		
		//information 
		infoBox.getChildren().addAll(info1, info2);
		
		pane.setRight(buttons);
		pane.setLeft(combatDisplay);
		pane.setBottom(infoBox);
		
		//animation thread
		//switches between frames of enemy
		Thread animationThread = new Thread(()->{
			while(isBattling.getValue()) {
				enemyView.setImage(enemy.getI1());
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				enemyView.setImage(enemy.getI2());
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		//makes it so thread will exit before the application is closed
		animationThread.setDaemon(true);
		animationThread.start();
		
		//does player attack and enemy logic
		attackBtn.setOnAction(event->{
			//(toolLevel*0.75)(1.15^(scaleStat-1))+(scaleStat*0.5)+5
			int damage = (int)(5 + (player.getWeapon()*0.75) * Math.pow(1.15, (player.getStr() - 1)) + (0.5 * player.getStr()));
			
			enemy.setHp(enemy.getHp() - damage);
			enemyHp.setText(String.valueOf(enemy.getHp()));
			
			//check if enemy is dead
			if(enemy.getHp() <= 0) {
				try {
					SceneHandler.loadEndCombatScene(stage, player, enemy, 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//update info text
			info2.setText(info1.getText());
			info1.setText(player.getName() + " dealt " + damage + " damage to " + enemy.getName());
			
			//enemy turn
			int plhptemp = player.getHp();
			enemy.logic(enemy, player, stage);
			
			//check if player is dead
			if(player.getHp() <= 0) {
				try {
					SceneHandler.loadEndCombatScene(stage, player, enemy, 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			playerHp.setText(String.valueOf(player.getHp()));
			//update info text
			info2.setText(info1.getText());
			info1.setText(enemy.getName() + " dealt " + (plhptemp - player.getHp()) + " damage to " + player.getName());
			
			//regain mana before next turn
			player.setMp((int)(player.getMpMax()/10));
			if(player.getMp() > player.getMpMax()) {
				player.setMp(player.getMpMax());
			}
			playerMp.setText(String.valueOf(player.getMp()));
		});
		
		//opens spell menu where you can then select spell to use or return to main button box
		spellBtn.setOnAction(event->{
			VBox spellButtons = new VBox();
			Button atkSpell = new Button("Attack Spell");
			Button healSpell = new Button("Heal Spell");
			Button back = new Button("Back");
			spellButtons.getChildren().addAll(atkSpell, healSpell, back);
			pane.setRight(spellButtons);
			
			//attack spell
			atkSpell.setOnAction(event1->{
				//spell cost calculation
				int spellCost = (int)(((player.getAtkSpell() * 0.2) * player.getMnd())+ 5);
				
				if(spellCost <= player.getMp()) {
					//(toolLevel*0.75)(1.15^(scaleStat-1))+(scaleStat*0.5)+5
					int damage = (int)(5 + (player.getAtkSpell()*0.75) * Math.pow(1.15, (player.getMnd() - 1)) + (0.5 * player.getMnd()));
					
					//update enemy hp
					enemy.setHp(enemy.getHp() - damage);
					
					//check if enemy is dead
					if(enemy.getHp() <= 0) {
						try {
							SceneHandler.loadEndCombatScene(stage, player, enemy, 1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					enemyHp.setText(String.valueOf(enemy.getHp()));
					
					//update player mp
					player.setMp(player.getMp() - spellCost);
					playerMp.setText(String.valueOf(player.getMp()));
					
					//update info text
					info2.setText(info1.getText());
					info1.setText(player.getName() + " payed " + spellCost + " MP to deal " + damage + " to " + enemy.getName());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Insufficient MP in order to cast Attack Spell");
					return;
				}
				
				//enemy turn
				int plhptemp = player.getHp();
				enemy.logic(enemy, player, stage);
				
				//check if player is dead
				if(player.getHp() <= 0) {
					try {
						SceneHandler.loadEndCombatScene(stage, player, enemy, 2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				playerHp.setText(String.valueOf(player.getHp()));
				//update info text
				info2.setText(info1.getText());
				info1.setText(enemy.getName() + " dealt " + (plhptemp - player.getHp()) + " damage to " + player.getName());
				pane.setRight(buttons);
				
				//regain mana before next turn
				player.setMp((int)(player.getMp() + (player.getMnd() / 2) + 1));
				if(player.getMp() > player.getMpMax()) {
					player.setMp(player.getMpMax());
				}
				playerMp.setText(String.valueOf(player.getMp()));
			});
			
			//heal spell
			healSpell.setOnAction(event1->{
				//spell cost calculation
				int spellCost = (int)(((player.getHealSpell() * 0.2) * player.getMnd())+ 5);
				
				if(spellCost <= player.getMp()) {
					//calculate healing amount
					//(toolLevel*0.75)(1.15^(scaleStat-1))+(scaleStat*0.5)+5
					int healAmount = (int)(5 + (player.getHealSpell()*0.75) * Math.pow(1.15, (player.getMnd() - 1)) + (0.5 * player.getMnd()));
					
					//update player hp
					player.setHp(player.getHp() + healAmount);
					if(player.getHp() > player.getHpMax()) {
						player.setHp(player.getHpMax());
					}
					playerHp.setText(String.valueOf(player.getHp()));
					
					//update player mp
					player.setMp(player.getMp() - spellCost);
					playerMp.setText(String.valueOf(player.getMp()));
					
					//update info text
					info2.setText(info1.getText());
					info1.setText(player.getName() + " payed " + spellCost + " MP to heal " + healAmount + " HP");
					pane.setRight(buttons);
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Insufficient MP in order to cast Heal Spell");
					return;
				}
				
				
				//enemy turn
				int plhptemp = player.getHp();
				enemy.logic(enemy, player, stage);
				
				//check if player is dead
				if(player.getHp() <= 0) {
					try {
						SceneHandler.loadEndCombatScene(stage, player, enemy, 2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				playerHp.setText(String.valueOf(player.getHp()));
				//update info text
				info2.setText(info1.getText());
				info1.setText(enemy.getName() + " dealt " + (plhptemp - player.getHp()) + " damage to " + player.getName());
				
				//regain mana before next turn
				player.setMp((int)(player.getMp() + (player.getMnd() / 2) + 1));
				if(player.getMp() > player.getMpMax()) {
					player.setMp(player.getMpMax());
				}
				playerMp.setText(String.valueOf(player.getMp()));
			});
			
			//return to buttons
			back.setOnAction(event1->{
				pane.setRight(buttons);
			});
		});
		
		itemBtn.setOnAction(event->{
			Button healthPotion = new Button("Health Potion");
			Button greatHealthPotion = new Button("Great Health Potion");
			Button manaPotion = new Button("Mana Potion");
			Button greatManaPotion = new Button("Great Mana Potion");
			Button back = new Button("Back");
			VBox potions = new VBox();
			
			//check player's potion inventory
			if(player.getHealthPotion() > 0) {
				potions.getChildren().add(healthPotion);
				
			}
			if(player.getGreatHealthPotion() > 0) {
				potions.getChildren().add(greatHealthPotion);
				
			}
			if(player.getManaPotion() > 0) {
				potions.getChildren().add(manaPotion);
			}
			if(player.getGreatManaPotion() > 0) {
				potions.getChildren().add(greatManaPotion);
			}
			potions.getChildren().add(back);
			pane.setRight(potions);
			
			//use health potion
			healthPotion.setOnAction(event1->{
				int healAmount = 10;
				player.setHp(player.getHp() + healAmount);
				if(player.getHp() > player.getHpMax()) {
					player.setHp(player.getHpMax());
				}
				playerHp.setText(String.valueOf(player.getHp()));
				
				player.setHealthPotion(player.getHealthPotion() - 1);
				
				pane.setRight(buttons);
			});
			
			//use great health potion
			greatHealthPotion.setOnAction(event1->{
				int healAmount = 30;
				player.setHp(player.getHp() + healAmount);
				if(player.getHp() > player.getHpMax()) {
					player.setHp(player.getHpMax());
				}
				playerHp.setText(String.valueOf(player.getHp()));
				
				player.setGreatHealthPotion(player.getGreatHealthPotion() - 1);
				
				pane.setRight(buttons);
			});
			
			//use mana potion
			manaPotion.setOnAction(event1->{
				int restoreAmount = 10;
				player.setMp(player.getMp() + restoreAmount);
				if(player.getMp() > player.getMpMax()) {
					player.setMp(player.getMpMax());
				}
				playerMp.setText(String.valueOf(player.getMp()));
				
				player.setManaPotion(player.getManaPotion() - 1);
				
				pane.setRight(buttons);
			});
			
			//use great mana potion
			greatManaPotion.setOnAction(event1->{
				int restoreAmount = 30;
				player.setMp(player.getMp() + restoreAmount);
				if(player.getMp() > player.getMpMax()) {
					player.setMp(player.getMpMax());
				}
				playerMp.setText(String.valueOf(player.getMp()));
				
				player.setGreatManaPotion(player.getGreatManaPotion() - 1);
				
				pane.setRight(buttons);
			});
			
			//return to buttons
			back.setOnAction(event1->{
				pane.setRight(buttons);
			});
		});
		
		//exits battle
		fleeBtn.setOnAction(event->{
			isBattling.setValue(false);
			try {
				SceneHandler.loadEndCombatScene(stage, player, enemy, 3);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Scene combatScene = new Scene(pane, 800, 600);
		
		stage.setScene(combatScene);
		stage.show();
	}
	
	public static void loadEndCombatScene(Stage stage, Player player, Enemy enemy, int endState) throws Exception{
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		
		VBox content = new VBox();
		Label result = new Label();
		
		Button cont = new Button("Continue");
		
		content.getChildren().addAll(result);
		content.setAlignment(Pos.CENTER);
		pane.setCenter(content);
		
		//victory
		if(endState == 1) {
			result.setText("Victory: Enemy Vanquished");
			
			//gold earned
			Label goldEarned = new Label(player.getName() + " earned " + enemy.getGold() + " gold.");
			content.getChildren().add(goldEarned);
			player.setGold(player.getGold() + enemy.getGold());
			
			//xp earned
			Label xpEarned = new Label(player.getName() + " earned " + enemy.getXp() + " XP");
			content.getChildren().add(xpEarned);
			player.setXp(player.getXp() + enemy.getXp());
			
			//check if leveled up
			int xpToNextLvl = (int)(Math.pow(1.1, (player.getLvl()-1)) + player.getLvl() + 20);
			if(player.getXp() >= xpToNextLvl) {
				cont.setOnAction(event->{
					//build stat upgrade pane
					MutableString toUpgrade = new MutableString("");
					MutableInteger numUpgrades = new MutableInteger(2);
					
					GridPane statsUpGrid = new GridPane();
					Button upStr = new Button("Upgrade");
					Button upMnd = new Button("Upgrade");
					Button upVit = new Button("Upgrade");
					Button upSrc = new Button("Upgrade");
					
					Button cont1 = new Button("Continue");
					
					Label dStr = new Label(String.valueOf(player.getStr()));
					Label dMnd = new Label(String.valueOf(player.getMnd()));
					Label dVit = new Label(String.valueOf(player.getVit()));
					Label dSrc = new Label(String.valueOf(player.getSrc()));
					
					Label dUpgrade = new Label(toUpgrade.getValue());
					
					statsUpGrid.add(new Label("Upgrade Stats:"), 1, 0);
					statsUpGrid.add(new Label("Strength:"), 0, 1);
					statsUpGrid.add(dStr, 1, 1);
					
					//check if stat is max
					if(!(player.getStr() == 20)) {
						statsUpGrid.add(upStr, 2, 1);
					}
					
					statsUpGrid.add(new Label("Mind:"), 0, 2);
					statsUpGrid.add(dMnd, 1, 2);
					
					//check if stat is max
					if(!(player.getMnd() == 20)) {
						statsUpGrid.add(upMnd, 2, 2);
					}
					
					statsUpGrid.add(new Label("Vitality:"), 0, 3);
					statsUpGrid.add(dVit, 1, 3);
					
					//check if stat is max
					if(!(player.getVit() == 20)) {
						statsUpGrid.add(upVit, 2, 3);
					}
					
					statsUpGrid.add(new Label("Source:"), 0, 4);
					statsUpGrid.add(dSrc, 1, 4);
					
					//check if stat is max
					if(!(player.getSrc() == 20)) {
						statsUpGrid.add(upSrc, 2, 4);
					}
					
					statsUpGrid.add(new Label("Upgrading: "), 0, 5);
					statsUpGrid.add(dUpgrade, 2, 5);
					statsUpGrid.add(cont1, 1, 6);
					
					statsUpGrid.setAlignment(Pos.CENTER);
					pane.setCenter(statsUpGrid);
					
					//select stat to upgrade
					upStr.setOnAction(event1->{
						toUpgrade.setValue("Strength");
						dUpgrade.setText(toUpgrade.getValue());
					});
					
					upMnd.setOnAction(event1->{
						toUpgrade.setValue("Mind");
						dUpgrade.setText(toUpgrade.getValue());
					});
					
					upVit.setOnAction(event1->{
						toUpgrade.setValue("Vitality");
						dUpgrade.setText(toUpgrade.getValue());
					});
					
					upSrc.setOnAction(event1->{
						toUpgrade.setValue("Source");
						dUpgrade.setText(toUpgrade.getValue());
					});
					
					//confirm selection
					cont1.setOnAction(event1->{
						if(!toUpgrade.getValue().equals("")) {
							switch(toUpgrade.getValue()) {
							case "Strength":
								player.setStr(player.getStr() + 1);
								
								//check if max
								if(player.getStr() == 20) {
									statsUpGrid.getChildren().remove(upStr);
								}
								
								toUpgrade.setValue("");
								dStr.setText(String.valueOf(player.getStr()));
								dUpgrade.setText(toUpgrade.getValue());
								break;
							case "Mind":
								player.setMnd(player.getMnd() + 1);
								
								//check if max
								if(player.getMnd() == 20) {
									statsUpGrid.getChildren().remove(upMnd);
								}
								
								toUpgrade.setValue("");
								dMnd.setText(String.valueOf(player.getMnd()));
								dUpgrade.setText(toUpgrade.getValue());
								break;
							case "Vitality":
								player.setVit(player.getVit() + 1);
								
								//check if max
								if(player.getVit() == 20) {
									statsUpGrid.getChildren().remove(upVit);
								}
								
								toUpgrade.setValue("");
								dVit.setText(String.valueOf(player.getVit()));
								dUpgrade.setText(toUpgrade.getValue());
								break;
							case "Source":
								player.setSrc(player.getSrc() + 1);
								
								//check if max
								if(player.getSrc() == 20) {
									statsUpGrid.getChildren().remove(upSrc);
								}
								
								toUpgrade.setValue("");
								dSrc.setText(String.valueOf(player.getSrc()));
								dUpgrade.setText(toUpgrade.getValue());
								break;
							}
							
							
							numUpgrades.setValue(numUpgrades.getValue() - 1);
							
							//checks number of upgrades
							if(numUpgrades.getValue() == 0) {
								try {
									SceneHandler.loadMapScene(stage, player);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					});
					
					
				});
			}
			else{
				cont.setOnAction(event->{
					try {
						SceneHandler.loadMapScene(stage, player);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
		//defeat
		else if(endState == 2) {
			result.setText("Defeat: Overpowered by Enemy...");
			player.setHp((int)(player.getHpMax() * 0.65));
			player.setMp((int)(player.getMpMax() * 0.65));
			
			cont.setOnAction(event->{
				try {
					SceneHandler.loadMapScene(stage, player);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		//fled
		else if(endState == 3) {
			result.setText("Fled from battle...");
			
			cont.setOnAction(event->{
				try {
					SceneHandler.loadMapScene(stage, player);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		content.getChildren().add(cont);
		
		Scene mainScene = new Scene(pane, 800, 600);
		
		stage.setScene(mainScene);
		stage.show();
	}
	
	public static void loadShopScene(Stage stage, Player player) throws Exception{
		BorderPane pane = new BorderPane();
		Button mainSceneBtn = new Button("Main Scene");
		
		pane.setTop(new StackPane(new Label("Shop Scene")));
		pane.setBottom(mainSceneBtn);
		
//		//potions
//		shopItem healthPotion = new shopItem("Health Potion", player.getHealthPotion(), 0, 15);
//		shopItem greatHealthPotion = new shopItem("Great Health Potion", player.getGreatHealthPotion(), 0, 40);
//		shopItem manaPotion = new shopItem("Mana Potion", player.getManaPotion(), 0, 15);
//		shopItem greatManaPotion = new shopItem("Great Mana Potion", player.getGreatManaPotion(), 0, 40);
//		
//		//tools
//		shopItem weapon = new shopItem("Weapon", 0, player.getWeapon(), (player.getWeapon() * 20));
//		shopItem attackSpell = new shopItem("Attack Spell", 0, player.getAtkSpell(), (player.getAtkSpell() * 20));
//		shopItem healSpell = new shopItem("Heal Spell", 0, player.getHealSpell(), (player.getHealSpell() * 20));
		
		//table for consumable items
		TableView<shopItem> itemsTable = new TableView<>();
		itemsTable.setEditable(false);
		
		//creating the columns for the itemsTable
		TableColumn<shopItem, String> nameICol = new TableColumn<>("Name:");
		nameICol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<shopItem, Integer> quantityICol = new TableColumn<>("Quantity:");
		quantityICol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		TableColumn<shopItem, Integer> costICol = new TableColumn<>("Cost:");
		costICol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		
		itemsTable.getColumns().addAll(nameICol, quantityICol, costICol);
		itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
//		itemsTable.getItems().addAll(healthPotion, greatHealthPotion, manaPotion, greatManaPotion);
		
		
		//table for weapon and spells
		TableView<shopItem> toolsTable = new TableView<>();
		
		//creating the columns for the toolsTable
		TableColumn<shopItem, String> nameTCol = new TableColumn<>("Name:");
		nameICol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<shopItem, Integer> levelTCol = new TableColumn<>("Level:");
		quantityICol.setCellValueFactory(new PropertyValueFactory<>("level"));
		
		TableColumn<shopItem, Integer> costTCol = new TableColumn<>("Cost:");
		costICol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		
		toolsTable.getColumns().addAll(nameTCol, levelTCol, costTCol);
		toolsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
//		toolsTable.getItems().addAll(weapon, attackSpell, healSpell);
		
        
		pane.setCenter(itemsTable);
		
		mainSceneBtn.setOnAction(event->{
			try {
				SceneHandler.loadMapScene(stage, player);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Scene shopScene = new Scene(pane, 800, 600);
		
		stage.setScene(shopScene);
		stage.show();
	}
	
	public static void loadStatusStage(Player player) throws Exception{
		Stage statusStage = new Stage();
		BorderPane pane = new BorderPane();
		
		int xpToNextLvl = (int)(Math.pow(1.1, (player.getLvl()-1)) + player.getLvl() + 20);
		
		VBox header = new VBox();
		header.getChildren().addAll(new Label(player.getName() + " Status:"), new Label("Gold: " + player.getGold()), new Label("Level: " + player.getLvl()), new Label("XP: " + player.getXp() + " / " + xpToNextLvl));
		header.setAlignment(Pos.CENTER);
		pane.setTop(header);
		pane.setPadding(new Insets(10));
		
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		Button statsBtn = new Button("Stats");
		Button itemsBtn = new Button("Items");
		Button toolsBtn = new Button("Tools");
		Button close = new Button("Close");
		buttons.getChildren().addAll(statsBtn, itemsBtn, toolsBtn, close);
		
		pane.setBottom(buttons);
		
		//stats grid
		GridPane stats = new GridPane();
		stats.setAlignment(Pos.CENTER);
		stats.setHgap(10.0);
		stats.setVgap(10.0);
		
		stats.add(new Label("Stat:"), 0, 0);
		stats.add(new Label("Level:"), 2, 0);
		
		stats.add(new Label("Strength"), 0, 1);
		stats.add(new Label(String.valueOf(player.getStr())), 2, 1);
		
		stats.add(new Label("Mind"), 0, 2);
		stats.add(new Label(String.valueOf(player.getMnd())), 2, 2);
		
		stats.add(new Label("Vitality"), 0, 3);
		stats.add(new Label(String.valueOf(player.getVit())), 2, 3);
		
		stats.add(new Label("Source"), 0, 4);
		stats.add(new Label(String.valueOf(player.getSrc())), 2, 4);

		//items grid
		GridPane items = new GridPane();
		items.setAlignment(Pos.CENTER);
		items.setHgap(10.0);
		items.setVgap(10.0);
		
		items.add(new Label("Item:"), 0, 0);
		items.add(new Label("Quantity:"), 2, 0);
		
		items.add(new Label("Health Potion"), 0, 1);
		items.add(new Label(String.valueOf(player.getHealthPotion())), 2, 1);
		
		items.add(new Label("Great Health Potion"), 0, 2);
		items.add(new Label(String.valueOf(player.getGreatHealthPotion())), 2, 2);
		
		items.add(new Label("Mana Potion"), 0, 3);
		items.add(new Label(String.valueOf(player.getManaPotion())), 2, 3);
		
		items.add(new Label("Great Mana Potion"), 0, 4);
		items.add(new Label(String.valueOf(player.getManaPotion())), 2, 4);

		//tools grid
		GridPane tools = new GridPane();
		tools.setAlignment(Pos.CENTER);
		tools.setHgap(10.0);
		tools.setVgap(10.0);
		
		tools.add(new Label("Tool:"), 0, 0);
		tools.add(new Label("Level:"), 2, 0);
		
		tools.add(new Label("Weapon"), 0, 1);
		tools.add(new Label(String.valueOf(player.getWeapon())), 2, 1);
		
		tools.add(new Label("Attack Spell"), 0, 2);
		tools.add(new Label(String.valueOf(player.getAtkSpell())), 2, 2);
		
		tools.add(new Label("Heal Spell"), 0, 3);
		tools.add(new Label(String.valueOf(player.getHealSpell())), 2, 3);
		
		tools.add(new Label(""), 0, 4);
		
		statsBtn.setOnAction(event->{
			pane.setCenter(stats);
		});

		itemsBtn.setOnAction(event->{
			pane.setCenter(items);
		});
		
		toolsBtn.setOnAction(event->{
			pane.setCenter(tools);
		});
		
		close.setOnAction(event->{
			statusStage.close();
		});
		
		pane.setCenter(stats);
		
		Scene scene = new Scene(pane, 512, 384);
		statusStage.setTitle("Player Status");
		statusStage.setScene(scene);
		statusStage.setResizable(false);
		statusStage.show();
	}
	
}
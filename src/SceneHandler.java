
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneHandler {
    
    private static Random random = new Random();
    
	public static void loadMapScene(Stage stage, Player player, DisplayMap dm, boolean toVillage) throws Exception{
		
		dm.start(stage,player, dm, toVillage);
	}
	
	public static void loadCombatScene(Stage stage, Player player, boolean isBoss, DisplayMap dm) throws Exception{
		MutableBoolean isBattling = new MutableBoolean(true);
		Enemy tempEnemy = new Enemy();
		if(isBoss) {
			int id = 0;
			if(player.getxPos() < 5 && player.getyPos() < 5) {
				id = 0;
			}
			else if(player.getxPos() < 5 && player.getyPos() > 5) {
				id = 1;
			}
			else if(player.getxPos() > 5 && player.getyPos() < 5) {
				id = 2;
			}
			else if(player.getxPos() > 5 && player.getyPos() > 5) {
				id = 3;
			}
			
			tempEnemy = EnemyCatalog.enemyFromBossCatalog(id);
		}
		else {
			int id = random.nextInt(5);
			tempEnemy = EnemyCatalog.enemyFromCatalog(id);
		}
		Enemy enemy = tempEnemy;
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		
		//Button layout
		StackPane sPane = new StackPane();
		ImageView background = new ImageView(new Image("img/MapDisplay.png", 150, 150, false, true));
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
		
		sPane.getChildren().addAll(background, enemyView);
		combatDisplay.getChildren().addAll(sPane, enemyHealth, playerHealth);
		
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
					if(isBoss) {
						player.setBossKill(player.getBossKill() + 1);
					}
					SceneHandler.loadEndCombatScene(stage, player, enemy, 1, dm);
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
					SceneHandler.loadEndCombatScene(stage, player, enemy, 2, dm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			enemyHp.setText(String.valueOf(enemy.getHp()));
			playerHp.setText(String.valueOf(player.getHp()));
			//update info text
			info2.setText(info1.getText());
			info1.setText(enemy.getName() + " dealt " + (plhptemp - player.getHp()) + " damage to " + player.getName());
			
			//regain mana before next turn
			player.setMp((int)(player.getMp() + player.getMpMax()/10));
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
							if(isBoss) {
								player.setBossKill(player.getBossKill() + 1);
							}
							SceneHandler.loadEndCombatScene(stage, player, enemy, 1, dm);
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
						SceneHandler.loadEndCombatScene(stage, player, enemy, 2, dm);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				enemyHp.setText(String.valueOf(enemy.getHp()));
				playerHp.setText(String.valueOf(player.getHp()));
				//update info text
				info2.setText(info1.getText());
				info1.setText(enemy.getName() + " dealt " + (plhptemp - player.getHp()) + " damage to " + player.getName());
				pane.setRight(buttons);
				
				//regain mana before next turn
				player.setMp((int)(player.getMp() + player.getMpMax()/10));
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
						SceneHandler.loadEndCombatScene(stage, player, enemy, 2, dm);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				enemyHp.setText(String.valueOf(enemy.getHp()));
				playerHp.setText(String.valueOf(player.getHp()));
				//update info text
				info2.setText(info1.getText());
				info1.setText(enemy.getName() + " dealt " + (plhptemp - player.getHp()) + " damage to " + player.getName());
				
				//regain mana before next turn
				player.setMp((int)(player.getMp() + player.getMpMax()/10));
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
				SceneHandler.loadEndCombatScene(stage, player, enemy, 3, dm);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Scene combatScene = new Scene(pane, 800, 600);
		combatScene.getStylesheets().add("game_styles.css");
		
		stage.setScene(combatScene);
		stage.show();
	}
	
	public static void loadEndCombatScene(Stage stage, Player player, Enemy enemy, int endState, DisplayMap dm) throws Exception{
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		
		VBox content = new VBox();
		Label result = new Label();
		
		Button cont = new Button("Continue");
		
		content.getChildren().addAll(result);
		content.setAlignment(Pos.CENTER);
		pane.setCenter(content);
		
		if(player.getBossKill() == 4) {
			End end = new End();
			end.start(stage);
			return;
		}
		
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
				player.setLvl(player.getLvl() + 1);
				
				player.setXp(player.getXp() - xpToNextLvl);
				
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
								player.setHpMax((int)(Math.pow(1.2, player.getVit()) + (3.3 * player.getVit()) + 8));
								player.setHp(player.getHpMax());
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
								player.setMpMax((int)(Math.pow(1.2, player.getSrc()) + (3.3 * player.getSrc()) + 8));
								player.setMp(player.getMpMax());
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
									SceneHandler.loadMapScene(stage, player, dm, false);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						else if(toUpgrade.getValue().equals("") && player.getStr() == 20 && player.getMnd() == 20 && player.getVit() == 20 && player.getSrc() == 20) {
							try {
								SceneHandler.loadMapScene(stage, player, dm, false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
					
				});
			}
			else{
				cont.setOnAction(event->{
					try {
						SceneHandler.loadMapScene(stage, player, dm, false);
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
					SceneHandler.loadMapScene(stage, player, dm, true);
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
					SceneHandler.loadMapScene(stage, player, dm, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		content.getChildren().add(cont);
		
		Scene endCombatScene = new Scene(pane, 800, 600);
		endCombatScene.getStylesheets().add("game_styles.css");
		
		stage.setScene(endCombatScene);
		stage.show();
	}
	
	public static void loadShopScene(Stage stage, Player player, DisplayMap dm) throws Exception{
		BorderPane pane = new BorderPane();
		
		HBox goldDisplay = new HBox();
		Label nameDisplay = new Label(player.getName() + "'s Gold: ");
		Label goldAmtDisplay = new Label(String.valueOf(player.getGold()));
		
		goldDisplay.getChildren().addAll(nameDisplay, goldAmtDisplay);
		goldDisplay.setAlignment(Pos.CENTER_RIGHT);
		
		VBox info = new VBox();
		Label info1 = new Label("Entered Shop");
		Label info2 = new Label("");
		
		TableView<shopItem> itemsTable = new TableView<>();
		TableView<shopItem> toolsTable = new TableView<>();
		
		//potions
		int hpq = player.getHealthPotion();
		shopItem healthPotion = new shopItem("Health Potion", hpq, 0, 15);
		healthPotion.getButton().setOnAction(event->{
			if(player.getGold() >= healthPotion.getCost()) {
				if(player.getHealthPotion() < 10) {
					//add potion to items
					player.setHealthPotion(player.getHealthPotion() + 1);
					healthPotion.setQuantity(healthPotion.getQuantity() + 1);
					player.setGold(player.getGold() - healthPotion.getCost());
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("hp:" + player.getHealthPotion() + " " + healthPotion.getQuantity());
					itemsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Purchaced Health Potion. In Pouch: " + player.getHealthPotion());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot purchace more Health Potions. In Pouch: 10");
				}
				
			}
			else {
				//update info text
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to purchace: " + healthPotion.getName());
			}
			
		});
		
		shopItem greatHealthPotion = new shopItem("Great Health Potion", player.getGreatHealthPotion(), 0, 40);
		greatHealthPotion.getButton().setOnAction(event->{
			if(player.getGold() >= greatHealthPotion.getCost()) {
				if(player.getGreatHealthPotion() < 10) {
					//add potion to items
					player.setGreatHealthPotion(player.getGreatHealthPotion() + 1);
					greatHealthPotion.setQuantity(greatHealthPotion.getQuantity() + 1);
					player.setGold(player.getGold() - greatHealthPotion.getCost());
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("ghp:" + player.getGreatHealthPotion() + " " + greatHealthPotion.getQuantity());
					itemsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Purchaced Great Health Potion. In Pouch: " + player.getGreatHealthPotion());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot purchace more Great Health Potions. In Pouch: 10");
				}
				
			}
			else {
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to purchace: " + greatHealthPotion.getName());
			}
		});
		
		shopItem manaPotion = new shopItem("Mana Potion", player.getManaPotion(), 0, 15);
		manaPotion.getButton().setOnAction(event->{
			if(player.getGold() >= manaPotion.getCost()) {
				if(player.getManaPotion() < 10) {
					//add potion to items
					player.setManaPotion(player.getManaPotion() + 1);
					manaPotion.setQuantity(manaPotion.getQuantity() + 1);
					player.setGold(player.getGold() - manaPotion.getCost());
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("mp:" + player.getManaPotion() + " " + manaPotion.getQuantity());
					itemsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Purchaced Mana Potion. In Pouch: " + player.getManaPotion());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot purchace more Mana Potions. In Pouch: 10");
				}
				
			}
			else {
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to purchace: " + manaPotion.getName());
			}
		});
		
		shopItem greatManaPotion = new shopItem("Great Mana Potion", player.getGreatManaPotion(), 0, 40);
		greatManaPotion.getButton().setOnAction(event->{
			if(player.getGold() >= greatManaPotion.getCost()) {
				if(player.getGreatManaPotion() < 10) {
					//add potion to items
					player.setGreatManaPotion(player.getGreatManaPotion() + 1);
					greatManaPotion.setQuantity(greatManaPotion.getQuantity() + 1);
					player.setGold(player.getGold() - greatManaPotion.getCost());
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("gmp:" + player.getGreatManaPotion() + " " + greatManaPotion.getQuantity());
					itemsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Purchaced Great Mana Potion. In Pouch: " + player.getGreatManaPotion());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot purchace more Great Mana Potions. In Pouch: 10");
				}
				
			}
			else {
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to purchace: " + greatManaPotion.getName());
			}
		});
		
		//tools
		shopItem weapon = new shopItem("Weapon", 0, player.getWeapon(), (player.getWeapon() * 20));
		weapon.getButton().setOnAction(event->{
			if(player.getGold() >= weapon.getCost()) {
				if(player.getWeapon() < 5) {
					//add level to tool
					player.setWeapon(player.getWeapon() + 1);
					weapon.setLevel(weapon.getLevel() + 1);
					player.setGold(player.getGold() - weapon.getCost());
					weapon.setCost(weapon.getLevel() * 20);
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("w:" + player.getWeapon() + " " + weapon.getLevel());
					toolsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Upgraded weapon: Now Level: " + player.getWeapon());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot upgrade weapon higher. Level: 5");
				}
			}
			else {
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to upgrade: " + weapon.getName());
			}
		});
		
		shopItem attackSpell = new shopItem("Attack Spell", 0, player.getAtkSpell(), (player.getAtkSpell() * 20));
		attackSpell.getButton().setOnAction(event->{
			if(player.getGold() >= attackSpell.getCost()) {
				if(player.getAtkSpell() < 5) {
					//add level to tool
					player.setAtkSpell(player.getAtkSpell() + 1);
					attackSpell.setLevel(attackSpell.getLevel() + 1);
					player.setGold(player.getGold() - attackSpell.getCost());
					attackSpell.setCost(attackSpell.getLevel() * 20);
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("as:" + player.getAtkSpell() + " " + attackSpell.getLevel());
					toolsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Upgraded Attack Spell: Now Level: " + player.getAtkSpell());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot upgrade Attack Spell higher. Level: 5");
				}
			}
			else {
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to updgrade: " + attackSpell.getName());
			}
		});
		
		shopItem healSpell = new shopItem("Heal Spell", 0, player.getHealSpell(), (player.getHealSpell() * 20));
		healSpell.getButton().setOnAction(event->{
			if(player.getGold() >= healSpell.getCost()) {
				if(player.getHealSpell() < 5) {
					//add level to tool
					player.setHealSpell(player.getHealSpell() + 1);
					healSpell.setLevel(healSpell.getLevel() + 1);
					player.setGold(player.getGold() - healSpell.getCost());
					healSpell.setCost(healSpell.getLevel() * 20);
					goldAmtDisplay.setText(String.valueOf(player.getGold()));
					System.out.println("hp:" + player.getHealSpell() + " " + healSpell.getLevel());
					toolsTable.refresh();
					//update info text
					info2.setText(info1.getText());
					info1.setText("Upgraded Heal Spell: Now Level: " + player.getHealSpell());
				}
				else {
					//update info text
					info2.setText(info1.getText());
					info1.setText("Cannot upgrade Heal Spell higher. Level: 5");
				}
			}
			else {
				info2.setText(info1.getText());
				info1.setText("Insufficient funds to upgrade: " + healSpell.getName());
			}
		});
		
		//table for consumable items
		
		itemsTable.setEditable(false);
		itemsTable.setSelectionModel(null);
		
		//creating the columns for the itemsTable
		TableColumn<shopItem, String> nameICol = new TableColumn<>("Name:");
		nameICol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameICol.setSortable(false);
		
		TableColumn<shopItem, Integer> quantityICol = new TableColumn<>("Quantity:");
		quantityICol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		quantityICol.setSortable(false);
		
		TableColumn<shopItem, Integer> costICol = new TableColumn<>("Cost:");
		costICol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		costICol.setSortable(false);
		
		TableColumn<shopItem, Button> purchaceICol = new TableColumn<>("");
		purchaceICol.setCellValueFactory(new PropertyValueFactory<>("button"));
		purchaceICol.setSortable(false);
		
		itemsTable.getColumns().addAll(nameICol, quantityICol, costICol, purchaceICol);
		itemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		itemsTable.getItems().addAll(healthPotion, greatHealthPotion, manaPotion, greatManaPotion);
		
		
		//table for weapon and spells
		
		toolsTable.setEditable(false);
		toolsTable.setSelectionModel(null);
		
		//creating the columns for the toolsTable
		TableColumn<shopItem, String> nameTCol = new TableColumn<>("Name:");
		nameTCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameTCol.setSortable(false);
		
		TableColumn<shopItem, Integer> levelTCol = new TableColumn<>("Level:");
		levelTCol.setCellValueFactory(new PropertyValueFactory<>("level"));
		nameTCol.setSortable(false);
		
		TableColumn<shopItem, Integer> costTCol = new TableColumn<>("Cost:");
		costTCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		costTCol.setSortable(false);
		
		TableColumn<shopItem, Button> purchaceTCol = new TableColumn<>("");
		purchaceTCol.setCellValueFactory(new PropertyValueFactory<>("button"));
		purchaceTCol.setSortable(false);
		
		toolsTable.getColumns().addAll(nameTCol, levelTCol, costTCol, purchaceTCol);
		toolsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		toolsTable.getItems().addAll(weapon, attackSpell, healSpell);
		
		pane.setCenter(itemsTable);
		
		
		pane.setTop(new StackPane(new Label("Ye Olde Shoppe")));
		
		//bottom of pane
		VBox bottom = new VBox();
		
		
		
		info.getChildren().addAll(info1, info2);
		
		//buttons on bottom row
		Button leaveShopBtn = new Button("Leave Shop");
		
		Button toItemsTable = new Button("Items");
		Button toToolsTable = new Button("Tools");
		
		HBox buttons = new HBox();
		
		toItemsTable.setOnAction(event->{
			pane.setCenter(itemsTable);
		});
		
		toToolsTable.setOnAction(event->{
			pane.setCenter(toolsTable);
		});
        
		buttons.getChildren().addAll(leaveShopBtn, toItemsTable, toToolsTable, goldDisplay);
		
		bottom.getChildren().addAll(info, buttons);
		
		pane.setBottom(bottom);
		
		leaveShopBtn.setOnAction(event->{
			try {
				SceneHandler.loadMapScene(stage, player, dm, false);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Scene shopScene = new Scene(pane, 800, 600);
		
		shopScene.getStylesheets().add("game_styles.css");
		
		stage.setScene(shopScene);
		stage.show();
	}
	
	public static void loadStatusStage(Player player) throws Exception{
		Stage statusStage = new Stage();
		BorderPane pane = new BorderPane();
		
		int xpToNextLvl = (int)(Math.pow(1.1, (player.getLvl()-1)) + player.getLvl() + 20);
		
		VBox bigHeader = new VBox();
		HBox header = new HBox();
		
		VBox headerLeft = new VBox();
		headerLeft.getChildren().addAll(new Label("HP: " + player.getHp() + " / " + player.getHpMax()), new Label("MP: " + player.getMp() + " / " + player.getMpMax()));
		
		VBox headerRight = new VBox();
		headerRight.getChildren().addAll( new Label("Gold: " + player.getGold()), new Label("Level: " + player.getLvl()), new Label("XP: " + player.getXp() + " / " + xpToNextLvl));
		
		header.getChildren().addAll(headerLeft, headerRight);
		header.setAlignment(Pos.CENTER);
		
		bigHeader.getChildren().addAll(new Label(player.getName() + " the " + player.getArch() + "'s Status:"), header);
		bigHeader.setAlignment(Pos.CENTER);
		
		pane.setTop(bigHeader);
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
		
		Scene statusScene = new Scene(pane, 500, 500);
		
		statusScene.getStylesheets().add("game_styles.css");
		
		statusStage.setTitle("Player Status");
		statusStage.setScene(statusScene);
		statusStage.setResizable(false);
		statusStage.show();
	}
	
}
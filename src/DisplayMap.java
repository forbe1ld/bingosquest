import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DisplayMap implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Map map;
	private int playerX = 5; // Starting X position (row)
	private int playerY = 5; // Starting Y position (column)
	
	// Custom buttons for "Yes" and "No"
    private boolean isFirst = true;
    
    private Random random = new Random();

	public void start(Stage primaryStage, Player pl, DisplayMap dm, boolean toVillage) throws Exception 
	{
		BorderPane pane = new BorderPane();
		if(toVillage) {
			dm.playerX = 5;
			dm.playerY = 5;
		}
		if(isFirst) {
			// Initialize the map and place dungeons and village
	        initializeMap();
	        placeDungeonsAndVillage();
	        // Add a random area of water with a specified number of tiles (e.g., 10)
	        generateWaterPond(10); 
	        // Turn 30% of remaining grass tiles into encounter grass
	        generateEncounterGrass(0.3);
	        dm.isFirst = false;
		}
		
        // Set up the GridPane for displaying the map
        GridPane mapGrid = new GridPane();
        displayMapGrid(mapGrid);
        
        StackPane sPane = new StackPane();
        sPane.getChildren().add(new ImageView(new Image("img/MapDisplay.png", 435, 425, false, true)));
        sPane.getChildren().add(mapGrid);
        
     // Add the player marker as a circle at the initial position
        Circle player = new Circle(10, Color.RED);
     // Initial placement of the player
        mapGrid.add(player, playerY, playerX); 
        GridPane.setHalignment(player, HPos.CENTER);
        GridPane.setValignment(player, VPos.CENTER);

        pane.setLeft(sPane);
        
        VBox infoText = new VBox();
        Label info1 = new Label("");
        Label info2 = new Label("");
        infoText.getChildren().addAll(info1, info2);
        pane.setBottom(infoText);
        
        Button up = new Button("UP");
        up.setAlignment(Pos.CENTER);
        up.setOnAction(event->{
            movePlayer(-1, 0, mapGrid, player, primaryStage, pl, dm); // Move up
            info2.setText(info1.getText());
            info1.setText("Bingo moved north");
        });
        Button down = new Button("DOWN");
        down.setAlignment(Pos.CENTER);
        down.setOnAction(event->{
            movePlayer(1, 0, mapGrid, player, primaryStage, pl, dm); // Move down
            info2.setText(info1.getText());
            info1.setText("Bingo moved south");
        });
        Button left = new Button("LEFT");
        left.setAlignment(Pos.CENTER);
        left.setOnAction(event->{
            movePlayer(0, -1, mapGrid, player, primaryStage, pl, dm); // Move left
            info2.setText(info1.getText());
            info1.setText("Bingo moved west");
        });
        Button right = new Button("RIGHT");
        right.setAlignment(Pos.CENTER);
        right.setOnAction(event->{
            movePlayer(0, 1, mapGrid, player, primaryStage, pl, dm); // Move right
            info2.setText(info1.getText());
            info1.setText("Bingo moved east");
        });
        Button status = new Button("Status");
        status.setOnAction(event->{
        	try {
				SceneHandler.loadStatusStage(pl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        Button quit = new Button("Exit Game");
        quit.setOnAction(event -> {
        	
        	try {
        		SaveLoadManager.saveGame(pl, dm);
        		
        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            	alert.setTitle("Game Saved");
            	alert.setHeaderText(null);
            	alert.setContentText("Your progress has been saved successfully!");
            	
            	DialogPane dPane = alert.getDialogPane();
            	dPane.getStylesheets().add("game_styles.css");
            	dPane.getStyleClass().add("dialog-pane");
            	
            	alert.showAndWait();
        	}catch (Exception e) {
        		e.printStackTrace();
        		
        		Alert alert = new Alert(Alert.AlertType.ERROR);
        		alert.setTitle("Save Error");
        		alert.setHeaderText("Failed to Save Game");
        		alert.setContentText("An error occurred while saving.");
        		
        		DialogPane dPane = alert.getDialogPane();
        		dPane.getStylesheets().add("game_styles.css");
        		dPane.getStyleClass().add("dialog-pane");
        		
        		alert.showAndWait();
        	}
        });
		StackPane sPane2 = new StackPane();
		sPane2.getChildren().add(new ImageView(new Image("img/MainMenuScreenIcon.gif", 250, 250, false, true)));
		sPane2.setAlignment(Pos.CENTER_RIGHT);
		sPane2.setPadding(new Insets(0, 100, 0, 0));
		
        GridPane buttons = new GridPane();
        buttons.add(up, 1, 0);
        buttons.add(down, 1, 1);
        buttons.add(left, 1, 2);
        buttons.add(right, 1, 3);
        buttons.add(status, 1, 9);
        buttons.add(quit, 1, 10);
        buttons.setAlignment(Pos.TOP_RIGHT);
        buttons.setVgap(20);
        buttons.setPadding(new Insets(25, 100, 0, 100));
        
        sPane2.getChildren().add(buttons);
        
        sPane.setAlignment(Pos.TOP_LEFT);
        pane.setRight(sPane2);
        
        // Create and show the scene
        Scene scene = new Scene(pane, 800, 600);
        
        pane.setBottom(infoText);
        
        scene.getStylesheets().add("game_styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
	public void initializeMap()
	{
		 // Initialize a map with a specific size (e.g., 11x11)
        map = new Map(11);
	}
	
	public void placeDungeonsAndVillage() 
	{
	    // Create instances of Dungeon and Village
	    Dungeon dungeon = new Dungeon();
	    Village village = new Village();

	    // Place the village in the center
	    village.generateVillage(map, 5, 5);

	    // Store dungeon coordinates
	    int[][] dungeonCoords = new int[4][2];

	    // Place dungeons in the four corners and store their positions
	    dungeonCoords[0] = dungeon.generateDungeon(map, 1, 1);  // Top-left corner
	    dungeonCoords[1] = dungeon.generateDungeon(map, 7, 1);  // Top-right corner
	    dungeonCoords[2] = dungeon.generateDungeon(map, 1, 7);  // Bottom-left corner
	    dungeonCoords[3] = dungeon.generateDungeon(map, 7, 7);  // Bottom-right corner

	    // Call generatePaths with dungeon coordinates
	    generatePaths(dungeonCoords);
	}

	public void generatePaths(int[][] dungeonCoords) 
	{
	    Tile[][] grid = map.getGrid();

	    // Define the village location
	    int villageX = 5;
	    int villageY = 5;

	    for (int[] dungeon : dungeonCoords) 
	    {
	        int dungeonX = dungeon[0];
	        int dungeonY = dungeon[1];

	        // Generate the path to the dungeon
	        int x = villageX;
	        int y = villageY;

	        // Move horizontally towards the dungeon's x-coordinate
	        while (x != dungeonX) 
	        {
	            x += (dungeonX > x) ? 1 : -1;
	            if (x == dungeonX && y == dungeonY) break; // Stop if reached dungeon
	            
	            // Check bounds before setting tile
	            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) 
	            {
	                grid[x][y].setTileType(Tile.PATH);
	            }
	        }

	        // Move vertically towards the dungeon's y-coordinate
	        while (y != dungeonY) 
	        {
	            y += (dungeonY > y) ? 1 : -1;
	            if (x == dungeonX && y == dungeonY) break; // Stop if reached dungeon
	            
	            // Check bounds before setting tile
	            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) 
	            {
	                grid[x][y].setTileType(Tile.PATH);
	            }
	        }
	    }
	}

	public void generateWaterPond(int waterTileCount) 
	{
	    Tile[][] grid = map.getGrid();

	    int startX, startY;
	    // Find a starting position for the pond that is `GRASS` and not on the edge
	    do 
	    {
	        startX = random.nextInt(grid.length - 2) + 1; // Range from 1 to grid.length - 2
	        startY = random.nextInt(grid[0].length - 2) + 1; // Range from 1 to grid[0].length - 2
	    } while (!grid[startX][startY].getTileType().equals(Tile.GRASS));

	    // Use a list to keep track of possible expansion tiles
	    List<int[]> pondTiles = new ArrayList<>();
	    pondTiles.add(new int[]{startX, startY});
	    int placedWaterTiles = 0;

	    while (placedWaterTiles < waterTileCount && !pondTiles.isEmpty()) 
	    {
	        // Pick a random tile from the pond to expand from
	        int[] tile = pondTiles.remove(random.nextInt(pondTiles.size()));
	        int x = tile[0];
	        int y = tile[1];

	        // Only place water if it's still GRASS (avoid overwriting other features)
	        if (grid[x][y].getTileType().equals(Tile.GRASS)) 
	        {
	            grid[x][y].setTileType(Tile.WATER);
	            placedWaterTiles++;
	        }

	        // Add neighboring tiles to the list to expand the pond, only if they are interior tiles
	        if (x > 1 && grid[x - 1][y].getTileType().equals(Tile.GRASS)) 
	        {
	            pondTiles.add(new int[]{x - 1, y});
	        }
	        if (x < grid.length - 2 && grid[x + 1][y].getTileType().equals(Tile.GRASS)) 
	        {
	            pondTiles.add(new int[]{x + 1, y});
	        }
	        if (y > 1 && grid[x][y - 1].getTileType().equals(Tile.GRASS)) 
	        {
	            pondTiles.add(new int[]{x, y - 1});
	        }
	        if (y < grid[0].length - 2 && grid[x][y + 1].getTileType().equals(Tile.GRASS)) 
	        {
	            pondTiles.add(new int[]{x, y + 1});
	        }
	    }
	}
	
	public void generateEncounterGrass(double percentage) 
	{
	    Tile[][] grid = map.getGrid();
	    Random random = new Random();

	    // Count all GRASS tiles
	    int totalGrassTiles = 0;
	    for (int i = 0; i < grid.length; i++) 
	    {
	        for (int j = 0; j < grid[i].length; j++) 
	        {
	            if (grid[i][j].getTileType().equals(Tile.GRASS)) 
	            {
	                totalGrassTiles++;
	            }
	        }
	    }

	    // Calculate the target number of ENCGRASS tiles
	    int targetEncGrassTiles = (int) (totalGrassTiles * percentage);

	    int placedEncGrassTiles = 0;

	    // Randomly convert GRASS tiles to ENCGRASS until reaching the target count
	    while (placedEncGrassTiles < targetEncGrassTiles) 
	    {
	        int x = random.nextInt(grid.length);
	        int y = random.nextInt(grid[0].length);

	        if (grid[x][y].getTileType().equals(Tile.GRASS)) 
	        {
	            grid[x][y].setTileType(Tile.ENCGRASS);
	            placedEncGrassTiles++;
	        }
	    }
	}
	
	public void movePlayer(int dx, int dy, GridPane mapGrid, Circle player, Stage stage, Player pl, DisplayMap dm) 
	{
	    int newX = playerX + dx;
	    int newY = playerY + dy;
	    pl.setxPos(playerX);
		pl.setyPos(playerY);

	    // Ensure the new position is within grid bounds
	    if (newX >= 0 && newX < map.getGrid().length && newY >= 0 && newY < map.getGrid()[0].length) 
	    {
	        Tile[][] grid = map.getGrid();
	        String destinationTileType = grid[newX][newY].getTileType();

	        // Prevent movement onto WATER tiles
	        if (destinationTileType.equals(Tile.WATER)) 
	        {
	            System.out.println("Cannot move to WATER tile at (" + newX + ", " + newY + ")");
	            return;
	        }

	        // Village confirmation dialog
	        if (destinationTileType.equals(Tile.VILLAGE)) 
	        {
	            if (!showVillageConfirmationDialog()) 
	            {
	            	
	                return;
	            }
	            else {
	            	try {
						SceneHandler.loadShopScene(stage, pl, dm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }

	        // Dungeon confirmation dialog
	        if (destinationTileType.equals(Tile.DUNGEON)) 
	        {
	            if (!showDungeonConfirmationDialog()) 
	            {
	            	
	                return;
	            }
	            else {
	            	try {	
						SceneHandler.loadCombatScene(stage, pl, true, dm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }

	        // Monster encounter alert for ENCGRASS tiles
	        if (destinationTileType.equals(Tile.ENCGRASS)) 
	        {
	            showEncounterAlert();
	            try {
					SceneHandler.loadCombatScene(stage, pl, false, dm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        // Move the player if the destination is valid
	        playerX = newX;
	        playerY = newY;

	        // Update the player's position on the grid
	        mapGrid.getChildren().remove(player);
	        mapGrid.add(player, playerY, playerX);

	        // Center the player within the new cell
	        GridPane.setHalignment(player, HPos.CENTER);
	        GridPane.setValignment(player, VPos.CENTER);
	    }
	}

	public boolean showVillageConfirmationDialog() 
	{
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Enter Village");
	    alert.setHeaderText("You are about to enter the village.");
	    alert.setContentText("Do you want to enter the village?");

	    // Custom buttons for "Yes" and "No"
	    ButtonType yesBtn = new ButtonType("Yes");
	    ButtonType noBtn = new ButtonType("No");
	    alert.getButtonTypes().setAll(yesBtn, noBtn);

	    // Set the style sheet for the Alerts
	    DialogPane dPane = alert.getDialogPane();
	    dPane.getStylesheets().add("game_styles.css");
	    dPane.getStylesheets().add("dialog-pane");
	    
	    // Access the stage to handle the close request
	    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    stage.setOnCloseRequest(event -> alert.setResult(noBtn)); // Treat "X" as "No"

	    // Show the dialog and capture the user's choice
	    Optional<ButtonType> result = alert.showAndWait();
	    return result.isPresent() && result.get() == yesBtn;
	}
	
	public boolean showDungeonConfirmationDialog() 
	{
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Enter Dungeon");
	    alert.setHeaderText("You are about to enter a dungeon.");
	    alert.setContentText("Do you want to enter the dungeon?");

	    // Custom buttons for "Yes" and "No"
	    ButtonType yesBtn = new ButtonType("Yes");
	    ButtonType noBtn = new ButtonType("No");
	    alert.getButtonTypes().setAll(yesBtn, noBtn);
	    
	    // Set the style sheet for the Alerts
	    DialogPane dPane = alert.getDialogPane();
	    dPane.getStylesheets().add("game_styles.css");
	    dPane.getStylesheets().add("dialog-pane");

	    // Access the stage to handle the close request
	    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    stage.setOnCloseRequest(event -> alert.setResult(noBtn)); // Treat "X" as "No"

	    // Show the dialog and capture the user's choice
	    Optional<ButtonType> result = alert.showAndWait();
	    return result.isPresent() && result.get() == yesBtn;
	}
	
	public void showEncounterAlert() 
	{
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Monster Encounter!");
	    alert.setHeaderText("You've encountered a monster!");
	    alert.setContentText("Prepare for battle!");
	    
	    //set the style sheet for the Alerts
	    DialogPane dPane = alert.getDialogPane();
	    dPane.getStylesheets().add("game_styles.css");
	    dPane.getStylesheets().add("dialog-pane");
	    
	    // Show the alert and wait for the player to close it
	    alert.showAndWait();
	}

	// Method to display the map on the GridPane
    public void displayMapGrid(GridPane mapGrid) 
    {
        Tile[][] grid = map.getGrid();
        for (int i = 0; i < grid.length; i++) 
        {
            for (int j = 0; j < grid[i].length; j++) 
            {
                // Create a rectangle for each tile
               // Rectangle tile = new Rectangle(35, 35);
            	ImageView tile = new ImageView();
                String tileType = grid[i][j].getTileType();

                // Set color based on tile type
                switch (tileType) {
                    case Tile.DUNGEON:
                        //tile.setFill(Color.DARKGRAY); // Dungeon color
                    	tile.setImage(new Image("img/DungeonTile.png", 35, 35, false, true));
                        break;
                    case Tile.VILLAGE:
                        //tile.setFill(Color.BROWN); // Village color
                    	tile.setImage(new Image("img/VillageTile.png", 35, 35, false, true));
                        break;
                    case Tile.PATH:
                       // tile.setFill(Color.TAN); // Color for path tiles
                    	tile.setImage(new Image("img/PathTile.png", 35, 35, false, true));
                        break;
                    case Tile.WATER:
                    	//tile.setFill(Color.AQUA);
                    	tile.setImage(new Image("img/WaterTile.png", 35, 35, false, true));
                    	break;
                    case Tile.ENCGRASS:
                    	//tile.setFill(Color.WHITE);
                    	tile.setImage(new Image("img/TileGrass.png", 35, 35, false, true));
                    	break;
                    default:
                       // tile.setFill(Color.LIMEGREEN); // Grass color
                    	tile.setImage(new Image("img/TileGrass.png", 35, 35, false, true));
                        break;
                }

                // Add the rectangle to the grid at the correct position
                mapGrid.add(tile, j, i);
            }
        }
        
    }

}
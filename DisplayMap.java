import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DisplayMap extends Application 
{
	private Map map;
	private int playerX = 5; // Starting X position (row)
	private int playerY = 5; // Starting Y position (column)
	
	// Custom buttons for "Yes" and "No"
    private ButtonType yesBtn = new ButtonType("Yes");
    private ButtonType noBtn = new ButtonType("No");
    
    private Random random = new Random();

	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		// Initialize the map and place dungeons and village
        initializeMap();
        placeDungeonsAndVillage();
        // Add a random area of water with a specified number of tiles (e.g., 10)
        generateWaterPond(10); 
        // Turn 30% of remaining grass tiles into encounter grass
        generateEncounterGrass(0.2);

        // Set up the GridPane for displaying the map
        GridPane mapGrid = new GridPane();
        displayMapGrid(mapGrid);
        
     // Add the player marker as a circle at the initial position
        Circle player = new Circle(10, Color.RED);
     // Initial placement of the player
        mapGrid.add(player, playerY, playerX); 
        GridPane.setHalignment(player, HPos.CENTER);
        GridPane.setValignment(player, VPos.CENTER);

        
        mapGrid.setAlignment(Pos.CENTER);

        // Create and show the scene
        Scene scene = new Scene(mapGrid, 300, 300);
        
        // Add key event handler for player movement
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) 
            {
                case UP:
                    movePlayer(-1, 0, mapGrid, player); // Move up
                    break;
                case DOWN:
                    movePlayer(1, 0, mapGrid, player); // Move down
                    break;
                case LEFT:
                    movePlayer(0, -1, mapGrid, player); // Move left
                    break;
                case RIGHT:
                    movePlayer(0, 1, mapGrid, player); // Move right
                    break;
            }
        });
        
        primaryStage.setTitle("Map Display");
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
	    for (int i = 0; i < grid.length; i++) {
	        for (int j = 0; j < grid[i].length; j++) {
	            if (grid[i][j].getTileType().equals(Tile.GRASS)) 
	            {
	                totalGrassTiles++;
	            }
	        }
	    }

	    // Calculate the target number of ENCGRASS tiles
	    int targetEncGrassTiles = (int) (totalGrassTiles * percentage);
	    int placedEncGrassTiles = 0;

	    // Place ENCGRASS tiles ensuring no adjacent ENCGRASS tiles
	    while (placedEncGrassTiles < targetEncGrassTiles) 
	    {
	        int x = random.nextInt(grid.length);
	        int y = random.nextInt(grid[0].length);

	        if (grid[x][y].getTileType().equals(Tile.GRASS) && !hasAdjacentEncGrass(grid, x, y)) 
	        {
	            grid[x][y].setTileType(Tile.ENCGRASS);
	            placedEncGrassTiles++;
	        }
	    }
	}
	
	private boolean hasAdjacentEncGrass(Tile[][] grid, int x, int y) 
	{
	    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; // Row offsets: top-left, top, top-right, etc.
	    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1}; // Column offsets

	    for (int i = 0; i < 8; i++) 
	    {
	        int newX = x + dx[i];
	        int newY = y + dy[i];

	        // Check bounds and tile type
	        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) 
	        {
	            if (grid[newX][newY].getTileType().equals(Tile.ENCGRASS)) 
	            {
	                return true; // Found an adjacent (or diagonal) ENCGRASS tile
	            }
	        }
	    }
	    return false; // No adjacent (or diagonal) ENCGRASS tiles found
	}
	
	public void movePlayer(int dx, int dy, GridPane mapGrid, Circle player) 
	{
	    int newX = playerX + dx;
	    int newY = playerY + dy;

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
	        }

	        // Dungeon confirmation dialog
	        if (destinationTileType.equals(Tile.DUNGEON)) 
	        {
	            if (!showDungeonConfirmationDialog()) 
	            {
	                return;
	            }
	        }

	        // Monster encounter alert for ENCGRASS tiles
	        if (destinationTileType.equals(Tile.ENCGRASS)) 
	        {
	            showEncounterAlert();
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
                Rectangle tile = new Rectangle(25, 25);
                String tileType = grid[i][j].getTileType();

                // Set color based on tile type
                switch (tileType) {
                    case Tile.DUNGEON:
                        tile.setFill(Color.DARKGRAY); // Dungeon color
                        break;
                    case Tile.VILLAGE:
                        tile.setFill(Color.BROWN); // Village color
                        break;
                    case Tile.PATH:
                        tile.setFill(Color.TAN); // Color for path tiles
                        break;
                    case Tile.WATER:
                    	tile.setFill(Color.AQUA);
                    	break;
                    case Tile.ENCGRASS:
                    	tile.setFill(Color.WHITE);
                    	break;
                    default:
                        tile.setFill(Color.LIMEGREEN); // Grass color
                        break;
                }

                // Add the rectangle to the grid at the correct position
                mapGrid.add(tile, j, i);
            }
        }
        
    }

}

import java.io.Serializable;

public class Tile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    // Constants representing different types of tiles
    public static final String GRASS = "GRASS";
    public static final String PATH = "PATH";
    public static final String DUNGEON = "DUNGEON";
    public static final String VILLAGE = "VILLAGE";
    public static final String WATER = "WATER";
    public static final String ENCGRASS = "ENCGRASS";

    // Attributes of the Tile class
    private String type;       // Type of the tile (e.g., GRASS, PATH)
    private String color;      // Visual color of the tile (for display purposes)
    private boolean isWalkable; // Indicates if the tile can be walked on
    private boolean hasEncounter; // Indicates if the tile has a special encounter (e.g., dungeon)

    /**
     * Sets the type of the tile and adjusts its properties based on the type.
     * @param type The type of tile, such as GRASS, PATH, etc.
     */
    public void setTileType(String type) {
        this.type = type; // Set the tile type
        
        // Adjust tile properties based on its type
        switch (type) {
            case GRASS:
                color = "limegreen";
                isWalkable = true;
                hasEncounter = false;
                break;
            case PATH:
                color = "tan";
                isWalkable = true;
                hasEncounter = false;
                break;
            case DUNGEON:
                color = "darkGray";
                isWalkable = true;
                hasEncounter = true; // Prompt the user to enter the dungeon or leave
                break;
            case VILLAGE:
                color = "brown";
                isWalkable = true;
                hasEncounter = true; // Prompt the user to enter the village or leave
                break;
            case WATER:
                color = "aqua";
                isWalkable = false; // Not walkable (e.g., water tile)
                hasEncounter = false;
                break;
            case ENCGRASS:
                color = "white";
                isWalkable = true; 
                hasEncounter = true; // Trigger an encounter when stepped on
                break;
            default:
                color = "unknown"; // Set color to "unknown" for undefined types
                isWalkable = false;
                hasEncounter = false;
                break;
        }
    }
    
    /**
     * Returns the type of the tile.
     * @return The type of tile (e.g., GRASS, PATH, DUNGEON, VILLAGE, etc.).
     */
    public String getTileType() 
    {
        return type;
    }

    /**
     * Checks if the tile is walkable.
     * @return true if the tile can be walked on, false otherwise.
     */
    public boolean isWalkable() 
    {
        return isWalkable;
    }

    /**
     * Checks if the tile has an encounter.
     * @return true if there is an encounter on this tile, false otherwise.
     */
    public boolean hasEncounter() 
    {
        return hasEncounter;
    }

    /**
     * Displays information about the tile type and any encounter on the tile.
     */
    public void displayTileInfo() 
    {
        System.out.println("Tile type: " + type + ", Color: " + color);
        if (hasEncounter) 
        {
            System.out.println("Encounter available!"); // Display encounter info if present
        }
    }

}

public class Map 
{
    private Tile[][] grid; // 2D array of Tiles representing the game map

    // Constructor to initialize the grid with a default tile type (e.g., GRASS)
    public Map(int gridSize) 
    {
        grid = new Tile[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) 
        {
            for (int j = 0; j < gridSize; j++) 
            {
                grid[i][j] = new Tile();
                grid[i][j].setTileType(Tile.GRASS); // Set default type to GRASS
            }
        }
    }

    // Method to get the grid
    public Tile[][] getGrid() 
    {
        return grid;
    }

    // Method to display the grid (for debugging or visualization)
    public void displayGrid() 
    {
        for (int i = 0; i < grid.length; i++) 
        {
            for (int j = 0; j < grid[i].length; j++) 
            {
                System.out.print(grid[i][j].getTileType().equals(Tile.DUNGEON) ? "[D]" :
                                 grid[i][j].getTileType().equals(Tile.VILLAGE) ? "[V]" : "[ ]");
            }
            System.out.println();
        }
    }
    
    public static void regenerateMonsters(Tile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Tile tile = grid[i][j];
                if (tile.getTileType().equals(Tile.ENCGRASS)) {
                    if (tile.getMonster() != null) {
                        tile.getMonster().setHp(tile.getMonster().getHpMax()); // Reset HP
                    } else {
                        // Create a new monster if none exists
                        Enemy newMonster = EnemyCatalog.enemyFromCatalog(1); // Adjust catalog ID as needed
                        tile.setMonster(newMonster);
                    }
                }
            }
        }
    }
}

/*
package com.cmu;

import java.util.Random;

public class Dungeon 
{
	private Tile[][] grid; // 2D array of Tiles representing the game map

	// Constructor to initialize the grid
    public Dungeon(int gridSize) {
        grid = new Tile[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new Tile(); // Initialize each tile
                grid[i][j].setTileType(Tile.GRASS); // Set all tiles to grass by default
            }
        }
    }
	
	public void generateDungeon(int x, int y)
	{
		 if (x < grid.length && y < grid[0].length) // Ensure we're within bounds
		 { 
	            grid[x][y].setTileType(Tile.DUNGEON); // Set the specified tile to a dungeon
	     }
	}
	
	public int[][] getRandomPosition()
	{
		// 4 dungeons, each with x and y coordinates
        int[][] dungeonCoords = new int[4][2];
        Random random = new Random();

        // Initialize each dungeon's coordinates in its respective corner
        dungeonCoords[0][0] = random.nextInt(5) + 2;       // x for top-left corner (2 to 6)
        dungeonCoords[0][1] = random.nextInt(5) + 2;       // y for top-left corner (2 to 6)

        dungeonCoords[1][0] = random.nextInt(5) + 14;      // x for top-right corner (14 to 18)
        dungeonCoords[1][1] = random.nextInt(5) + 2;       // y for top-right corner (2 to 6)

        dungeonCoords[2][0] = random.nextInt(5) + 2;       // x for bottom-left corner (2 to 6)
        dungeonCoords[2][1] = random.nextInt(5) + 14;      // y for bottom-left corner (14 to 18)

        dungeonCoords[3][0] = random.nextInt(5) + 14;      // x for bottom-right corner (14 to 18)
        dungeonCoords[3][1] = random.nextInt(5) + 14;      // y for bottom-right corner (14 to 18)

        // Place the dungeons on the grid using generateDungeon
        for (int i = 0; i < dungeonCoords.length; i++) 
        {
            generateDungeon(dungeonCoords[i][0], dungeonCoords[i][1]);
        }
        
        return dungeonCoords;
	}
	
	public void displayGrid() 
	{
        for (int i = 0; i < grid.length; i++) 
        {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].hasEncounter() ? "[D]" : "[ ]"); // 'D' for dungeon tiles
            }
            System.out.println();
        }
    }

}
*/
/*
package com.cmu;

import java.util.Random;

public class Dungeon {
    private Tile[][] grid; // 2D array of Tiles representing the game map

    // Constructor to initialize the grid
    public Dungeon(int gridSize) {
        grid = new Tile[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new Tile(); // Initialize each tile
                grid[i][j].setTileType(Tile.GRASS); // Set all tiles to grass by default
            }
        }
        
        // Place the village in the center (2x2 area)
        placeVillage();
    }

    // Method to place the 2x2 village in the center
    private void placeVillage() {
        grid[5][5].setTileType(Tile.VILLAGE);
    }

    // Method to generate a dungeon at a specific location (single tile within a corner)
    public void generateDungeon(int x, int y) {
        if (x < grid.length && y < grid[0].length) { // Ensure we're within bounds
            grid[x][y].setTileType(Tile.DUNGEON); // Set the specified tile to a dungeon
        }
    }

    // Method to get random positions for dungeons and place them
    public int[][] getRandomPosition() {
        int[][] dungeonCoords = new int[4][2];
        Random random = new Random();

        // Set random positions for each dungeon within a centered 3x3 area in each 10x10 corner
        dungeonCoords[0][0] = random.nextInt(3) + 1;       // x for top-left corner (1 to 3)
        dungeonCoords[0][1] = random.nextInt(3) + 1;       // y for top-left corner (1 to 3)

        dungeonCoords[1][0] = random.nextInt(3) + 7;       // x for top-right corner (7 to 9)
        dungeonCoords[1][1] = random.nextInt(3) + 1;       // y for top-right corner (1 to 3)

        dungeonCoords[2][0] = random.nextInt(3) + 1;       // x for bottom-left corner (1 to 3)
        dungeonCoords[2][1] = random.nextInt(3) + 7;       // y for bottom-left corner (7 to 9)

        dungeonCoords[3][0] = random.nextInt(3) + 7;       // x for bottom-right corner (7 to 9)
        dungeonCoords[3][1] = random.nextInt(3) + 7;       // y for bottom-right corner (7 to 9)

        // Place the dungeons on the grid using generateDungeon
        for (int i = 0; i < dungeonCoords.length; i++) {
            generateDungeon(dungeonCoords[i][0], dungeonCoords[i][1]);
        }

        return dungeonCoords;
    }

    // Optional: Display the grid for debugging
    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].hasEncounter()) {
                    System.out.print(grid[i][j].getTileType().equals(Tile.DUNGEON) ? "[D]" : "[V]"); // 'D' for dungeon, 'V' for village
                } else {
                    System.out.print("[ ]"); // Empty space
                }
            }
            System.out.println();
        }
    }
}
*/

package com.cmu;

import java.util.Random;

public class Dungeon 
{
    private Random random = new Random();

    // Method to place a single dungeon tile within a specified 3x3 area in each corner
    public int[] generateDungeon(Map map, int startX, int startY) {
        Tile[][] grid = map.getGrid();

        // Ensure that the dungeon stays within the specified 3x3 area
        // Limit x and y to startX + [0, 1, 2] and startY + [0, 1, 2]
        int x = startX + random.nextInt(3);  // Generates x in range [startX, startX + 2]
        int y = startY + random.nextInt(3);  // Generates y in range [startY, startY + 2]

        // Set the tile at (x, y) as a dungeon
        grid[x][y].setTileType(Tile.DUNGEON);

        // Return the dungeon coordinates
        return new int[]{x, y};
    }


}


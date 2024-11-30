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


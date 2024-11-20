package com.cmu;

public class Village 
{
    // Method to place the village marker at a specified tile
    public void generateVillage(Map map, int x, int y) 
    {
        Tile[][] grid = map.getGrid();

        if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) 
        { // Ensure within bounds
            grid[x][y].setTileType(Tile.VILLAGE);
        }
    }
}

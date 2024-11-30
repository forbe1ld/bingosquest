import java.io.*;
import java.util.Scanner;

public class SaveLoadManager {

    private static final String SAVE_FILE = "savefile.txt";

    /**
     * Saves the player's state and inventory to a save file.
     * 
     * @param player    The player object containing the player's data.
     * @param inventory The inventory object containing the player's items.
     */
    public static void saveGame(Player player, Inventory inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            // Save Player Data
            writer.println(player.getName());
            writer.println(player.lvl);  // Player level
            writer.println(player.gold); // Player coins
            writer.println(player.hp);   // Player HP

            // Save Inventory Data
            writer.println(inventory.weapon != null ? inventory.weapon.name : "null");
            writer.println(inventory.shield != null ? inventory.shield.name : "null");
            writer.println(inventory.food != null ? inventory.food.name : "null");
            writer.println(inventory.aSpell != null ? inventory.aSpell.name : "null");
            writer.println(inventory.hSpell != null ? inventory.hSpell.name : "null");

            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    /**
     * Loads the player's state and inventory from a save file.
     * 
     * @return An array containing the loaded Player and Inventory objects.
     */
    public static Object[] loadGame() {
        try (Scanner scanner = new Scanner(new File(SAVE_FILE))) {
            // Load Player Data
            String name = scanner.nextLine();
            int level = Integer.parseInt(scanner.nextLine());
            int gold = Integer.parseInt(scanner.nextLine());
            int hp = Integer.parseInt(scanner.nextLine());

            Player player = new PlayerBuilder().setname(name).build();
            player.lvl = level;
            player.gold = gold;
            player.hp = hp;

            // Load Inventory Data
            String weaponName = scanner.nextLine();
            String shieldName = scanner.nextLine();
            String foodName = scanner.nextLine();
            String attackSpellName = scanner.nextLine();
            String healingSpellName = scanner.nextLine();

            Inventory inventory = new Inventory(player, null, null, null);
            inventory.weapon = !"null".equals(weaponName) ? new Weapon(weaponName, 1, "Unknown", level) : null;
            inventory.shield = !"null".equals(shieldName) ? new Shield(shieldName, 1.0, level) : null;
            inventory.food = !"null".equals(foodName) ? new Food(foodName, 10, "Unknown", level, 5) : null;
            inventory.aSpell = !"null".equals(attackSpellName) ? new AttackSpell(attackSpellName, level, 20, 10) : null;
            inventory.hSpell = !"null".equals(healingSpellName) ? new HealingSpell(healingSpellName, level, 25, 15) : null;

            System.out.println("Game loaded successfully!");
            return new Object[] { player, inventory };
        } catch (FileNotFoundException e) {
            System.err.println("Save file not found. Start a new game or create a save.");
        } catch (Exception e) {
            System.err.println("Failed to load game: " + e.getMessage());
        }

        return null;
    }
}

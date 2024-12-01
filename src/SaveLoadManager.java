import java.io.*;
import javafx.stage.Stage;

public class SaveLoadManager {
	// Final path for save file
    private static final String SAVE_FILE = "game_save.dat";

    /**
     * Saves the player's stats and map content to a file
     *
     * @param player - The Player object containing the player's data
     * @param map - The Map containing the current map configuration
     */
    public static void saveGame(Player player, DisplayMap displayMap) {
    	// Utilize ObjectOutputStream to output to a save file
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            // Save the player and map data
        	stream.writeObject(player);
        	stream.writeObject(displayMap);
        	// Success message
            System.out.println("Game saved successfully!");
            System.exit(0);
        } catch (IOException e) {
        	// Error message
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    /**
     * Loads the player's stats and map content from a file
     *
     * @param tempStage - The main stage of the JavaFX application.
     */
    public static void loadGame(Stage tempStage) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            // Load Player and DisplayMap data
            Player player = (Player) ois.readObject();
            DisplayMap displayMap = (DisplayMap) ois.readObject();
            
            // Sucecss message
            System.out.println("Game loaded successfully!");

            // Start the game with the loaded DisplayMap
            try {
				displayMap.start(tempStage, player, displayMap, false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch (FileNotFoundException e) {
        	// Error messages
            System.err.println("Save file not found. Start a new game or create a save.");
            showErrorAlert("Save File Not Found", "Please create a save before loading.");
        } catch (IOException | ClassNotFoundException e) {
        	// More error messages...
            System.err.println("Failed to load game: " + e.getMessage());
            showErrorAlert("Load Error", "An error occurred while loading the save file.");
        }
    }

    /**
     * Displays an error
     *
     * @param title The title of the alert.
     * @param message The message content of the alert.
     */
    private static void showErrorAlert(String title, String message) {
    	// Make the alert and set its parameters
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Style the alert with the style sheet
        javafx.scene.control.DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("game_styles.css");
        dialogPane.getStyleClass().add("dialog-pane");

        alert.showAndWait();
    }
}

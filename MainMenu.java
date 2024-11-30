import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the root pane
        BorderPane root = new BorderPane();
        root.setId("main-menu");

        // Title Label
        Label title = new Label("BINGO'S QUEST");
        title.setId("game-title");

        // Buttons
        Button btnStartGame = new Button("Start Game");
        Button btnLoadGame = new Button("Load Game");
        Button btnCredits = new Button("Credits");

        // Add functionality to buttons
        btnStartGame.setOnAction(e -> showScrollingStory(primaryStage));
        btnLoadGame.setOnAction(e -> SaveLoadManager.loadGame());
        btnCredits.setOnAction(e -> CreditsScene.showCredits(primaryStage));

        // Add buttons to a VBox
        VBox buttonBox = new VBox(20, btnStartGame, btnLoadGame, btnCredits);
        buttonBox.setAlignment(Pos.CENTER);

        // Set the title and buttons in the layout
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(buttonBox);

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("game_styles.css");

        // Primary stage setup
        primaryStage.setTitle("Bingo's Quest - Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showScrollingStory(Stage primaryStage) {
        // Show the scrolling story
        ScrollingTextIntro intro = new ScrollingTextIntro();
        intro.showIntro(primaryStage, () -> {
            // After story, start the Driver
            Driver driver = new Driver();
            try {
                driver.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

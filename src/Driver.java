import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a main-menu pane
        BorderPane pane = new BorderPane();
        pane.setId("main-menu");

        // Create the game-title, and format it correctly
        Label title = new Label("BINGO'S QUEST");
        title.setId("game-title");
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(50, 0, 20, 0));
     

        // All the necessary buttons for game functionality
        Button btnStart = new Button("Start Game");
        Button btnLoad = new Button("Load Game");
        Button btnCredit = new Button("Credits");
        
        // Set the appropriate actions for each button
        btnStart.setOnAction(e -> showIntro(primaryStage));
        btnLoad.setOnAction(e -> SaveLoadManager.loadGame(primaryStage));
        btnCredit.setOnAction(e -> Credits.showCredits(primaryStage));

        // Create a VBox for the btn's
        VBox vBox = new VBox(20, btnStart, btnLoad, btnCredit);
        vBox.setAlignment(Pos.CENTER);

        // Create a stackPane for the main menu background graphic
        StackPane sPane = new StackPane();
        sPane.getChildren().addAll(new ImageView(new Image("img/MainMenuScreenIcon.gif")), vBox);
        
        // Align everything properly in the main pane
        pane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setCenter(sPane);

        // Create and format the main scene
        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add("game_styles.css");

        // Show and set the proper configs for the primaryStage
        primaryStage.setTitle("Bingo's Quest");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void showIntro(Stage primaryStage) {
        // Call the scrolling introduction and display properly
        Intro scrIntro = new Intro();
        scrIntro.showIntro(primaryStage, () -> {
        	
            // After the intro finishes, start the game
            try {
            	Player pl = new PlayerBuilder().build();
            	DisplayMap dm = new DisplayMap();
                SceneHandler.loadMapScene(primaryStage, pl, dm, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
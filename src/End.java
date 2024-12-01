import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class End extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Start the end screen
        showEnd(primaryStage, () -> {
            try {
                // If Restart is clicked, go back to the Main Menu
                new Driver().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void showEnd(Stage primaryStage, Runnable onRestart) {
        // Create and set the main pane
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;"); // Set black background

        // Create the text to be scrolled
        Text end = new Text(
                "Congratulations!\n\n" +
                "Bingo's journey has come to an end.\n\n" +
                "Through countless battles and challenges,\n" +
                "Bingo's bravery and determination prevailed.\n\n" +
                "The monsters have been vanquished, the land is safe,\n" +
                "and the secrets of the forgotten dungeons\n" +
                "have been uncovered.\n\n" +
                "The people of the land will forever\n" +
                "remember the name Bingo, the Hero.\n\n\n" +
                "Thank you for playing Bingo's Quest!\n\n\n" +
                "THE END"
        );
        
        // Format the end text correctly
        end.setStyle("-fx-font-family: 'alagard'; -fx-font-size: 28; -fx-fill: white;");
        end.setWrappingWidth(800);
        end.setLayoutX(15);
        end.setLayoutY(600); // Start below the visible window

        // Add the text to the pane
        pane.getChildren().add(end);

        // Set up a scrolling animation similar to the intro
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), event -> {
        	end.setLayoutY(end.getLayoutY() - 1);
        }));
        
        // Configure the animation timing
        timeline.setCycleCount(1150); // Adjust cycle count based on text length
        timeline.setOnFinished(e -> showEndButtons(primaryStage, onRestart));
        timeline.play();

        // Create the main scene, configure it, and set the stage
        Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add("game_styles.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void showEndButtons(Stage primaryStage, Runnable onRestart) {
        // VBox for buttons
        VBox btnBox = new VBox(20);
        btnBox.setAlignment(Pos.CENTER);

        // Create the two necessary buttons
        Button restart = new Button("Restart");
        Button exit = new Button("Exit Game");

        // Set the actions for restart and exit
        restart.setOnAction(event -> onRestart.run());
        exit.setOnAction(event -> System.exit(0));

        // Set style for each of the buttons
        restart.setStyle("-fx-font-family: 'alagard'; -fx-font-size: 18;");
        exit.setStyle("-fx-font-family: 'alagard'; -fx-font-size: 18;");

        // Add buttons to VBox
        btnBox.getChildren().addAll(restart, exit);

        // Create a border pane for better managmeent
        BorderPane bPane = new BorderPane();
        bPane.setStyle("-fx-background-color: black;");
        bPane.setCenter(btnBox);

        // Create and set end scene
        Scene end = new Scene(bPane, 800, 600);
        end.getStylesheets().add("game_styles.css");
        primaryStage.setScene(end);
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}

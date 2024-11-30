import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScrollingTextIntro {

    public void showIntro(Stage primaryStage, Runnable onFinish) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;"); // Set black background

        // Create scrolling text
        Text introText = new Text(
                "Welcome to Bingo's Quest!\n" +
                "In a land shrouded in mystery, where monsters roam freely,\n" +
                "a brave adventurer named Bingo must rise.\n" +
                "Armed with courage and a sharp blade, Bingo sets forth to\n" +
                "defend the weak and uncover the secrets of the ancient dungeons.\n\n" +
                "Prepare yourself for an epic journey filled with challenges,\n" +
                "danger, and glory. The fate of the world lies in your hands...\n\n" +
                "Let the adventure begin!"
        );
        introText.setStyle("-fx-font-family: 'alagard'; -fx-font-size: 28; -fx-fill: white;"); // Set text to white
        introText.setWrappingWidth(800);
        introText.setLayoutX(15);
        introText.setLayoutY(600);

        pane.getChildren().add(introText);

        // Create scrolling animation using Timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), event -> {
            introText.setLayoutY(introText.getLayoutY() - 1);
        }));
        timeline.setCycleCount(900); // Adjust cycle count to match the text length
        timeline.setOnFinished(e -> onFinish.run());
        timeline.play();

        // Scene and Stage
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

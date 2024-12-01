import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Credits {

    public static void showCredits(Stage primaryStage) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        Text creditsText = new Text("Bingo's Quest\n\nDeveloped by:\n\nMichael Leiby\nLucas Forbes\nAaron Lingaur\nBrian Vorac\n\nThank you for playing!");
        creditsText.setStyle("-fx-font-family: 'alagard'; -fx-font-size: 20; -fx-fill: white;");

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> new Driver().start(primaryStage));

        vbox.getChildren().addAll(creditsText, backButton);

        Scene creditsScene = new Scene(vbox, 800, 600);
        creditsScene.getStylesheets().add("game_styles.css");
        primaryStage.setScene(creditsScene);
    }
}
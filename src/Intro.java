import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Intro {

    public void showIntro(Stage primaryStage, Runnable onFinish) {
    	// Create a new pane and set the background to black
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;"); // Set black background

        // Create the text for the introduction
        Text intro = new Text(
        		"In an age long past, when the Kingdom was rife of fortune and luck,\n"+
        		"a shadow crept across the land. Tyrants, cloaked in mystery and"+
        		" malice, cast despair upon the realm, unleashing beasts and chaos"+
        		" upon its villages and people."+
        		"\n\nFrom the humble village of Eldergrove, a hero would rise. Bingo,"+
        		" an unassuming adventurer, armed with luck, wits, \nand an unyielding"+
        		" spirit, answers the call. The ancient prophecies spoke of one who"+
        		" would challenge the darkness and restore peace to the land."+
        		"\n\nThrough treacherous lands, cursed woods, and forgotten dungeons,"+
        		" Bingo must vanquish the tyrants once and for all. Guided by hope and"+
        		"a sense of duty, Bingo embarks on a journey of valor, discovery,"+
        		"and sacrifice."+
        		"\n\nYou must direct Bingo to triumph against all odds and bring light"+
        		" to a realm cloaked in darkness. The fate of the lands lies in your hands..."
        );
        
        // Set the intro's text and correct size/spacing
        intro.setStyle("-fx-font-family: 'alagard'; -fx-font-size: 28; -fx-fill: white;"); // Set text to white
        intro.setWrappingWidth(785);
        intro.setLayoutX(15);
        intro.setLayoutY(600);

        // Add the text to the pane
        pane.getChildren().add(intro);

        // Create a scrolling effect for the text
        Timeline tLine = new Timeline(new KeyFrame(Duration.millis(25), event -> {
        	intro.setLayoutY(intro.getLayoutY() - 1);
        }));
        
        // Configure the scrolling effect animation cycle
        tLine.setCycleCount(1200); // Adjust cycle count to match the text length
        tLine.setOnFinished(e -> onFinish.run());
        tLine.play();

        // Create a scene, pass it to the primaryStage, show pStage
        Scene scene = new Scene(pane, 800, 600);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
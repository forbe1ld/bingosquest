
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Driver extends Application{
	
	public void start(Stage stage) throws Exception {
		Button next = new Button("Start Game");
		BorderPane p = new BorderPane();
		Player player = new Player(1);
		p.setCenter(next);
		
		next.setOnAction(event->{
			try {
				SceneHandler.loadMapScene(stage, player);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Scene startScene = new Scene(p, 1024, 768);
		
		stage.setTitle("Bingo's Quest");
		stage.setScene(startScene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
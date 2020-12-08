import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("pulldownmenu.fxml"));
        primaryStage.setTitle("Pengolahan Citra Digital - 181524031 Zara Veda M.");
        primaryStage.setScene(new Scene(root, 800, 500));
        
        primaryStage.setMaxHeight(500);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(800);
        primaryStage.setMinWidth(400);
        
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
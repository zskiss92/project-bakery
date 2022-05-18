import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class BakeryApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/BakeryUI.fxml"));
        stage.setTitle("Bakery Application");
        stage.setScene(new Scene(root));
        stage.show();




    }

}

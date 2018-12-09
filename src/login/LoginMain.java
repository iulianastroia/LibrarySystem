package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginMain extends Application {
    public static Stage stage;

    public static void setStage(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/appearance/StageAppearance.css");
        stage.setScene(scene);
        Image projectIcon = new Image("https://image.freepik.com/free-icon/open-book_318-62025.jpg");
        stage.getIcons().add(projectIcon);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("loginFXML.fxml"));


        Parent root = FXMLLoader.load(getClass().getResource("/userOrAdmin/userOrAdmin.fxml"));

        setStage(stage, root);

    }


    public static void main(String[] args) {
        launch(args);
    }
}

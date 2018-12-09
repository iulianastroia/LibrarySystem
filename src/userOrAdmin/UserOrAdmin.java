package userOrAdmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.LoginMain;


public class UserOrAdmin {
    static javafx.scene.image.Image projectIcon = new Image("https://image.freepik.com/free-icon/open-book_318-62025.jpg");
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button userButton;
    @FXML
    Button adminButton;

    public void chooseUserOrAdmin(Stage stage, Parent root) {
        LoginMain.setStage(stage, root);
        LoginMain.stage = stage;

//        close are you user or admin stage
        Stage stage1 = (Stage) anchorPane.getScene().getWindow();
        stage1.close();

    }


    public void userButtonAction(ActionEvent actionEvent) throws Exception {
//        Label label = new Label("You chose user");
////        window.Window.showWindow(label, projectIcon);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/userLogin/user.fxml"));
        chooseUserOrAdmin(stage, root);

    }

    public void adminButtonAction(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/login/loginFXML.fxml"));
//        LoginMain.setStage(stage, root);
//        LoginMain.stage = stage;
//
////        close are you user or admin stage
//        Stage stage1 = (Stage) anchorPane.getScene().getWindow();
//        stage1.close();
        chooseUserOrAdmin(stage, root);


    }
}

package userLogin;

import com.jfoenix.controls.JFXTextArea;
import database.WriteToMySql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginMain;
import window.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static login.LoginController.alert;
import static login.LoginController.projectIcon;

//controller for user.fxml
public class UserRegister implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    JFXTextArea passId;
    @FXML
    JFXTextArea userId;

    @FXML
    JFXTextArea phoneId;
    @FXML
    JFXTextArea emailId;

    WriteToMySql writeToMySql;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        writeToMySql = new WriteToMySql();
    }


    public void cancelAction(ActionEvent actionEvent) {
        Stage stageLogin = (Stage) anchorPane.getScene().getWindow();
        stageLogin.close();
    }

    public void registerAction(ActionEvent actionEvent) {

        String userUser = userId.getText();
        String passUser = passId.getText();
        String phoneUser = phoneId.getText();
        String emailUser = emailId.getText();


        if (userUser.isEmpty() || passUser.isEmpty() || phoneUser.isEmpty() || emailUser.isEmpty()) {
            alert();
            return;
        }

        WriteToMySql.ConnectionToMySqlUser(userUser, passUser, phoneUser, emailUser);
        Label label = new Label("Connection established. Database updated! :)");
        Window.showWindow(label, projectIcon);
    }

    public void loginAction(ActionEvent actionEvent) throws Exception{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/userLogin/userLogin.fxml"));
        LoginMain.setStage(stage, root);
        LoginMain.stage = stage;

//        close are you user or admin stage
        Stage stage1 = (Stage) anchorPane.getScene().getWindow();
        stage1.close();

    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        LoginController loginController=new LoginController();
        loginController.goBackToUserOrAdmin();
    }
}

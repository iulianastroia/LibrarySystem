package userLogin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import database.EmailSend;
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
import java.sql.*;
import java.util.ResourceBundle;

//import static database.WriteToMySql.connection;
import static login.LoginController.alert;
import static login.LoginController.projectIcon;
import static login.LoginMain.setStage;

//controller for userLogin
public class UserLogin implements Initializable {
    @FXML
    JFXButton forgotButton;
    @FXML
    JFXButton loginButton;
    @FXML
    JFXTextArea passId;
    @FXML
    JFXTextArea userId;
    @FXML
    AnchorPane anchorPane;
    WriteToMySql writeToMySql;
    static String userNameForBorrow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        writeToMySql = new WriteToMySql();
    }

    public void loginAction(ActionEvent actionEvent) throws Exception {
        userNameForBorrow = userId.getText();
        String user = userId.getText();
        String pass = passId.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            alert();
            return;
        } else {
            writeToMySql.LoginActionUser(user, pass);
        }
    }

    public void forgotAction(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/userLogin/sendMail.fxml"));
        LoginMain.stage.close();
        setStage(stage, root);
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        LoginController loginController = new LoginController();
        loginController.goBackToUserOrAdmin();
    }
}

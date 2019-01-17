package login;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.sun.xml.internal.bind.v2.TODO;
//import database.ShowDataEntry;
import database.WriteToMySql;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import userOrAdmin.UserOrAdmin;
import window.Window;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

//controller for admin login
public class LoginController implements Initializable {
    @FXML
    public Button backButton;
    @FXML
    JFXButton cancelButton;
    @FXML
    JFXButton loginButton;
    @FXML
    JFXButton registerButton;
    @FXML
    JFXTextArea passId;
    @FXML
    JFXTextArea userId;
    @FXML
    AnchorPane anchorPane;


    public static Image projectIcon = new Image("https://image.freepik.com/free-icon/open-book_318-62025.jpg");
    WriteToMySql writeToMySql;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        writeToMySql = new WriteToMySql();
    }

    //    alert if username or password fields are empty
    public static void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(projectIcon); // To add an icon

        alert.setHeaderText(null);
        alert.setContentText("Please write information in all fields");
        alert.showAndWait();

    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stageLogin = (Stage) anchorPane.getScene().getWindow();
        stageLogin.close();
    }

    //    login action for admin
    public void loginAction(ActionEvent actionEvent) throws Exception {
        String user = userId.getText();
        String pass = passId.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            alert();
            return;
        } else {
            writeToMySql.LoginAction(user, pass);
        }
    }


    public void registerAction(ActionEvent actionEvent) {

        String userAdmin = userId.getText();
        String passAdmin = passId.getText();

        if (userAdmin.isEmpty() || passAdmin.isEmpty()) {
            alert();
            return;
        }

        WriteToMySql.ConnectionToMySqlAdmin(userAdmin, passAdmin);
        Label label = new Label("Connection established. Database updated! :)");
        Window.showWindow(label, projectIcon);
    }


    public void backButton(ActionEvent actionEvent) throws IOException {
        goBackToUserOrAdmin();
    }

    public void goBackToUserOrAdmin() throws IOException {
        LoginMain.stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/userOrAdmin/userOrAdmin.fxml"));
        Stage stage = new Stage();
        LoginMain.setStage(stage, root);

    }
}

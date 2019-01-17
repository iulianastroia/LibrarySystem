package userLogin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import database.EmailSend;
import database.WriteToMySql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static login.LoginController.alert;

public class SendMail implements Initializable {


    @FXML
    JFXButton sendId;
    @FXML
    JFXTextArea textId;
   private static String pass;
    private static String user;

//    get user and pass from user database
    private static void getTableEntry(String email) {
        try {
//            WriteToMySql.connection();
            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);

            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("SELECT * FROM user_db WHERE email=?");

            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println(preparedStatement.toString());
            while (rs.next()) {
                pass = rs.getString("password");
//                System.out.println("Pass is: " + pass);
                user=rs.getString("username");

            }

        } catch (Exception e) {
            System.err.println("Got an exception at reading the database table! ");
            System.err.println(e.getMessage());
        }
    }


    public void sendAction(ActionEvent actionEvent) throws Exception {
        String email = textId.getText();


        if (email.isEmpty()) {
            alert();
            return;
        } else {
            getTableEntry(email);
//            System.out.println("pass from send action is: " + pass);

            EmailSend.sendmail(email,pass,user);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

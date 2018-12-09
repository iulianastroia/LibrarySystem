package book;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

import com.jfoenix.controls.JFXTextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.WriteToMySql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginMain;
import window.Window;


public class BookController implements Initializable {
    private Image projectIcon = new Image("https://image.freepik.com/free-icon/open-book_318-62025.jpg");

    @FXML
    private JFXTextArea id;

    @FXML
    private JFXTextArea author;

    @FXML
    private JFXTextArea title;

    @FXML
    private JFXTextArea status;
    @FXML
    private AnchorPane anchorPane;
    private boolean maximise = false;
    @FXML
    private MenuItem fullScreenId;
//    Stage stage = (Stage) anchorPane.getScene().getWindow();

   private WriteToMySql writeToMySql;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        writeToMySql = new WriteToMySql();
    }

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();


    }

    @FXML
    void saveAction(ActionEvent event) {
        String bookID = id.getText();
        String bookAuthor = author.getText();
        String bookTitle = title.getText();
        String bookStatus = status.getText();

        if (bookID.isEmpty() || bookAuthor.isEmpty() || bookTitle.isEmpty() || bookStatus.isEmpty()) {
            LoginController.alert();
            return;
        }


        writeToMySql.ConnectionToMySql(bookID, bookAuthor, bookTitle, bookStatus);
        Label label = new Label("Connection established. Database updated! :)");
        Window.showWindow(label, projectIcon);

    }


    public void viewBooksAction(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/table/bookList.fxml"));
        LoginMain.setStage(stage, root);

    }

    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void fullScreenAction(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        if (maximise == false) {
            stage.setMaximized(true);
            maximise = true;
            fullScreenId.setText("Minimise");
        } else {
            stage.setMaximized(false);
            maximise = false;
            fullScreenId.setText("Maximise");
        }


    }


    public void aboutAction(ActionEvent actionEvent) {
        Label aboutLabel = new Label("Welcome to our Library System. You can find your favourite book titles here and you can take them home for 2 weeks. After that time, you can either reserve them longer or return them. Have a pleasant time with us! :)");
        window.Window.showWindow(aboutLabel, projectIcon);
    }
}




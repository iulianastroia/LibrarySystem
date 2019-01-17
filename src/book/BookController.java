package book;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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

//controller for books.fxml
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
    @FXML
    private TextField bookTitleInput;
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

    public void updateAction(ActionEvent actionEvent) {
        WriteToMySql.updateBooks(id.getText(), author.getText(), title.getText(), status.getText());
        if (id.getText().isEmpty())
            WriteToMySql.alert("Write something into id field");
        else if (title.getText().isEmpty() && author.getText().isEmpty() && status.getText().isEmpty()) {
            WriteToMySql.alert("Cannot update book! Change some fields. :)");
        } else if (title.getText().isEmpty() || author.getText().isEmpty() || status.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

//            Label labelWarning = new Label();
            alert.setContentText("You might want to update the other fields, too. They are currently empty.");
            alert.showAndWait();
//            Window.showWindow(labelWarning, projectIcon);
        } else {
//            TODO update book if id is existent!
            Label labelUpdate = new Label("Book with id \"" + id.getText() + "\" updated successfully.");
            Window.showWindow(labelUpdate, projectIcon);
        }
    }

    public void deleteAction(ActionEvent actionEvent) {

        WriteToMySql.DeleteRowAdmin(id.getText(), title.getText());
        if (id.getText().isEmpty() && title.getText().isEmpty()) {
            WriteToMySql.alert("Write something into id or title field");
        } else if (title.getText().isEmpty()) {
            Label labelDelete = new Label("Book with id " + id.getText() + " deleted successfully.");
            Window.showWindow(labelDelete, projectIcon);
        } else if (id.getText().isEmpty()) {
            Label labelDelete = new Label("Book with title \"" + title.getText() + "\" deleted successfully.");
            Window.showWindow(labelDelete, projectIcon);
        } else {
            Label labelDelete = new Label("Book deleted successfully.");
            Window.showWindow(labelDelete, projectIcon);
        }
//     TODO   check if book title/id existent in database
    }

    //    view info about users
    public void viewBorrowedBooks(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/table/userList.fxml"));
        LoginMain.setStage(stage, root);
    }

//    view info about borrowed books
    public void viewBorrowInfo(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/table/borrowList.fxml"));
        LoginMain.setStage(stage, root);


    }


    public void backButton(ActionEvent actionEvent) throws IOException {
        LoginController loginController=new LoginController();
        LoginMain.stage.close();
        loginController.goBackToUserOrAdmin();
    }
}




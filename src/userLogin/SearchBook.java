package userLogin;

import com.jfoenix.effects.JFXDepthManager;
import database.WriteToMySql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Iuliana
 */
public class SearchBook implements Initializable {
    @FXML
    public Text bookID;
    @FXML
    public Text bookAuthor;
    @FXML
    public Text bookTitle;
    @FXML
    public Text bookStatus;
    @FXML
    public VBox vBox;
    @FXML
    public TextField bookTitleInput;
    @FXML
    private HBox hBox;
    public static String id;

    public static String status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXDepthManager.setDepth(hBox, 1);
    }

    //    search book by title into book database and print the result
    public void loadBookInfo(ActionEvent actionEvent) {
        String title = bookTitleInput.getText();
        String author = bookTitleInput.getText();

        try {
            Connection connect = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
//            PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * FROM test_db WHERE title= '" + title + "'");
            PreparedStatement statement = (PreparedStatement) connect.prepareStatement("SELECT * FROM test_db WHERE title= '" + title + "' OR author='" + author + "'");


            ResultSet rs = statement.executeQuery();
            Boolean findBook = false;

            while (rs.next()) {
                id = rs.getString("id");
                String author2 = rs.getString("author");
//                String status = rs.getString("status");
                status = rs.getString("status");

                String title2 = rs.getString("title");

                bookTitle.setText(title2);
                bookID.setText(id);
                bookAuthor.setText(author2);
                bookStatus.setText(status);

                findBook = true;
            }
            if (!findBook) {
                bookAuthor.setText("Sorry, we do not own this book.");
                bookTitle.setText("");
                bookID.setText("");
                bookStatus.setText("");
            }

            connect.close();
            statement.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void borrowBook(ActionEvent actionEvent) {
        String title = bookTitleInput.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Borrowing book...");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to borrow the book " + title + " ?");

        Optional<ButtonType> response = alert.showAndWait();
        if (response.get() == ButtonType.OK) {
//            borrow books
//            if (status.equals("Available")) {
            if (bookStatus.getText().equals("Available")) {
                WriteToMySql.connectToBorrowTableDB(WriteToMySql.userName, id);
                String setStatus = "Not available";
                WriteToMySql.updateStatusOfBook(id, setStatus);


                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setContentText("Book " + title + " was borrowed successfully.");
                alert1.showAndWait();

            } else if (bookStatus.getText().equals("Not available")) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Book " + title + " is already borrowed.");
                alert1.showAndWait();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Sorry, we do not own this book: '" + title+"'");
                alert1.showAndWait();
            }
        }

    }
}

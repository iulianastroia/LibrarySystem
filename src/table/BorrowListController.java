package table;
/**
 * @author Iuliana
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.WriteToMySql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BorrowListController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ModelTableBorrow> tableView;

    @FXML
    private TableColumn<ModelTableBorrow, String> borrowUsername;

    @FXML
    private TableColumn<ModelTableBorrow, String> bookId;

    @FXML
    private TableColumn<ModelTableBorrow, String> today;


    @FXML
    private TableColumn<ModelTableBorrow, String> borrowtime;

    ObservableList<ModelTableBorrow> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
//            WriteToMySql.connection();
            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
            String query = "SELECT * FROM borrow_db";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                observableList.add(new ModelTableBorrow(resultSet.getString("borrowUsername"), resultSet.getString("bookId"), resultSet.getString("today"), resultSet.getString("borrowtime")));

            }


        } catch (SQLException e) {
//            System.out.println("Exception at reading table data from database");
            Logger.getLogger(UsersListController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        borrowUsername.setCellValueFactory(new PropertyValueFactory<>("borrowUsername"));
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        today.setCellValueFactory(new PropertyValueFactory<>("today"));
        borrowtime.setCellValueFactory(new PropertyValueFactory<>("borrowtime"));
        tableView.setItems(observableList);
    }

    //    delete user and book when user returns book
    public void deleteUserAndBook(ActionEvent actionEvent) {
        try {
//gets id of selected line from tableView
            ModelTableBorrow id = tableView.getSelectionModel().getSelectedItem();
            String userName = id.getBorrowUsername();
            String idBook = id.getBookId();

            WriteToMySql.deleteUserAndBorrowedBook(idBook);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successfully deleted");
            alert.setHeaderText("You have successfully deleted book with id " + idBook + " from user " + userName);
            alert.showAndWait();


//book becomes available when user returns it
            String setStatus = "Available";
            WriteToMySql.updateStatusOfBook(idBook, setStatus);
        } catch (Exception e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please select a book to return. ");
            alert.showAndWait();
        }


    }
}

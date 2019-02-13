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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

public class BookListController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ModelTable> tableView;

    @FXML
    private TableColumn<ModelTable, String> idColumn;

    @FXML
    private TableColumn<ModelTable, String> authorColumn;

    @FXML
    private TableColumn<ModelTable, String> titleColumn;

    @FXML
    private TableColumn<ModelTable, String> statusColumn;

    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
            String query = "SELECT * FROM test_db";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                observableList.add(new ModelTable(resultSet.getString("id"), resultSet.getString("author"), resultSet.getString("title"), resultSet.getString("status")));
            }

        } catch (SQLException e) {
            Logger.getLogger(BookListController.class.getName()).log(Level.SEVERE, null, e);
        }


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(observableList);

        tableView.setEditable(true); //update book field
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    //update author
    public void updateBookAuthor(TableColumn.CellEditEvent<ModelTable, String> oldAuthor) {
        ModelTable newAuthor = tableView.getSelectionModel().getSelectedItem();
        newAuthor.setAuthor(oldAuthor.getNewValue());
        String id = newAuthor.getId(); //get id of selected book
        String status = newAuthor.getStatus(); //get status of selected book
        String title = newAuthor.getTitle(); //get title of selected book

        WriteToMySql.updateBooks(id, newAuthor.getAuthor().toString(), title, status);
        updateAlert(id);
    }

    public void updateAlert(String id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successfully updated book");
        alert.setHeaderText("You have successfully updated book with id " + id);
        alert.showAndWait();
    }

//    update book title
    public void updateBookTitle(TableColumn.CellEditEvent<ModelTable, String> oldTitle) {
        ModelTable newTitle = tableView.getSelectionModel().getSelectedItem();
        newTitle.setTitle(oldTitle.getNewValue());
        String id = newTitle.getId(); //get id of selected book
        String status = newTitle.getStatus(); //get status of selected book
        String author = newTitle.getAuthor(); //get author of selected book
        WriteToMySql.updateBooks(id, author, newTitle.getTitle().toString(), status);
        updateAlert(id);
    }

    public void updateBookStatus(TableColumn.CellEditEvent<ModelTable, String> oldStatus) {
        ModelTable newStatus = tableView.getSelectionModel().getSelectedItem();
        newStatus.setStatus(oldStatus.getNewValue());
        String id = newStatus.getId(); //get id of selected book
        String title = newStatus.getTitle(); //get title of selected book
        String author = newStatus.getAuthor(); //get author of selected book
        WriteToMySql.updateBooks(id, author, title, newStatus.getStatus().toString());
        updateAlert(id);
    }
}

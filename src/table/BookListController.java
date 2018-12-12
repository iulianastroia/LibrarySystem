package table;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
//            WriteToMySql.connection();
            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
            String query = "SELECT * FROM test_db";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                observableList.add(new ModelTable(resultSet.getString("id"), resultSet.getString("author"), resultSet.getString("title"), resultSet.getString("status")));

            }


        } catch (SQLException e) {
//            System.out.println("Exception at reading table data from database");
            Logger.getLogger(BookListController.class.getName()).log(Level.SEVERE, null, e);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(observableList);
    }

}

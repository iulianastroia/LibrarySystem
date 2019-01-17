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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import static database.WriteToMySql.writeLogger;

public class UsersListController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<ModelTableUsers> tableView;

    @FXML
    private TableColumn<ModelTableUsers, String> idColumn;

    @FXML
    private TableColumn<ModelTableUsers, String> authorColumn;

    @FXML
    private TableColumn<ModelTableUsers, String> titleColumn;

    @FXML
    private TableColumn<ModelTableUsers, String> statusColumn;

    ObservableList<ModelTableUsers> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
//            WriteToMySql.connection();
            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
            String query = "SELECT * FROM user_db";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                observableList.add(new ModelTableUsers(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email")));

            }


        } catch (SQLException e) {
//            System.out.println("Exception at reading table data from database");
            Logger.getLogger(UsersListController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            writeLogger(e);

        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.setItems(observableList);
    }

}

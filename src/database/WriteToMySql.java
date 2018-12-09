package database;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import login.LoginMain;
import window.Window;

import java.sql.*;


public class WriteToMySql {

    static Image projectIcon = new Image("https://image.freepik.com/free-icon/open-book_318-62025.jpg");

    //    used to connect to database

    public static String host = "jdbc:mysql://remotemysql.com:3306/B6SWh4erqu";
    public static String username = "B6SWh4erqu";
    public static String passwordServer = "9yQbvyIdBy";

    public static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //    connect to database to insert data into test_db(database for books)
    public static void ConnectionToMySql(String id, String author, String title, String status) {
        connection();

        try {
            Connection connect = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO test_db(id,author,title,status)VALUES(?,?,?,?)");
            statement.setString(1, id);
            statement.setString(2, author);
            statement.setString(3, title);
            statement.setString(4, status);
            statement.executeUpdate();
            statement.close();
            connect.close();
            System.out.println("Connection established. Database updated :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(projectIcon); // To add an icon

        alert.setHeaderText(null);
        alert.setContentText("Wrong username or password");
        alert.showAndWait();

    }


    //    checks login info for admin(currently)
    public void LoginAction(String user, String password) {
        connection();
        try {
            Connection connection = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT `username`, `password` FROM `admin_db` WHERE `username`=? AND `password`=?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("login successful");
                Label label = new Label("Login successful!");
                Window.showWindow(label, projectIcon);

                try {

                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/book/books.fxml"));


//                    close login window
                    LoginMain.stage.close();
                    LoginMain.setStage(stage, root);

                } catch (Exception e) {
                    System.out.print("Cannot open");
                }

            } else {
                alert();
            }
        } catch (Exception e) {
            System.out.println("Login fail");
        }
    }

//    connect to database to insert data into admin_db(database for admins)

    public static void ConnectionToMySqlAdmin(String user, String password) {
        connection();

        try {
            Connection connect = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement statement = (PreparedStatement) connect.prepareStatement("INSERT INTO admin_db(username,password)VALUES(?,?)");
            statement.setString(1, user);
            statement.setString(2, password);
            statement.executeUpdate();
            statement.close();
            connect.close();
            System.out.println("Connection established. Database updated :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    connect to database to insert data into user_db(database for users)
    public static void ConnectionToMySqlUser(String user, String password, String phone, String email) {
        connection();

        try {
            Connection connect = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement statement =  connect.prepareStatement("INSERT INTO user_db(username,password,phone,email)VALUES(?,?,?,?)");
            statement.setString(1, user);
            statement.setString(2, password);
            statement.setString(3, phone);
            statement.setString(4, email);

            statement.executeUpdate();
            statement.close();
            connect.close();
            System.out.println("Connection established. Database updated :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkPass() {

    }

    //    checks login info for user
    public void LoginActionUser(String user, String password) {
        connection();
        try {
            Connection connection = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT `username`, `password` FROM `user_db` WHERE `username`=? AND `password`=?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
//                System.out.println("login successful");
//                Label label = new Label("Login successful!");
//                Window.showWindow(label, projectIcon);

                try {
                    Label labelUser = new Label("Login Successful");
                    Window.showWindow(labelUser, projectIcon);

                } catch (Exception e) {
                    System.out.print("Cannot open");
                }

            } else {
                alert();
            }
        } catch (Exception e) {
            System.out.println("Login fail");
        }
    }


    public static void main(String args[]) {
//        ConnectionToMySql("1", "Marin Preda", "Morometii", "Available");
    }
}

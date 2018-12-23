package database;

import logger.LoggerClass;
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
    public static String host = "jdbc:sqlserver://librarysprojectserver.database.windows.net:1433;database=library_db";
    public static String username = "libraryadmin";
    public static String passwordServer = "Librarysystem1";


    //    connect to database to insert data into test_db(database for books)
    public static void ConnectionToMySql(String id, String author, String title, String status) {
//        connection();
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

    public static void alert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(projectIcon); // To add an icon
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();

    }


    //    checks login info for admin(currently)
    public void LoginAction(String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT username, password FROM admin_db WHERE username=? AND password=?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Label label = new Label("Login successful!");
                Window.showWindow(label, projectIcon);
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/book/books.fxml"));
//                    Parent root = FXMLLoader.load(getClass().getResource("/bookr/books.fxml"));

//                    close login window
                    LoginMain.stage.close();
                    LoginMain.setStage(stage, root);
                    connection.close();
                    resultSet.close();
                } catch (Exception e) {
//                    e.printStackTrace();
//                    TODO ADD LOGGER TO EVERY EXCEPTION
                    LoggerClass loggerClass = new LoggerClass();
                    loggerClass.writeToFile(e);
//                    END TODO
                }

            } else {
                alert("Wrong username or password");
            }

        } catch (Exception e) {
            e.printStackTrace();

//            System.out.println("Login fail");
        }
    }

    //    connect to database to insert data into admin_db(database for admins)
    public static void ConnectionToMySqlAdmin(String user, String password) {

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
//        connection();

        try {
            Connection connect = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement statement = connect.prepareStatement("INSERT INTO user_db(username,password,phone,email)VALUES(?,?,?,?)");
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
        try {
            Connection connection = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, password FROM user_db WHERE username=? AND password=?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Label label = new Label("Login successful!");
                Window.showWindow(label, projectIcon);

                try {
//                    Label labelUser = new Label("Login Successful");
//                    Window.showWindow(labelUser, projectIcon);
//TODO code to open login winodow
                    preparedStatement.close();
                    resultSet.close();
                    connection.close();
//                    TODO
                } catch (Exception e) {
//                    System.out.print("Cannot open");
                    e.printStackTrace();
                }

            } else {
                alert("Wrong username or password");
            }
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Login fail");
        }
    }

    //delete row by id/title of book
    public static void DeleteRowAdmin(String id, String title) {
        try {
            Connection connection = DriverManager.getConnection(host, username, passwordServer);
            PreparedStatement st = connection.prepareStatement("DELETE FROM test_db WHERE id=? or title=?");

            st.setString(1, id);
            st.setString(2, title);

//TODO check if book is deleted or not
//            Label deleteLabel = new Label("Entry was deleted.");
//            Window.showWindow(deleteLabel, projectIcon);
            st.executeUpdate();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBooks(String id, String author, String title, String status) {
        try {
            Connection connection = DriverManager.getConnection(host, username, passwordServer);
            String sql = "UPDATE  test_db set author = ?,title=?,status=? where id= ? ";
            PreparedStatement st = connection.prepareStatement(sql);

            st = connection.prepareStatement(sql);
            st.setString(1, author);
            st.setString(2, title);
            st.setString(3, status);
            st.setString(4, id);


//          TODO check if id exists in database
//            Statement stmt = connection.createStatement();


//            TODO
//            Label updateLabel = new Label("Book with id " + id + " was updated.");
//            Window.showWindow(updateLabel, projectIcon);


            st.executeUpdate();
            st.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
    }
}

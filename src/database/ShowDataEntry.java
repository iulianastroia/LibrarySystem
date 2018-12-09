//package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//public class ShowDataEntry {
//    public static void getTableEntry() {
//        try {
//            WriteToMySql.connection();
//            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
//
////            select entries from the table database which are not null
//            String query = "SELECT * FROM admin_db WHERE username <>'' and password<>'' ";
//
////             create the java statement
//            Statement st = conn.createStatement();
//
////             execute the query, and get a java resultset
//            ResultSet rs = st.executeQuery(query);
//
////             iterate through the java resultset
//            while (rs.next()) {
//                String user = rs.getString("username");
//                String pass = rs.getString("password");
//
////                 print data from the table
//                System.out.println("Username: " + user + "; Password: " + pass);
//            }
//            st.close();
//        } catch (Exception e) {
//            System.err.println("Got an exception at reading the database table! ");
//            System.err.println(e.getMessage());
//        }
//    }

//    public static void getTableEntry() {
//        try {
//            WriteToMySql.connection();
//            Connection conn = DriverManager.getConnection(WriteToMySql.host, WriteToMySql.username, WriteToMySql.passwordServer);
//
////            select entries from the table database which are not null
//            String query = "SELECT * FROM user_db WHERE username <>'' and password<>'' and phone <>'' and email<>'' ";
//
////             create the java statement
//            Statement st = conn.createStatement();
//
////             execute the query, and get a java resultset
//            ResultSet rs = st.executeQuery(query);
//
////             iterate through the java resultset
//            while (rs.next()) {
//                String user = rs.getString("username");
//                String pass = rs.getString("password");
//                String phone = rs.getString("phone");
//                String email = rs.getString("email");
////                 print data from the table
//                System.out.println("Username: " + user + "; Password: " + pass);
//                System.out.println("Phone: " + phone + "; Email: " + email);
//
//            }
//            st.close();
//        } catch (Exception e) {
//            System.err.println("Got an exception at reading the database table! ");
//            System.err.println(e.getMessage());
//        }
//    }
//}

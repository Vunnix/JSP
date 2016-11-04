package DAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by handyc on 02/11/16.
 */
public class ConnectionFactory{
    public Connection getCon(){
        try{
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException cne){
                cne.printStackTrace();
            }
            return DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rinaaaa
 */
public abstract class DBContext<T> {
    protected Connection connection;
    public DBContext()
    {
        String user="rina";
        String pass = "26122004";
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS01:1433;databaseName=DBAssignment;trustServerCertificate=true;";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void insert(T model);
    public abstract void update(T model);
    public abstract void delete(T model);
    public abstract ArrayList<T> list();
    public abstract T get(int id);
}

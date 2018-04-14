package DAO;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectingDB 
{
     private static Connection con = null;
     private static String url = "jdbc:sqlserver://";
     private static String serverName= ".";
     private static String portNumber = "1433";
     private static String databaseName= "KhachSan";

     public ConnectingDB()
     {
         
     }
     
     public static String getConnectionUrl()
     {
         return url + serverName + ":" + portNumber + "; databaseName=" + databaseName + "; integratedSecurity=true";
     }

     public static Connection getConnection()
     {
          try
          {
//               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
//               con = DriverManager.getConnection(getConnectionUrl());
//               
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
              String url = "jdbc:sqlserver://localhost:1433;Instance=SQLEXPRESS;databaseName=KhachSan;integratedSecurity=true";
              con = DriverManager.getConnection(url);
//              String url = "jdbc:sqlserver://TTC\\SQLEXPRESS;databaseName=MYDB;integratedSecurity=true";
//              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//              Connection con = DriverManager.getConnection(url);
          }
          catch (ClassNotFoundException | SQLException ex)
          {
              System.out.println(ex.getMessage());
              con = null;
          }
          return con;
      }

     public static void closeConnection()
     {
          try
          {
               if(con!=null)
                    con.close();
               con=null;
          }
          catch(Exception e)
          {
               e.printStackTrace();
          }
     }
}

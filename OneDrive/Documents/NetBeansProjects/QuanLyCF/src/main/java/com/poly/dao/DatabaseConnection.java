/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DatabaseConnection {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String connectionUrl = "jdbc:sqlserver://localhost;database=quanlyquancafe;";
    private static String username="sa";
    private static String password="123";
   
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public DatabaseConnection(){
        try {            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;"
            + "databaseName=QLSanPham;", username, password);
            System.out.println("connect successfully!");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi thiếu thư viện kết nối");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối CSDL!");
        }
    }
    public static PreparedStatement getStmt(String sql, Object ... args) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl, username,password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt =connection.prepareCall(sql);
        }else{
            pstmt=connection.prepareStatement(sql);
        }
        for(int i=0;i< args.length;i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    public static int update(String sql, Object ... args) { 
         try {
             PreparedStatement stmt = DatabaseConnection.getStmt(sql, args);
             try {
                 return stmt.executeUpdate();
             } finally {
                 stmt.getConnection().close();
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
    public static ResultSet query(String sql, Object ... args) {
         try {
             PreparedStatement stmt = getStmt(sql, args);
             return stmt.executeQuery();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
    public static Object value(String sql, Object ... args) {
         try {
             ResultSet rs = query(sql, args);
             if (rs.next()) {
                 return  rs.getObject(0);
             }
             rs.getStatement().getConnection().close();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
         return null;
     }
   
}

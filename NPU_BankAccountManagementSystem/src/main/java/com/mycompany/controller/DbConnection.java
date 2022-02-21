/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;

/**
 *
 * @author pc
 */
@Controller
public class DbConnection {

    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private String classname = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localHost:3306/bankdb";
    private String dbUser = "root";
    private String dbPass = "2022";

    public DbConnection() {
        try {
            Class.forName(classname);
            conn = DriverManager.getConnection(url, dbUser, dbPass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            System.out.println("Please include Driver File");
        }catch(SQLException ex){
        System.out.println("Cannot connected");
        }
    }
    
    public ResultSet select(String sql){
    try{
        st = conn.createStatement();
        return st.executeQuery(sql);
    }catch(SQLException ex){
    return null;
    }
    }
    
    public boolean iud(String sql){
    try{
        st = conn.createStatement();
        int r = st.executeUpdate(sql);
        return r>0;
    }catch(SQLException ex){
    return false;
    }
    }
    
    public Connection getConnected(){
    return conn;
    }
}

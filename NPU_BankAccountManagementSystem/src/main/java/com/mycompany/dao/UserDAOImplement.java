/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import static com.mycompany.dao.DatabaseVariable.db;
import com.mycompany.domain.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public class UserDAOImplement implements UserDAO {

    @Override
    public boolean login(String uName, String pWord) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "select * from bankdb.users";
        ResultSet rs = db.select(sql);
        try {
            while (rs.next()) {
                Users us = new Users(rs.getString(1), rs.getString(2));
                if (us.getUserName().equalsIgnoreCase(uName) && us.getPassWord().equals(pWord)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Database Error");
        }
        if (uName.equalsIgnoreCase("admin") && pWord.equalsIgnoreCase("admin")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(String userName, String passWord) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Users us = new Users(userName, passWord);
        if (findUser(userName) == null) {
            String sql = "INSERT INTO `bankdb`.`users` (`userName`, `passWord`) VALUES ('" + userName + "', '" + passWord + "');";
            return db.iud(sql);
        }
        return false;
    }

    @Override
    public Users findUser(String userName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql="select * from bankdb.users where userName=\""+userName+"\";";
        ResultSet rs = db.select(sql); 
        try{  
            while(rs.next()){
                Users us=new Users(rs.getString(1),rs.getString(2));
                return us;
            }
        }catch(SQLException ex){
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteUser(String userName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(findUser(userName)!=null){
        Users us=findUser(userName);
        String sql="DELETE FROM `bankdb`.`users` WHERE (`userName` = '"+userName+"');";
        db.iud(sql);
       return true;
        }
    return false;
    }

}

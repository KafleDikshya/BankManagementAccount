/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.domain;

/**
 *
 * @author pc
 */
public class Users {
    private String UserName;
    private String PassWord;

    public Users(String UserName, String PassWord) {
        this.UserName = UserName;
        this.PassWord = PassWord;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }
    
     
     
     
   
}

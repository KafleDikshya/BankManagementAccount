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
public class Accounts {
    private int accNember;
    private String accName;
    private int accBalance;
    
    public Accounts(){
}

    public Accounts(int accNember, String accName, int accBalance) {
        this.accNember = accNember;
        this.accName = accName;
        this.accBalance = accBalance;
    }

    public int getAccNember() {
        return accNember;
    }

    public String getAccName() {
        return accName;
    }

    public int getAccBalance() {
        return accBalance;
    }

    public void setAccNember(int accNember) {
        this.accNember = accNember;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setAccBalance(int accBalance) {
        this.accBalance = accBalance;
    }
    
    
    
    
}

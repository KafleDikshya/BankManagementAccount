  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.command;

/**
 *
 * @author pc
 */
public class TransferAmountCommand {

    private int saccNo;
    private int raccNo;
    private int balance;

    public TransferAmountCommand() {
       
    }

    
    public void setSaccNo(int saccNo) {
        this.saccNo = saccNo;
    }

    public void setRaccNo(int raccNo) {
        this.raccNo = raccNo;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSaccNo() {
        return saccNo;
    }

    public int getRaccNo() {
        return raccNo;
    }

    public int getBalance() {
        return balance;
    }

}

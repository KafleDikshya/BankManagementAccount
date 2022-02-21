/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.domain.Accounts;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public interface AccountDAO {
    public boolean addAccount (int accNo, String accName, int balance);
    public Accounts findAccount(int accNo);
    public void checkAmount(int accNo);
    public boolean depositAmount(int accNo, int deposit);
    public int withdrawAmount(int accNo, int withdraw);
    public int transferAmount(int saccNo, int raccNo, int transfer);
    public boolean deleteAccount(int accNo);
    public ArrayList<Accounts> listAccount();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import static com.mycompany.dao.DatabaseVariable.db;
import com.mycompany.domain.Accounts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public class AccountDAOImplement implements AccountDAO {

    ArrayList<Accounts> acc = new ArrayList<>();

    @Override
    public boolean addAccount(int accNo, String accName, int balance) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Accounts ac = new Accounts(accNo, accName, balance);
        if (findAccount(accNo) == null) {
            String sql = "INSERT INTO `bankdb`.`accounts` (`accountNumber`, `accountName`, `balance`) VALUES ('" + accNo + "', '" + accName + "', '" + balance + "');";
            return db.iud(sql);
        }
        return false;
    }

    @Override
    public Accounts findAccount(int accNo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "select * from bankdb.accounts where accountNumber=" + accNo + ";";
        ResultSet rs = db.select(sql);
        try {
            while (rs.next()) {
                Accounts ac = new Accounts(rs.getInt(1), rs.getString(2), rs.getInt(3));
                return ac;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    @Override
    public void checkAmount(int accNo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (findAccount(accNo) != null) {
            Accounts ac = findAccount(accNo);
            System.out.println("The balance is : " + ac.getAccBalance());
        } else {
            System.out.println("Enter valid account number");
        }
    }

    @Override
    public boolean depositAmount(int accNo, int deposit) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (findAccount(accNo) != null) {
            Accounts ac = findAccount(accNo);
            int newBalance = deposit + ac.getAccBalance();
            ac.setAccBalance(newBalance);
            String sql = "UPDATE `bankdb`.`accounts` SET `balance` = '" + newBalance + "' WHERE (`accountNumber` = '" + accNo + "');";
            db.iud(sql);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int withdrawAmount(int accNo, int withdraw) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (findAccount(accNo) != null) {
            Accounts ac = findAccount(accNo);
            if (ac.getAccBalance() >= withdraw) {
                int newBalance = ac.getAccBalance() - withdraw;
                ac.setAccBalance(newBalance);
                String sql = "UPDATE `bankdb`.`accounts` SET `balance` = '" + newBalance + "' WHERE (`accountNumber` = '" + accNo + "');";
                if (db.iud(sql)) {
                    return 1;
                }
            }
            return -1;
        }
        return 0;
    }

    @Override
    public int transferAmount(int saccNo, int raccNo, int transfer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if ((findAccount(saccNo) != null) && (findAccount(raccNo) != null)) {
            Accounts sac = findAccount(saccNo);
            Accounts rac = findAccount(raccNo);
            if (sac.getAccBalance() >= transfer) {
                int sendBalance = sac.getAccBalance() - transfer;
                sac.setAccBalance(sendBalance);
                String sqls = "UPDATE `bankdb`.`accounts` SET `balance` = '" + sendBalance + "' WHERE (`accountNumber` = '" + saccNo + "');";
                int recBalance = rac.getAccBalance() + transfer;
                rac.setAccBalance(recBalance);
                String sqlr = "UPDATE `bankdb`.`accounts` SET `balance` = '" + recBalance + "' WHERE (`accountNumber` = '" + raccNo + "');";
                if (db.iud(sqls) && db.iud(sqlr)) {
                    return 1;
                }
            }
            return -1;
        }
        return 0;
    }

    @Override
    public boolean deleteAccount(int accNo) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (findAccount(accNo) != null) {
            Accounts ac = findAccount(accNo);
            String sql = "DELETE FROM `bankdb`.`accounts` WHERE (`accountNumber` = '" + accNo + "');";
            db.iud(sql);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Accounts> listAccount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Account Number" + "  " + "Name" + "  " + "Balance");
        String sql = ("select * from bankdb.accounts");
        ResultSet rs = db.select(sql);
        this.acc.clear();
        try {
            while (rs.next()) {
                Accounts acc = new Accounts(rs.getInt(1), rs.getString(2), rs.getInt(3));
                this.acc.add(acc);
            }
        } catch (SQLException e) {
            return null;
        }
        return this.acc;
    }

}

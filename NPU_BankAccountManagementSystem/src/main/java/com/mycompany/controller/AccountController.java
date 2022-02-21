/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.command.TransferAmountCommand;
import com.mycompany.dao.AccountDAO;
import com.mycompany.domain.Accounts;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author pc
 */
@Controller
public class AccountController {

    @Autowired
    AccountDAO ad;

    @RequestMapping(value = {"/createnewaccount"})
    public String viewCreatenewAccount(Model m, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            m.addAttribute("account", new Accounts());
            return "createnewaccount";
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = {"/processNewAccount"}, method = RequestMethod.POST)
    public String viewProcessAccount(@ModelAttribute("account") Accounts ac, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            if (ad.addAccount(ac.getAccNember(), ac.getAccName(), ac.getAccBalance())) {
                session.setAttribute("message", "Account added");
                return "redirect:createnewaccount";
            } else {
                session.setAttribute("message", "Account already exist");
                return "redirect:createnewaccount";
            }
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = {"/deleteaccount"})
    public String viewDeleteAccount(Model m, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            m.addAttribute("account", new Accounts());
            return "deleteaccount";
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = {"/processdeleteaccount"}, method = RequestMethod.POST)
    public String viewProcessDeleteAccount(@ModelAttribute("account") Accounts ac, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            if (ad.deleteAccount(ac.getAccNember())) {
                session.setAttribute("message", "Account deleted");
                return "redirect:deleteaccount";
            } else {
                session.setAttribute("message", "Account donot exist");
                return "redirect:deleteaccount";
            }
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = {"/withdrawamount"})
    public String viewWithdrawAmount(Model m, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            m.addAttribute("account", new Accounts());
            return "withdrawamount";
        } else {
            return "redirect:login";
        }
    }


    @RequestMapping(value = {"/processwithdraw"}, method = RequestMethod.POST)
    public String viewProcessWithdraw(@ModelAttribute("account") Accounts ac, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            int x = ad.withdrawAmount(ac.getAccNember(), ac.getAccBalance());
            if (x ==1) 
            {
                session.setAttribute("message", "Withdraw sucessful");
                return "redirect:withdrawamount";
            } else if (x == -1) {
                session.setAttribute("message", "Insufficient balance");
                return "redirect:withdrawamount";
            } else {
                session.setAttribute("message", "Invalid account number");
                return "redirect:withdrawamount";
            }
        }else{
        return "redirect:login";
        }
    }
    
    @RequestMapping(value = {"depositamount"})
    public String viewDepositAmount(Model m, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            m.addAttribute("account", new Accounts());
            return "depositamount";
        } else {
            return "redirect:login";
        }
    }


    @RequestMapping(value = {"/processdeposit"}, method = RequestMethod.POST)
    public String viewProcessDeposit(@ModelAttribute("account") Accounts ac, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            if (ad.depositAmount(ac.getAccNember(), ac.getAccBalance())){
            
                session.setAttribute("message", "Deposit sucessful");
                return "redirect:depositamount";
            }
            else {
                session.setAttribute("message", "Invalid account number");
                return "redirect:depositamount";
            }
        }else{
        return "redirect:login";
        }
    }
    
    @RequestMapping(value = {"/transferamount"})
    public String viewTransferAmount(Model m, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            m.addAttribute("fundtransferhelper", new TransferAmountCommand());
            return "transferamount";
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = {"/processfundtransfer"}, method = RequestMethod.POST)
    public String viewProcessTransfer(@ModelAttribute("fundtransferhelper") TransferAmountCommand tac, HttpSession session) {
        if (session.getAttribute("uName") != null) {
            int x = ad.transferAmount(tac.getSaccNo(), tac.getRaccNo(),tac.getBalance());
            if (x == 1) {
                session.setAttribute("message", "Transfer sucessful");
                return "redirect:transferamount";
            } else if (x == -1) {
                session.setAttribute("message", "Insufficient balance");
                return "redirect:transferamount";
            } else {
                session.setAttribute("message", "Invalid account number");
                return "redirect:transferamount";
            }
        }
        else{
        return "redirect:login";
        }
    }
    
    @RequestMapping("/listallaccounts")
    public String listAccount(Model m, HttpSession session){
       if(session.getAttribute("uName")!=null){
       ArrayList<Accounts> aList=ad.listAccount();
       m.addAttribute("aList", aList);
       return "listallaccounts";
       } 
       else{
       return "redirect:login";
       }
    }
}

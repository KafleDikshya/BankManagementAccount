
package com.mycompany.dao;

import com.mycompany.domain.Users;
import java.util.ArrayList;


public interface UserDAO {
    ArrayList<Users> usr = new ArrayList<>();
    public boolean login(String uName, String pWord);
    public boolean addUser(String userName,String passWord);
    public Users findUser(String userName);
    public boolean deleteUser(String userName);
}

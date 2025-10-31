package com.pcl.lms.DB;

import com.pcl.lms.model.User;
import com.pcl.lms.utill.security.PasswordManager;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<>();
    static{
        userTable.add(new User("Daham Sandaruwan","sdaham937@gmail.com",23,new PasswordManager().encode("123")));
    }
}

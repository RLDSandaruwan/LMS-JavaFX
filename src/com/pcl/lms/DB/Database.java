package com.pcl.lms.DB;

import com.pcl.lms.model.Student;
import com.pcl.lms.model.User;
import com.pcl.lms.utill.security.PasswordManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<>();
    public static ArrayList<Student>  studentTable = new ArrayList<>();


    static{
        userTable.add(new User("Daham Sandaruwan","sdaham937@gmail.com",23,new PasswordManager().encode("123")));
//        try {
//            studentTable.add(new Student("S-6","DS","123",new SimpleDateFormat("yyyy-MM-dd").parse("2025-11-01")));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        studentTable.add(new Student("S-6","DS","123", new Date()));
    }
}

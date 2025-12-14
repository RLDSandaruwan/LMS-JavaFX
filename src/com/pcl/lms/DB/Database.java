package com.pcl.lms.DB;

import com.pcl.lms.model.*;
import com.pcl.lms.utill.security.PasswordManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<>();
    public static ArrayList<Student>  studentTable = new ArrayList<>();
    public static ArrayList<Teacher>  teacherTable = new ArrayList<>();
    public static ArrayList<Program> programTable = new ArrayList<>();
    public static ArrayList<Intake> intakeTable = new ArrayList<>();
    public static ArrayList<Enroll> enrollTable = new ArrayList<>();

    static{
        userTable.add(new User("Daham Sandaruwan","sdaham937@gmail.com",23,new PasswordManager().encode("123")));
//        try {
//            studentTable.add(new Student("S-6","DS","123",new SimpleDateFormat("yyyy-MM-dd").parse("2025-11-01")));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        studentTable.add(new Student("S-6","DS","123", new Date()));
        teacherTable.add(new Teacher("T-1","Gihan","123/4 Kandy","071111678"));
        teacherTable.add(new Teacher("T-2","Kasun","123/4 Colombo","071111678"));
        programTable.add(new Program("P-1","Java",15000.0,"Gihan",new String[]{"OOP","JAVA FX"}));
    }
}

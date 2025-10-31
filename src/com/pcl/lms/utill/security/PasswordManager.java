package com.pcl.lms.utill.security;

import org.mindrot.BCrypt;

public class PasswordManager {
    public String encode(String rowPassword){
        return BCrypt.hashpw(rowPassword, BCrypt.gensalt(10));
    }

    public boolean decode(String rowPassword,String hashedPassword){
        return BCrypt.checkpw(rowPassword,hashedPassword);
    }
}

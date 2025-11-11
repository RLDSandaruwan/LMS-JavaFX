package com.pcl.lms.view.tm;

import javafx.scene.control.Button;

public class TeacherTm {
    private String id;
    private String name;
    private String Contact;
    private String Address;
    private Button btn;

    public TeacherTm(String id, String name, String contact, String address, Button btn) {
        this.id = id;
        this.name = name;
        Contact = contact;
        Address = address;
        this.btn = btn;
    }

    public TeacherTm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}

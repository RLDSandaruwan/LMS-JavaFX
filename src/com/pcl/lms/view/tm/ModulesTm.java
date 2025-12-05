package com.pcl.lms.view.tm;

import javafx.scene.control.Button;

public class ModulesTm {
    private int id;
    private String name;
    private Button btn;

    @Override
    public String toString() {
        return "ModulesTm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", btn=" + btn +
                '}';
    }

    public ModulesTm() {
    }

    public ModulesTm(int id, String name, Button btn) {
        this.id = id;
        this.name = name;
        this.btn = btn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}

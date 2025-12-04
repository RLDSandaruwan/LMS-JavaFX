package com.pcl.lms.view.tm;

import javafx.scene.control.Button;

public class ProgramTm {
    private String programid;
    private String programName;
    private String teacher;
    private Button btnModules;
    private double cost;
    private Button btnDelete;

    @Override
    public String toString() {
        return "ProgramTm{" +
                "programid='" + programid + '\'' +
                ", programName='" + programName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", btnModules=" + btnModules +
                ", cost=" + cost +
                ", btnDelete=" + btnDelete +
                '}';
    }

    public ProgramTm() {
    }

    public ProgramTm(String programid, String programName, String teacher, Button btnModules, double cost, Button btnDelete) {
        this.programid = programid;
        this.programName = programName;
        this.teacher = teacher;
        this.btnModules = btnModules;
        this.cost = cost;
        this.btnDelete = btnDelete;
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Button getBtnModules() {
        return btnModules;
    }

    public void setBtnModules(Button btnModules) {
        this.btnModules = btnModules;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}

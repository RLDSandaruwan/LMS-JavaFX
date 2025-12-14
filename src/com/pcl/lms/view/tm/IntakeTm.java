package com.pcl.lms.view.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class IntakeTm {
    private String intakeid;
    private String intakeName;
    private Date date;
    private String programName;
    private Button btn;

    @Override
    public String toString() {
        return "IntakeTm{" +
                "intakeid='" + intakeid + '\'' +
                ", intakeName='" + intakeName + '\'' +
                ", date=" + date +
                ", programName='" + programName + '\'' +
                ", btn=" + btn +
                '}';
    }

    public IntakeTm() {
    }

    public IntakeTm(String intakeid, String intakeName, Date date, String programName, Button btn) {
        this.intakeid = intakeid;
        this.intakeName = intakeName;
        this.date = date;
        this.programName = programName;
        this.btn = btn;
    }

    public String getIntakeid() {
        return intakeid;
    }

    public void setIntakeid(String intakeid) {
        this.intakeid = intakeid;
    }

    public String getIntakeName() {
        return intakeName;
    }

    public void setIntakeName(String intakeName) {
        this.intakeName = intakeName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}

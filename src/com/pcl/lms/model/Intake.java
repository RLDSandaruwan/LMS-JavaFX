package com.pcl.lms.model;

import java.util.Date;

public class Intake {
    private String id;
    private String name;
    private Date date;
    private String programName;

    @Override
    public String toString() {
        return "Intake{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", programName='" + programName + '\'' +
                '}';
    }

    public Intake() {
    }

    public Intake(String id, String name, Date date, String programName) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.programName = programName;
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
}

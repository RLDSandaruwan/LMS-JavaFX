package com.pcl.lms.model;

import java.util.Arrays;

public class Program {
    private String programid;
    private String programname;
    private Double cost;
    private String taecher;
    private String[] module;

    @Override
    public String toString() {
        return "Program{" +
                "programid='" + programid + '\'' +
                ", programname='" + programname + '\'' +
                ", cost=" + cost +
                ", taecher='" + taecher + '\'' +
                ", module=" + Arrays.toString(module) +
                '}';
    }

    public Program() {
    }

    public Program(String programid, String programname, Double cost, String taecher, String[] module) {
        this.programid = programid;
        this.programname = programname;
        this.cost = cost;
        this.taecher = taecher;
        this.module = module;
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getTaecher() {
        return taecher;
    }

    public void setTaecher(String taecher) {
        this.taecher = taecher;
    }

    public String[] getModule() {
        return module;
    }

    public void setModule(String[] module) {
        this.module = module;
    }
}
